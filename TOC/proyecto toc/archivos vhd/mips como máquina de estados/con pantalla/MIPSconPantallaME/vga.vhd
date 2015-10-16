library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use work.tipos.all;


entity vgacore is
	port
	(
		reset: in std_logic;	-- reset
		clock: in std_logic;
		ins: in std_logic_vector(31 downto 0);--instruccion
		salidaAlu: in std_logic_vector(31 downto 0);--salidaALU
		estado: in Estados;
		reg0: in std_logic_vector(31 downto 0);
		reg1: in std_logic_vector(31 downto 0);
		reg2: in std_logic_vector(31 downto 0);
		reg3: in std_logic_vector(31 downto 0);
		reg4: in std_logic_vector(31 downto 0);
		reg5: in std_logic_vector(31 downto 0);
		reg6: in std_logic_vector(31 downto 0);
		reg7: in std_logic_vector(31 downto 0);
		hsyncb: inout std_logic;	-- horizontal (line) sync
		vsyncb: out std_logic;	-- vertical (frame) sync
		rgb: out std_logic_vector(8 downto 0) -- red,green,blue colors
	);
end vgacore;

architecture vgacore_arch of vgacore is

component divisor is
	 port (
        reset: in STD_LOGIC;
        clk: in STD_LOGIC; -- reloj de entrada de la entity superior
        reloj: out STD_LOGIC -- reloj que se utiliza en los process del programa principal
    );
end component;

component bitx is
	 port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					encendido: in std_logic;
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);
end component;

component Instruction is
port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					funct: in std_logic_vector(5 downto 0);
					op: in std_logic_vector(5 downto 0);
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);
end component;

component Constantes is
port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					tipo: in std_logic_vector(2 downto 0);
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);

end component;

component States is
port ( 	hcnt: in std_logic_vector(8 downto 0);	-- horizontal pixel counter
					vcnt: in std_logic_vector(9 downto 0);		
					estado: in Estados;
					pintar: out std_logic;
					pos_x:in std_logic_vector (8 downto 0);
					pos_y: in std_logic_vector(9 downto 0)
		);

end component;


signal hcnt: std_logic_vector(8 downto 0);	-- horizontal pixel counter
signal vcnt: std_logic_vector(9 downto 0);	-- vertical line counter
signal pintar: std_logic;					-- video blanking signal
signal pintar2: std_logic_vector(31 downto 0);
signal pintar3: std_logic_vector(31 downto 0);
signal pintar4: std_logic;
signal pintar5: std_logic;
signal pintar6: std_logic;
signal pintar7: std_logic;

signal pintarMem: std_logic;
signal pintarReg: std_logic;

--pintar registros:
signal pintar10: std_logic_vector(31 downto 0);
signal pintar11: std_logic_vector(31 downto 0);
signal pintar12: std_logic_vector(31 downto 0);
signal pintar13: std_logic_vector(31 downto 0);
signal pintar14: std_logic_vector(31 downto 0);
signal pintar15: std_logic_vector(31 downto 0);
signal pintar16: std_logic_vector(31 downto 0);
signal pintar17: std_logic_vector(31 downto 0);
signal reloj: std_logic;





