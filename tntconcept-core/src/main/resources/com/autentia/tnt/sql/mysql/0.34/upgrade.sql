--
-- TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
-- Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

-- Debe añadirse debajo de [mysqld] en el archivo my.cnf de la instalación mysql de la máquina destino.
-- Si se realiza mediante script necesita ser ejecutado por un usuario con permiso SUPER.
-- set global sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------------------------------
-- Country
-- -----------------------------------------------------------------------------

CREATE TABLE `Country` (
  `id` int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos codigos fijos',
  `code` smallint(6) default NULL,
  `iso3166a1` char(2) COLLATE utf8_spanish_ci default NULL,
  `iso3166a2` char(3) COLLATE utf8_spanish_ci default NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci default NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Catalogo de paises';

INSERT INTO `Country` VALUES (1, 4, 'AF', 'AFG', 'Afganistán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (2, 248, 'AX', 'ALA', 'Islas Gland', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (3, 8, 'AL', 'ALB', 'Albania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (4, 276, 'DE', 'DEU', 'Alemania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (5, 20, 'AD', 'AND', 'Andorra', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (6, 24, 'AO', 'AGO', 'Angola', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (7, 660, 'AI', 'AIA', 'Anguilla', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (8, 10, 'AQ', 'ATA', 'Antártida', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (9, 28, 'AG', 'ATG', 'Antigua y Barbuda', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (10, 530, 'AN', 'ANT', 'Antillas Holandesas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (11, 682, 'SA', 'SAU', 'Arabia Saudí', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (12, 12, 'DZ', 'DZA', 'Argelia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (13, 32, 'AR', 'ARG', 'Argentina', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (14, 51, 'AM', 'ARM', 'Armenia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (15, 533, 'AW', 'ABW', 'Aruba', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (16, 36, 'AU', 'AUS', 'Australia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (17, 40, 'AT', 'AUT', 'Austria', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (18, 31, 'AZ', 'AZE', 'Azerbaiyán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (19, 44, 'BS', 'BHS', 'Bahamas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (20, 48, 'BH', 'BHR', 'Bahréin', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (21, 50, 'BD', 'BGD', 'Bangladesh', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (22, 52, 'BB', 'BRB', 'Barbados', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (23, 112, 'BY', 'BLR', 'Bielorrusia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (24, 56, 'BE', 'BEL', 'Bélgica', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (25, 84, 'BZ', 'BLZ', 'Belice', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (26, 204, 'BJ', 'BEN', 'Benin', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (27, 60, 'BM', 'BMU', 'Bermudas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (28, 64, 'BT', 'BTN', 'Bhután', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (29, 68, 'BO', 'BOL', 'Bolivia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (30, 70, 'BA', 'BIH', 'Bosnia y Herzegovina', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (31, 72, 'BW', 'BWA', 'Botsuana', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (32, 74, 'BV', 'BVT', 'Isla Bouvet', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (33, 76, 'BR', 'BRA', 'Brasil', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (34, 96, 'BN', 'BRN', 'Brunéi', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (35, 100, 'BG', 'BGR', 'Bulgaria', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (36, 854, 'BF', 'BFA', 'Burkina Faso', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (37, 108, 'BI', 'BDI', 'Burundi', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (38, 132, 'CV', 'CPV', 'Cabo Verde', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (39, 136, 'KY', 'CYM', 'Islas Caimán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (40, 116, 'KH', 'KHM', 'Camboya', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (41, 120, 'CM', 'CMR', 'Camerún', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (42, 124, 'CA', 'CAN', 'Canadá', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (43, 140, 'CF', 'CAF', 'República Centroafricana', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (44, 148, 'TD', 'TCD', 'Chad', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (45, 203, 'CZ', 'CZE', 'República Checa', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (46, 152, 'CL', 'CHL', 'Chile', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (47, 156, 'CN', 'CHN', 'China', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (48, 196, 'CY', 'CYP', 'Chipre', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (49, 162, 'CX', 'CXR', 'Isla de Navidad', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (50, 336, 'VA', 'VAT', 'Ciudad del Vaticano', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (51, 166, 'CC', 'CCK', 'Islas Cocos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (52, 170, 'CO', 'COL', 'Colombia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (53, 174, 'KM', 'COM', 'Comoras', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (54, 180, 'CD', 'COD', 'República Democrática del Congo', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (55, 178, 'CG', 'COG', 'Congo', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (56, 184, 'CK', 'COK', 'Islas Cook', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (57, 408, 'KP', 'PRK', 'Corea del Norte', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (58, 410, 'KR', 'KOR', 'Corea del Sur', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (59, 384, 'CI', 'CIV', 'Costa de Marfil', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (60, 188, 'CR', 'CRI', 'Costa Rica', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (61, 191, 'HR', 'HRV', 'Croacia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (62, 192, 'CU', 'CUB', 'Cuba', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (63, 208, 'DK', 'DNK', 'Dinamarca', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (64, 212, 'DM', 'DMA', 'Dominica', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (65, 214, 'DO', 'DOM', 'República Dominicana', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (66, 218, 'EC', 'ECU', 'Ecuador', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (67, 818, 'EG', 'EGY', 'Egipto', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (68, 222, 'SV', 'SLV', 'El Salvador', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (69, 784, 'AE', 'ARE', 'Emiratos Árabes Unidos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (70, 232, 'ER', 'ERI', 'Eritrea', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (71, 703, 'SK', 'SVK', 'Eslovaquia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (72, 705, 'SI', 'SVN', 'Eslovenia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (73, 724, 'ES', 'ESP', 'España', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (74, 581, 'UM', 'UMI', 'Islas ultramarinas de Estados Unidos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (75, 840, 'US', 'USA', 'Estados Unidos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (76, 233, 'EE', 'EST', 'Estonia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (77, 231, 'ET', 'ETH', 'Etiopía', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (78, 234, 'FO', 'FRO', 'Islas Feroe', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (79, 608, 'PH', 'PHL', 'Filipinas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (80, 246, 'FI', 'FIN', 'Finlandia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (81, 242, 'FJ', 'FJI', 'Fiyi', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (82, 250, 'FR', 'FRA', 'Francia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (83, 266, 'GA', 'GAB', 'Gabón', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (84, 270, 'GM', 'GMB', 'Gambia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (85, 268, 'GE', 'GEO', 'Georgia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (86, 239, 'GS', 'SGS', 'Islas Georgias del Sur y Sandwich del Sur', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (87, 288, 'GH', 'GHA', 'Ghana', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (88, 292, 'GI', 'GIB', 'Gibraltar', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (89, 308, 'GD', 'GRD', 'Granada', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (90, 300, 'GR', 'GRC', 'Grecia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (91, 304, 'GL', 'GRL', 'Groenlandia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (92, 312, 'GP', 'GLP', 'Guadalupe', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (93, 316, 'GU', 'GUM', 'Guam', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (94, 320, 'GT', 'GTM', 'Guatemala', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (95, 254, 'GF', 'GUF', 'Guayana Francesa', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (96, 324, 'GN', 'GIN', 'Guinea', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (97, 226, 'GQ', 'GNQ', 'Guinea Ecuatorial', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (98, 624, 'GW', 'GNB', 'Guinea-Bissau', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (99, 328, 'GY', 'GUY', 'Guyana', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (100, 332, 'HT', 'HTI', 'Haití', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (101, 334, 'HM', 'HMD', 'Islas Heard y McDonald', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (102, 340, 'HN', 'HND', 'Honduras', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (103, 344, 'HK', 'HKG', 'Hong Kong', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (104, 348, 'HU', 'HUN', 'Hungría', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (105, 356, 'IN', 'IND', 'India', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (106, 360, 'ID', 'IDN', 'Indonesia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (107, 364, 'IR', 'IRN', 'Irán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (108, 368, 'IQ', 'IRQ', 'Iraq', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (109, 372, 'IE', 'IRL', 'Irlanda', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (110, 352, 'IS', 'ISL', 'Islandia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (111, 376, 'IL', 'ISR', 'Israel', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (112, 380, 'IT', 'ITA', 'Italia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (113, 388, 'JM', 'JAM', 'Jamaica', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (114, 392, 'JP', 'JPN', 'Japón', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (115, 400, 'JO', 'JOR', 'Jordania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (116, 398, 'KZ', 'KAZ', 'Kazajstán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (117, 404, 'KE', 'KEN', 'Kenia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (118, 417, 'KG', 'KGZ', 'Kirguistán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (119, 296, 'KI', 'KIR', 'Kiribati', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (120, 414, 'KW', 'KWT', 'Kuwait', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (121, 418, 'LA', 'LAO', 'Laos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (122, 426, 'LS', 'LSO', 'Lesotho', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (123, 428, 'LV', 'LVA', 'Letonia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (124, 422, 'LB', 'LBN', 'Líbano', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (125, 430, 'LR', 'LBR', 'Liberia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (126, 434, 'LY', 'LBY', 'Libia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (127, 438, 'LI', 'LIE', 'Liechtenstein', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (128, 440, 'LT', 'LTU', 'Lituania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (129, 442, 'LU', 'LUX', 'Luxemburgo', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (130, 446, 'MO', 'MAC', 'Macao', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (131, 807, 'MK', 'MKD', 'ARY Macedonia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (132, 450, 'MG', 'MDG', 'Madagascar', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (133, 458, 'MY', 'MYS', 'Malasia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (134, 454, 'MW', 'MWI', 'Malawi', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (135, 462, 'MV', 'MDV', 'Maldivas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (136, 466, 'ML', 'MLI', 'Malí', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (137, 470, 'MT', 'MLT', 'Malta', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (138, 238, 'FK', 'FLK', 'Islas Malvinas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (139, 580, 'MP', 'MNP', 'Islas Marianas del Norte', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (140, 504, 'MA', 'MAR', 'Marruecos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (141, 584, 'MH', 'MHL', 'Islas Marshall', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (142, 474, 'MQ', 'MTQ', 'Martinica', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (143, 480, 'MU', 'MUS', 'Mauricio', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (144, 478, 'MR', 'MRT', 'Mauritania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (145, 175, 'YT', 'MYT', 'Mayotte', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (146, 484, 'MX', 'MEX', 'México', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (147, 583, 'FM', 'FSM', 'Micronesia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (148, 498, 'MD', 'MDA', 'Moldavia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (149, 492, 'MC', 'MCO', 'Mónaco', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (150, 496, 'MN', 'MNG', 'Mongolia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (151, 500, 'MS', 'MSR', 'Montserrat', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (152, 508, 'MZ', 'MOZ', 'Mozambique', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (153, 104, 'MM', 'MMR', 'Myanmar', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (154, 516, 'NA', 'NAM', 'Namibia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (155, 520, 'NR', 'NRU', 'Nauru', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (156, 524, 'NP', 'NPL', 'Nepal', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (157, 558, 'NI', 'NIC', 'Nicaragua', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (158, 562, 'NE', 'NER', 'Níger', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (159, 566, 'NG', 'NGA', 'Nigeria', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (160, 570, 'NU', 'NIU', 'Niue', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (161, 574, 'NF', 'NFK', 'Isla Norfolk', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (162, 578, 'NO', 'NOR', 'Noruega', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (163, 540, 'NC', 'NCL', 'Nueva Caledonia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (164, 554, 'NZ', 'NZL', 'Nueva Zelanda', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (165, 512, 'OM', 'OMN', 'Omán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (166, 528, 'NL', 'NLD', 'Países Bajos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (167, 586, 'PK', 'PAK', 'Pakistán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (168, 585, 'PW', 'PLW', 'Palau', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (169, 275, 'PS', 'PSE', 'Palestina', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (170, 591, 'PA', 'PAN', 'Panamá', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (171, 598, 'PG', 'PNG', 'Papúa Nueva Guinea', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (172, 600, 'PY', 'PRY', 'Paraguay', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (173, 604, 'PE', 'PER', 'Perú', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (174, 612, 'PN', 'PCN', 'Islas Pitcairn', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (175, 258, 'PF', 'PYF', 'Polinesia Francesa', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (176, 616, 'PL', 'POL', 'Polonia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (177, 620, 'PT', 'PRT', 'Portugal', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (178, 630, 'PR', 'PRI', 'Puerto Rico', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (179, 634, 'QA', 'QAT', 'Qatar', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (180, 826, 'GB', 'GBR', 'Reino Unido', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (181, 638, 'RE', 'REU', 'Reunión', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (182, 646, 'RW', 'RWA', 'Ruanda', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (183, 642, 'RO', 'ROU', 'Rumania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (184, 643, 'RU', 'RUS', 'Rusia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (185, 732, 'EH', 'ESH', 'Sahara Occidental', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (186, 90, 'SB', 'SLB', 'Islas Salomón', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (187, 882, 'WS', 'WSM', 'Samoa', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (188, 16, 'AS', 'ASM', 'Samoa Americana', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (189, 659, 'KN', 'KNA', 'San Cristóbal y Nevis', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (190, 674, 'SM', 'SMR', 'San Marino', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (191, 666, 'PM', 'SPM', 'San Pedro y Miquelón', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (192, 670, 'VC', 'VCT', 'San Vicente y las Granadinas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (193, 654, 'SH', 'SHN', 'Santa Helena', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (194, 662, 'LC', 'LCA', 'Santa Lucía', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (195, 678, 'ST', 'STP', 'Santo Tomé y Príncipe', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (196, 686, 'SN', 'SEN', 'Senegal', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (197, 891, 'CS', 'SCG', 'Serbia y Montenegro', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (198, 690, 'SC', 'SYC', 'Seychelles', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (199, 694, 'SL', 'SLE', 'Sierra Leona', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (200, 702, 'SG', 'SGP', 'Singapur', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (201, 760, 'SY', 'SYR', 'Siria', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (202, 706, 'SO', 'SOM', 'Somalia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (203, 144, 'LK', 'LKA', 'Sri Lanka', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (204, 748, 'SZ', 'SWZ', 'Suazilandia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (205, 710, 'ZA', 'ZAF', 'Sudáfrica', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (206, 736, 'SD', 'SDN', 'Sudán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (207, 752, 'SE', 'SWE', 'Suecia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (208, 756, 'CH', 'CHE', 'Suiza', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (209, 740, 'SR', 'SUR', 'Surinam', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (210, 744, 'SJ', 'SJM', 'Svalbard y Jan Mayen', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (211, 764, 'TH', 'THA', 'Tailandia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (212, 158, 'TW', 'TWN', 'Taiwán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (213, 834, 'TZ', 'TZA', 'Tanzania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (214, 762, 'TJ', 'TJK', 'Tayikistán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (215, 86, 'IO', 'IOT', 'Territorio Británico del Océano Índico', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (216, 260, 'TF', 'ATF', 'Territorios Australes Franceses', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (217, 626, 'TL', 'TLS', 'Timor Oriental', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (218, 768, 'TG', 'TGO', 'Togo', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (219, 772, 'TK', 'TKL', 'Tokelau', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (220, 776, 'TO', 'TON', 'Tonga', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (221, 780, 'TT', 'TTO', 'Trinidad y Tobago', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (222, 788, 'TN', 'TUN', 'Túnez', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (223, 796, 'TC', 'TCA', 'Islas Turcas y Caicos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (224, 795, 'TM', 'TKM', 'Turkmenistán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (225, 792, 'TR', 'TUR', 'Turquía', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (226, 798, 'TV', 'TUV', 'Tuvalu', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (227, 804, 'UA', 'UKR', 'Ucrania', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (228, 800, 'UG', 'UGA', 'Uganda', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (229, 858, 'UY', 'URY', 'Uruguay', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (230, 860, 'UZ', 'UZB', 'Uzbekistán', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (231, 548, 'VU', 'VUT', 'Vanuatu', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (232, 862, 'VE', 'VEN', 'Venezuela', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (233, 704, 'VN', 'VNM', 'Vietnam', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (234, 92, 'VG', 'VGB', 'Islas Vírgenes Británicas', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (235, 850, 'VI', 'VIR', 'Islas Vírgenes de los Estados Unidos', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (236, 876, 'WF', 'WLF', 'Wallis y Futuna', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (237, 887, 'YE', 'YEM', 'Yemen', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (238, 262, 'DJ', 'DJI', 'Yibuti', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (239, 894, 'ZM', 'ZMB', 'Zambia', NULL, NULL, NULL, NULL);
INSERT INTO `Country` VALUES (240, 716, 'ZW', 'ZWE', 'Zimbabue', NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------

ALTER TABLE `tntconcept`.`Organization`
ADD COLUMN `countryId` INT(11) NULL DEFAULT 73 AFTER `evaluationCriteria`,
ADD INDEX `fk_organization_countryId_idx` (`countryId` ASC);

ALTER TABLE `tntconcept`.`Organization`
ADD CONSTRAINT `fk_organization_countryId`
  FOREIGN KEY (`countryId`)
  REFERENCES `tntconcept`.`Country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

UPDATE Organization as o
SET o.countryId = IFNULL((SELECT id FROM Country AS c WHERE c.name LIKE o.country), 73);

-- ALTER TABLE Organization ADD CONSTRAINT Organization_Country_FK FOREIGN KEY (countryId) REFERENCES Country(id);

ALTER TABLE Organization DROP COLUMN country;

-- -----------------------------------------------------------------------------
-- Province
-- -----------------------------------------------------------------------------

INSERT INTO Province (id,name)
	VALUES (54,'AA - Otras (Extranjeras)');

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
update Version set version='0.35';