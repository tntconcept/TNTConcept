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

DROP TABLE IF EXISTS `EntityChange`;
CREATE TABLE  `EntityChange` (
  `id` int(11) NOT NULL auto_increment,
  `field` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `userId` int(10) default NULL,
  `insertDate` datetime default NULL,
  `entityId` int(11) NOT NULL,
  `entityName` varchar(1024) collate utf8_spanish_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_entityChange_user` (`userId`),
  CONSTRAINT `fk_entityChange_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ;

-- Migración de la tabla de cambios de contactos a la genÃ©rica
insert into `EntityChange` (`field`, oldValue, newValue, userId, insertDate, entityId, entityName)
 (select `field`, oldValue, newValue, userId, insertDate, contactId, "com.autentia.tnt.businessobject.Contact" from `ContactChange`);

update `Contact` set country = '' where country is null;

-- eliminamos la tabla de cambios de contactos
DROP table IF EXISTS  `ContactChange`;

-- create 'BillPayment' Table

DROP TABLE IF EXISTS `BillPayment`;
CREATE TABLE `BillPayment` (
  `id` INTEGER  NOT NULL AUTO_INCREMENT,
  `billId` INTEGER NOT NULL,
  `amount` DECIMAL(10,2) not null default 0,
  `expirationDate` DATE NOT NULL,
  `ownerId` INTEGER NULL,
  `departmentId` INTEGER unsigned NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,  
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_bill_billPayment` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Migrate data from 'Bill' to 'BillPayment'

insert into `BillPayment` (`billId`, amount, expirationDate)
 (select b.id, coalesce(sum((bb.units*bb.amount)*(1+(bb.iva/100))),0) as total, date_add(creationDate, INTERVAL expiration DAY) as date from Bill b left join BillBreakDown bb on b.id=bb.billId, Organization o, Project p where b.projectId = p.id and p.organizationId = o.id group by b.id);

-- remove column 'expiration'

ALTER TABLE `Bill` DROP COLUMN `expiration`;

-- increment the size of a few columns to be able to write more text 

ALTER TABLE `Offer` MODIFY COLUMN `description` VARCHAR(4096) DEFAULT NULL;

ALTER TABLE `Offer` MODIFY COLUMN `observations` VARCHAR(4096) DEFAULT NULL;

ALTER TABLE `OfferRole` MODIFY COLUMN `name` VARCHAR(4096) DEFAULT NULL;

ALTER TABLE `OfferCost` MODIFY COLUMN `name` VARCHAR(4096) DEFAULT NULL;

update Version set version='0.18';