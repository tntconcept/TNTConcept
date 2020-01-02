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
-- IVAType
-- -----------------------------------------------------------------------------

CREATE TABLE IVAType (
    id int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos codigos fijos',
    iva decimal(4,2) default 21.00,
    name varchar(30) default 'IVA General',
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tipos de IVA';

INSERT INTO IVAType VALUES (1, 21.00, 'IVA General', NULL, NULL, NULL, NULL);
INSERT INTO IVAType VALUES (2, 10.00, 'IVA Reducido', NULL, NULL, NULL, NULL);
INSERT INTO IVAType VALUES (3, 04.00, 'IVA Superreducido', NULL, NULL, NULL, NULL);
INSERT INTO IVAType VALUES (4, 00.00, 'Exento de IVA', NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- IVAReason
-- -----------------------------------------------------------------------------

CREATE TABLE IVAReason (
    id int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos codigos fijos',
    code varchar(2) NOT NULL,
    reason varchar(70) NOT NULL,
    exempt bool NOT NULL,
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) unsigned DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tipos de IVA';

INSERT INTO IVAReason VALUES (1, 'E1', 'Exenta por el articulo 20', true, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (2, 'E2', 'Exenta por el articulo 21', true, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (3, 'E3', 'Exenta por el articulo 22', true, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (4, 'E4', 'Exenta por el articulo 23 y 24', true, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (5, 'E5', 'Exenta por el articulo 25', true, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (6, 'E5', 'Exenta por otros', true, NULL, NULL, NULL, NULL);

INSERT INTO IVAReason VALUES (7, 'S1', 'No exenta - Sin inversion sujeto pasivo', false, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (8, 'S2', 'No exenta - Con inversion sujeto pasivo', false, NULL, NULL, NULL, NULL);
INSERT INTO IVAReason VALUES (9, 'S3', 'No exenta - Sin inversion sujeto pasivo y con inversion sujeto pasivo', false, NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- BillBreakDown
-- -----------------------------------------------------------------------------

ALTER TABLE BillBreakDown ADD COLUMN IVAReasonId INT(11);

UPDATE BillBreakDown as bbd SET bbd.IVAReasonId = 6 WHERE bbd.iva = 0;
UPDATE BillBreakDown as bbd SET bbd.IVAReasonId = 7 WHERE bbd.iva != 0;

ALTER TABLE BillBreakDown ADD CONSTRAINT IVAReason_FK FOREIGN KEY (IVAReasonId) REFERENCES IVAReason(id);

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
update Version set version='0.36';