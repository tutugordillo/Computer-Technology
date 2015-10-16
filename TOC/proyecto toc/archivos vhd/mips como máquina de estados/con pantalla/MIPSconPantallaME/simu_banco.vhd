--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   19:11:41 12/14/2012
-- Design Name:   
-- Module Name:   C:/hlocal/hoy/simu_banco.vhd
-- Project Name:  hoy
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: BancoDeRegistros
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
 
ENTITY simu_banco IS
END simu_banco;
 
ARCHITECTURE behavior OF simu_banco IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT BancoDeRegistros
    PORT(
         Clock : IN  std_logic;
         Reg_Write : IN  std_logic;
         RA : IN  std_logic_vector(4 downto 0);
         RB : IN  std_logic_vector(4 downto 0);
         RW : IN  std_logic_vector(4 downto 0);
         busW : IN  std_logic_vector(31 downto 0);
         busA : OUT  std_logic_vector(31 downto 0);
         busB : OUT  std_logic_vector(31 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal Clock : std_logic := '0';
   signal Reg_Write : std_logic := '0';
   signal RA : std_logic_vector(4 downto 0) := (others => '0');
   signal RB : std_logic_vector(4 downto 0) := (others => '0');
   signal RW : std_logic_vector(4 downto 0) := (others => '0');
   signal busW : std_logic_vector(31 downto 0) := (others => '0');

 	--Outputs
   signal busA : std_logic_vector(31 downto 0);
   signal busB : std_logic_vector(31 downto 0);

   -- Clock period definitions
   constant Clock_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: BancoDeRegistros PORT MAP (
          Clock => Clock,
          Reg_Write => Reg_Write,
          RA => RA,
          RB => RB,
          RW => RW,
          busW => busW,
          busA => busA,
          busB => busB
        );

   -- Clock process definitions
   Clock_process :process
   begin
		Clock <= '0';
		wait for Clock_period/2;
		Clock <= '1';
		wait for Clock_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
		reg_write<='1';
      wait for 100 ns;	
		RA<="00000";
		RB<="00001";
		RW<="00010";
		
		busW<=X"FFFFFFFF";

      wait for 100 ns;	
		RA<="00010";
		RB<="00011";
		RW<="00011";
		
		busW<=X"AAAAAAAA";

      -- insert stimulus here 

      wait;
   end process;

END;