begin
filas: for i in 0 to 31  generate 
			celdaBit: bitx port map (hcnt, vcnt, ins(31-i), pintar2(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(2*16, 10));--Segunda (instrucción) 2
			celdaBit2: bitx port map (hcnt, vcnt, salidaALU(31-i), pintar3(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(6*16, 10)); --cuarta 6
			celdaBit30: bitx port map (hcnt, vcnt, reg0(31-i), pintar10(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(12*16, 10));--sexta 
			celdaBit31: bitx port map (hcnt, vcnt, reg1(31-i), pintar11(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(13*16, 10));--séptimo
			celdaBit32: bitx port map (hcnt, vcnt, reg2(31-i), pintar12(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(14*16, 10));--octavo
			celdaBit33: bitx port map (hcnt, vcnt, reg3(31-i), pintar13(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(15*16, 10));--noveno
			celdaBit34: bitx port map (hcnt, vcnt, reg4(31-i), pintar14(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(19*16, 10));--décimo
			celdaBit35: bitx port map (hcnt, vcnt, reg5(31-i), pintar15(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(20*16, 10));--undécimo
			celdaBit36: bitx port map (hcnt, vcnt, reg6(31-i), pintar16(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(21*16, 10));--duodécimo
			celdaBit37: bitx port map (hcnt, vcnt, reg7(31-i), pintar17(i), conv_std_logic_vector(8*(i), 9) , conv_std_logic_vector(22*16, 10));--décimotercero
	
	end generate filas;
	

	
Inst: Instruction 	port map(hcnt, vcnt, ins(5 downto 0), ins(31 downto 26), pintar, conv_std_logic_vector(13*8, 9) , conv_std_logic_vector(0, 10));--primera derecha 0
Instruc: Constantes 	port map(hcnt, vcnt, "000", pintar4, conv_std_logic_vector(0, 9), conv_std_logic_vector(0, 10));--primera izquierda 0
State: Constantes 	port map(hcnt, vcnt, "010", pintar5, conv_std_logic_vector(0, 9), conv_std_logic_vector(8*16, 10));--quinta 8
ALU: Constantes 		port map(hcnt, vcnt, "001", pintar6, conv_std_logic_vector(0, 9), conv_std_logic_vector(4*16, 10));--tercero 4
Stat: States 			port map(hcnt, vcnt, estado, pintar7, conv_std_logic_vector(7*8, 9), conv_std_logic_vector(8*16, 10));--quinta 8
Registros:Constantes	port map(hcnt, vcnt, "011", pintarReg, conv_std_logic_vector(0, 9), conv_std_logic_vector(10*16, 10));--quinta 8
Memoria:	Constantes	port map(hcnt, vcnt, "100", pintarMem, conv_std_logic_vector(0, 9), conv_std_logic_vector(17*16, 10));--quinta 8
Nuevo_reloj: divisor port map(reset,clock,reloj);


A: process(reloj,reset)
begin
	-- reset asynchronously clears pixel counter
	if reset='1' then
		hcnt <= "000000000";
	-- horiz. pixel counter increments on rising edge of dot clock
	elsif (reloj'event and reloj='1') then
		-- horiz. pixel counter rolls-over after 381 pixels
		if hcnt<380 then
			hcnt <= hcnt + 1;
		else
			hcnt <= "000000000";
		end if;
	end if;
end process;

B: process(hsyncb,reset)
begin
	-- reset asynchronously clears line counter
	if reset='1' then
		vcnt <= "0000000000";
	-- vert. line counter increments after every horiz. line
	elsif (hsyncb'event and hsyncb='1') then
		-- vert. line counter rolls-over after 528 lines
		if vcnt<527 then
			vcnt <= vcnt + 1;
		else
			vcnt <= "0000000000";
		end if;
	end if;
end process;

C: process(reloj,reset)
begin
	-- reset asynchronously sets horizontal sync to inactive
	if reset='1' then
		hsyncb <= '1';
	-- horizontal sync is recomputed on the rising edge of every dot clock
	elsif (reloj'event and reloj='1') then
		-- horiz. sync is low in this interval to signal start of a new line
		if (hcnt>=291 and hcnt<337) then
			hsyncb <= '0';
		else
			hsyncb <= '1';
		end if;
	end if;
end process;

D: process(hsyncb,reset)
begin
	-- reset asynchronously sets vertical sync to inactive
	if reset='1' then
		vsyncb <= '1';
	-- vertical sync is recomputed at the end of every line of pixels
	elsif (hsyncb'event and hsyncb='1') then
		-- vert. sync is low in this interval to signal start of a new frame
		if (vcnt>=490 and vcnt<492) then
			vsyncb <= '0';
		else
			vsyncb <= '1';
		end if;
	end if;
end process;
----------------------------------------------------------------------------
--
-- A partir de aqui escribir la parte de dibujar en la pantalla
--
-- Tienen que generarse al menos dos process uno que actua sobre donde
-- se va a pintar, decide de qué pixel a que pixel se va a pintar
-- Puede haber tantos process como señales pintar (figuras) diferentes 
-- queramos dibujar
--
-- Otro process (tipo case para dibujos complicados) que dependiendo del
-- valor de las diferentes señales pintar genera diferentes colores (rgb)
-- Sólo puede haber un process para trabajar sobre rgb
--
----------------------------------------------------------------------------
--
----------------------------------------------------------------------------
--
-- Ejemplo
--


colorear: process( hcnt, vcnt)
begin
	if conv_integer(pintar2) /= 0 then rgb<="000100111";
	elsif conv_integer(pintar3) /= 0 then rgb<="000101011";
	elsif pintar = '1' then rgb<="011000011";
	elsif pintar4 = '1' then rgb<="111111111";
	elsif pintar5 = '1' then rgb<="111111111";
	elsif pintar6 = '1' then rgb<="111111111";
	elsif pintar7 = '1' then rgb<="111011000";
	elsif pintarReg = '1' then rgb<="111111111";
	elsif pintarMem = '1' then rgb<="111111111";
	--pintar
	elsif conv_integer(pintar10) /= 0 then rgb<="111000000";
	elsif conv_integer(pintar11) /= 0 then rgb<="111000000";
	elsif conv_integer(pintar12) /= 0 then rgb<="111000000";
	elsif conv_integer(pintar13) /= 0 then rgb<="111000000";
	elsif conv_integer(pintar14) /= 0 then rgb<="000110000";
	elsif conv_integer(pintar15) /= 0 then rgb<="000110000";
	elsif conv_integer(pintar16) /= 0 then rgb<="000110000";
	elsif conv_integer(pintar17) /= 0 then rgb<="000110000";
	else rgb<="000000000";
	end if;
end process colorear;
---------------------------------------------------------------------------
end vgacore_arch;