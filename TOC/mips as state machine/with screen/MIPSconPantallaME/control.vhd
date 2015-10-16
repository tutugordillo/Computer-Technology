----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    21:03:44 11/22/2012 
-- Design Name: 
-- Module Name:    control - Behavioral 
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
use IEEE.std_logic_arith.all;
use ieee.std_logic_signed.all;
use work.tipos.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity control is

port(instruction: in std_logic_vector(5 downto 0);
		estado: in ESTADOS;
		sig_estado: out ESTADOS;
		RegDst, RegWrite, MemWrite, MemRead, MemtoReg, PcWriteCond0, PcWriteCond1, PcWriteCond2,
		ALUOp0, ALUOp1, ALUOp2, ALUA, ALUB0, ALUB1, PcSrc0, PcSrc1, PcWrite:out std_logic);
end control;

architecture Behavioral of control is

begin
process(instruction, estado)
begin
	if(estado = WB) then sig_estado <= F;
	end if;
	if(estado = F) then 
		RegDst <= '0';
		RegWrite <= '0';
		MemWrite <= '0';
		MemRead <= '0';
		MemtoReg <= '0';		
		PcWriteCond2 <= '0';
		PcWriteCond1 <= '0';
		PCWriteCond0 <= '0';
		PcWrite <= '1';
		ALUOp2 <= '0';
		ALUOp1  <= '0';
		ALUOp0 <= '0';
		ALUA <= '0';
		ALUB1 <= '0';
		ALUB0 <= '1';
		PcSrc1 <= '0';
		PcSrc0 <= '0';
		sig_estado <= ID;
		
	elsif(estado = ID) then
		RegDst <= '0'; -- valor indeterminado, solo lee
		RegWrite <= '0'; -- lee de registro
		MemWrite <= '0';
		MemRead <= '0';
		MemtoReg <= '0';		
		PcWriteCond2 <= '0';
		PcWriteCond1 <= '0';
		PCWriteCond0 <= '0';
		PcWrite <= '0';
		ALUOp2 <= '0';
		ALUOp1  <= '0';
		ALUOp0 <= '0';
		ALUA <= '0';
		ALUB1 <= '1';
		ALUB0 <= '1';
		PcSrc1 <= '0';
		PcSrc0 <= '0';
		sig_estado <= EX;
		
	else	
		
		-- tipo R
		if instruction = "000000" then
			RegDst <= '1';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';		
			PcWriteCond2 <= '0';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0';
			PcWrite <= '0';
			ALUOp2 <= '0';
			ALUOp1  <= '1';
			ALUOp0 <= '0';
			ALUA <= '1';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '0'; -- indeterminado
			PcSrc0 <= '0'; -- indeterminado
			if(estado = EX) then
				sig_estado <= WB;
			end if;
			if(estado=WB) then
				RegWrite <= '1';
			else
				RegWrite <= '0';
			end if;
						
		-- lw
		elsif instruction = "100011" then 
			RegDst <= '0';
			MemWrite <= '0';
			MemRead <= '1';
			PcWriteCond2 <= '0';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0';
			PcWrite <= '0';
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '0';
			ALUA <= '1';
			ALUB1 <= '1';
			ALUB0 <= '0';
			PcSrc1 <= '0'; -- indeterminado
			PcSrc0 <= '0'; -- indeterminado
			if (estado = EX) then 
				sig_estado <= MEM;
				RegWrite <= '0';
				MemtoReg <= '0';
			elsif (estado = MEM) then 
				sig_estado <= WB;
				RegWrite <= '0';
				MemtoReg <= '0';
			else 
				MemtoReg <= '1';
				RegWrite <= '1';
			end if;
			
		-- sw
		elsif instruction = "101011" then 
			RegDst <= '0'; -- indeterminado
			RegWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';  -- indeterminado
			PcWriteCond2 <= '0';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0';
			PcWrite <= '0';
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '0';
			ALUA <= '1';
			ALUB1 <= '1';
			ALUB0 <= '0';
			PcSrc1 <= '0'; -- indeterminado
			PcSrc0 <= '0'; -- indeterminado
			if (estado = EX) then 
				sig_estado <= MEM;
				MemWrite <= '0';
			elsif (estado = MEM) then 
				sig_estado <= F;
				MemWrite <= '1';
			else 
				MemWrite <= '0';
			end if;
			
			-- beq	
		elsif instruction = "000100" then 
			RegDst <= '0'; -- indeterminado
			RegWrite <= '0';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';  -- indeterminado
			PcWriteCond2 <= '1';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0'; -- bit de salto
			PcWrite <= '0';  -- indeterminado
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '1';
			ALUA <= '1';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '0';
			PcSrc0 <= '1';
			sig_estado <= F;
			
			-- bne
		elsif instruction = "000101" then
			RegDst <= '0'; -- indeterminado
			RegWrite <= '0';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';  -- indeterminado
			PcWriteCond2 <= '1';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '1'; -- bit de salto
			PcWrite <= '0';  -- indeterminado
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '1';
			ALUA <= '1';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '0';
			PcSrc0 <= '1';
			sig_estado <= F;
			
			-- bgt
		elsif instruction = "000110" then
			RegDst <= '0'; -- indeterminado
			RegWrite <= '0';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';  -- indeterminado
			PcWriteCond2 <= '1';
			PcWriteCond1 <= '1';
			PCWriteCond0 <= '0'; -- bit de salto
			PcWrite <= '0';  -- indeterminado
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '1';
			ALUA <= '1';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '0';
			PcSrc0 <= '1';
			sig_estado <= F;
			
			-- blt
		elsif instruction = "000111" then
			RegDst <= '0'; -- indeterminado
			RegWrite <= '0';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';  -- indeterminado
			PcWriteCond2 <= '1';
			PcWriteCond1 <= '1';
			PCWriteCond0 <= '1'; -- bit de salto
			PcWrite <= '0';  -- indeterminado
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '1';
			ALUA <= '1';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '0';
			PcSrc0 <= '1';
			sig_estado <= F;
			
		-- jump
		elsif instruction = "111111" then--inventado
			RegDst <= '0'; -- indeterminado
			RegWrite <= '0';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';  -- indeterminado
			PcWriteCond2 <= '0';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0'; -- bit de salto
			PcWrite <= '1';  
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '1';
			ALUA <= '1';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '1';
			PcSrc0 <= '1';
			sig_estado <= F;
		
		-- addi, subi, andi, ori 
		elsif (instruction="010000" or instruction="010001" or instruction="010010" or instruction="010011") then 
			RegDst <= '0';
			RegWrite <= '1';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';		
			PcWriteCond2 <= '0';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0';
			PcWrite <= '0';			
			ALUA <= '1';
			ALUB1 <= '1';
			ALUB0 <= '0';
			PcSrc1 <= '0'; -- indeterminado
			PcSrc0 <= '0'; -- indeterminado
			if(estado=WB) then
				RegWrite <= '1';
			else
			 RegWrite <= '0';
			end if;
			if instruction="010000" then -- addi
				ALUOp2 <= '0';
				ALUOp1 <= '0';
				ALUOp0  <= '0';
			elsif instruction="010001" then --subi
				ALUOp2 <= '0';
				ALUOp1 <= '0';
				ALUOp0  <= '1';
			elsif instruction="010010" then --andi
				ALUOp2 <= '1';
				ALUOp1 <= '0';
				ALUOp0  <= '0';
			else --ori
				ALUOp2 <= '1';
				ALUOp1 <= '0';
				ALUOp0  <= '1';		
			end if;
			if(estado = EX) then
				sig_estado <= WB;
			end if;	
		else
			RegDst <= '0';
			RegWrite <= '0';
			MemWrite <= '0';
			MemRead <= '0';
			MemtoReg <= '0';
			PcWriteCond2 <= '0';
			PcWriteCond1 <= '0';
			PCWriteCond0 <= '0';
			PcWrite <= '0';
			ALUOp2 <= '0';
			ALUOp1 <= '0';
			ALUOp0  <= '0';
			ALUA <= '0';
			ALUB1 <= '0';
			ALUB0 <= '0';
			PcSrc1 <= '0';
			PcSrc0 <= '0';
			sig_estado <= F;
		end if;
	end if;
end process;
end Behavioral;

