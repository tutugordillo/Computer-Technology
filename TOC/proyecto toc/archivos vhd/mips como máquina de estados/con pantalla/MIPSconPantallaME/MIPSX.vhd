----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:22:14 12/14/2012 
-- Design Name: 
-- Module Name:    MIPSX - Behavioral 
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

entity MIPSX is
port (clk, reset : in std_logic;
sal: out std_logic_vector(31 downto 0)
);	
end MIPSX;

architecture Behavioral of MIPSX is
signal Estado, Sig_Estado: Estados;


--Señales PC
signal tempPC: std_logic_vector(31 downto 0);
signal PC: std_logic_vector(31 downto 0);
signal loadPC: std_logic;


begin
sal<=PC;

Síncrono: process(clk, reset)
begin
		if reset='1' then
			Estado<=F;
			
		elsif clk'event and clk ='1' then
			Estado<=Sig_Estado;
			
			if loadPC='1' then
				PC<=tempPC;
			end if;
			
		end if;
end process Síncrono;


Combinacional:process(Estado)
begin
case Estado is
		when F =>
				loadPC<='1';
				tempPC<=X"00000000";
				Sig_Estado <= ID;
		
		when ID =>
				loadPC<='0';
				tempPC<=X"00000001";
				Sig_Estado <= EX;
				
		when EX =>
				loadPC<='0';
				tempPC<=X"0000000B";
				Sig_Estado <= MEM;
						
		when MEM =>
				loadPC<='1';
				tempPC<=X"0000000A";
				Sig_Estado <= WB;
		
		when WB =>
				loadPC<='1';
				tempPC<=X"FFFFFFFF";
				Sig_Estado <= F;
		
end case;
end process Combinacional;

end Behavioral;

