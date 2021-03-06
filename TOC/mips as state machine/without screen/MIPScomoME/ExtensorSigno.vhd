----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:10:08 11/21/2012 
-- Design Name: 
-- Module Name:    ExtensorSigno - Behavioral 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ExtensorSigno is port (A: in std_logic_vector (15 downto 0);
										S: out std_logic_vector (31 downto 0));
end ExtensorSigno;

architecture Behavioral of ExtensorSigno is
	
	

begin

	S(15 downto 0)<=A;
	S(31 downto 16)<= (others => A(15));

end Behavioral;

