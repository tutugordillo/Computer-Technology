--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   19:20:52 12/14/2012
-- Design Name:   
-- Module Name:   C:/hlocal/hoy/simu_ControlG.vhd
-- Project Name:  hoy
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: control
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
use work.tipos.all;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY simu_ControlG IS
END simu_ControlG;
 
ARCHITECTURE behavior OF simu_ControlG IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT control
    PORT(
         instruction : IN  std_logic_vector(5 downto 0);
         estado : IN  ESTADOS;
         RegDst : OUT  std_logic;
         RegWrite : OUT  std_logic;
         MemWrite : OUT  std_logic;
         MemRead : OUT  std_logic;
         MemtoReg : OUT  std_logic;
         PcWriteCond0 : OUT  std_logic;
         PcWriteCond1 : OUT  std_logic;
         PcWriteCond2 : OUT  std_logic;
         ALUOp0 : OUT  std_logic;
         ALUOp1 : OUT  std_logic;
         ALUA : OUT  std_logic;
         ALUB0 : OUT  std_logic;
         ALUB1 : OUT  std_logic;
         PcSrc0 : OUT  std_logic;
         PcSrc1 : OUT  std_logic;
         PcWrite : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal instruction : std_logic_vector(5 downto 0) := (others => '0');
   signal estado :  ESTADOS;

 	--Outputs
   signal RegDst : std_logic;
   signal RegWrite : std_logic;
   signal MemWrite : std_logic;
   signal MemRead : std_logic;
   signal MemtoReg : std_logic;
   signal PcWriteCond0 : std_logic;
   signal PcWriteCond1 : std_logic;
   signal PcWriteCond2 : std_logic;
   signal ALUOp0 : std_logic;
   signal ALUOp1 : std_logic;
   signal ALUA : std_logic;
   signal ALUB0 : std_logic;
   signal ALUB1 : std_logic;
   signal PcSrc0 : std_logic;
   signal PcSrc1 : std_logic;
   signal PcWrite : std_logic;
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: control PORT MAP (
          instruction => instruction,
          estado => estado,
          RegDst => RegDst,
          RegWrite => RegWrite,
          MemWrite => MemWrite,
          MemRead => MemRead,
          MemtoReg => MemtoReg,
          PcWriteCond0 => PcWriteCond0,
          PcWriteCond1 => PcWriteCond1,
          PcWriteCond2 => PcWriteCond2,
          ALUOp0 => ALUOp0,
          ALUOp1 => ALUOp1,
          ALUA => ALUA,
          ALUB0 => ALUB0,
          ALUB1 => ALUB1,
          PcSrc0 => PcSrc0,
          PcSrc1 => PcSrc1,
          PcWrite => PcWrite
        );

   -- Clock process definitions

 

   -- Stimulus process
   stim_proc: process
   begin	
		
		-- hold reset state for 100 ns.
			wait for 100 ns;	
		instruction<="000000";
      estado<=F;
		wait for 100 ns;	
      estado<=id;
		wait for 100 ns;	
      estado<=ex;

      -- insert stimulus here 

      wait;
   end process;

END;
