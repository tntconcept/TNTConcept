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


-- -----------------------------------------------------------------------------
-- Frequency
-- -----------------------------------------------------------------------------

-- Add feature 1677200-La frecuencia de pagos periódicos puede ser bi-mensual
insert into Frequency(name) values ('Bimensual');


-- ----------------------------------------------------------------------------
-- Bill
-- ----------------------------------------------------------------------------
-- Necesitamos un script para adecuarnos al nuevo modelo.
-- Campo amount y campo Rappel
ALTER TABLE `Bill` MODIFY COLUMN `amount` decimal(10,2) default 0;
ALTER TABLE `Bill` ADD COLUMN `startBillDate` date not null default '1980-01-01';
ALTER TABLE `Bill` ADD COLUMN `endBillDate` date not null default '1980-01-01';

ALTER TABLE `Bill` ADD COLUMN `billType` varchar(16) not null default 'ISSUED';
ALTER TABLE `Bill` ADD COLUMN `orderNumber` varchar(64);
ALTER TABLE `Bill` ADD COLUMN `contactId` int;
ALTER TABLE `Bill` ADD index `ndx_bill_contactId` (`contactId`);
ALTER TABLE `Bill` ADD constraint `fk_bill_contactId` foreign key (`contactId`) references `Contact` (`id`);
-- 
--
-- Bill_BreakDown
--


CREATE TABLE IF NOT EXISTS `BillBreakDown` (
`id` int unsigned NOT NULL auto_increment,
`billId` int NOT NULL,
`concept` varchar(1024) collate utf8_spanish_ci NOT NULL,
`units` decimal(10,2) not null default 1,
`amount` decimal(10,2) not null,
`iva` decimal(4,2) not null default 16,
 PRIMARY KEY  (`id`),
 index `ndx_billBreakDown_bill` (`billId`),
 CONSTRAINT `fk_billBreakDown_bill` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


--
-- Procedimiento para acoplar la tabla de facturas al nuevo modelo.
--

DELIMITER $$
DROP PROCEDURE IF EXISTS migrate_bill$$
CREATE PROCEDURE migrate_bill()
BEGIN
DECLARE endOfTable INT DEFAULT 0;
DECLARE var_id int;
DECLARE var_name varchar(128);
DECLARE var_amount decimal(10,2);
DECLARE var_amount_final decimal(10,2);
DECLARE var_rappel decimal(4,2);
DECLARE var_rappel_final decimal(10,2);
DECLARE var_creationDate date;
DECLARE message varchar(2048);
DECLARE cur1 CURSOR FOR SELECT id, name, amount, rappel, creationDate FROM Bill;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET endOfTable = 1;
	
OPEN cur1;

	billTable: loop
		FETCH cur1 
		INTO var_id, var_name, var_amount, var_rappel, var_creationDate;
		IF endOfTable THEN
			leave billTable;
		END IF;
		select var_amount / 1.16 into var_amount_final;		
		INSERT INTO BillBreakDown (billId,concept,units,amount,iva) values (var_id, var_name, 1, var_amount_final,16);
		IF var_rappel >= 0 THEN
			select -1 * (var_amount_final * (var_rappel / 100)) into var_rappel_final;	
			INSERT INTO BillBreakDown (billId,concept,units,amount,iva) values (var_id, 'rappel', 1, var_rappel_final,0);
		END IF;
		UPDATE Bill set startBillDate = var_creationDate, endBillDate = var_creationDate  where id = var_id;
		
	end loop;
	
	
CLOSE cur1;
END$$
call migrate_bill()$$
drop procedure if exists migrate_bill$$
DELIMITER ;

UPDATE Bill set state = 'EMITTED' where state IN ('PENDING','ACCEPTED');

ALTER TABLE `Bill` DROP COLUMN `rappel`;
ALTER TABLE `Bill` DROP COLUMN `amount`;

-- -----------------------------------------------------------------------------
-- Request holiday
-- -----------------------------------------------------------------------------

-- Fix bug 1680471-No se crea la tabla RequestHoliday en sistemas Unix
CREATE TABLE IF NOT EXISTS `RequestHoliday` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `beginDate` datetime NOT NULL,
  `finalDate` datetime NOT NULL,
  `state` varchar(16) collate utf8_spanish_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  index `ndx_requestHoliday_userId` (`userId`),
  CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


ALTER TABLE AccountEntry DROP COLUMN  `review`;

-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------

ALTER TABLE Organization ADD COLUMN notes VARCHAR(1024) AFTER ftpsite;

-- -----------------------------------------------------------------------------
-- AccountEntryType
-- -----------------------------------------------------------------------------

ALTER TABLE `AccountEntryType` ADD COLUMN `accountEntryTypeId` INTEGER AFTER `observations`,
 ADD CONSTRAINT `FK_accountentrytype_accountEntryTypeId` FOREIGN KEY `FK_accountentrytype_accountEntryTypeId` (`accountEntryTypeId`)
    REFERENCES `AccountEntryType` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;



CREATE TABLE IF NOT EXISTS `Department` (
`id` int unsigned NOT NULL auto_increment,
`parentId` int unsigned,
`name` varchar(128) not null,
`description` varchar(2048) collate utf8_spanish_ci NOT NULL,
 PRIMARY KEY  (`id`),
 index `ndx_department_department` (`parentId`),
 CONSTRAINT `fk_department_department` FOREIGN KEY (`parentId`) REFERENCES `Department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO Department (id,name,description) values (1,'Dirección','Departamento de dirección.');
INSERT INTO Department (parentId,name,description) values (1,'I+D+I','Departamento de I+D+I.');
INSERT INTO Department (parentId,name,description) values (1,'Consultoría','Departamento de Consultoría.');



CREATE TABLE IF NOT EXISTS `ContractType` (
`id` int unsigned NOT NULL auto_increment,
`name` varchar(128) not null,
`description` varchar(2048) collate utf8_spanish_ci NOT NULL,
 PRIMARY KEY  (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO ContractType (name,description) values ('Prácticas','Departamento de dirección.');
INSERT INTO ContractType (name,description) values ('Duración determinada','Departamento de dirección.');
INSERT INTO ContractType (name,description) values ('Indefinido','Departamento de dirección.');




ALTER TABLE `User` ADD COLUMN notes VARCHAR(1024) AFTER healthInsurance;
ALTER TABLE `User` ADD COLUMN photo varchar(255) AFTER notes;
ALTER TABLE `User` ADD COLUMN endTestPeriodDate date AFTER photo;
ALTER TABLE `User` ADD COLUMN endContractDate date AFTER endTestPeriodDate;
ALTER TABLE `User` ADD COLUMN departmentId int unsigned not null default 1 AFTER endContractDate;
ALTER TABLE `User` ADD index `ndx_user_departmentId` (`departmentId`);
ALTER TABLE `User` ADD constraint `fk_user_departmentId` foreign key (`departmentId`) references `Department` (`id`);
ALTER TABLE `User` ADD COLUMN contractTypeId int unsigned AFTER departmentId;
ALTER TABLE `User` ADD index `ndx_user_contractTypeId` (`contractTypeId`);
ALTER TABLE `User` ADD constraint `fk_user_contractTypeId` foreign key (`contractTypeId`) references `ContractType` (`id`);
ALTER TABLE `User` ADD COLUMN contractObservations varchar(2048) AFTER contractTypeId;

ALTER TABLE `User` MODIFY COLUMN nif varchar(16);


-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

-- Update version number
update Version set version='0.2';
