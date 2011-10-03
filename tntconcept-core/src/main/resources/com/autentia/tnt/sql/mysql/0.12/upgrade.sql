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

-- Se aumenta el tamanio de estos campos
ALTER TABLE `Offer` MODIFY COLUMN `observations` VARCHAR(2048);
ALTER TABLE `Offer` MODIFY COLUMN `description` VARCHAR(2048);

-- Indica si en el informe de la oferta deberá aparecer el IVA 
ALTER TABLE `Offer` ADD COLUMN `showIvaIntoReport` boolean DEFAULT true AFTER `observations`;

-- Cambio de integer a decimal
ALTER TABLE `OfferCost` MODIFY COLUMN `units` decimal(10,2) NOT NULL DEFAULT 0.00;

-- Update version number
update Version set version='0.13';
