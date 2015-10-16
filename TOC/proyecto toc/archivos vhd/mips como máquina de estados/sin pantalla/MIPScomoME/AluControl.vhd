----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    13:13:04 11/28/2012 
-- Design Name: 
-- Module Name:    AluControl - Behavioral 
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

entity AluControl is port (funct: in std_logic_vector(5  downto 0);
									AluOp:in std_logic_vector(2 downto 0);
									--AluOp(1) es 1
									AluCtr: out std_logic_vector(1 downto 0));
end AluControl;

architecture Behavioral of AluControl is

begin


process(AluOp, funct)
	begin-- tipo R
	AluCtr<="00";
	if AluOp = "010" then
		if funct= "100000" then --suma
			AluCtr<="10";
		elsif funct= "100010" then --resta
			AluCtr<="11";
		
		elsif funct= "100100" then --and
			AluCtr<="00";
			
		elsif	funct= "100101" then --or
			AluCtr<="01";
		
		end if;
		
		
	-- beq,bne,bgt,blt,subi
	elsif AluOp = "001" then --resta
		AluCtr<="11";
		
	-- lw, sw, addi
	elsif AluOp = "000" then --suma
		AluCtr<="10";
	
   --	andi
	elsif AluOp = "100" then
		AluCtr<="00";
	
	-- ori
	elsif AluOp = "101" then
		AluCtr<="01";
		
	else
		AluCtr<="10";
		
		
	end if;
end process;

end Behavioral;

