----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:40:15 01/09/2013 
-- Design Name: 
-- Module Name:    Instruction - Behavioral 
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

entity Instruction is
port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					funct: in std_logic_vector(5 downto 0);
					op: in std_logic_vector(5 downto 0);
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);
end Instruction;

architecture Behavioral of Instruction is

signal Px: std_logic_vector(2 downto 0);
signal Py: std_logic_vector(3 downto 0);

begin

Px<=hcnt(2 downto 0);
Py<=vcnt(3 downto 0);

process(hcnt, vcnt)
begin
pintar<='0';
	--primero
	if hcnt >= pos_x and hcnt < pos_x + 8 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if op = "000000" then --tipo R
			
				if funct ="000000" then --nop
					if(N(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100000" then --add
					if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100010" then --sub
					if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100100" then --and
					if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100101" then --or
					if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				end if; --nop
				
				
			elsif op = "100011" then--lw
				if(L(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "101011" then--sw
				if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000100" then--beq
				if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000110" then--bgt
				if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000111" then--blt
				if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000101" then--bne
				if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010010" then--andi
				if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010011" then--ori
				if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010000" then--addi
				if(A(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010001" then--subi
				if(S(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "111111" then--J
				if(J(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;-- tipo R
			
		end if;
	end if;
	--segundo
	if hcnt >= pos_x+8 and hcnt < pos_x + 16 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if op = "000000" then --tipo R
			
				if funct ="000000" then --nop
					if(O(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100000" then --add
					if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100010" then --sub
					if(U(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100100" then --and
					if(N(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100101" then --or
					if(R(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				end if; --nop
				
				
			elsif op = "100011" then--lw
				if(W(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "101011" then--sw
				if(W(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000100" then--beq
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000110" then--bgt
				if(G(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000111" then--blt
				if(L(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000101" then--bne
				if(N(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010010" then--andi
				if(N(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010011" then--ori
				if(R(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010000" then--addi
				if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010001" then--subi
				if(U(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "111111" then--J
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;-- tipo R
			
		end if;
	end if;
	
	--tercero
	if hcnt >= pos_x+16 and hcnt < pos_x + 24 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if op = "000000" then --tipo R
			
				if funct ="000000" then --nop
					if(p(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100000" then --add
					if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100010" then --sub
					if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100100" then --and
					if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100101" then --or
					if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				end if; --nop
				
				
			elsif op = "100011" then--lw
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "101011" then--sw
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000100" then--beq
				if(Q(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000110" then--bgt
				if(t(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000111" then--blt
				if(T(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000101" then--bne
				if(E(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010010" then--andi
				if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010011" then--ori
				if(I(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010000" then--addi
				if(D(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010001" then--subi
				if(B(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "111111" then--J
				if(VACIO(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;-- tipo R
			
		end if;
	end if;
	
	
	if hcnt >= pos_x+24 and hcnt < pos_x + 32 then
		if vcnt >= pos_y and vcnt < pos_y + 16 then
		
			if op = "000000" then --tipo R
			
				if funct ="000000" then --nop
					if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100000" then --add
					if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100010" then --sub
					if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100100" then --and
					if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				elsif funct ="100101" then --or
					if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
					else pintar<='0';
					end if;
				end if; --nop
				
				
			elsif op = "100011" then--lw
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "101011" then--sw
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000100" then--beq
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000110" then--bgt
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000111" then--blt
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "000101" then--bne
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010010" then--andi
				if(I(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010011" then--ori
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010000" then--addi
				if(I(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "010001" then--subi
				if(I(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			elsif op = "111111" then--J
				if(vacio(conv_integer(Py))(conv_integer(Px))='1') then pintar<='1';
				else pintar<='0';
				end if;
			end if;-- tipo R
			
		end if;
	end if;
end process;
end Behavioral;

