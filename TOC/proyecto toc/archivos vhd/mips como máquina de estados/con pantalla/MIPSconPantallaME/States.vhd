----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:50:49 01/11/2013 
-- Design Name: 
-- Module Name:    Estados - Behavioral 
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

entity States is
port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					estado:in Estados;
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);

end States;

architecture Behavioral of States is


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
		
			if estado=F then --fetch
				if(II(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=ID then --deco
				if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=EX then --exe
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=MEM then --mem
				if(M(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=WB then --wb
				if(W(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x +8 and hcnt < pos_x + 16 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if estado=F then --fetch
				if(FF(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=ID then --deco
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=EX then --exe
				if(X(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=MEM then --mem
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=WB then --wb
				if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x + 16 and hcnt < pos_x + 24 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if estado=F then --fetch
				pintar<='0';
			elsif estado=ID then --deco
				if(C(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=EX then --exe
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=MEM then --mem
				if(M(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=WB then --wb
				pintar<='0';
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x + 24 and hcnt < pos_x + 32 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if estado=F then --fetch
				pintar<='0';
			elsif estado=ID then --deco
				if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif estado=EX then --exe
				pintar<='0';
			elsif estado=MEM then --mem
				pintar<='0';
			elsif estado=WB then --wb
				pintar<='0';
			end if;
		
		end if;
	end if;
		
end process;


end Behavioral;

