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
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity MemoriaDeDatos is
	--generic( P: integer:=32; -- ancho de palabra 32 bits
			--	N: integer:=32; -- nº de palabras 32 de 32
				--M: integer:= 64; -- grupos de 4 palabras (2^32 / 4)=1073741824
				--tam_addr: integer:=5); -- ancho dirección 2^5=32
				
	port( Clock: in std_logic;
		ADDR, write_addr: in std_logic_vector(4 downto 0);
		DR : out std_logic_vector(31 downto 0);
		DW: in std_logic_vector(31 downto 0);
		R, W: in std_logic
	);
end MemoriaDeDatos;

architecture Behavioral of MemoriaDeDatos is
	type mem_type is array (0 to 31) of std_logic_vector(31 downto 0);
	signal tmp_mem: mem_type:=(
"00000000000000010000000000100000",--suma
"00000000010000110000000000100000",
"00000000100001010000000000100000",
"00000000110001110000000000100000",
"00000001000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100010",--resta
"00000000000000010000000000100010",
"00000000000000010000000000100010",
"00000000000000010000000000100101",--or
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100100",--and
"00010000000000000000000000000011",--beq
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000",
"00000000000000010000000000100000");

begin
-- Lectura
process(Clock)
begin
	if (Clock'event and Clock='1') then
		if(R = '1') then
			DR <= tmp_mem(conv_integer(ADDR));
		else
			DR <= (DR' range => 'Z');
		end if;
	end if;
end process;

-- Escritura
process(Clock)
begin
	if (Clock'event and Clock='1') then
		if(W = '1') then
			tmp_mem(conv_integer(write_addr))<= DW;	
			end if;
	end if;
end process;


end Behavioral;



