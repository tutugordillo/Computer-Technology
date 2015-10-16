----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    21:44:15 11/21/2012 
-- Design Name: 
-- Module Name:    BancoDeRegistros - Behavioral 
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

entity BancoDeRegistros is
	--generic( P: integer:=32; -- ancho de palabra
			--N: integer:=32; -- nº de palabras
			--tam_addr: integer:=5); -- ancho dirección
			
	port( Clock: in std_logic;
	Reg_Write: in std_logic;
	RA: in std_logic_vector(4 downto 0);
	RB: in std_logic_vector(4 downto 0);
	RW: in std_logic_vector(4 downto 0);
	busW: in std_logic_vector(31 downto 0);
	busA: out std_logic_vector(31 downto 0);
	busB: out std_logic_vector(31 downto 0)
);
end BancoDeRegistros;

architecture Behavioral of BancoDeRegistros is
	type br_type is array (0 to 31) of std_logic_vector(31 downto 0);
	signal tmp: br_type:=(
	"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000111",
"00000000000000000000000000000001",
"00000000000000000000000000000110",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000000001",
"00000000000000000000000000000000",
"00000000000000000000000000001111");

begin
-- Lectura asíncrona
	busA <= tmp(conv_integer(RA));
	busB <= tmp(conv_integer(RB));

-- Escritura
process(Clock, Reg_Write)
begin
	if (Clock'event and Clock='1') then
		if Reg_Write='1' then
			tmp(conv_integer(RW)) <= busW;
		end if;
	end if;
end process;


end Behavioral;

