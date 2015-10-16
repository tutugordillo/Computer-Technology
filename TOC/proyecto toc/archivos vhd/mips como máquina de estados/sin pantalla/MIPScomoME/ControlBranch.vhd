----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    13:04:29 12/04/2012 
-- Design Name: 
-- Module Name:    ControlBranch - Behavioral 
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

entity ControlBranch is 
				port (cero , mayorque, saltar,EscrPC,EscrPC_Cond1,EscrPC_Cond2: in std_logic;--1 es menos significativo
						salida: out std_logic);
end ControlBranch;

architecture Behavioral of ControlBranch is
signal aux: std_logic;
begin

salida<= aux or EscrPC;

process(cero , mayorque,EscrPC,EscrPC_Cond1,EscrPC_Cond2,saltar)
	begin
		if(saltar='1')then
			if(EscrPC_Cond2='0')then
				if(EscrPC_Cond1='0')then	--caso 00 beq
				
					if(cero='1')then				
						aux<='1';
					else
						aux<='0';
					end if;
				
				else 								--caso 01 bne
					if(cero='0')then
						aux<='1';
					else
						aux<='0';
					end if;
				end if;
			
			else									--cond2=1
				if(EscrPC_Cond1='0')then	--caso 10 bgt
				
					if(cero='0' and mayorque='1')then				
						aux<='1';
					else
						aux<='0';
					end if;
				
				else 								--caso 11 blt
					if(cero='0' and mayorque='0')then
						aux<='1';
					else
						aux<='0';
					end if;
				end if;
			end if;
		
		else
			aux<='0';
		end if;
	end process;

end Behavioral;

