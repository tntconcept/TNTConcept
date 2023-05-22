CREATE TABLE `Version`
(
    `version` varchar(32) NOT NULL
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci COMMENT = 'The database version';

INSERT INTO Version VALUES ('0.47');


CREATE TABLE `Country`
(
    `id`           int                                       NOT NULL,
    `code`         smallint,
    `iso3166a1`    char(2)      COLLATE utf8mb4_spanish_ci,
    `iso3166a2`    char(3)      COLLATE utf8mb4_spanish_ci,
    `name`         varchar(128) COLLATE utf8mb4_spanish_ci,
    `ownerId`      int,
    `departmentId` int          UNSIGNED,

    `insertDate`   datetime                                  DEFAULT NOW(),
    `updateDate`   datetime                                  DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO `Country` (id, code, iso3166a1, iso3166a2, name, ownerId, departmentId)
VALUES (1, 724, 'ES', 'ESP', 'España', NULL, NULL),
       (2, 248, 'AX', 'ALA', 'Islas Gland', NULL, NULL),
       (3, 8, 'AL', 'ALB', 'Albania', NULL, NULL),
       (4, 276, 'DE', 'DEU', 'Alemania', NULL, NULL),
       (5, 20, 'AD', 'AND', 'Andorra', NULL, NULL),
       (6, 24, 'AO', 'AGO', 'Angola', NULL, NULL),
       (7, 660, 'AI', 'AIA', 'Anguilla', NULL, NULL),
       (8, 10, 'AQ', 'ATA', 'Antártida', NULL, NULL),
       (9, 28, 'AG', 'ATG', 'Antigua y Barbuda', NULL, NULL),
       (10, 530, 'AN', 'ANT', 'Antillas Holandesas', NULL, NULL),
       (11, 682, 'SA', 'SAU', 'Arabia Saudí', NULL, NULL),
       (12, 12, 'DZ', 'DZA', 'Argelia', NULL, NULL),
       (13, 32, 'AR', 'ARG', 'Argentina', NULL, NULL),
       (14, 51, 'AM', 'ARM', 'Armenia', NULL, NULL),
       (15, 533, 'AW', 'ABW', 'Aruba', NULL, NULL),
       (16, 36, 'AU', 'AUS', 'Australia', NULL, NULL),
       (17, 40, 'AT', 'AUT', 'Austria', NULL, NULL),
       (18, 31, 'AZ', 'AZE', 'Azerbaiyán', NULL, NULL),
       (19, 44, 'BS', 'BHS', 'Bahamas', NULL, NULL),
       (20, 48, 'BH', 'BHR', 'Bahréin', NULL, NULL),
       (21, 50, 'BD', 'BGD', 'Bangladesh', NULL, NULL),
       (22, 52, 'BB', 'BRB', 'Barbados', NULL, NULL),
       (23, 112, 'BY', 'BLR', 'Bielorrusia', NULL, NULL),
       (24, 56, 'BE', 'BEL', 'Bélgica', NULL, NULL),
       (25, 84, 'BZ', 'BLZ', 'Belice', NULL, NULL),
       (26, 204, 'BJ', 'BEN', 'Benin', NULL, NULL),
       (27, 60, 'BM', 'BMU', 'Bermudas', NULL, NULL),
       (28, 64, 'BT', 'BTN', 'Bhután', NULL, NULL),
       (29, 68, 'BO', 'BOL', 'Bolivia', NULL, NULL),
       (30, 70, 'BA', 'BIH', 'Bosnia y Herzegovina', NULL, NULL),
       (31, 72, 'BW', 'BWA', 'Botsuana', NULL, NULL),
       (32, 74, 'BV', 'BVT', 'Isla Bouvet', NULL, NULL),
       (33, 76, 'BR', 'BRA', 'Brasil', NULL, NULL),
       (34, 96, 'BN', 'BRN', 'Brunéi', NULL, NULL),
       (35, 100, 'BG', 'BGR', 'Bulgaria', NULL, NULL),
       (36, 854, 'BF', 'BFA', 'Burkina Faso', NULL, NULL),
       (37, 108, 'BI', 'BDI', 'Burundi', NULL, NULL),
       (38, 132, 'CV', 'CPV', 'Cabo Verde', NULL, NULL),
       (39, 136, 'KY', 'CYM', 'Islas Caimán', NULL, NULL),
       (40, 116, 'KH', 'KHM', 'Camboya', NULL, NULL),
       (41, 120, 'CM', 'CMR', 'Camerún', NULL, NULL),
       (42, 124, 'CA', 'CAN', 'Canadá', NULL, NULL),
       (43, 140, 'CF', 'CAF', 'República Centroafricana', NULL, NULL),
       (44, 148, 'TD', 'TCD', 'Chad', NULL, NULL),
       (45, 203, 'CZ', 'CZE', 'República Checa', NULL, NULL),
       (46, 152, 'CL', 'CHL', 'Chile', NULL, NULL),
       (47, 156, 'CN', 'CHN', 'China', NULL, NULL),
       (48, 196, 'CY', 'CYP', 'Chipre', NULL, NULL),
       (49, 162, 'CX', 'CXR', 'Isla de Navidad', NULL, NULL),
       (50, 336, 'VA', 'VAT', 'Ciudad del Vaticano', NULL, NULL),
       (51, 166, 'CC', 'CCK', 'Islas Cocos', NULL, NULL),
       (52, 170, 'CO', 'COL', 'Colombia', NULL, NULL),
       (53, 174, 'KM', 'COM', 'Comoras', NULL, NULL),
       (54, 180, 'CD', 'COD', 'República Democrática del Congo', NULL, NULL),
       (55, 178, 'CG', 'COG', 'Congo', NULL, NULL),
       (56, 184, 'CK', 'COK', 'Islas Cook', NULL, NULL),
       (57, 408, 'KP', 'PRK', 'Corea del Norte', NULL, NULL),
       (58, 410, 'KR', 'KOR', 'Corea del Sur', NULL, NULL),
       (59, 384, 'CI', 'CIV', 'Costa de Marfil', NULL, NULL),
       (60, 188, 'CR', 'CRI', 'Costa Rica', NULL, NULL),
       (61, 191, 'HR', 'HRV', 'Croacia', NULL, NULL),
       (62, 192, 'CU', 'CUB', 'Cuba', NULL, NULL),
       (63, 208, 'DK', 'DNK', 'Dinamarca', NULL, NULL),
       (64, 212, 'DM', 'DMA', 'Dominica', NULL, NULL),
       (65, 214, 'DO', 'DOM', 'República Dominicana', NULL, NULL),
       (66, 218, 'EC', 'ECU', 'Ecuador', NULL, NULL),
       (67, 818, 'EG', 'EGY', 'Egipto', NULL, NULL),
       (68, 222, 'SV', 'SLV', 'El Salvador', NULL, NULL),
       (69, 784, 'AE', 'ARE', 'Emiratos Árabes Unidos', NULL, NULL),
       (70, 232, 'ER', 'ERI', 'Eritrea', NULL, NULL),
       (71, 703, 'SK', 'SVK', 'Eslovaquia', NULL, NULL),
       (72, 705, 'SI', 'SVN', 'Eslovenia', NULL, NULL),
       (73, 4, 'AF', 'AFG', 'Afganistán', NULL, NULL),
       (74, 581, 'UM', 'UMI', 'Islas ultramarinas de Estados Unidos', NULL, NULL),
       (75, 840, 'US', 'USA', 'Estados Unidos', NULL, NULL),
       (76, 233, 'EE', 'EST', 'Estonia', NULL, NULL),
       (77, 231, 'ET', 'ETH', 'Etiopía', NULL, NULL),
       (78, 234, 'FO', 'FRO', 'Islas Feroe', NULL, NULL),
       (79, 608, 'PH', 'PHL', 'Filipinas', NULL, NULL),
       (80, 246, 'FI', 'FIN', 'Finlandia', NULL, NULL),
       (81, 242, 'FJ', 'FJI', 'Fiyi', NULL, NULL),
       (82, 250, 'FR', 'FRA', 'Francia', NULL, NULL),
       (83, 266, 'GA', 'GAB', 'Gabón', NULL, NULL),
       (84, 270, 'GM', 'GMB', 'Gambia', NULL, NULL),
       (85, 268, 'GE', 'GEO', 'Georgia', NULL, NULL),
       (86, 239, 'GS', 'SGS', 'Islas Georgias del Sur y Sandwich del Sur', NULL, NULL),
       (87, 288, 'GH', 'GHA', 'Ghana', NULL, NULL),
       (88, 292, 'GI', 'GIB', 'Gibraltar', NULL, NULL),
       (89, 308, 'GD', 'GRD', 'Granada', NULL, NULL),
       (90, 300, 'GR', 'GRC', 'Grecia', NULL, NULL),
       (91, 304, 'GL', 'GRL', 'Groenlandia', NULL, NULL),
       (92, 312, 'GP', 'GLP', 'Guadalupe', NULL, NULL),
       (93, 316, 'GU', 'GUM', 'Guam', NULL, NULL),
       (94, 320, 'GT', 'GTM', 'Guatemala', NULL, NULL),
       (95, 254, 'GF', 'GUF', 'Guayana Francesa', NULL, NULL),
       (96, 324, 'GN', 'GIN', 'Guinea', NULL, NULL),
       (97, 226, 'GQ', 'GNQ', 'Guinea Ecuatorial', NULL, NULL),
       (98, 624, 'GW', 'GNB', 'Guinea-Bissau', NULL, NULL),
       (99, 328, 'GY', 'GUY', 'Guyana', NULL, NULL),
       (100, 332, 'HT', 'HTI', 'Haití', NULL, NULL),
       (101, 334, 'HM', 'HMD', 'Islas Heard y McDonald', NULL, NULL),
       (102, 340, 'HN', 'HND', 'Honduras', NULL, NULL),
       (103, 344, 'HK', 'HKG', 'Hong Kong', NULL, NULL),
       (104, 348, 'HU', 'HUN', 'Hungría', NULL, NULL),
       (105, 356, 'IN', 'IND', 'India', NULL, NULL),
       (106, 360, 'ID', 'IDN', 'Indonesia', NULL, NULL),
       (107, 364, 'IR', 'IRN', 'Irán', NULL, NULL),
       (108, 368, 'IQ', 'IRQ', 'Iraq', NULL, NULL),
       (109, 372, 'IE', 'IRL', 'Irlanda', NULL, NULL),
       (110, 352, 'IS', 'ISL', 'Islandia', NULL, NULL),
       (111, 376, 'IL', 'ISR', 'Israel', NULL, NULL),
       (112, 380, 'IT', 'ITA', 'Italia', NULL, NULL),
       (113, 388, 'JM', 'JAM', 'Jamaica', NULL, NULL),
       (114, 392, 'JP', 'JPN', 'Japón', NULL, NULL),
       (115, 400, 'JO', 'JOR', 'Jordania', NULL, NULL),
       (116, 398, 'KZ', 'KAZ', 'Kazajstán', NULL, NULL),
       (117, 404, 'KE', 'KEN', 'Kenia', NULL, NULL),
       (118, 417, 'KG', 'KGZ', 'Kirguistán', NULL, NULL),
       (119, 296, 'KI', 'KIR', 'Kiribati', NULL, NULL),
       (120, 414, 'KW', 'KWT', 'Kuwait', NULL, NULL),
       (121, 418, 'LA', 'LAO', 'Laos', NULL, NULL),
       (122, 426, 'LS', 'LSO', 'Lesotho', NULL, NULL),
       (123, 428, 'LV', 'LVA', 'Letonia', NULL, NULL),
       (124, 422, 'LB', 'LBN', 'Líbano', NULL, NULL),
       (125, 430, 'LR', 'LBR', 'Liberia', NULL, NULL),
       (126, 434, 'LY', 'LBY', 'Libia', NULL, NULL),
       (127, 438, 'LI', 'LIE', 'Liechtenstein', NULL, NULL),
       (128, 440, 'LT', 'LTU', 'Lituania', NULL, NULL),
       (129, 442, 'LU', 'LUX', 'Luxemburgo', NULL, NULL),
       (130, 446, 'MO', 'MAC', 'Macao', NULL, NULL),
       (131, 807, 'MK', 'MKD', 'ARY Macedonia', NULL, NULL),
       (132, 450, 'MG', 'MDG', 'Madagascar', NULL, NULL),
       (133, 458, 'MY', 'MYS', 'Malasia', NULL, NULL),
       (134, 454, 'MW', 'MWI', 'Malawi', NULL, NULL),
       (135, 462, 'MV', 'MDV', 'Maldivas', NULL, NULL),
       (136, 466, 'ML', 'MLI', 'Malí', NULL, NULL),
       (137, 470, 'MT', 'MLT', 'Malta', NULL, NULL),
       (138, 238, 'FK', 'FLK', 'Islas Malvinas', NULL, NULL),
       (139, 580, 'MP', 'MNP', 'Islas Marianas del Norte', NULL, NULL),
       (140, 504, 'MA', 'MAR', 'Marruecos', NULL, NULL),
       (141, 584, 'MH', 'MHL', 'Islas Marshall', NULL, NULL),
       (142, 474, 'MQ', 'MTQ', 'Martinica', NULL, NULL),
       (143, 480, 'MU', 'MUS', 'Mauricio', NULL, NULL),
       (144, 478, 'MR', 'MRT', 'Mauritania', NULL, NULL),
       (145, 175, 'YT', 'MYT', 'Mayotte', NULL, NULL),
       (146, 484, 'MX', 'MEX', 'México', NULL, NULL),
       (147, 583, 'FM', 'FSM', 'Micronesia', NULL, NULL),
       (148, 498, 'MD', 'MDA', 'Moldavia', NULL, NULL),
       (149, 492, 'MC', 'MCO', 'Mónaco', NULL, NULL),
       (150, 496, 'MN', 'MNG', 'Mongolia', NULL, NULL),
       (151, 500, 'MS', 'MSR', 'Montserrat', NULL, NULL),
       (152, 508, 'MZ', 'MOZ', 'Mozambique', NULL, NULL),
       (153, 104, 'MM', 'MMR', 'Myanmar', NULL, NULL),
       (154, 516, 'NA', 'NAM', 'Namibia', NULL, NULL),
       (155, 520, 'NR', 'NRU', 'Nauru', NULL, NULL),
       (156, 524, 'NP', 'NPL', 'Nepal', NULL, NULL),
       (157, 558, 'NI', 'NIC', 'Nicaragua', NULL, NULL),
       (158, 562, 'NE', 'NER', 'Níger', NULL, NULL),
       (159, 566, 'NG', 'NGA', 'Nigeria', NULL, NULL),
       (160, 570, 'NU', 'NIU', 'Niue', NULL, NULL),
       (161, 574, 'NF', 'NFK', 'Isla Norfolk', NULL, NULL),
       (162, 578, 'NO', 'NOR', 'Noruega', NULL, NULL),
       (163, 540, 'NC', 'NCL', 'Nueva Caledonia', NULL, NULL),
       (164, 554, 'NZ', 'NZL', 'Nueva Zelanda', NULL, NULL),
       (165, 512, 'OM', 'OMN', 'Omán', NULL, NULL),
       (166, 528, 'NL', 'NLD', 'Países Bajos', NULL, NULL),
       (167, 586, 'PK', 'PAK', 'Pakistán', NULL, NULL),
       (168, 585, 'PW', 'PLW', 'Palau', NULL, NULL),
       (169, 275, 'PS', 'PSE', 'Palestina', NULL, NULL),
       (170, 591, 'PA', 'PAN', 'Panamá', NULL, NULL),
       (171, 598, 'PG', 'PNG', 'Papúa Nueva Guinea', NULL, NULL),
       (172, 600, 'PY', 'PRY', 'Paraguay', NULL, NULL),
       (173, 604, 'PE', 'PER', 'Perú', NULL, NULL),
       (174, 612, 'PN', 'PCN', 'Islas Pitcairn', NULL, NULL),
       (175, 258, 'PF', 'PYF', 'Polinesia Francesa', NULL, NULL),
       (176, 616, 'PL', 'POL', 'Polonia', NULL, NULL),
       (177, 620, 'PT', 'PRT', 'Portugal', NULL, NULL),
       (178, 630, 'PR', 'PRI', 'Puerto Rico', NULL, NULL),
       (179, 634, 'QA', 'QAT', 'Qatar', NULL, NULL),
       (180, 826, 'GB', 'GBR', 'Reino Unido', NULL, NULL),
       (181, 638, 'RE', 'REU', 'Reunión', NULL, NULL),
       (182, 646, 'RW', 'RWA', 'Ruanda', NULL, NULL),
       (183, 642, 'RO', 'ROU', 'Rumania', NULL, NULL),
       (184, 643, 'RU', 'RUS', 'Rusia', NULL, NULL),
       (185, 732, 'EH', 'ESH', 'Sahara Occidental', NULL, NULL),
       (186, 90, 'SB', 'SLB', 'Islas Salomón', NULL, NULL),
       (187, 882, 'WS', 'WSM', 'Samoa', NULL, NULL),
       (188, 16, 'AS', 'ASM', 'Samoa Americana', NULL, NULL),
       (189, 659, 'KN', 'KNA', 'San Cristóbal y Nevis', NULL, NULL),
       (190, 674, 'SM', 'SMR', 'San Marino', NULL, NULL),
       (191, 666, 'PM', 'SPM', 'San Pedro y Miquelón', NULL, NULL),
       (192, 670, 'VC', 'VCT', 'San Vicente y las Granadinas', NULL, NULL),
       (193, 654, 'SH', 'SHN', 'Santa Helena', NULL, NULL),
       (194, 662, 'LC', 'LCA', 'Santa Lucía', NULL, NULL),
       (195, 678, 'ST', 'STP', 'Santo Tomé y Príncipe', NULL, NULL),
       (196, 686, 'SN', 'SEN', 'Senegal', NULL, NULL),
       (197, 891, 'CS', 'SCG', 'Serbia y Montenegro', NULL, NULL),
       (198, 690, 'SC', 'SYC', 'Seychelles', NULL, NULL),
       (199, 694, 'SL', 'SLE', 'Sierra Leona', NULL, NULL),
       (200, 702, 'SG', 'SGP', 'Singapur', NULL, NULL),
       (201, 760, 'SY', 'SYR', 'Siria', NULL, NULL),
       (202, 706, 'SO', 'SOM', 'Somalia', NULL, NULL),
       (203, 144, 'LK', 'LKA', 'Sri Lanka', NULL, NULL),
       (204, 748, 'SZ', 'SWZ', 'Suazilandia', NULL, NULL),
       (205, 710, 'ZA', 'ZAF', 'Sudáfrica', NULL, NULL),
       (206, 736, 'SD', 'SDN', 'Sudán', NULL, NULL),
       (207, 752, 'SE', 'SWE', 'Suecia', NULL, NULL),
       (208, 756, 'CH', 'CHE', 'Suiza', NULL, NULL),
       (209, 740, 'SR', 'SUR', 'Surinam', NULL, NULL),
       (210, 744, 'SJ', 'SJM', 'Svalbard y Jan Mayen', NULL, NULL),
       (211, 764, 'TH', 'THA', 'Tailandia', NULL, NULL),
       (212, 158, 'TW', 'TWN', 'Taiwán', NULL, NULL),
       (213, 834, 'TZ', 'TZA', 'Tanzania', NULL, NULL),
       (214, 762, 'TJ', 'TJK', 'Tayikistán', NULL, NULL),
       (215, 86, 'IO', 'IOT', 'Territorio Británico del Océano Índico', NULL, NULL),
       (216, 260, 'TF', 'ATF', 'Territorios Australes Franceses', NULL, NULL),
       (217, 626, 'TL', 'TLS', 'Timor Oriental', NULL, NULL),
       (218, 768, 'TG', 'TGO', 'Togo', NULL, NULL),
       (219, 772, 'TK', 'TKL', 'Tokelau', NULL, NULL),
       (220, 776, 'TO', 'TON', 'Tonga', NULL, NULL),
       (221, 780, 'TT', 'TTO', 'Trinidad y Tobago', NULL, NULL),
       (222, 788, 'TN', 'TUN', 'Túnez', NULL, NULL),
       (223, 796, 'TC', 'TCA', 'Islas Turcas y Caicos', NULL, NULL),
       (224, 795, 'TM', 'TKM', 'Turkmenistán', NULL, NULL),
       (225, 792, 'TR', 'TUR', 'Turquía', NULL, NULL),
       (226, 798, 'TV', 'TUV', 'Tuvalu', NULL, NULL),
       (227, 804, 'UA', 'UKR', 'Ucrania', NULL, NULL),
       (228, 800, 'UG', 'UGA', 'Uganda', NULL, NULL),
       (229, 858, 'UY', 'URY', 'Uruguay', NULL, NULL),
       (230, 860, 'UZ', 'UZB', 'Uzbekistán', NULL, NULL),
       (231, 548, 'VU', 'VUT', 'Vanuatu', NULL, NULL),
       (232, 862, 'VE', 'VEN', 'Venezuela', NULL, NULL),
       (233, 704, 'VN', 'VNM', 'Vietnam', NULL, NULL),
       (234, 92, 'VG', 'VGB', 'Islas Vírgenes Británicas', NULL, NULL),
       (235, 850, 'VI', 'VIR', 'Islas Vírgenes de los Estados Unidos', NULL, NULL),
       (236, 876, 'WF', 'WLF', 'Wallis y Futuna', NULL, NULL),
       (237, 887, 'YE', 'YEM', 'Yemen', NULL, NULL),
       (238, 262, 'DJ', 'DJI', 'Yibuti', NULL, NULL),
       (239, 894, 'ZM', 'ZMB', 'Zambia', NULL, NULL),
       (240, 716, 'ZW', 'ZWE', 'Zimbabue', NULL, NULL);


CREATE TABLE `Province`
(
    `id`           int          NOT NULL,
    `name`         varchar(64)  NOT NULL,
    `ownerId`      int          NULL,
    `departmentId` int UNSIGNED NULL,
    `insertDate`   datetime     DEFAULT NOW(),
    `updateDate`   datetime     DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert default provinces
INSERT INTO Province (id, name)
VALUES (01, 'Alava'),
       (02, 'Albacete'),
       (03, 'Alicante'),
       (04, 'Almería'),
       (05, 'Avila'),
       (06, 'Badajoz'),
       (07, 'Balears, Illes'),
       (08, 'Barcelona'),
       (09, 'Burgos'),
       (10, 'Cáceres'),
       (11, 'Cádiz'),
       (12, 'Castellón'),
       (13, 'Ciudad Real'),
       (14, 'Córdoba'),
       (15, 'Coruña, A'),
       (16, 'Cuenca'),
       (17, 'Girona'),
       (18, 'Granada'),
       (19, 'Guadalajara'),
       (20, 'Guipúzcoa'),
       (21, 'Huelva'),
       (22, 'Huesca'),
       (23, 'Jaén'),
       (24, 'León'),
       (25, 'Lleida'),
       (26, 'Rioja, La'),
       (27, 'Lugo'),
       (28, 'Madrid'),
       (29, 'Málaga'),
       (30, 'Murcia'),
       (31, 'Navarra'),
       (32, 'Ourense'),
       (33, 'Asturias'),
       (34, 'Palencia'),
       (35, 'Palmas, Las'),
       (36, 'Pontevedra'),
       (37, 'Salamanca'),
       (38, 'Santa Cruz de Tenerife'),
       (39, 'Cantabria'),
       (40, 'Segovia'),
       (41, 'Sevilla'),
       (42, 'Soria'),
       (43, 'Tarragona'),
       (44, 'Teruel'),
       (45, 'Toledo'),
       (46, 'Valencia'),
       (47, 'Valladolid'),
       (48, 'Vizcaya'),
       (49, 'Zamora'),
       (50, 'Zaragoza'),
       (51, 'Ceuta'),
       (52, 'Melilla'),
       (53, 'Indefinida'),
       (54, 'AA - Otras (Extranjeras)');


CREATE TABLE `Document`
(
    `id`           int           UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `creationDate` datetime                                 DEFAULT NULL,
    `name`         varchar(256)  COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `description`  varchar(4096) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `ownerId`      int                                      NULL,
    `departmentId` int           UNSIGNED                   NULL,
    `insertDate`   datetime                                 NOT NULL DEFAULT NOW(),
    `updateDate`   datetime                                 NOT NULL DEFAULT NOW(),

    PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `DocumentCategory`
(
    `id`                  int UNSIGNED                             NOT NULL AUTO_INCREMENT,
    `name`                varchar(255)  COLLATE utf8mb4_spanish_ci NOT NULL,
    `description`         varchar(4096) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `code`                varchar(45)   COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `categoryid`          int UNSIGNED                             DEFAULT NULL,
    `documentslastupdate` datetime                                 DEFAULT NULL,
    `ownerId`             int                                      NULL,
    `departmentId`        int UNSIGNED                             NULL,
    `insertDate`          datetime                                 DEFAULT NOW(),
    `updateDate`          datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- default values for categories
INSERT INTO DocumentCategory (name, description, code, categoryid, documentsLastUpdate)
VALUES ('Documentos de Calidad', 'Documentos de calidad', 'CALIDAD', NULL, NULL),
       ('Otros Documentos', 'Otros documentos no clasificados', ' ', NULL, NULL),
       ('Curriculum Vitae', '', '', NULL, NULL),
       ('Documentos de Usuarios', '', '', NULL, NULL),
       ('(I1-PC01) Lista de distribución de la documentación', '', 'I1-PC01', 1, NULL),
       ('(I1-PC02) Acta de reunión de revisión del sistema', '', 'I1-PC02', 1, NULL),
       ('(I1-PC08) Informe de auditoría interna', '', 'I1-PC08', 1, NULL),
       ('(I2-PC02) Planificación de objetivos de calidad', '', 'I2-PC02', 1, NULL),
       ('(I2-PC08) Informe de no conformidad/reclamación del cliente', '', 'I2-PC08', 1, NULL),
       ('(I3-PC08) Informe de acción correctiva/preventiva', '', 'I3-PC08', 1, NULL),
       ('(PC01) Sistema de Gestión de la Calidad', 'Documento inicial de descripción', 'PC01', 1, NULL),
       ('(PC02) Responsabilidad de la Dirección', '', 'PC02', 1, NULL),
       ('(PC03) Gestión de los Recursos', '', 'PC03', 1, NULL),
       ('(PC04) Procesos relacionados con los clientes', '', 'PC04', 1, NULL),
       ('(PC05) Gestión de compras', '', 'PC05', 1, NULL),
       ('(PC06) Evaluación de proveedores y subcontratistas', '', 'PC06', 1, NULL),
       ('(PC07) Prestación del servicio', '', 'PC07', 1, NULL),
       ('(PC08) Medición análisis y mejora', '', 'PC08', 1, NULL),
       ('Control documentación entregada y recibida', '', '', 1, NULL),
       ('Criterio de evaluación y seguimiento de procesos', '', '', 1, NULL),
       ('Cuestionario de satisfacción del cliente', '', '', 1, NULL),
       ('E-mail aprobación documentación', '', '', 1, NULL),
       ('E-mail de comunicaciones', '', '', 1, NULL),
       ('Ficha de mantenimiento de equipos', '', '', 1, NULL),
       ('Índice de ediciones de documentos', '', '', 1, NULL),
       ('Índice de no conformidades', '', '', 1, NULL),
       ('Inventario de recursos', '', '', 1, NULL),
       ('Listado de documentación externa en vigor', '', '', 1, NULL),
       ('Listado de proveedores y subcontratistas evaluados', '', '', 1, NULL),
       ('Listado de registros', '', '', 1, NULL),
       ('Manual de Gestión de la Calidad (MGC)', '', 'MGC', 1, NULL),
       ('Perfil del empleado', '', '', 1, NULL),
       ('Perfil puesto trabajo', '', '', 1, NULL),
       ('Plan de auditoría anual', '', '', 1, NULL),
       ('Plan de formación anual', '', '', 1, NULL),
       ('Política de Calidad', '', '', 1, NULL),
       ('(I5-PC03) Registro perfil del empleado', '', 'I5-PC03', 1, NULL),
       ('(I8-PC03) Cuestionario de satisfacción laboral', '', 'I8-PC03', 1, NULL),
       ('(I6-PC08) Evaluación de satisfacción del cliente', '', 'I6-PC08', 1, NULL);


CREATE TABLE `DocumentVersion`
(
    `id`           int UNSIGNED                            NOT NULL AUTO_INCREMENT,
    `documentPath` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
    `creationDate` datetime                                NOT NULL,
    `version`      varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
    `documentid`   int          UNSIGNED                   NOT NULL,
    `ownerId`      int                                     NULL,
    `departmentId` int          UNSIGNED                   NULL,
    `insertDate`   datetime                                DEFAULT NOW(),
    `updateDate`   datetime                                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_DocumentVersion_document_id` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `DocumentCategoryDoc`
(
    `id`           int UNSIGNED NOT NULL AUTO_INCREMENT,
    `categoryid`   int UNSIGNED NOT NULL,
    `documentid`   int UNSIGNED NOT NULL,
    `ownerId`      int              NULL,
    `departmentId` int UNSIGNED     NULL,
    `insertDate`   datetime     DEFAULT NOW(),
    `updateDate`   datetime     DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_DocumentCategorydoc_category` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
    CONSTRAINT `fk_DocumentCategorydoc_docu` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


CREATE TABLE `OrganizationType`
(
    `id`           int                    NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)           NOT NULL COMMENT 'Organization type descriptive name',
    `description`  varchar(1024)                   COMMENT 'Organization type description',
    `ownerId`      int                    NULL,
    `departmentId` int           UNSIGNED NULL,
    `insertDate`   datetime               DEFAULT NOW(),
    `updateDate`   datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert default organization types
INSERT INTO OrganizationType (name, description)
VALUES ('Cliente', ''),
       ('Proveedor', ''),
       ('Cliente y proveedor', ''),
       ('Prospecto', 'Posible cliente');


CREATE TABLE `OrganizationISOCategory`
(
    `id`           int                    NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)           NOT NULL COMMENT 'ISO Organization Category descriptive name',
    `description`  varchar(1024)                   COMMENT 'ISO Organization Category description',
    `ownerId`      int                    NULL,
    `departmentId` int           UNSIGNED NULL,
    `insertDate`   datetime               DEFAULT NOW(),
    `updateDate`   datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Inserting default ISO categories
INSERT INTO OrganizationISOCategory (name, description)
VALUES ('A', 'Proveedor / Subcontratista habitual.'),
       ('B', 'Proveedor / Subcontratista recomendado.'),
       ('C', 'Proveedor / Subcontratista no habitual.'),
       ('D', 'Proveedor / Subcontratista que haya tenido disconformidades.');


CREATE TABLE OrganizationDocCategory
(
    id           int         NOT NULL,
    code         varchar(3)  NOT NULL,
    name         varchar(70) NOT NULL,
    ownerId      int,
    departmentId int,
    `insertDate` datetime    DEFAULT NOW(),
    `updateDate` datetime    DEFAULT NOW(),

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO OrganizationDocCategory (id, code, name, ownerId, departmentId)
VALUES (1, '02', 'NIF-IVA', NULL, NULL),
       (2, '03', 'PASAPORTE', NULL, NULL),
       (3, '04', 'DOCUMENTO EXPEDIDO POR PAIS O TERRITORIO RESIDENCIA', NULL, NULL),
       (4, '05', 'CERTIFICADO DE RESIDENCIA', NULL, NULL),
       (5, '06', 'OTRO DOCUMENTO PROBATORIO', NULL, NULL),
       (6, '07', 'NO CENSADO', NULL, NULL);


CREATE TABLE `Organization`
(
    `id`                        int                    NOT NULL AUTO_INCREMENT,
    `organizationTypeId`        int                    NOT NULL DEFAULT 1,
    `organizationISOCategoryId` int                    NOT NULL,
    `name`                      varchar(256)           NOT NULL,
    `documentNumber`            varchar(50),
    `phone`                     varchar(15),
    `street`                    varchar(256),
    `number`                    varchar(16)                      COMMENT 'Building number in street',
    `locator`                   varchar(256)                     COMMENT 'Location information inside building',
    `postalCode`                varchar(32),
    `city`                      varchar(256),
    `provinceId`                int                    NOT NULL,
    `state`                     varchar(256),
    `countryId`                 INT                    NOT NULL,
    `fax`                       varchar(16),
    `email`                     varchar(256),
    `website`                   varchar(256),
    `ftpsite`                   varchar(256),
    `notes`                     VARCHAR(1024),
    `ownerId`                   int                    NULL,
    `departmentId`              int           UNSIGNED NULL,
    `evaluationCriteria`        VARCHAR(45)            DEFAULT NULL,
    organizationDocCategoryId   INT                    NOT NULL,
    freelance                   BOOLEAN                NOT NULL,
    `insertDate`                datetime               DEFAULT NOW(),
    `updateDate`                datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_organization_organizationTypeId` FOREIGN KEY (`organizationTypeId`) REFERENCES `OrganizationType` (`id`),
    CONSTRAINT `fk_organization_isoOrganizationCategoryId` FOREIGN KEY (`organizationISOCategoryId`) REFERENCES `OrganizationISOCategory` (`id`),
    CONSTRAINT `fk_organization_countryId` FOREIGN KEY (`countryId`) REFERENCES `Country` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `fk_organization_organizationDocCategory` FOREIGN KEY (organizationDocCategoryId) REFERENCES OrganizationDocCategory (id)

) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert default own company
INSERT INTO Organization(organizationTypeId, organizationISOCategoryId, name, organizationDocCategoryId, freelance,
                         provinceId, countryId, ownerId, departmentId)
VALUES (2, 1, 'Nuestra empresa', 1, FALSE, 53, 1, NULL, NULL),
       (1, 1, 'Indefinida', 1, 53, FALSE, 1, 1, 1);


CREATE TABLE IF NOT EXISTS `Department`
(
    `id`           int           UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `parentId`     int           UNSIGNED,
    `name`         varchar(256)                             NOT NULL,
    `description`  varchar(2048) COLLATE utf8mb4_spanish_ci NOT NULL,
    `ownerId`      int                                      NULL,
    `departmentId` int           UNSIGNED                   NULL,
    `insertDate`   datetime                                 DEFAULT NOW(),
    `updateDate`   datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_department_department` FOREIGN KEY (`parentId`) REFERENCES `Department` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO Department (parentId, name, description, ownerId, departmentId)
VALUES (NULL, 'Dirección', 'Departamento de dirección.', NULL, NULL),
       (1, 'I+D+I', 'Departamento de I+D+I.', NULL, NULL),
       (1, 'Consultoría', 'Departamento de Consultoría.', NULL, NULL),
       (NULL, 'Indefinido', 'Departamento sin definir', 1, 1);


CREATE TABLE `Contact`
(
    `id`           int                   NOT NULL AUTO_INCREMENT,
    `name`         varchar(150)          NOT NULL,
    `email`        varchar(128),
    `phone`        varchar(15),
    `mobile`       varchar(15),
    `notified`     BOOLEAN               NOT NULL DEFAULT FALSE,
    `ownerId`      int                   NULL,
    `departmentId` int          UNSIGNED NULL,
    `insertDate`   datetime              DEFAULT NOW(),
    `updateDate`   datetime              DEFAULT NOW(),
    email2         varchar(128),
    phone2         varchar(15),
    fax            varchar(15),
    address        varchar(100),
    postalCode     varchar(5),
    city           varchar(100),
    country        varchar(100),
    provinceId     INTEGER,
    active         boolean               DEFAULT TRUE,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_contact_province` FOREIGN KEY (`provinceId`) REFERENCES Province (id)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Position
(
    id           int              NOT NULL AUTO_INCREMENT,
    name         varchar(256)     NOT NULL,
    description  varchar(1024)    NOT NULL,
    ownerId      INTEGER          NULL,
    departmentId INTEGER UNSIGNED NULL,
    deleteDate   datetime         DEFAULT NULL,
    email        varchar(128),
    phone        varchar(15),
    fax          varchar(15),
    address      varchar(100),
    postalCode   varchar(5),
    city         varchar(100),
    country      varchar(100),
    provinceId   INTEGER,
    `insertDate` datetime         DEFAULT NOW(),
    `updateDate` datetime         DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT `fk_position_province` FOREIGN KEY (`provinceId`) REFERENCES Province (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


INSERT INTO Position (name, description, ownerId, departmentId, insertDate, updateDate)
VALUES ('Indefinido', 'Puesto sin definir', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


CREATE TABLE Position_Department
(
    id           int          NOT NULL AUTO_INCREMENT,
    positionId   int          NOT NULL,
    departmentId int UNSIGNED NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_position_department_position FOREIGN KEY (positionId) REFERENCES Position (id),
    CONSTRAINT fk_position_department_department FOREIGN KEY (departmentId) REFERENCES Department (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO `Position_Department` (positionId, departmentId)
    (SELECT p.id, d.id
     FROM Position p,
          Department d
     WHERE p.name = 'Indefinido');


CREATE TABLE Department_Organization
(
    id             int          NOT NULL AUTO_INCREMENT,
    departmentId   int UNSIGNED NOT NULL,
    organizationId int          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_department_organization_department FOREIGN KEY (departmentId) REFERENCES Department (id),
    CONSTRAINT fk_department_organization_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO `Department_Organization` (departmentId, organizationId)
    (SELECT d.id AS department, o.id AS organization
     FROM Organization o,
          Department d
     WHERE d.name = 'Indefinida');


CREATE TABLE ContactInfo
(
    id             int          NOT NULL AUTO_INCREMENT,
    contactId      int          NOT NULL,
    positionId     int          NOT NULL,
    departmentId   int UNSIGNED NOT NULL,
    organizationId int          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_contactinfo_contact FOREIGN KEY (contactId) REFERENCES Contact (id),
    CONSTRAINT fk_contactinfo_position FOREIGN KEY (positionId) REFERENCES Position (id),
    CONSTRAINT fk_contactinfo_department FOREIGN KEY (departmentId) REFERENCES Department (id),
    CONSTRAINT fk_contactinfo_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Project`
(
    `id`             int                    NOT NULL AUTO_INCREMENT,
    `organizationId` int                    NOT NULL,
    `startDate`      date                   NOT NULL,
    `endDate`        date,
    `open`           boolean                DEFAULT FALSE,
    `name`           varchar(128)           NOT NULL,
    `description`    varchar(2048),
    `ownerId`        int                    NULL,
    `departmentId`   int           UNSIGNED NULL,
    `billable`       boolean                NOT NULL DEFAULT TRUE,
    offerId          INT                    NULL,
    `insertDate`     datetime               DEFAULT NOW(),
    `updateDate`     datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_project_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert generic projects
INSERT INTO Project(id, organizationId, startDate, name)
VALUES (1, 1, CURDATE(), 'Vacaciones'),
       (2, 1, CURDATE(), 'Permiso retribuido'),
       (3, 1, CURDATE(), 'Baja por enfermedad'),
       (4, 1, CURDATE(), 'Auto-formación'),
       (5, 1, CURDATE(), 'Histórico');


CREATE TABLE `ProjectRole`
(
    `id`            int                     NOT NULL AUTO_INCREMENT,
    `projectId`     int                     NOT NULL,
    `name`          varchar(128)            NOT NULL,
    `costPerHour`   decimal(10, 2)          NOT NULL,
    `expectedHours` int                     NOT NULL,
    requireEvidence BOOLEAN                 NOT NULL,
    `ownerId`       int                     NULL,
    `departmentId`  int            UNSIGNED NULL,
    `insertDate`    datetime                DEFAULT NOW(),
    `updateDate`    datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_projectRole_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Create one default role per old project
INSERT INTO ProjectRole(projectId, name, costPerHour, expectedHours, requireEvidence)
VALUES (1, 'vacaciones', 0, 0, FALSE),
       (2, 'permiso', 0, 0, TRUE),
       (3, 'baja', 0, 0, FALSE),
       (4, 'asistente', 0, 0, FALSE),
       (5, 'histórico', 0, 0, FALSE);


CREATE TABLE `ProjectCost`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `projectId`    int                     NOT NULL,
    `name`         varchar(128)            NOT NULL,
    `cost`         decimal(10, 2)          NOT NULL,
    `billable`     boolean                 NOT NULL DEFAULT TRUE,
    `ownerId`      int                     NULL,
    `departmentId` int            UNSIGNED NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_projectCost_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Role`
(
    `id`           int                  NOT NULL AUTO_INCREMENT,
    `name`         varchar(64)          NOT NULL,
    `ownerId`      int                  NULL,
    `departmentId` int         UNSIGNED NULL,
    `insertDate`   datetime             DEFAULT NOW(),
    `updateDate`   datetime             DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci COMMENT ='Security application roles';

-- Add default roles
INSERT INTO Role (name)
VALUES ('Administrador'),
       ('Supervisor'),
       ('Usuario'),
       ('Staff'),
       ('Cliente'),
       ('Project Manager');


CREATE TABLE `ContractType`
(
    `id`           int           UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)                             NOT NULL,
    `description`  varchar(2048) COLLATE utf8mb4_spanish_ci NOT NULL,
    `ownerId`      int                                      NULL,
    `departmentId` int           UNSIGNED                   NULL,
    `insertDate`   datetime                                 DEFAULT NOW(),
    `updateDate`   datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO ContractType (name, description)
VALUES ('Prácticas', 'Departamento de dirección.'),
       ('Duración determinada', 'Departamento de dirección.'),
       ('Indefinido', 'Departamento de dirección.');


CREATE TABLE `WorkingAgreement`
(
    `id`           int                  NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)         NOT NULL,
    `description`  varchar(2048),
    `holidays`     int                  NOT NULL DEFAULT 22,
    `ownerId`      int                  NULL,
    `departmentId` int         UNSIGNED NULL,
    yearDuration   integer              NOT NULL COMMENT 'In minutes',
    `insertDate`   datetime             DEFAULT NOW(),
    `updateDate`   datetime             DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO WorkingAgreement (name, description, holidays, yearDuration)
VALUES ('Convenio Nuestra Empresa', 'Este es el convenio de nuestra empresa', 22, 105900);


CREATE TABLE IF NOT EXISTS WorkingAgreementTerms
(
    id                 int  NOT NULL AUTO_INCREMENT,
    effectiveFrom      date NOT NULL,
    vacation           int  NOT NULL,
    annualWorkingTime  int  NOT NULL,
    workingAgreementId int  NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_effective_from_agreement_id UNIQUE (effectiveFrom, workingAgreementId),
    CONSTRAINT fk_WorkingAgreementTerms_WorkingAgreement FOREIGN KEY (workingAgreementId) REFERENCES WorkingAgreement (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

INSERT INTO WorkingAgreementTerms (effectiveFrom, vacation, annualWorkingTime, workingAgreementId)
VALUES ('1970-01-01', 22, 105900, (SELECT w.id FROM WorkingAgreement w LIMIT 0, 1)),
       ('2022-07-22', 23, 105900, (SELECT w.id FROM WorkingAgreement w LIMIT 0, 1));


CREATE TABLE `UserCategory`
(
    `id`           int                  NOT NULL AUTO_INCREMENT,
    `name`         varchar(64)          NOT NULL,
    `ownerId`      int                  NULL,
    `departmentId` int         UNSIGNED NULL,
    `insertDate`   datetime             DEFAULT NOW(),
    `updateDate`   datetime             DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Add generic user categories
INSERT INTO UserCategory(name)
VALUES ('Socio'),
       ('Becario'),
       ('Administrativo'),
       ('Gerente');


CREATE TABLE `User`
(
    `id`                    int                       NOT NULL AUTO_INCREMENT,

    -- Application data
    `login`                 varchar(50)               NOT NULL,
    `password`              varchar(50)               NOT NULL,
    `passwordExpireDate`    DATE,
    `roleId`                int                       NOT NULL,
    `active`                boolean                   NOT NULL DEFAULT TRUE,

    -- Personal data
    `name`                  varchar(200)              NOT NULL,
    `nif`                   varchar(16),
    `birthDate`             date,
    `academicQualification` varchar(200),

    `phone`                 varchar(12),
    `mobile`                varchar(12),

    `street`                varchar(100),
    `city`                  varchar(100),
    `postalCode`            varchar(5),
    `provinceId`            int,

    `married`               boolean                   NOT NULL COMMENT 'Married (1) or not (0)',
    `childrenNumber`        tinyint                   NOT NULL,
    `drivenLicenseType`     varchar(10),
    `vehicleType`           varchar(50),
    `licensePlate`          varchar(45),

    -- Company data
    `startDate`             date                      NOT NULL,
    `categoryId`            int                       NOT NULL,
    `socialSecurityNumber`  varchar(45),
    `bank`                  varchar(100),
    `account`               varchar(34),
    `travelAvailability`    varchar(128),
    `workingInClient`       boolean                   NOT NULL,
    `email`                 varchar(128),
    `genre`                 VARCHAR(16),
    `salary`                DECIMAL(10, 2),
    `salaryExtras`          DECIMAL(10, 2),
    `documentCategoryId`    int UNSIGNED,
    `securityCard`          VARCHAR(64),
    `healthInsurance`       VARCHAR(64),

    `notes`                 VARCHAR(1024),
    `photo`                 varchar(255),
    `endTestPeriodDate`     date,
    `endContractDate`       date,
    `departmentId`          int             UNSIGNED  NOT NULL DEFAULT 1,
    `contractTypeId`        int             UNSIGNED,
    `contractObservations`  varchar(2048),
    `dayDuration`           integer                   NOT NULL COMMENT 'In minutes',
    `agreementId`           int                       NOT NULL,
    agreementYearDuration   integer,
    `insertDate`            DATETIME                  DEFAULT NOW(),
    `updateDate`            DATETIME                  DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_roleId` FOREIGN KEY (`roleId`) REFERENCES `Role` (`id`),
    CONSTRAINT `fk_user_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`),
    CONSTRAINT `fk_user_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `UserCategory` (`id`),
    CONSTRAINT `fk_user_documentCategoryId` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`),
    CONSTRAINT `fk_user_departmentId` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
    CONSTRAINT `fk_user_contractTypeId` FOREIGN KEY (`contractTypeId`) REFERENCES `ContractType` (`id`),
    CONSTRAINT `fk_user_agreementId` FOREIGN KEY (`agreementId`) REFERENCES `WorkingAgreement` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Create administrator user (login=admin, password=adminadmin)
INSERT INTO User(name, login, password, passwordExpireDate, roleId, categoryId, startDate, workingInClient, married,
                 childrenNumber,
                 dayDuration, agreementId)
VALUES ('Administrador', 'admin', 'dd94709528bb1c83d08f3088d4043f4742891f4f', NULL, 1, 1, CURDATE(), 0, 0, 0, 480, 1);


CREATE TABLE AnnualWorkSummary
(
    userId       INT           NOT NULL,
    year         INT           NOT NULL,
    targetHours  DECIMAL(7, 2) NOT NULL,
    workedHours  DECIMAL(7, 2) NOT NULL,

    `insertDate` datetime      DEFAULT NOW(),
    `updateDate` datetime      DEFAULT NOW(),

    PRIMARY KEY (userId, year),
    CONSTRAINT fk_annualworksummary_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO AnnualWorkSummary
SELECT u.id, 2021, 0, 0, SYSDATE(), SYSDATE()
FROM User u
WHERE u.active = 1;


CREATE TABLE AnnualWorkSummaryJob
(
    `id`     INT      NOT NULL AUTO_INCREMENT,
    started  DATETIME NOT NULL,
    finished DATETIME NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Activity`
(
    `id`           int            NOT NULL AUTO_INCREMENT,
    `userId`       int            NOT NULL,
    `startDate`    datetime       NOT NULL DEFAULT '2000-01-01 00:00:00',
    `duration`     int            NOT NULL COMMENT 'Duration in minutes',
    `billable`     BOOLEAN        NOT NULL DEFAULT TRUE,
    `roleId`       INTEGER,
    `description`  varchar(2048),
    `departmentId` int UNSIGNED   NULL,
    hasImage       BOOLEAN        NOT NULL,
    `insertDate`   datetime       DEFAULT NOW(),
    `updateDate`   datetime       DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_activity_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
    CONSTRAINT `fk_activity_roleId` FOREIGN KEY (`roleId`)
        REFERENCES `ProjectRole` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `AccountType`
(
    `id`           int                   NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)          NOT NULL COMMENT 'Account type descriptive name',
    `ownerId`      int                   NULL,
    `departmentId` int          UNSIGNED NULL,
    `insertDate`   datetime              DEFAULT NOW(),
    `updateDate`   datetime              DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Default account types
INSERT INTO AccountType(name)
VALUES ('Caja'),
       ('Cuenta corriente'),
       ('Cuenta de crédito'),
       ('Depósito');


CREATE TABLE `Account`
(
    `id`            int                    NOT NULL AUTO_INCREMENT,
    `name`          varchar(128)           NOT NULL COMMENT 'Account descriptive name',
    `number`        varchar(20)            NOT NULL,
    `accountTypeId` int                    NOT NULL,
    `description`   varchar(2048),
    `ownerId`       int                    NULL,
    `departmentId`  int           UNSIGNED NULL,
    `insertDate`    datetime               DEFAULT NOW(),
    `updateDate`    datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_account_accountTypeId` FOREIGN KEY (`accountTypeId`) REFERENCES `AccountType` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Default account for cash
INSERT INTO Account(id, name, number, accountTypeId) VALUES (1, 'Caja', '0000000000000000000', 1);


CREATE TABLE `AccountEntryGroup`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)            NOT NULL COMMENT 'Account entry group descriptive name',
    `description`  varchar(1024),
    `ownerId`      int                     NULL,
    `departmentId` int            UNSIGNED NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- The default values for AccountEntryGroup
INSERT INTO AccountEntryGroup(id, name, description)
VALUES (1, 'Ingreso', 'Ingresos en cuenta'),
       (2, 'Gasto', 'Gastos en cuenta'),
       (3, 'Traspaso', 'Traspasos'),
       (4, 'Arranque anual', 'Movimiento que representa al arranque anual');


CREATE TABLE `AccountEntryType`
(
    `id`                  int                     NOT NULL AUTO_INCREMENT,
    `accountEntryGroupId` int                     NOT NULL,
    `name`                varchar(256)            NOT NULL COMMENT 'Account entry type descriptive name',
    `observations`        varchar(1024),
    `accountEntryTypeId`  INTEGER,
    `ownerId`             int                     NULL,
    `departmentId`        int            UNSIGNED NULL,
    `customizableId`      int                     NULL,
    `insertDate`          datetime                DEFAULT NOW(),
    `updateDate`          datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_accountEntryType_accountEntryGroupId` FOREIGN KEY (`accountEntryGroupId`) REFERENCES `AccountEntryGroup` (`id`),
    CONSTRAINT `fk_accountentrytype_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert initial entry type
INSERT INTO `AccountEntryType` (`id`, `accountEntryGroupId`, `name`, observations)
VALUES (1, 4, 'Arranque inicial', 'Tipo de asiento que representa el arranque inicial de un año');


CREATE TABLE `AccountEntry`
(
    `id`                 int                     NOT NULL AUTO_INCREMENT,
    `accountId`          int                     NOT NULL COMMENT 'Account where the entry is charged',
    `accountEntryTypeId` int                     NOT NULL,
    `entryDate`          date                    NOT NULL,
    `entryAmountDate`    date                    NOT NULL COMMENT 'Account entry amount date (fecha valor)',
    `concept`            varchar(1024)           NOT NULL,
    `amount`             decimal(10, 2)          NOT NULL,
    `observations`       varchar(1024),
    `ownerId`            int                     NULL,
    `departmentId`       int            UNSIGNED NULL,
    `entryNumber`        varchar(16)             NULL,
    `docNumber`          varchar(50)             NULL,
    `insertDate`         datetime                DEFAULT NOW(),
    `updateDate`         datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_accountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
    CONSTRAINT `fk_accountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE IVAType
(
    id           int           NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos codigos fijos',
    iva          decimal(4, 2) DEFAULT 21.00,
    name         varchar(30)   DEFAULT 'IVA General',
    ownerId      int           DEFAULT NULL,
    departmentId int           DEFAULT NULL,
    insertDate   datetime      DEFAULT NULL,
    updateDate   datetime      DEFAULT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci COMMENT ='Tipos de IVA';

INSERT INTO IVAType VALUES (1, 21.00, 'IVA General', NULL, NULL, NULL, NULL);
INSERT INTO IVAType VALUES (2, 10.00, 'IVA Reducido', NULL, NULL, NULL, NULL);
INSERT INTO IVAType VALUES (3, 04.00, 'IVA Superreducido', NULL, NULL, NULL, NULL);
INSERT INTO IVAType VALUES (4, 00.00, 'Exento de IVA', NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- IVAReason
-- -----------------------------------------------------------------------------

CREATE TABLE IVAReason
(
    id           int                   NOT NULL,
    code         varchar(2)            NOT NULL,
    reason       varchar(70)           NOT NULL,
    exempt       bool                  NOT NULL,
    ownerId      int,
    departmentId int         UNSIGNED,
    `insertDate` datetime              DEFAULT NOW(),
    `updateDate` datetime              DEFAULT NOW(),

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO IVAReason (id, code, reason, exempt, ownerId, departmentId)
VALUES (1, 'E1', 'Exenta por el articulo 20', TRUE, NULL, NULL),
       (2, 'E2', 'Exenta por el articulo 21', TRUE, NULL, NULL),
       (3, 'E3', 'Exenta por el articulo 22', TRUE, NULL, NULL),
       (4, 'E4', 'Exenta por el articulo 23 y 24', TRUE, NULL, NULL),
       (5, 'E5', 'Exenta por el articulo 25', TRUE, NULL, NULL),
       (6, 'E5', 'Exenta por otros', TRUE, NULL, NULL),
       (7, 'S1', 'No exenta - Sin inversion sujeto pasivo', FALSE, NULL, NULL),
       (8, 'S2', 'No exenta - Con inversion sujeto pasivo', FALSE, NULL, NULL),
       (9, 'S3', 'No exenta - Con y sin inversion sujeto pasivo', FALSE, NULL, NULL);

CREATE TABLE BillCategory
(
    id           int          NOT NULL,
    code         varchar(2)   NOT NULL,
    name         varchar(100) NOT NULL,
    rectify      bool         NOT NULL COMMENT 'Use TRUE to bills that reftify another bill',
    ownerId      int,
    departmentId int,
    `insertDate` datetime     DEFAULT NOW(),
    `updateDate` datetime     DEFAULT NOW(),

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO BillCategory (id, code, name, rectify, ownerId, departmentId)
VALUES (1, 'F1', 'Factura', FALSE, NULL, NULL),
       (2, 'F2', 'Factura Simplificada (ticket)', FALSE, NULL, NULL),
       (3, 'F3', 'Factura emitida en sustitución de facturas simplificadas facturadas y declaradas', FALSE, NULL, NULL),
       (4, 'F4', 'Asiento resumen de facturas', FALSE, NULL, NULL),
       (5, 'R1', 'Error fundado en derecho y Art. 80 Uno Dos y Seis LIVA', TRUE, NULL, NULL),
       (6, 'R2', 'Factura Rectificativa (Art. 80.3)', TRUE, NULL, NULL),
       (7, 'R3', 'Factura Rectificativa (Art. 80.4)', TRUE, NULL, NULL),
       (8, 'R4', 'Factura Rectificativa (Resto)', TRUE, NULL, NULL),
       (9, 'R5', 'Factura Rectificativa en facturas simplificadas', TRUE, NULL, NULL);


CREATE TABLE RectifiedBillCategory
(
    id           int         NOT NULL,
    code         varchar(2)  NOT NULL,
    name         varchar(40) NOT NULL,
    ownerId      int,
    departmentId int,
    `insertDate` datetime    DEFAULT NOW(),
    `updateDate` datetime    DEFAULT NOW(),

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO RectifiedBillCategory (id, code, name, ownerId, departmentId)
VALUES (1, 'S', 'Por sustitución', NULL, NULL),
       (2, 'I', 'Por diferencias', NULL, NULL);


CREATE TABLE BillRegime
(
    id                 int                                     NOT NULL,
    code               varchar(2)                              NOT NULL,
    name               varchar(250)                            NOT NULL,
    associatedBillType varchar(16)  COLLATE utf8mb4_spanish_ci NOT NULL,
    ownerId            int,
    departmentId       int,
    `insertDate`       datetime                                DEFAULT NOW(),
    `updateDate`       datetime                                DEFAULT NOW(),

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO BillRegime (id, code, associatedBillType, name, ownerId, departmentId)
VALUES (1, '01', 'ISSUED', 'Operación de régimen común', NULL, NULL),
       (2, '02', 'ISSUED', 'Exportación', NULL, NULL),
       (3, '03', 'ISSUED',
        'Operaciones a las que se aplique el régimen especial de bienes usados, objetos de arte, antigüedades y objetos de colección (135-139 LIVA)',
        NULL, NULL),
       (4, '04', 'ISSUED', 'Régimen especial oro de inversión', NULL, NULL),
       (5, '05', 'ISSUED', 'Régimen especial agencias de viajes', NULL, NULL),
       (6, '06', 'ISSUED', 'Régimen especial grupo de entidades en IVA (Nivel Avanzado)', NULL, NULL),
       (7, '07', 'ISSUED', 'Régimen especial criterio de caja', NULL, NULL),
       (8, '08', 'ISSUED', 'Operaciones sujetas al IPSI / IGIC', NULL, NULL),
       (9, '09', 'ISSUED',
        'Facturación de las prestaciones de servicios de agencias de viaje que actúan como mediadoras en nombre y por cuenta ajena (D.A.4ª RD1619/2012)',
        NULL, NULL),
       (10, '10', 'ISSUED',
        'Cobros por cuenta de terceros de honorarios profesionales o de derechos derivados de la propiedad industrial, de autor, asociados...',
        NULL, NULL),
       (11, '11', 'ISSUED', 'Operaciones de arrendamiento de local de negocio sujetas a retención', NULL, NULL),
       (12, '12', 'ISSUED', 'Operaciones de arrendamiento de local de negocio no sujetos a retención', NULL, NULL),
       (13, '13', 'ISSUED', 'Operaciones de arrendamiento de local de negocio sujetas y no sujetas a retención', NULL,
        NULL),
       (14, '14', 'ISSUED',
        'Factura con IVA pendiente de devengo (certificaciones de obra cuyo destinatario sea una Administración Pública)',
        NULL, NULL),
       (15, '15', 'ISSUED', 'Factura con IVA pendiente de devengo - operaciones de tracto sucesivo', NULL, NULL),
       (16, '16', 'ISSUED', 'Primer semestre 2017', NULL, NULL),
       (17, '01', 'RECIEVED', 'Operación de régimen general', NULL, NULL),
       (18, '02', 'RECIEVED',
        'Operaciones por las que los empresarios satisfacen compensaciones en las adquisiciones a personas acogidas al Régimen especial de la agricultura, ganadería y pesca (REAGYP)',
        NULL, NULL),
       (19, '03', 'RECIEVED',
        'Operaciones a las que se aplique el régimen especial de bienes usados, objetos de arte, antigüedades y objetos de colección (REBU)',
        NULL, NULL),
       (20, '04', 'RECIEVED', 'Régimen especial de oro de inversión', NULL, NULL),
       (21, '05', 'RECIEVED', 'Régimen especial de agencias de viajes', NULL, NULL),
       (22, '06', 'RECIEVED', 'Régimen especial grupo de entidades en IVA (Nivel Avanzado)', NULL, NULL),
       (23, '07', 'RECIEVED', 'Régimen especial del criterio de caja', NULL, NULL),
       (24, '08', 'RECIEVED',
        'Operaciones sujetas al IPSI/IGIC (Impuesto sobre la Producción, los servicios y la Importación / Impuesto General Indirecto Canario',
        NULL, NULL),
       (25, '09', 'RECIEVED', 'Adquisiciones intracomunitarias de bienes y prestación de servicios', NULL, NULL),
       (26, '12', 'RECIEVED', 'Operaciones de arrendamiento de local de negocio', NULL, NULL),
       (27, '13', 'RECIEVED', 'Factura correspondiente a una importación (informada sin asociar a un DUA)', NULL, NULL),
       (28, '14', 'RECIEVED', 'Primer semestre de 2017 y otras factruras anteriores a la inclusión en el SII', NULL,
        NULL);


CREATE TABLE `Bill`
(
    `id`                    int                     NOT NULL AUTO_INCREMENT,
    `creationDate`          date                    NOT NULL,
    `paymentMode`           varchar(16),
    `state`                 varchar(16)             NOT NULL,
    `number`                VARCHAR(64)             NOT NULL,
    `name`                  VARCHAR(4096)           NOT NULL,
    `file`                  VARCHAR(512),
    `fileMime`              VARCHAR(64),
    `observations`          VARCHAR(4096),
    `projectId`             INT                     NOT NULL DEFAULT 5,
    `startBillDate`         DATE                    NOT NULL DEFAULT '1980-01-01',
    `endBillDate`           DATE                    NOT NULL DEFAULT '1980-01-01',
    `billType`              VARCHAR(16)             NOT NULL DEFAULT 'ISSUED',
    `orderNumber`           VARCHAR(64),
    `bookNumber`            VARCHAR(64),
    `contactId`             INT,
    `providerId`            INT,
    `ownerId`               INT                     NULL,
    `departmentId`          INT            UNSIGNED NULL,
    `accountId`             INTEGER,
    submitted               INT                     NOT NULL,
    billCategoryId          INT                     NOT NULL,
    rectifiedBillCategoryId INT,
    provideService          bool                    NOT NULL,
    billRegimeId            INT                     NOT NULL,
    deductibleIVAPercentage TINYINT                 NOT NULL,
    freelanceIRPFPercentage INT                     NOT NULL,
    `insertDate`            DATETIME                DEFAULT NOW(),
    `updateDate`            DATETIME                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_bill_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
    CONSTRAINT `fk_bill_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
    CONSTRAINT `fk_bill_providerId` FOREIGN KEY (`providerId`) REFERENCES `Organization` (`id`),
    CONSTRAINT `fk_bill_accountId` FOREIGN KEY (accountId) REFERENCES `Account` (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT `fk_bill_billCategory` FOREIGN KEY (billCategoryId) REFERENCES BillCategory (id),
    CONSTRAINT `fk_bill_rectifiedBillCategory` FOREIGN KEY (rectifiedBillCategoryId) REFERENCES RectifiedBillCategory (id),
    CONSTRAINT `fk_bill_billRegime` FOREIGN KEY (billRegimeId) REFERENCES BillRegime (id)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Bill_AccountEntry`
(
    `billId`         int NOT NULL,
    `accountEntryId` int NOT NULL,
    `observations`   varchar(2048),

    PRIMARY KEY (`billId`, `accountEntryId`),
    CONSTRAINT `fk_billAccountEntry_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
    CONSTRAINT `fk_billAccountEntry_accountEntryId` FOREIGN KEY (`accountEntryId`) REFERENCES `AccountEntry` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE IF NOT EXISTS `BillBreakDown`
(
    `id`               int            UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `billId`           int                                       NOT NULL,
    `concept`          varchar(4096)  COLLATE utf8mb4_spanish_ci NOT NULL,
    `units`            decimal(10, 2)                            NOT NULL DEFAULT 1,
    `amount`           decimal(10, 2)                            NOT NULL,
    `iva`              decimal(4, 2)                             NOT NULL DEFAULT 16,
    `ownerId`          int                                       NULL,
    `departmentId`     int            UNSIGNED                   NULL,
    `place`            INTEGER                                   DEFAULT NULL,
    IVAReasonId        INT,
    IVAReasonIdOnlySii INT,
    ivaOnlySII         decimal(4, 2),
    `insertDate`       datetime                                  DEFAULT NOW(),
    `updateDate`       datetime                                  DEFAULT NOW(),

    PRIMARY KEY (`id`),
    INDEX `ndx_billBreakDown_bill` (`billId`),
    CONSTRAINT `fk_billBreakDown_bill` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
    CONSTRAINT `fk_billBreakDown_ivaReason` FOREIGN KEY (IVAReasonId) REFERENCES IVAReason (id),
    CONSTRAINT `fk_billBreakDown_ivaReasonOnlySii` FOREIGN KEY (IVAReasonIdOnlySii) REFERENCES IVAReason (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `BillPayment`
(
    `id`             INTEGER                 NOT NULL AUTO_INCREMENT,
    `billId`         INTEGER                 NOT NULL,
    `amount`         DECIMAL(10, 2)          NOT NULL DEFAULT 0,
    `expirationDate` DATE                    NOT NULL,
    `ownerId`        INTEGER                 NULL,
    `departmentId`   INTEGER        UNSIGNED NULL,
    `insertDate`     datetime                DEFAULT NOW(),
    `updateDate`     datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_bill_billPayment` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `BulletinBoardCategory`
(
    `id`           int                   NOT NULL AUTO_INCREMENT,
    `name`         varchar(64)           NOT NULL,
    `ownerId`      int                   NULL,
    `departmentId` int          UNSIGNED NULL,
    `insertDate`   datetime              DEFAULT NOW(),
    `updateDate`   datetime              DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Add default bulletin board categories
INSERT INTO BulletinBoardCategory (name)
VALUES ('Pública'),
       ('General');


CREATE TABLE `CompanyState`
(
    `id`           int               NOT NULL AUTO_INCREMENT,
    `userId`       int               NOT NULL,
    `creationDate` datetime          NOT NULL,
    `description`  longtext          NOT NULL,
    `departmentId` int      UNSIGNED NULL,
    `insertDate`   datetime          DEFAULT NOW(),
    `updateDate`   datetime          DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_companystate_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Idea`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `userId`       int                     NOT NULL,
    `creationDate` datetime                NOT NULL,
    `description`  varchar(2048)           NOT NULL,
    `cost`         varchar(500),
    `benefits`     varchar(2048),
    `name`         varchar(300)            NOT NULL,
    `departmentId` int            UNSIGNED NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_idea_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Inventory`
(
    `id`           int                    NOT NULL AUTO_INCREMENT,
    `buyDate`      date,
    `asignedToId`  int,
    `renting`      boolean                NOT NULL DEFAULT FALSE COMMENT 'Renting (1) or not (0)',
    `cost`         decimal(10, 2),
    `amortizable`  boolean                NOT NULL DEFAULT FALSE COMMENT 'Amortizable (1) or not (0)consumible',
    `serialNumber` varchar(30)            NOT NULL,
    `type`         varchar(16)            NOT NULL,
    `provider`     varchar(128),
    `trademark`    varchar(128),
    `model`        varchar(128),
    `speed`        varchar(10),
    `storage`      varchar(10),
    `ram`          varchar(10),
    `location`     varchar(128),
    `description`  varchar(256),
    `ownerId`      int                    NULL,
    `departmentId` int           UNSIGNED NULL,
    `insertDate`   datetime               DEFAULT NOW(),
    `updateDate`   datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_inventory_userId` FOREIGN KEY (`asignedToId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Objective`
(
    `id`           int                  NOT NULL AUTO_INCREMENT,
    `userId`       int                  NOT NULL,
    `projectId`    int                  NOT NULL,
    `startDate`    date                 NOT NULL,
    `endDate`      date,
    `state`        varchar(16),
    `name`         varchar(512)         NOT NULL,
    `log`          longtext,
    `departmentId` int         UNSIGNED NULL,
    `insertDate`   datetime             DEFAULT NOW(),
    `updateDate`   datetime             DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_objective_projectId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
    CONSTRAINT `fk_objective_userId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Magazine`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)            NOT NULL,
    `description`  varchar(2048),
    `ownerId`      int                     NULL,
    `departmentId` int            UNSIGNED NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Tutorial`
(
    `id`              int                     NOT NULL AUTO_INCREMENT,
    `userId`          int                     NOT NULL,
    `maxDeliveryDate` datetime                NOT NULL,
    `endDate`         datetime                DEFAULT NULL,
    `name`            varchar(128)            NOT NULL,
    `description`     varchar(2048),
    `departmentId`    int            UNSIGNED NULL,
    `insertDate`      datetime                DEFAULT NOW(),
    `updateDate`      datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_tutorial_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Publication`
(
    `id`                      int                   NOT NULL AUTO_INCREMENT,
    `name`                    varchar(128)          NOT NULL,
    `magazineId`              int                   NOT NULL,
    `magazineDeliveryDate`    datetime              DEFAULT NULL,
    `magazinePublicationDate` date,
    `ownPublicationDate`      date,
    `accepted`                boolean               COMMENT 'Accepted (1) or not (0)',
    `ownerId`                 int                   NULL,
    `departmentId`            int          UNSIGNED NULL,
    `insertDate`              datetime              DEFAULT NOW(),
    `updateDate`              datetime              DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_publication_magazineId` FOREIGN KEY (`magazineId`) REFERENCES `Magazine` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `BulletinBoard`
(
    `id`                  int                    NOT NULL AUTO_INCREMENT,
    `categoryId`          int                    NOT NULL,
    `userId`              int                    NOT NULL,
    `creationDate`        datetime               NOT NULL,
    `message`             varchar(2048)          NOT NULL,
    `title`               varchar(128)           NOT NULL,
    `documentPath`        varchar(128),
    `documentContentType` varchar(128),
    `departmentId`        int           UNSIGNED NULL,
    `insertDate`          datetime               DEFAULT NOW(),
    `updateDate`          datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_bulletinboard_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `BulletinBoardCategory` (`id`),
    CONSTRAINT `fk_bulletinboard_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Book`
(
    `id`           int            UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `name`         varchar(255)   COLLATE utf8mb4_spanish_ci NOT NULL,
    `author`       varchar(255)   COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `ISBN`         varchar(13)    COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `URL`          varchar(255)   COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `price`        decimal(10, 2)                            DEFAULT NULL,
    `purchaseDate` datetime                                  DEFAULT NULL,
    `userId`       int                                       DEFAULT NULL,
    `ownerId`      int                                       NULL,
    `departmentId` int            UNSIGNED                   NULL,
    `insertDate`   datetime                                  DEFAULT NOW(),
    `updateDate`   datetime                                  DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_Book_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Frequency`
(
    `id`           int                                     NOT NULL AUTO_INCREMENT,
    `name`         varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
    `months`       INTEGER      UNSIGNED,
    `ownerId`      int                                     NULL,
    `departmentId` int          UNSIGNED                   NULL,
    `insertDate`   datetime                                DEFAULT NOW(),
    `updateDate`   datetime                                DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert default frequencies
INSERT INTO Frequency (name, months)
VALUES ('Mensual', 1),
       ('Trimestral', 3),
       ('Semestral', 6),
       ('Anual', 12),
       ('Bimensual', 2),
       ('Bianual', 24),
       ('Ocasional', 0);


CREATE TABLE `PeriodicalAccountEntry`
(
    `id`                 int                     NOT NULL AUTO_INCREMENT,
    `accountId`          int                     NOT NULL COMMENT 'Account where the entry is charged',
    `accountEntryTypeId` int                     NOT NULL,
    `frequencyId`        int                     NOT NULL,
    `concept`            varchar(1024)           NOT NULL,
    `entryDate`          date                    NOT NULL,
    `amount`             decimal(10, 2)          NOT NULL,
    `rise`               decimal(4, 2),
    `observations`       varchar(1024),
    `ownerId`            int                     NULL,
    `departmentId`       int            UNSIGNED NULL,
    `organizationId`     INTEGER                 DEFAULT NULL,
    `insertDate`         datetime                DEFAULT NOW(),
    `updateDate`         datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_periodicalAccountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
    CONSTRAINT `fk_periodicalAccountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
    CONSTRAINT `fk_periodicalAccountEntry_frequencyId` FOREIGN KEY (`frequencyId`) REFERENCES `Frequency` (`id`),
    CONSTRAINT `fk_periodicalaccountentry_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Holiday`
(
    `id`           int           UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `description`  varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    `date`         datetime                                 NOT NULL,
    `ownerId`      int                                      NULL,
    `departmentId` int           UNSIGNED                   NULL,
    `insertDate`   datetime                                 DEFAULT NOW(),
    `updateDate`   datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `RequestHoliday`
(
    `id`           int           UNSIGNED                   NOT NULL AUTO_INCREMENT,
    `beginDate`    datetime                                 NOT NULL,
    `finalDate`    datetime                                 NOT NULL,
    `state`        varchar(16)   COLLATE utf8mb4_spanish_ci NOT NULL,
    `userId`       int                                      NOT NULL,
    `observations` varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `departmentId` int           UNSIGNED                   NULL,
    `userComment`  varchar(1024)                            NULL,
    `chargeYear`   date                                     NOT NULL,
    `insertDate`   datetime                                 DEFAULT NOW(),
    `updateDate`   datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `OfferRejectReason`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `title`        varchar(128)            NOT NULL,
    `description`  varchar(1024),
    `ownerId`      int                     NULL,
    `departmentId` int            UNSIGNED NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

INSERT INTO OfferRejectReason (id, title, description, ownerId, departmentId)
VALUES (1, 'Sin respuesta', 'El cliente no responde a la oferta', 1, 1),
       (2, 'Oferta cara', 'El cliente considera la oferta excesivamente cara', 1, 1),
       (3, 'Tecnología inadecuada', 'Tecnología escogida en la oferta inadecuada', 1, 1),
       (4, 'Proyecto retrasado', 'El proyecto para el cual se hizo la oferta ha sido retrasado', 1, 1),
       (5, 'Proyecto cancelado', 'El proyecto para el cual se hizo la oferta ha sido cancelado', 1, 1);


CREATE TABLE `Offer`
(
    `id`                  int                    NOT NULL AUTO_INCREMENT,
    `number`              varchar(64)            NOT NULL,
    `title`               varchar(128)           NOT NULL,
    `description`         varchar(4096),
    `userId`              int                    NOT NULL,
    `organizationId`      int                    NOT NULL,
    `contactId`           int                    NOT NULL,
    `creationDate`        date                   NOT NULL,
    `validityDate`        date                   NULL,
    `maturityDate`        date                   NULL,
    `offerPotential`      varchar(16)            NOT NULL,
    `offerState`          varchar(16)            NOT NULL,
    `offerRejectReasonId` int                    NULL,
    `ownerId`             int                    NULL,
    `departmentId`        int           UNSIGNED NULL,
    `observations`        varchar(4096)          NULL,
    `showIvaIntoReport`   boolean                NOT NULL DEFAULT TRUE,
    `insertDate`          datetime               DEFAULT NOW(),
    `updateDate`          datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    INDEX ndx_offer_number (`number` ASC),
    CONSTRAINT `fk_offer_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
    CONSTRAINT `fk_offer_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
    CONSTRAINT `fk_offer_offerRejectReasonId` FOREIGN KEY (`offerRejectReasonId`) REFERENCES `OfferRejectReason` (`id`),
    CONSTRAINT `fk_offer_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `OfferRole`
(
    `id`            int                     NOT NULL AUTO_INCREMENT,
    `offerId`       int                     NOT NULL,
    `name`          varchar(4096)           NOT NULL,
    `costPerHour`   decimal(10, 2)          NOT NULL,
    `expectedHours` int                     NOT NULL,
    `iva`           decimal(4, 2)           NOT NULL DEFAULT 16,
    `ownerId`       int                     NULL,
    `departmentId`  int            UNSIGNED NULL,
    `place`         INTEGER                 DEFAULT NULL,
    `insertDate`    datetime                DEFAULT NOW(),
    `updateDate`    datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_offerRole_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `OfferCost`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `offerId`      int                     NOT NULL,
    `name`         varchar(4096)           NOT NULL,
    `cost`         decimal(10, 2)          NOT NULL,
    `billable`     boolean                 NOT NULL DEFAULT TRUE,
    `iva`          decimal(4, 2)           NOT NULL DEFAULT 16,
    `ownerId`      int                     NULL,
    `departmentId` int            UNSIGNED NULL,
    `units`        decimal(10, 2)          NOT NULL,
    `place`        INTEGER                 DEFAULT NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_offerCost_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `FinancialRatio`
(
    `id`                        int                     NOT NULL AUTO_INCREMENT,
    `title`                     varchar(128)            NOT NULL,
    `ratioDate`                 date                    NOT NULL,
    `banksAccounts`             decimal(10, 2)          NOT NULL,
    `customers`                 decimal(10, 2)          NOT NULL,
    `stocks`                    decimal(10, 2)          NOT NULL,
    `amortizations`             decimal(10, 2)          NOT NULL,
    `infrastructures`           decimal(10, 2)          NOT NULL,
    `shortTermLiability`        decimal(10, 2)          NOT NULL,
    `obligationBond`            decimal(10, 2)          NOT NULL,
    `capital`                   decimal(10, 2)          NOT NULL,
    `reserves`                  decimal(10, 2)          NOT NULL,
    `incomes`                   decimal(10, 2)          NOT NULL,
    `expenses`                  decimal(10, 2)          NOT NULL,
    `otherExploitationExpenses` decimal(10, 2)          NOT NULL,
    `financialExpenses`         decimal(10, 2)          NOT NULL,
    `taxes`                     decimal(10, 2)          NOT NULL,
    `ownerId`                   int                     NULL,
    `departmentId`              int            UNSIGNED NULL,
    `insertDate`                datetime                DEFAULT NOW(),
    `updateDate`                datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `InteractionType`
(
    `id`           int                    NOT NULL AUTO_INCREMENT,
    `name`         varchar(128)           NOT NULL COMMENT 'Interaction type descriptive name',
    `description`  varchar(1024)                   COMMENT 'Interaction type description',
    `ownerId`      int                    NULL,
    `departmentId` int           UNSIGNED NULL,
    `insertDate`   datetime               DEFAULT NOW(),
    `updateDate`   datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;

-- Insert default interaction types
INSERT INTO InteractionType (name, description)
VALUES ('No conformidad', ''),
       ('Accion comercial', ''),
       ('Envío de oferta', ''),
       ('Envío de factura', ''),
       ('Accion administrativa', ''),
       ('No definida', ''),
       ('Primer contacto', ''),
       ('Confirmación de oferta', '');


CREATE TABLE `Interaction`
(
    `id`                int                    NOT NULL AUTO_INCREMENT,
    `projectId`         int                    NOT NULL DEFAULT 5,
    `userId`            int                    NOT NULL DEFAULT 1,
    `interactionTypeId` int                    NOT NULL DEFAULT 6,
    `creationDate`      datetime               NOT NULL,
    `interest`          varchar(16)            NOT NULL,
    `description`       varchar(2048)          NOT NULL,
    `file`              varchar(400),
    `fileMime`          varchar(128),
    `departmentId`      int           UNSIGNED NULL,
    `offerId`           int                    NULL,
    `insertDate`        datetime               DEFAULT NOW(),
    `updateDate`        datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_interaction_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
    CONSTRAINT `fk_interaction_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
    CONSTRAINT `fk_interaction_interactionTypeId` FOREIGN KEY (`interactionTypeId`) REFERENCES `InteractionType` (`id`),
    CONSTRAINT `fk_interaction_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `Setting`
(
    `id`           int                                      NOT NULL AUTO_INCREMENT,
    `type`         varchar(64)   COLLATE utf8mb4_spanish_ci NOT NULL,
    `name`         varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    `value`        varchar(4096) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    `ownerId`      int                                      NULL,
    `departmentId` int           UNSIGNED                   NULL,
    `insertDate`   datetime                                 DEFAULT NOW(),
    `updateDate`   datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci COMMENT ='User settings';


CREATE TABLE `Occupation`
(
    `id`           int                     NOT NULL AUTO_INCREMENT,
    `projectId`    int                     NOT NULL,
    `userId`       int                     NOT NULL,
    `startDate`    date                    NOT NULL,
    `endDate`      date                    NOT NULL,
    `description`  varchar(1024),
    `duration`     int                     NOT NULL COMMENT 'In minutes',
    `ownerId`      int                     NULL,
    `departmentId` int            UNSIGNED NULL,
    `insertDate`   datetime                DEFAULT NOW(),
    `updateDate`   datetime                DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_occupation_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
    CONSTRAINT `fk_occupation_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci COMMENT ='Future occupations of Users';


CREATE TABLE `CreditTitle`
(
    `id`             int                    NOT NULL AUTO_INCREMENT,
    `number`         varchar(64)            NOT NULL,
    `concept`        varchar(1024),
    `amount`         decimal(10, 2)         NOT NULL,
    `state`          varchar(16),
    `type`           varchar(16),
    `issueDate`      date                   NOT NULL,
    `expirationDate` date,
    `organizationId` int                    NOT NULL,
    `observations`   varchar(1024),
    `ownerId`        int                    NULL,
    `departmentId`   int           UNSIGNED NULL,
    `insertDate`     datetime               DEFAULT NOW(),
    `updateDate`     datetime               DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_credittitle_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `CreditTitle_Bill`
(
    `billId`        int NOT NULL,
    `creditTitleId` int NOT NULL,

    PRIMARY KEY (`billId`, `creditTitleId`),
    CONSTRAINT `fk_creditTitle_Bill_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
    CONSTRAINT `fk_creditTitle_Bill_creditTitleId` FOREIGN KEY (`creditTitleId`) REFERENCES `CreditTitle` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Collaborator
(
    id             INTEGER               NOT NULL AUTO_INCREMENT,
    userId         INTEGER,
    contactId      INTEGER,
    organizationId INTEGER,
    ownerId        INTEGER               NULL,
    departmentId   INTEGER      UNSIGNED NULL,
    `insertDate`   datetime              DEFAULT NOW(),
    `updateDate`   datetime              DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_collaborator_user FOREIGN KEY (userId) REFERENCES User (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT fk_collaborator_contact FOREIGN KEY (contactId) REFERENCES Contact (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT fk_collaborator_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Commissioning
(
    id                   int                                      NOT NULL AUTO_INCREMENT,
    requestDate          datetime                                 NOT NULL,
    name                 varchar(512)  COLLATE utf8mb4_spanish_ci NOT NULL,
    scope                varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    content              varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    products             varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    deliveryDate         datetime                                 NOT NULL,
    budget               decimal(10, 2)                           DEFAULT NULL,
    notes                varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    authorSignature      boolean                                  NOT NULL DEFAULT FALSE,
    reviserSignature     boolean                                  NOT NULL DEFAULT FALSE,
    adminSignature       boolean                                  NOT NULL DEFAULT FALSE,
    justifyInformation   boolean                                  NOT NULL DEFAULT FALSE,
    developedActivities  varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    difficultiesAppeared varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    results              varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    conclusions          varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    evaluation           varchar(1024) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    status               varchar(20)   COLLATE utf8mb4_spanish_ci DEFAULT NULL,
    projectId            int                                      DEFAULT NULL,
    `deleteDate`         datetime                                 DEFAULT NULL,
    `insertDate`         datetime                                 DEFAULT NOW(),
    `updateDate`         datetime                                 DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_commissioning_project FOREIGN KEY (projectId) REFERENCES Project (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE CommissioningDelay
(
    id              int                                      NOT NULL AUTO_INCREMENT,
    reason          varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    originalDate    datetime                                 NOT NULL,
    delayedToDate   datetime                                 NOT NULL,
    commissioningId int                                      DEFAULT NULL,
    `status`        varchar(20)                              DEFAULT NULL,
    `insertDate`    datetime                                 DEFAULT NOW(),
    `updateDate`    datetime                                 DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_commissioningDelay_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE CommissioningPaymentData
(
    id              int          NOT NULL AUTO_INCREMENT,
    commissioningId int          NOT NULL,
    collaboratorId  INTEGER      NOT NULL,
    paymentMode     varchar(32),
    bankAccount     varchar(50),
    billNumber      varchar(50),
    insertDate      datetime,
    updateDate      datetime,

    PRIMARY KEY (id),
    CONSTRAINT fk_commissioning_collaborator_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
    CONSTRAINT fk_commissioning_collaborator_collaborator FOREIGN KEY (collaboratorId) REFERENCES Collaborator (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Commissioning_User
(
    id              int NOT NULL AUTO_INCREMENT,
    commissioningId int NOT NULL,
    userId          int NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_commissioning_user_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
    CONSTRAINT fk_commissioning_user_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE CommissioningChange
(
    id              int           NOT NULL AUTO_INCREMENT,
    field           varchar(1024) NOT NULL,
    oldValue        varchar(1024) NOT NULL,
    newValue        varchar(1024) NOT NULL,
    commissioningId int           DEFAULT NULL,
    userId          int           DEFAULT NULL,
    status          varchar(20)   DEFAULT NULL,
    deleteDate      datetime      DEFAULT NULL,
    `insertDate`    datetime      DEFAULT NOW(),
    `updateDate`    datetime      DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_commissioningChange_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
    CONSTRAINT fk_commissioningChange_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE CommissioningFile
(
    id              int          NOT NULL AUTO_INCREMENT,
    commissioningId int          NOT NULL,
    userId          int          NOT NULL,
    file            varchar(400) NOT NULL,
    fileMime        varchar(128) DEFAULT NULL,
    `insertDate`    datetime     DEFAULT NOW(),
    `updateDate`    datetime     DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_commissioningFile_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
    CONSTRAINT fk_commissioningFile_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE ExternalActivity
(
    id                 INTEGER                  NOT NULL AUTO_INCREMENT,
    name               varchar(256),
    category           varchar(256),
    startDate          DATETIME                 NOT NULL,
    endDate            DATETIME                 NOT NULL,
    location           varchar(256),
    organizer          varchar(256),
    comments           varchar(2048),
    documentCategoryId INTEGER        UNSIGNED,
    ownerId            INTEGER,
    departmentId       INTEGER,
    `insertDate`       datetime                 DEFAULT NOW(),
    `updateDate`       datetime                 DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_externalactivity_documentcategory FOREIGN KEY (documentCategoryId) REFERENCES DocumentCategory (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE ActivityFile
(
    id                 int          NOT NULL AUTO_INCREMENT,
    externalActivityId int          NOT NULL,
    userId             int          NOT NULL,
    file               varchar(400) NOT NULL,
    fileMime           varchar(128) DEFAULT NULL,
    `insertDate`       datetime     DEFAULT NOW(),
    `updateDate`       datetime     DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_activityFile_externalActivity FOREIGN KEY (externalActivityId) REFERENCES ExternalActivity (id),
    CONSTRAINT fk_activityFile_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Tag
(
    id           int                    NOT NULL AUTO_INCREMENT,
    name         varchar(1024)          NOT NULL,
    description  varchar(1024)          NOT NULL,
    ownerId      INTEGER                NULL,
    departmentId INTEGER       UNSIGNED NULL,
    `insertDate` datetime               DEFAULT NOW(),
    `updateDate` datetime               DEFAULT NOW(),

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Contact_Tag
(
    id        int     NOT NULL AUTO_INCREMENT,
    tagId     int     NOT NULL,
    contactId integer NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_contact_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
    CONSTRAINT fk_contact_tag_contact FOREIGN KEY (contactId) REFERENCES Contact (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Position_Tag
(
    id         int     NOT NULL AUTO_INCREMENT,
    tagId      int     NOT NULL,
    positionId integer NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_position_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
    CONSTRAINT fk_position_tag_position FOREIGN KEY (positionId) REFERENCES Position (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Department_Tag
(
    id           int              NOT NULL AUTO_INCREMENT,
    tagId        int              NOT NULL,
    departmentId integer UNSIGNED NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_department_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
    CONSTRAINT fk_department_tag_department FOREIGN KEY (departmentId) REFERENCES Department (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE Organization_Tag
(
    id             int     NOT NULL AUTO_INCREMENT,
    tagId          int     NOT NULL,
    organizationId integer NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_organization_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
    CONSTRAINT fk_organization_tag_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE PositionChange
(
    id           int           NOT NULL AUTO_INCREMENT,
    field        varchar(1024) NOT NULL,
    oldValue     varchar(1024) NOT NULL,
    newValue     varchar(1024) NOT NULL,
    positionId   int           DEFAULT NULL,
    userId       int           DEFAULT NULL,
    `insertDate` datetime      DEFAULT NOW(),
    `updateDate` datetime      DEFAULT NOW(),

    PRIMARY KEY (id),
    CONSTRAINT fk_positionChange_position FOREIGN KEY (positionId) REFERENCES Position (id),
    CONSTRAINT fk_positionChange_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `ContactOwner`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `contactId` int NOT NULL,
    `userId`    int NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_contactowner_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
    CONSTRAINT `fk_contactowner_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_spanish_ci;


CREATE TABLE `EntityChange`
(
    `id`         int                                      NOT NULL AUTO_INCREMENT,
    `field`      varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    `oldValue`   varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    `newValue`   varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    `userId`     int                                      DEFAULT NULL,
    `entityId`   int                                      NOT NULL,
    `entityName` varchar(1024) COLLATE utf8mb4_spanish_ci NOT NULL,
    `insertDate` datetime                                 DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_entityChange_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
);


CREATE TABLE Link
(
    id           int           PRIMARY KEY AUTO_INCREMENT,
    user         varchar(128),
    link         varchar(128),
    `insertDate` datetime      DEFAULT NOW()
);
