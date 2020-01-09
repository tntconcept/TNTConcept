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
-- BillCategory
-- -----------------------------------------------------------------------------

CREATE TABLE BillCategory (
    id int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos codigos fijos',
    code varchar(2) NOT NULL,
    name varchar(100) NOT NULL,
    rectify bool NOT NULL COMMENT 'TRUE para facturas rectificativas',
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tipos de Factura';

INSERT INTO BillCategory VALUES (1, 'F1', 'Factura', false, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (2, 'F2', 'Factura Simplificada (ticket)', false, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (3, 'F3', 'Factura emitida en sustitución de facturas simplificadas facturadas y declaradas', false, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (4, 'F4', 'Asiento resumen de facturas', false, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (5, 'R1', 'Error fundado en derecho y Art. 80 Uno Dos y Seis LIVA', true, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (6, 'R2', 'Factura Rectificativa (Art. 80.3)', true, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (7, 'R3', 'Factura Rectificativa (Art. 80.4)', true, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (8, 'R4', 'Factura Rectificativa (Resto)', true, NULL, NULL, NULL, NULL);

INSERT INTO BillCategory VALUES (9, 'R5', 'Factura Rectificativa en facturas simplificadas', true, NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- RectifiedBillCategory
-- -----------------------------------------------------------------------------
CREATE TABLE RectifiedBillCategory (
    id int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos codigos fijos',
    code varchar(2) NOT NULL,
    name varchar(40) NOT NULL,
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tipos de Factura Rectificativa';

INSERT INTO RectifiedBillCategory VALUES (1, 'I', 'Por diferencias', NULL, NULL, NULL, NULL);

INSERT INTO RectifiedBillCategory VALUES (2, 'S', 'Por sustitución', NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- Bill
-- -----------------------------------------------------------------------------
ALTER TABLE Bill ADD COLUMN billCategoryId INT(11);

ALTER TABLE Bill ADD COLUMN rectifiedBillCategoryId INT(11) DEFAULT NULL;

UPDATE Bill AS bbd SET bbd.billCategoryId = 1;

ALTER TABLE Bill ADD CONSTRAINT BillCategory_FK FOREIGN KEY (billCategoryId) REFERENCES BillCategory(id);

ALTER TABLE Bill ADD CONSTRAINT RectifiedBillCategory_FK FOREIGN KEY (rectifiedBillCategoryId) REFERENCES RectifiedBillCategory(id);

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
UPDATE Version SET version='0.37';