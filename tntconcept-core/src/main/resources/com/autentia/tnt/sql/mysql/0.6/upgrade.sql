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

-- Update table Activity to change startTime dataType
ALTER TABLE `Activity` MODIFY `startDate` DATETIME DEFAULT '0000-00-00 00:00:00';


-- Adding a new field to store a customizable id for this type
ALTER TABLE `AccountEntryType` ADD COLUMN customizableId int null;

-- Adding new fields.
ALTER TABLE `AccountEntry` ADD COLUMN entryNumber varchar(16) null;
ALTER TABLE `AccountEntry` ADD COLUMN docNumber varchar(50) null;


-- Update version number
update Version set version='0.7';