----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:52:22 01/11/2013 
-- Design Name: 
-- Module Name:    divisor2 - Behavioral 
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
use IEEE.std_logic_1164.all;
USE IEEE.std_logic_unsigned.ALL;

entity divisor2 is
    port (
        reset: in STD_LOGIC;
        clk: in STD_LOGIC; -- reloj de entrada de la entity superior
        reloj: out STD_LOGIC; -- reloj que se utiliza en los process del programa principal
		  velocidad0: in std_logic;
		  velocidad1: in std_logic
    );
end divisor2;

architecture divisor_arch of divisor2 is
 SIGNAL cuenta: std_logic_vector(24 downto 0);
 SIGNAL clk_aux: std_logic;
  
  begin

 
reloj<=clk_aux;
  contador:
  PROCESS(reset, clk)
  BEGIN
    IF (reset='1') THEN
      cuenta<= (OTHERS=>'0');
    ELSIF(clk'EVENT AND clk='1') THEN
		if velocidad0='0' and velocidad1='0' then
			IF (cuenta="1111111111111111111111111") THEN 
				clk_aux <= not clk_aux;
				cuenta<= (OTHERS=>'0');
			ELSE
			  cuenta <= cuenta+'1';
			END IF;
		elsif velocidad0='1' and velocidad1='0' then
			IF (cuenta="0111111111111111111111111") THEN 
				clk_aux <= not clk_aux;
				cuenta<= (OTHERS=>'0');
			ELSE
			  cuenta <= cuenta+'1';
			END IF;
		elsif velocidad0='0' and velocidad1='1' then
			IF (cuenta="0011111111111111111111111") THEN 
				clk_aux <= not clk_aux;
				cuenta<= (OTHERS=>'0');
			ELSE
			  cuenta <= cuenta+'1';
			END IF;
		else
			IF (cuenta="0001111111111111111111111") THEN 
				clk_aux <= not clk_aux;
				cuenta<= (OTHERS=>'0');
			ELSE
			  cuenta <= cuenta+'1';
			END IF;
		end if;	
      
    END IF;
  END PROCESS contador;

end divisor_arch;