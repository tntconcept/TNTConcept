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
-- Si se realiza mediante script necesita ser ejecutado por un usuario con perm
-- xit
-- iso SUPER.
-- set global sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------------------------------
-- BillRegime
-- -----------------------------------------------------------------------------

ALTER TABLE BillRegime ADD COLUMN associatedBillType varchar(16) NOT NULL DEFAULT 'ISSUED';

INSERT INTO BillRegime VALUES (17, '01', 'Operación de régimen general', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (18, '02', 'Operaciones por las que los empresarios satisfacen compensaciones en las adquisiciones a personas acogidas al Régimen especial de la agricultura, ganadería y pesca (REAGYP)', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (19, '03', 'Operaciones a las que se aplique el régimen especial de bienes usados, objetos de arte, antigüedades y objetos de colección (REBU)', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (20, '04', 'Régimen especial de oro de inversión', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (21, '05', 'Régimen especial de agencias de viajes', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (22, '06', 'Régimen especial grupo de entidades en IVA (Nivel Avanzado)', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (23, '07', 'Régimen especial del criterio de caja', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (24, '08', 'Operaciones sujetas al IPSI/IGIC (Impuesto sobre la Producción, los servicios y la Importación / Impuesto General Indirecto Canario', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (25, '09', 'Adquisiciones intracomunitarias de bienes y prestación de servicios', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (26, '12', 'Operaciones de arrendamiento de local de negocio', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (27, '13', 'Factura correspondiente a una importación (informada sin asociar a un DUA)', NULL, NULL, NULL, NULL, 'RECIEVED');
INSERT INTO BillRegime VALUES (28, '14', 'Primer semestre de 2017 y otras factruras anteriores a la inclusión en el SII', NULL, NULL, NULL, NULL, 'RECIEVED');

UPDATE Bill SET billRegimeId = 17 WHERE billType = 'RECIEVED';

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
UPDATE Version SET version='0.40';