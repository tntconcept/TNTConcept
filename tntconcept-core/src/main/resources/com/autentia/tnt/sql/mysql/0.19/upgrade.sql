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

-- bugzilla:3019 - ordenacion de conceptos en ofertas y facturas 
ALTER TABLE `BillBreakDown` ADD COLUMN `place` INTEGER DEFAULT NULL;
ALTER TABLE `OfferCost` ADD COLUMN `place` INTEGER DEFAULT NULL;
ALTER TABLE `OfferRole` ADD COLUMN `place` INTEGER DEFAULT NULL;

-- bugzilla:3022 - crear apartado 'valoracion'
ALTER TABLE `Organization` ADD COLUMN `evaluationCriteria` VARCHAR(45) DEFAULT NULL;


-- ----------------------------------------------------------------------------
-- PeriodicalAccountEntry
-- ----------------------------------------------------------------------------
ALTER TABLE `PeriodicalAccountEntry` ADD COLUMN `organizationId` INTEGER DEFAULT NULL;
ALTER TABLE `PeriodicalAccountEntry` ADD INDEX `ndx_periodicalAccountEntry_organizationId`(`organizationId`);
ALTER TABLE `PeriodicalAccountEntry`
 ADD CONSTRAINT `FK_periodicalaccountentry_organizationId` FOREIGN KEY `FK_periodicalaccountentry_organizationId` (`organizationId`)
    REFERENCES `Organization` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

    
update Version set version='0.20';