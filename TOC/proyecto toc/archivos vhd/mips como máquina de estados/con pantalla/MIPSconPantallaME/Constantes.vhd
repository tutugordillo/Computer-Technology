----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:05:59 01/09/2013 
-- Design Name: 
-- Module Name:    Constantes - Behavioral 
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

entity Constantes is
port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					tipo: in std_logic_vector(2 downto 0);
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);

end Constantes;

architecture Behavioral of Constantes is


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
		
			if tipo="000" then --Instruction:
				if(II(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="010" then --STATE:
				if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="011" then --Registros:
				if(R(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(M(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x +8 and hcnt < pos_x + 16 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(N(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				if(L(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="010" then --JUMPDIR:
				if(T(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="011" then --Registros:
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x + 16 and hcnt < pos_x + 24 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				if(U(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="010" then --JUMPDIR:
				if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="011" then --Registros:
				if(G(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(M(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x + 24 and hcnt < pos_x + 32 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(T(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				if(DOSPUNTOS(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="010" then --JUMPDIR:
				if(T(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="011" then --Registros:
				if(II(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x + 32 and hcnt < pos_x + 40 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(R(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
				
			elsif tipo="001" then --ALU:
				pintar<='0';
				
			elsif tipo="010" then --JUMPDIR:
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="011" then --Registros:
				if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(R(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x +40 and hcnt < pos_x + 48 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(U(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="01" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				if(DOSPUNTOS(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="011" then --Registros:
				if(T(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(II(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x +48 and hcnt < pos_x + 56 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(C(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				pintar<='0';
			elsif tipo="011" then --Registros:
				if(R(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x + 56 and hcnt < pos_x + 64 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(T(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				pintar<='0';
			elsif tipo="011" then --Registros:
				if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				if(DOSPUNTOS(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x +64 and hcnt < pos_x + 72 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(II(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				pintar<='0';
			elsif tipo="011" then --Registros:
				if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				pintar<='0';
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x +72 and hcnt < pos_x + 80 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				pintar<='0';
			elsif tipo="011" then --Registros:
				if(DOSPUNTOS(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="100" then --Memoria:
				pintar<='0';
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x+80 and hcnt < pos_x + 88 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(N(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				pintar<='0';
			elsif tipo="011" then --Registros:
				pintar<='0';
			elsif tipo="100" then --Memoria:
				pintar<='0';
			end if;
		
		end if;
	end if;
	
	if hcnt >= pos_x+88 and hcnt < pos_x + 96 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if tipo="000" then --Instruction:
				if(DOSPUNTOS(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif tipo="001" then --ALU:
				pintar<='0';
			elsif tipo="010" then --JUMPDIR:
				pintar<='0';
			elsif tipo="011" then --Registros:
				pintar<='0';
			elsif tipo="100" then --Memoria:
				pintar<='0';
			end if;
		
		end if;
	end if;

end process;


end Behavioral;


