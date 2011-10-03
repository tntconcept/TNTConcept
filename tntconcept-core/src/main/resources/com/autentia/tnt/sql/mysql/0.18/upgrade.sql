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

ALTER TABLE `Bill` ADD COLUMN `accountId` INTEGER;
ALTER TABLE `Bill` ADD CONSTRAINT FOREIGN KEY `fk_bill_accountId` (accountId) REFERENCES `Account`(id) ON DELETE RESTRICT ON UPDATE RESTRICT; 

insert into `Position_Department` (positionId, departmentId)
(select p.id, d.id from Position p, Department d where p.name = 'Indefinido');

insert into `Department_Organization` (departmentId, organizationId)
(select d.id as department, o.id as organization from Organization o, Department d where d.name = 'Indefinido');

update Version set version='0.19';