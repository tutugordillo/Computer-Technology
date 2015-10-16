----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:27:35 12/21/2012 
-- Design Name: 
-- Module Name:    bitx - Behavioral 
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
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use work.tipos.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity bitx is
		port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					encendido: in std_logic;
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);
end bitx;

architecture Behavioral of bitx is

signal Px: std_logic_vector(2 downto 0);
signal Py: std_logic_vector(3 downto 0);

begin

Px<=hcnt(2 downto 0);
Py<=vcnt(3 downto 0);

process(hcnt, vcnt)
begin
pintar<='0';
	if hcnt >= pos_x and hcnt < pos_x + 8 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
			if encendido='1' then
				if(UNO(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			else
				if(CERO(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		end if;
	end if;
end process;
end Behavioral;

