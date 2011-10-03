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

ALTER TABLE `Offer` ADD COLUMN observations varchar(1024) null;

ALTER TABLE `RequestHoliday` ADD COLUMN chargeYear date not null default '2007-01-01'; 

CREATE TABLE `WorkingAgreement` (
  `id` int not null auto_increment,
  `name` varchar(128) not null,
  `description` varchar(2048),
  `holidays` int not null default 22,
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Convenios laborales';

INSERT INTO WorkingAgreement (id,name,description,holidays) values (1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22);

ALTER TABLE `User` ADD COLUMN `agreementId` int not null default 1;
ALTER TABLE `User` ADD index `ndx_user_agreementId` (`agreementId`);
ALTER TABLE `User` ADD constraint `fk_user_agreementId` foreign key (`agreementId`) references `WorkingAgreement` (`id`);

-- Update version number
update Version set version='0.9';