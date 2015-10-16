----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    12:45:27 11/28/2012 
-- Design Name: 
-- Module Name:    mips - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use work.tipos.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity mips is
port (clk, reset : in std_logic;
	inst: out std_logic_vector(31 downto 0)
);			
end mips;



architecture Behavioral of mips is
signal Estado, sig_estado: Estados;

--Señales memorias
	--banco de registros
signal RW:  std_logic_vector(4 downto 0);
signal busW, busB, busA:  std_logic_vector(31 downto 0);

	--memoria de instrucciones
--signal ADDR:  std_logic_vector(4 downto 0);
signal DR:  std_logic_vector(31 downto 0);

	--memoria de datos
signal DRDat :  std_logic_vector(31 downto 0);
signal RDat, WDat:  std_logic;

--Señales Controles
	--control ALU
signal ALUCtrl: std_logic_vector(1 downto 0);
	--control saltos
signal EscrPC: std_logic;
signal salida: std_logic;
	
	--control general
signal PcWriteCond0, PcWriteCond1, PcWriteCond2: std_logic;
signal Reg_Write: std_logic;
signal ALUOp0, ALUOp1, ALUOp2: std_logic;
signal ALUOp: std_logic_vector(2 downto 0);
signal RegDst, MemtoReg: std_logic;
signal ALUA,ALUB0,ALUB1, PcSrc0, PcSrc1:std_logic;
signal sig_estadoControl: Estados;

--Señales ALU
signal entradaA, entradaB: std_logic_vector(31 downto 0);
signal cero: std_logic;
signal mayorque: std_logic;
signal resultado: std_logic_vector(31 downto 0);

--Señales Extensor
signal extendido: std_logic_vector(31 downto 0);

--señales PC
signal loadPC: std_logic;
signal tempPC, PC: std_logic_vector(31 downto 0);

--registros temporales --
signal registroA, registroB, salidaALU, Instruction: std_logic_vector(31 downto 0);
signal entradaControl: std_logic_vector(5 downto 0);
signal tempA, tempB, tempSalidaALU, tempInstruction: std_logic_vector(31 downto 0);
signal loadA,loadB,loadSalidaALU, loadInstruction, loadEntradaControl: std_logic;

signal entradaExtensor: std_logic_vector(15 downto 0);

component MemoriaDeInstrucciones is			
	port( Clock: in std_logic;
		ADDR: in std_logic_vector(4 downto 0); 
		DR : out std_logic_vector(31 downto 0)
	);
end component;

component MemoriaDeDatos is
		port( Clock: in std_logic;
			ADDR, write_addr: in std_logic_vector(4 downto 0);
			DR : out std_logic_vector(31 downto 0);
			DW: in std_logic_vector(31 downto 0);
			R, W: in std_logic
		);
end component;

component BancoDeRegistros is
	port(
			Clock: in std_logic;
			Reg_Write: in std_logic;
			RA: in std_logic_vector(4 downto 0);
			RB: in std_logic_vector(4 downto 0);
			RW: in std_logic_vector(4 downto 0);
			busW: in std_logic_vector(31 downto 0);
			busA: out std_logic_vector(31 downto 0);
			busB: out std_logic_vector(31 downto 0)
		);
		
end component;

component ExtensorSigno is 
	port(
			A: in std_logic_vector (15 downto 0);
			S: out std_logic_vector (31 downto 0)
		);
end component;

component alu8bit is
	port(
			a, b : in std_logic_vector(31 downto 0);	
		op : in std_logic_vector(1 downto 0);
		zero, bigthan : out std_logic;
	        result : out std_logic_vector(31 downto 0)
		);
end component;


component control is
	port(instruction: in std_logic_vector(5 downto 0);
		Estado: in ESTADOS;
		sig_estado: out ESTADOS;
		RegDst, RegWrite, MemWrite, MemRead, MemtoReg, PcWriteCond0, PcWriteCond1, PcWriteCond2,
		ALUOp0, ALUOp1, ALUOp2, ALUA, ALUB0, ALUB1, PcSrc0, PcSrc1, PcWrite:out std_logic
		);
end component;

component AluControl is 
	port (funct: in std_logic_vector(5  downto 0);
			AluOp:in std_logic_vector(2 downto 0);
			--AluOp(1) es 1
			AluCtr: out std_logic_vector(1 downto 0)	
		);
end component;

component ControlBranch is 
		port (cero , mayorque, saltar,EscrPC,EscrPC_Cond1,EscrPC_Cond2: in std_logic;--1 es menos significativo
			salida: out std_logic
		);
