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
    id int(11) NOT NULL,
    code varchar(2) NOT NULL,
    name varchar(100) NOT NULL,
    rectify boolean NOT NULL,
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY (id)
);

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
    id int(11) NOT NULL,
    code varchar(2) NOT NULL,
    name varchar(40) NOT NULL,
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY  (id)
);

INSERT INTO RectifiedBillCategory VALUES (1, 'S', 'Por sustitución', NULL, NULL, NULL, NULL);

INSERT INTO RectifiedBillCategory VALUES (2, 'I', 'Por diferencias', NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- BillRegime
-- -----------------------------------------------------------------------------

CREATE TABLE BillRegime (
    id int(11) NOT NULL,
    code varchar(2) NOT NULL,
    name varchar(250) NOT NULL,
    ownerId int(11) DEFAULT NULL,
    departmentId int(10) DEFAULT NULL,
    insertDate datetime DEFAULT NULL,
    updateDate datetime DEFAULT NULL,
    PRIMARY KEY  (id)
);

INSERT INTO BillRegime VALUES (1, '01', 'Operación de régimen común', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (2, '02', 'Exportación', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (3, '03', 'Operaciones a las que se aplique el régimen especial de bienes usados, objetos de arte, antigüedades y objetos de colección (135-139 LIVA)', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (4, '04', 'Régimen especial oro de inversión', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (5, '05', 'Régimen especial agencias de viajes', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (6, '06', 'Régimen especial grupo de entidades en IVA (Nivel Avanzado)', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (7, '07', 'Régimen especial criterio de caja', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (8, '08', 'Operaciones sujetas al IPSI / IGIC', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (9, '09', 'Facturación de las prestaciones de servicios de agencias de viaje que actúan como mediadoras en nombre y por cuenta ajena (D.A.4ª RD1619/2012)', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (10, '10', 'Cobros por cuenta de terceros de honorarios profesionales o de derechos derivados de la propiedad industrial, de autor, asociados...', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (11, '11', 'Operaciones de arrendamiento de local de negocio sujetas a retención', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (12, '12', 'Operaciones de arrendamiento de local de negocio no sujetos a retención', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (13, '13', 'Operaciones de arrendamiento de local de negocio sujetas y no sujetas a retención', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (14, '14', 'Factura con IVA pendiente de devengo (certificaciones de obra cuyo destinatario sea una Administración Pública)', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (15, '15', 'Factura con IVA pendiente de devengo - operaciones de tracto sucesivo', NULL, NULL, NULL, NULL);
INSERT INTO BillRegime VALUES (16, '16', 'Primer semestre 2017', NULL, NULL, NULL, NULL);

-- -----------------------------------------------------------------------------
-- Bill
-- -----------------------------------------------------------------------------
ALTER TABLE Bill ADD COLUMN billCategoryId INT(11) NOT NULL DEFAULT 1;

ALTER TABLE Bill ADD COLUMN rectifiedBillCategoryId INT(11) DEFAULT NULL;

ALTER TABLE Bill ADD COLUMN provideService boolean NOT NULL DEFAULT 1;

UPDATE Bill AS bbd SET bbd.billCategoryId = 1;
ALTER TABLE Bill ADD COLUMN billRegimeId INT(11) NOT NULL DEFAULT 1;

UPDATE Bill SET Bill.rectifiedBillCategoryId = 1 WHERE ((SELECT RIGHT(NUMBER,1)) = 'R');

UPDATE Bill SET Bill.billCategoryId = 5 WHERE ((SELECT RIGHT(NUMBER,1)) = 'R');

ALTER TABLE Bill ADD CONSTRAINT BillCategory_FK FOREIGN KEY (billCategoryId) REFERENCES BillCategory(id);

ALTER TABLE Bill ADD CONSTRAINT RectifiedBillCategory_FK FOREIGN KEY (rectifiedBillCategoryId) REFERENCES RectifiedBillCategory(id);

ALTER TABLE Bill ADD CONSTRAINT BillRegime_FK FOREIGN KEY (billRegimeId) REFERENCES BillRegime(id);

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
UPDATE Version SET version='0.37';