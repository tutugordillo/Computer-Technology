----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    20:33:34 11/21/2012 
-- Design Name: 
-- Module Name:    MemoriaDeInstrucciones - Behavioral 
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
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;
use std.textio.all;


-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity MemoriaDeInstrucciones is
	--generic( P: integer:=32; -- ancho de palabra 32 bits
			--	N: integer:=32; -- nº de palabras 32 de 32
				--M: integer:= 64; -- grupos de 4 palabras (2^32 / 4)=1073741824
				--tam_addr: integer:=5); -- ancho dirección 2^5=32
				
	port( Clock: in std_logic;
		ADDR: in std_logic_vector(4 downto 0); 
		--write_addr: in std_logic_vector(4 downto 0);
		DR : out std_logic_vector(31 downto 0)
		--DW: in std_logic_vector(31 downto 0);
		--R: in std_logic
		--W: in std_logic
	);
end MemoriaDeInstrucciones;

architecture Behavioral of MemoriaDeInstrucciones is
	type RamType is array (0 to 31) of bit_vector(31 downto 0);
		
	impure function InitRamFromFile (RamFileName : in string) return RamType is
		FILE RamFile : text is in RamFileName;
		variable RamFileLine : line;
		variable RAM : RamType;
		begin
			for I in RamType'range loop
				readline (RamFile, RamFileLine);
				read (RamFileLine, RAM(I));
			end loop;
			return RAM;
	end function;
	
	signal RAM : RamType := InitRamFromFile("partidaGuardada.txt");
--	signal RAM : RamType :=(
--"00000000000000000000000000100010", --sub 0 0 0   #contador
--"00000000001000010000100000100010", --sub 1 1 1   #g(n-1)
--"00000000010000100001000000100010", --sub 2 2 2   #g(n)
--"00000000011000110001100000100010", --sub 3 3 3   #g(n+1)
--"00000000100001000010000000100010", --sub 4 4 4   #maximo
--"00000000101001010010100000100010", --sub 5 5 5   #indice array
--"00000000110001100011000000100010", --sub 6 6 6   #es un 0
--"01000000010000100000000000000001", --addi 2 2 1  #g(n) = 1 y g(n-1) = 0
--"01000000100001000000000000000101", --addi 4 4 5  #inicializa maximo a 5
--"10101100001000010000000000000000", --sw 1 1 0    #almacenamos las primeras componentes g(0) y g(1)
--"10101100010000100000000000000000", --sw 2 2 0   -- store r2, r2[0] r1 guardamos en la dir r1+0
--"01000000101001010000000000000010", --addi 5 5 2   #se inicializa a 2
--"00010000000001000000000000000111", --beq 0 4 buclefin1 // fibo
--"00000000010000010001100000100000", --add 3 2 1   # g(n+1) <= g(n) + g(n-1)
--"00000000011000010000100000100010", --sub 1 3 1   # g(n-1) <= g(n) = g(n+1) - g(n-1)
--"00000000011001100001000000100010", --sub 2 3 6   # g(n) <= g(n+1) + 0
--"10101100101000110000000000000000", --sw 3 5 0    # guardamos g(n+1) en el indice del array
--"01000000101001010000000000000001", --addi 5 5 1  # actualizamos el indice del array
--"01000000000000000000000000000001", --addi 0 0 1  # actualizamos el contador
--"11111100000000000000000000001100", --j fibo
--"11111100000000000000000000010101",
--"11111100000000000000000000010100",
--
--
--"11111100000000000000000000000101",--jump
--"00010100001000000000000000000011", --bne
--"00011000001000000000000000000010",--bgt al 4
--"01001011111001100000000000001010",--andi R6, R31, 10
--"10001100000000100000000000000000",--lw R2, R0, 0
--"00011000001000000000000000001000",--bgt
--"00010000001000000000000000000011",--beq
--"00000000111001110011100000100010",--resta R7, R7, R7 NOP
--"00000000111001110011100000100010",--resta R7, R7, R7 NOP
--"00000000000000010000000000100000");

begin
--Salida registros


-- Lectura
process(Clock)
begin
	if (Clock'event and Clock='1') then
			DR <= to_stdlogicvector(RAM(conv_integer(ADDR)));
	end if;
end process;

end Behavioral;



