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

-- añadir deleteDate
ALTER TABLE CommissioningChange add column deleteDate datetime DEFAULT NULL;

-- Cambiar el nombre de la tabla Commissioning_Collaborator por CommissioningPaymentData
ALTER TABLE Commissioning_Collaborator RENAME TO CommissioningPaymentData;

-- Agregar campo paymentMode
ALTER TABLE CommissioningPaymentData ADD COLUMN paymentMode varchar(32);
ALTER TABLE CommissioningPaymentData ADD COLUMN bankAccount varchar(50);
ALTER TABLE CommissioningPaymentData ADD COLUMN billNumber  varchar(50);
ALTER TABLE CommissioningPaymentData ADD COLUMN insertDate  datetime;
ALTER TABLE CommissioningPaymentData ADD COLUMN updateDate  datetime;

-- Eliminar campo paymentMode de Commissioning
ALTER TABLE Commissioning drop column paymentMode;


-- Update version number
update Version set version='0.16';