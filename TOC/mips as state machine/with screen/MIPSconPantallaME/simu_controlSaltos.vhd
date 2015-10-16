--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   19:35:42 12/14/2012
-- Design Name:   
-- Module Name:   C:/hlocal/hoy/simu_controlSaltos.vhd
-- Project Name:  hoy
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: ControlBranch
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY simu_controlSaltos IS
END simu_controlSaltos;
 
ARCHITECTURE behavior OF simu_controlSaltos IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT ControlBranch
    PORT(
         cero : IN  std_logic;
         mayorque : IN  std_logic;
         saltar : IN  std_logic;
         EscrPC : IN  std_logic;
         EscrPC_Cond1 : IN  std_logic;
         EscrPC_Cond2 : IN  std_logic;
         salida : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal cero : std_logic := '0';
   signal mayorque : std_logic := '0';
   signal saltar : std_logic := '0';
   signal EscrPC : std_logic := '0';
   signal EscrPC_Cond1 : std_logic := '0';
   signal EscrPC_Cond2 : std_logic := '0';

 	--Outputs
   signal salida : std_logic;
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: ControlBranch PORT MAP (
          cero => cero,
          mayorque => mayorque,
          saltar => saltar,
          EscrPC => EscrPC,
          EscrPC_Cond1 => EscrPC_Cond1,
          EscrPC_Cond2 => EscrPC_Cond2,
          salida => salida
        );

   -- Clock process definitions
 
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      wait for 100 ns;	
			EscrPc<='1';
		wait for 100 ns;
			EscrPc<='0';
			cero<='1';
			mayorque<='0';
			saltar<='1';
			EscrPC_Cond2 <= '0';
			EscrPC_Cond1 <= '1';


      -- insert stimulus here 

      wait;
   end process;

END;