end component;	

						
begin

ALUOp<=ALUOp2&ALUOp1&ALUOp0;

MemIns: MemoriaDeInstrucciones 
							port map(clk, PC(4 downto 0), DR);--siempre se lee nunca se escribe	
MemDatos: MemoriaDeDatos
							port map(clk, salidaALU(4 downto 0), salidaALU(4 downto 0), DRDat, registroB, RDat, WDat);
Banco_Registros: BancoDeRegistros 
							port map(clk, Reg_Write, DR(25 downto 21), DR(20 downto 16), RW, busW, busA, busB);
ControlSaltos: ControlBranch 
							port map(cero , mayorque, PcWriteCond2,EscrPC, PcWriteCond0,PcWriteCond1, salida);
ControlAlu:		AluControl 
							port map(Instruction(5 downto 0), ALUOp, ALUCtrl);
ControlG:		control 
							port map(entradaControl, Estado, sig_estadoControl,
			RegDst, Reg_Write, WDat, RDat, MemtoReg, PcWriteCond0,PcWriteCond1,PcWriteCond2, ALUOp0, ALUOp1,
			ALUOp2, ALUA, ALUB0, ALUB1, PcSrc0, PcSrc1, EscrPc);
ALU:				alu8bit 
							port map(entradaA,entradaB, ALUCtrl, cero, mayorque, resultado);
Extensor:		ExtensorSigno 
							port map(entradaExtensor,extendido);


 RW<=Instruction(20 downto 16) when RegDst='0' else
		Instruction(15 downto 11);
		
 busW<= salidaALU when MemtoReg='0' else
		 DRDat;
		  
 entradaA<=PC when ALUA='0' else
		registroA;
 
 entradaB<= registroB when (ALUB1='0' and ALUB0='0') else
		X"00000001" when (ALUB1='0' and ALUB0='1') else
		extendido;
				
 tempPC<= resultado when (PcSrc1='0' and PcSrc0='0') else
		salidaALU when (PcSrc1='0' and PcSrc0='1') else
		PC(31 downto 26)&Instruction(25 downto 0);



loadPC<=salida;
tempSalidaALU<=resultado;
tempInstruction<=DR;
tempA<=busA;
tempB<=busB;
sig_estado <= sig_estadoControl;

inst<=Instruction;
Síncrono: process(clk, reset)
begin
		if reset='1' then
			Estado<=F;
			PC<=X"00000000";
		elsif clk'event and clk ='1' then
			Estado<=sig_estado;
			if loadPC='1' then
				PC<=tempPC;
			end if;
			if loadA='1' then
				registroA<=tempA;
			end if;
			if loadB='1' then
				registroB<=tempB;
			end if;
			if loadSalidaALU='1' then
				salidaALU<=tempSalidaALU;
			end if;
			if loadInstruction='1' then
				Instruction<=tempInstruction;
			end if;
			if loadEntradaControl='0' then
				entradaControl <= DR(31 downto 26);
			elsif loadEntradaControl='1' then
				entradaControl <= Instruction(31 downto 26);
			end if;
			
		end if;
end process Síncrono;

Combinacional:process(clk,Estado--,registroA,registroB,salidaALU, PC
)
begin
case Estado is
		when F =>
				loadInstruction<='1';
				loadA<='0';
				loadB<='0';
				loadSalidaALU<='0';
				loadEntradaControl <= '0';
				entradaExtensor <= DR(15 downto 0);--indefinido
				
		
		when ID => --en ID la instrucción siempre es la correcta
				loadInstruction<='1';
				loadA<='1';
				loadB<='1';
				loadSalidaALU<='1';
				loadEntradaControl<='0';
				entradaExtensor <= DR(15 downto 0);
				
								
		when EX =>
				loadInstruction<='0';
				loadA<='0';
				loadB<='0';
				loadSalidaALU<='1';
				loadEntradaControl<='1';
				entradaExtensor <= Instruction(15 downto 0);
				
									
		when MEM =>
				loadInstruction<='0';
				loadA<='0';
				loadB<='0';
				loadSalidaALU<='0';
				loadEntradaControl<='1';
				entradaExtensor <= Instruction(15 downto 0);
					
		when WB =>
				loadInstruction<='0';
				loadA<='0';
				loadB<='0';
				loadSalidaALU<='0';
				loadEntradaControl<='1';
				entradaExtensor <= Instruction(15 downto 0);
				
				
end case;
end process Combinacional;
end Behavioral;