----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:48:03 11/21/2012 
-- Design Name: 
-- Module Name:    ALU - Behavioral 
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
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;
use ieee.std_logic_arith.all;

ENTITY alu8bit IS
	port(a, b : in std_logic_vector(31 downto 0);	
		op : in std_logic_vector(1 downto 0);
		zero, bigthan : out std_logic;
	        result : out std_logic_vector(31 downto 0));
END alu8bit;


architecture behavioral of alu8bit is
signal temp : std_logic_vector(31 downto 0);
begin

	temp <=		a and b when op = "00" else
					a or b when op ="01" else
					a + b when op = "10" else
					a - b when op = "11" else
					(others => ('0'));
					
	result 	<= 	temp;
	
	process(op, temp)
		begin
		if(op = "11" and temp = 0) then 
						zero <= '1';
						bigthan <= '0';
		elsif(op = "11" and temp > 0) then
						zero <= '0';
						bigthan <= '1';
		else 	
						zero <= '0';
						bigthan <= '0';
		end if;
	end process;
end behavioral;
