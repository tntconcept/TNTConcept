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

ALTER TABLE `RequestHoliday` ADD COLUMN userComment varchar(1024) null;

ALTER TABLE `User` ADD COLUMN `dayDuration` int not null default 480 comment 'Duración en minutos';


-- Create table
create table `Occupation` (
  `id` int not null auto_increment,
  `projectId` int not null,
  `userId` int not null,
  `startDate` date not null,
  `endDate` date not null,  
  `description` varchar(1024),
  `duration` int not null comment 'Duración en minutos',
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,  
  primary key (`id`),
  index `ndx_occupation_userId` (`userId`),
  index `ndx_occupation_projectId` (`projectId`),
  constraint `fk_occupation_userId` foreign key (`userId`) references `User` (`id`),
  constraint `fk_occupation_projectId` foreign key (`projectId`) references `Project` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Future occupations of Users';


ALTER TABLE `Project` ADD COLUMN `billable` boolean  NOT NULL DEFAULT true;


-- Update version number
update Version set version='0.8';