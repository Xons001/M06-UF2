

--
-- Estructura de tabla para la tabla `PELICULA`
--

CREATE TABLE `PELICULA` (
  `COD_PEL` int(4) NOT NULL,
  `TITULO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DURACION` int(11) DEFAULT NULL,
  `DIRECTOR` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `PELICULA`
--

INSERT INTO `PELICULA` (`COD_PEL`, `TITULO`, `DURACION`, `DIRECTOR`) VALUES
(1, 'LA SIRENITA', 100, 'WARNER BROS'),
(2, 'EL REY LEON', 140, 'WARNER BROS'),
(3, 'THE MATRIX', 200, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SOCIO`
--

CREATE TABLE `SOCIO` (
  `COD_SOC` int(2) NOT NULL,
  `NOMBRE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APELLIDOS` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DIRECCION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TELEFONO` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `POBLACION` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CP` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PROVINCIA` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PAIS` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EDAD` int(11) DEFAULT NULL,
  `FECHAALTA` date DEFAULT NULL,
  `CUOTA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `SOCIO`
--

INSERT INTO `SOCIO` (`COD_SOC`, `NOMBRE`, `APELLIDOS`, `DIRECCION`, `TELEFONO`, `POBLACION`, `CP`, `PROVINCIA`, `PAIS`, `EDAD`, `FECHAALTA`, `CUOTA`) VALUES
(1, 'MARIA', 'GOMEZ GOMEZ', 'BONAVISTA s/n', '936877899', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 20, '1981-11-17', 2000),
(2, 'TONI', 'PEREZ RUIZ', 'BONAVISTA s/n', '933453455', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 22, '1981-11-17', 3000),
(3, 'MIGUEL', 'LOPEZ GOMEZ', 'BONAVISTA s/n', '936437899', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 21, '1981-11-17', 1000),
(4, 'PEPE', 'SORIANO SORIANO', 'REP. ARGENTINA s/n', '936872899', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 21, '1999-11-17', 4000),
(5, 'SONIA', 'GARRIDO GOMEZ', 'BONAVISTA s/n', '936875399', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 22, '1999-10-17', 6000),
(6, 'MARCOS', 'RUIZ SERRANO', 'BONAVISTA s/n', '935437899', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 23, '1981-11-17', 7000),
(7, 'JORGE', 'MILLAN MILLAN', 'BONAVISTA s/n', '935577899', 'CORNELLA', '08940', 'BARCELONA', 'ESPAÑA', 24, '1981-11-17', 3000),
(8, 'JESUS', 'RODRIGUEZ RODRIGUEZ', 'BONAVISTA s/n', '935577899', 'ESPLUGUES', '08940', 'BARCELONA', 'ESPAÑA', 26, '2000-10-13', 4000),
(9, 'MARIA', 'LUZ AGUA', 'AVDA. EL SOL s/n', '935477899', 'VALLECAS', '08940', 'MADRID', 'ESPAÑA', 18, '2000-01-09', 6000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TIENE`
--

CREATE TABLE `TIENE` (
  `COD_SOC` int(2) NOT NULL,
  `COD_PEL` int(4) NOT NULL,
  `FECHA_ADQ` date DEFAULT NULL,
  `FECHA_DEV` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `TIENE`
--

INSERT INTO `TIENE` (`COD_SOC`, `COD_PEL`, `FECHA_ADQ`, `FECHA_DEV`) VALUES
(1, 1, '1999-02-18', '1999-03-18'),
(1, 2, '1999-03-17', '1999-04-17'),
(2, 1, '2000-04-11', NULL),
(3, 2, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `PELICULA`
--
ALTER TABLE `PELICULA`
  ADD PRIMARY KEY (`COD_PEL`);

--
-- Indices de la tabla `SOCIO`
--
ALTER TABLE `SOCIO`
  ADD PRIMARY KEY (`COD_SOC`);

--
-- Indices de la tabla `TIENE`
--
ALTER TABLE `TIENE`
  ADD PRIMARY KEY (`COD_SOC`,`COD_PEL`);
COMMIT;

