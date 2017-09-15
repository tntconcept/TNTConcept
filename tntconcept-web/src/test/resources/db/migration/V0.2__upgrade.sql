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
ALTER TABLE Bill ALTER COLUMN amount SET default 0;
ALTER TABLE Bill ADD COLUMN startBillDate date not null default '1980-01-01';
ALTER TABLE Bill ADD COLUMN endBillDate date not null default '1980-01-01';

ALTER TABLE Bill ADD COLUMN billType varchar(16) not null default 'ISSUED';
ALTER TABLE Bill ADD COLUMN orderNumber varchar(64);
ALTER TABLE Bill ADD COLUMN contactId int;
create index ndx_bill_contactId on Bill (contactId);
ALTER TABLE Bill ADD constraint fk_bill_contactId foreign key (contactId) references Contact (id);
--
--
-- Bill_BreakDown
--


CREATE TABLE IF NOT EXISTS BillBreakDown (
id int NOT NULL auto_increment,
billId int NOT NULL,
concept varchar(1024) NOT NULL,
units decimal(10,2) not null default 1,
amount decimal(10,2) not null,
iva decimal(4,2) not null default 16,
 PRIMARY KEY  (id),
 index ndx_billBreakDown_bill on (billId),
 CONSTRAINT fk_billBreakDown_bill FOREIGN KEY (billId) REFERENCES Bill (id)
) ;


UPDATE Bill set state = 'EMITTED' where state IN ('PENDING','ACCEPTED');

ALTER TABLE Bill DROP COLUMN rappel;
ALTER TABLE Bill DROP COLUMN amount;

-- -----------------------------------------------------------------------------
-- Request holiday
-- -----------------------------------------------------------------------------

-- Fix bug 1680471-No se crea la tabla RequestHoliday en sistemas Unix
CREATE TABLE IF NOT EXISTS RequestHoliday (
  id int(10)  NOT NULL auto_increment,
  beginDate datetime NOT NULL,
  finalDate datetime NOT NULL,
  state varchar(16)  NOT NULL,
  userId int(11) NOT NULL,
  observations varchar(1024)  default NULL,
  PRIMARY KEY  (id),
  index ndx_requestHoliday_userId on (userId),
  CONSTRAINT fk_requestHoliday_userId FOREIGN KEY (userId) REFERENCES User (id)
) ;


ALTER TABLE AccountEntry DROP COLUMN  review;

-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------

ALTER TABLE Organization ADD COLUMN notes VARCHAR(1024) ;

-- -----------------------------------------------------------------------------
-- AccountEntryType
-- -----------------------------------------------------------------------------

ALTER TABLE AccountEntryType ADD COLUMN accountEntryTypeId INTEGER;
ALTER TABLE AccountEntryType
 ADD CONSTRAINT FK_accountentrytype_accountEntryTypeId FOREIGN KEY (accountEntryTypeId)
    REFERENCES AccountEntryType (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;



CREATE TABLE IF NOT EXISTS Department (
id int  NOT NULL auto_increment,
parentId int ,
name varchar(128) not null,
description varchar(2048) NOT NULL,
 PRIMARY KEY  (id),
 index ndx_department_department on (parentId),
 CONSTRAINT fk_department_department FOREIGN KEY (parentId) REFERENCES Department (id)
) ;

INSERT INTO Department (id,name,description) values (1,'Dirección','Departamento de dirección.');
INSERT INTO Department (parentId,name,description) values (1,'I+D+I','Departamento de I+D+I.');
INSERT INTO Department (parentId,name,description) values (1,'Consultoría','Departamento de Consultoría.');



CREATE TABLE IF NOT EXISTS ContractType (
id int  NOT NULL auto_increment,
name varchar(128) not null,
description varchar(2048) NOT NULL,
 PRIMARY KEY  (id)
) ;

INSERT INTO ContractType (name,description) values ('Prácticas','Departamento de dirección.');
INSERT INTO ContractType (name,description) values ('Duración determinada','Departamento de dirección.');
INSERT INTO ContractType (name,description) values ('Indefinido','Departamento de dirección.');




ALTER TABLE User ADD COLUMN notes VARCHAR(1024) ;
ALTER TABLE User ADD COLUMN photo varchar(255) ;
ALTER TABLE User ADD COLUMN endTestPeriodDate date ;
ALTER TABLE User ADD COLUMN endContractDate date ;
ALTER TABLE User ADD COLUMN departmentId int  not null default 1 ;
CREATE index ndx_user_departmentId on User (departmentId);
ALTER TABLE User ADD constraint fk_user_departmentId foreign key (departmentId) references Department (id);
ALTER TABLE User ADD COLUMN contractTypeId int  ;
CREATE index ndx_user_contractTypeId on User (contractTypeId);
ALTER TABLE User ADD constraint fk_user_contractTypeId foreign key (contractTypeId) references ContractType (id);
ALTER TABLE User ADD COLUMN contractObservations varchar(2048) ;

ALTER TABLE User ALTER COLUMN nif varchar(16);


-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

-- Update version number
update Version set version='0.2';
