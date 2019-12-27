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


-- ----------------------------------------------------------------------------
-- Role
-- ----------------------------------------------------------------------------
insert into Role(id,name) values (4,'Staff');
insert into Role(id,name) values (5,'Cliente');

-- -----------------------------------------------------------------------------
-- Frequency
-- -----------------------------------------------------------------------------
ALTER TABLE Frequency ADD COLUMN months INTEGER  ;
UPDATE Frequency set months = '1' where id = '1';
UPDATE Frequency set months = '3' where id = '2';
UPDATE Frequency set months = '6' where id = '3';
UPDATE Frequency set months = '12' where id = '4';
UPDATE Frequency set months = '2' where id = '5';
UPDATE Frequency set months = '24' where id = '6';
UPDATE Frequency set name = 'Bianual' where id = '6';
INSERT INTO Frequency (name, months) VALUES ('Ocasional', 0);

-- -----------------------------------------------------------------------------
-- Project
-- -----------------------------------------------------------------------------
ALTER TABLE Project ADD COLUMN open boolean DEFAULT false ;

--
-- Actualizamos proyectos para abrir cerrar proyectos
--

UPDATE Project SET open = true where endDate > now() or endDate is null;



-- -----------------------------------------------------------------------------
-- Account
-- -----------------------------------------------------------------------------
ALTER TABLE Account ADD COLUMN ownerId int null;
ALTER TABLE Account ADD COLUMN departmentId int  null;
ALTER TABLE Account ADD COLUMN insertDate datetime null;
ALTER TABLE Account ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- AccountEntry
-- -----------------------------------------------------------------------------
ALTER TABLE AccountEntry ADD COLUMN ownerId int null;
ALTER TABLE AccountEntry ADD COLUMN departmentId int  null;
ALTER TABLE AccountEntry ADD COLUMN insertDate datetime null;
ALTER TABLE AccountEntry ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- AccountEntryGroup
-- -----------------------------------------------------------------------------
ALTER TABLE AccountEntryGroup ADD COLUMN ownerId int null;
ALTER TABLE AccountEntryGroup ADD COLUMN departmentId int  null;
ALTER TABLE AccountEntryGroup ADD COLUMN insertDate datetime null;
ALTER TABLE AccountEntryGroup ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- AccountEntryType
-- -----------------------------------------------------------------------------
ALTER TABLE AccountEntryType ADD COLUMN ownerId int null;
ALTER TABLE AccountEntryType ADD COLUMN departmentId int  null;
ALTER TABLE AccountEntryType ADD COLUMN insertDate datetime null;
ALTER TABLE AccountEntryType ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- AccountType
-- -----------------------------------------------------------------------------
ALTER TABLE AccountType ADD COLUMN ownerId int null;
ALTER TABLE AccountType ADD COLUMN departmentId int  null;
ALTER TABLE AccountType ADD COLUMN insertDate datetime null;
ALTER TABLE AccountType ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Activity
-- -----------------------------------------------------------------------------

ALTER TABLE Activity ADD COLUMN departmentId int  null;
ALTER TABLE Activity ADD COLUMN insertDate datetime null;
ALTER TABLE Activity ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- Bill
-- -----------------------------------------------------------------------------
ALTER TABLE Bill ADD COLUMN ownerId int null;
ALTER TABLE Bill ADD COLUMN departmentId int  null;
ALTER TABLE Bill ADD COLUMN insertDate datetime null;
ALTER TABLE Bill ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- BillBreakDown
-- -----------------------------------------------------------------------------
ALTER TABLE BillBreakDown ADD COLUMN ownerId int null;
ALTER TABLE BillBreakDown ADD COLUMN departmentId int  null;
ALTER TABLE BillBreakDown ADD COLUMN insertDate datetime null;
ALTER TABLE BillBreakDown ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Book
-- -----------------------------------------------------------------------------
ALTER TABLE Book ADD COLUMN ownerId int null;
ALTER TABLE Book ADD COLUMN departmentId int  null;
ALTER TABLE Book ADD COLUMN insertDate datetime null;
ALTER TABLE Book ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- BulletinBoard
-- -----------------------------------------------------------------------------

ALTER TABLE BulletinBoard ADD COLUMN departmentId int  null;
ALTER TABLE BulletinBoard ADD COLUMN insertDate datetime null;
ALTER TABLE BulletinBoard ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- BulletinBoardCategory
-- -----------------------------------------------------------------------------
ALTER TABLE BulletinBoardCategory ADD COLUMN ownerId int null;
ALTER TABLE BulletinBoardCategory ADD COLUMN departmentId int  null;
ALTER TABLE BulletinBoardCategory ADD COLUMN insertDate datetime null;
ALTER TABLE BulletinBoardCategory ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- CompanyState
-- -----------------------------------------------------------------------------

ALTER TABLE CompanyState ADD COLUMN departmentId int  null;
ALTER TABLE CompanyState ADD COLUMN insertDate datetime null;
ALTER TABLE CompanyState ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Contact
-- -----------------------------------------------------------------------------
ALTER TABLE Contact ADD COLUMN ownerId int null;
ALTER TABLE Contact ADD COLUMN departmentId int  null;
ALTER TABLE Contact ADD COLUMN insertDate datetime null;
ALTER TABLE Contact ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- ContractType
-- -----------------------------------------------------------------------------
ALTER TABLE ContractType ADD COLUMN ownerId int null;
ALTER TABLE ContractType ADD COLUMN departmentId int  null;
ALTER TABLE ContractType ADD COLUMN insertDate datetime null;
ALTER TABLE ContractType ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- Department
-- -----------------------------------------------------------------------------
ALTER TABLE Department ADD COLUMN ownerId int null;
ALTER TABLE Department ADD COLUMN departmentId int  null;
ALTER TABLE Department ADD COLUMN insertDate datetime null;
ALTER TABLE Department ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Document
-- -----------------------------------------------------------------------------
ALTER TABLE Document ADD COLUMN ownerId int null;
ALTER TABLE Document ADD COLUMN departmentId int  null;
ALTER TABLE Document ADD COLUMN insertDate datetime null;
ALTER TABLE Document ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- DocumentCategory
-- -----------------------------------------------------------------------------
ALTER TABLE DocumentCategory ADD COLUMN ownerId int null;
ALTER TABLE DocumentCategory ADD COLUMN departmentId int  null;
ALTER TABLE DocumentCategory ADD COLUMN insertDate datetime null;
ALTER TABLE DocumentCategory ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- DocumentCategoryDoc
-- -----------------------------------------------------------------------------
ALTER TABLE DocumentCategoryDoc ADD COLUMN ownerId int null;
ALTER TABLE DocumentCategoryDoc ADD COLUMN departmentId int  null;
ALTER TABLE DocumentCategoryDoc ADD COLUMN insertDate datetime null;
ALTER TABLE DocumentCategoryDoc ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- DocumentVersion
-- -----------------------------------------------------------------------------
ALTER TABLE DocumentVersion ADD COLUMN ownerId int null;
ALTER TABLE DocumentVersion ADD COLUMN departmentId int  null;
ALTER TABLE DocumentVersion ADD COLUMN insertDate datetime null;
ALTER TABLE DocumentVersion ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Frequency
-- -----------------------------------------------------------------------------
ALTER TABLE Frequency ADD COLUMN ownerId int null;
ALTER TABLE Frequency ADD COLUMN departmentId int  null;
ALTER TABLE Frequency ADD COLUMN insertDate datetime null;
ALTER TABLE Frequency ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Holiday
-- -----------------------------------------------------------------------------
ALTER TABLE Holiday ADD COLUMN ownerId int null;
ALTER TABLE Holiday ADD COLUMN departmentId int  null;
ALTER TABLE Holiday ADD COLUMN insertDate datetime null;
ALTER TABLE Holiday ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Idea
-- -----------------------------------------------------------------------------

ALTER TABLE Idea ADD COLUMN departmentId int  null;
ALTER TABLE Idea ADD COLUMN insertDate datetime null;
ALTER TABLE Idea ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Interaction
-- -----------------------------------------------------------------------------

ALTER TABLE Interaction ADD COLUMN departmentId int  null;
ALTER TABLE Interaction ADD COLUMN insertDate datetime null;
ALTER TABLE Interaction ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- InteractionType
-- -----------------------------------------------------------------------------
ALTER TABLE InteractionType ADD COLUMN ownerId int null;
ALTER TABLE InteractionType ADD COLUMN departmentId int  null;
ALTER TABLE InteractionType ADD COLUMN insertDate datetime null;
ALTER TABLE InteractionType ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Inventory
-- -----------------------------------------------------------------------------
ALTER TABLE Inventory ADD COLUMN ownerId int null;
ALTER TABLE Inventory ADD COLUMN departmentId int  null;
ALTER TABLE Inventory ADD COLUMN insertDate datetime null;
ALTER TABLE Inventory ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Magazine
-- -----------------------------------------------------------------------------
ALTER TABLE Magazine ADD COLUMN ownerId int null;
ALTER TABLE Magazine ADD COLUMN departmentId int  null;
ALTER TABLE Magazine ADD COLUMN insertDate datetime null;
ALTER TABLE Magazine ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Objective
-- -----------------------------------------------------------------------------

ALTER TABLE Objective ADD COLUMN departmentId int  null;
ALTER TABLE Objective ADD COLUMN insertDate datetime null;
ALTER TABLE Objective ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------
ALTER TABLE Organization ADD COLUMN ownerId int null;
ALTER TABLE Organization ADD COLUMN departmentId int  null;
ALTER TABLE Organization ADD COLUMN insertDate datetime null;
ALTER TABLE Organization ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- OrganizationISOCategory
-- -----------------------------------------------------------------------------
ALTER TABLE OrganizationISOCategory ADD COLUMN ownerId int null;
ALTER TABLE OrganizationISOCategory ADD COLUMN departmentId int  null;
ALTER TABLE OrganizationISOCategory ADD COLUMN insertDate datetime null;
ALTER TABLE OrganizationISOCategory ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- OrganizationType
-- -----------------------------------------------------------------------------
ALTER TABLE OrganizationType ADD COLUMN ownerId int null;
ALTER TABLE OrganizationType ADD COLUMN departmentId int  null;
ALTER TABLE OrganizationType ADD COLUMN insertDate datetime null;
ALTER TABLE OrganizationType ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- PeriodicalAccountEntry
-- -----------------------------------------------------------------------------
ALTER TABLE PeriodicalAccountEntry ADD COLUMN ownerId int null;
ALTER TABLE PeriodicalAccountEntry ADD COLUMN departmentId int  null;
ALTER TABLE PeriodicalAccountEntry ADD COLUMN insertDate datetime null;
ALTER TABLE PeriodicalAccountEntry ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Project
-- -----------------------------------------------------------------------------
ALTER TABLE Project ADD COLUMN ownerId int null;
ALTER TABLE Project ADD COLUMN departmentId int  null;
ALTER TABLE Project ADD COLUMN insertDate datetime null;
ALTER TABLE Project ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- ProjectCost
-- -----------------------------------------------------------------------------
ALTER TABLE ProjectCost ADD COLUMN ownerId int null;
ALTER TABLE ProjectCost ADD COLUMN departmentId int  null;
ALTER TABLE ProjectCost ADD COLUMN insertDate datetime null;
ALTER TABLE ProjectCost ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- ProjectRole
-- -----------------------------------------------------------------------------
ALTER TABLE ProjectRole ADD COLUMN ownerId int null;
ALTER TABLE ProjectRole ADD COLUMN departmentId int  null;
ALTER TABLE ProjectRole ADD COLUMN insertDate datetime null;
ALTER TABLE ProjectRole ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Province
-- -----------------------------------------------------------------------------
ALTER TABLE Province ADD COLUMN ownerId int null;
ALTER TABLE Province ADD COLUMN departmentId int  null;
ALTER TABLE Province ADD COLUMN insertDate datetime null;
ALTER TABLE Province ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Publication
-- -----------------------------------------------------------------------------
ALTER TABLE Publication ADD COLUMN ownerId int null;
ALTER TABLE Publication ADD COLUMN departmentId int  null;
ALTER TABLE Publication ADD COLUMN insertDate datetime null;
ALTER TABLE Publication ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- RequestHoliday
-- -----------------------------------------------------------------------------

ALTER TABLE RequestHoliday ADD COLUMN departmentId int  null;
ALTER TABLE RequestHoliday ADD COLUMN insertDate datetime null;
ALTER TABLE RequestHoliday ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- Role
-- -----------------------------------------------------------------------------
ALTER TABLE Role ADD COLUMN ownerId int null;
ALTER TABLE Role ADD COLUMN departmentId int  null;
ALTER TABLE Role ADD COLUMN insertDate datetime null;
ALTER TABLE Role ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- Tutorial
-- -----------------------------------------------------------------------------

ALTER TABLE Tutorial ADD COLUMN departmentId int  null;
ALTER TABLE Tutorial ADD COLUMN insertDate datetime null;
ALTER TABLE Tutorial ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- User
-- -----------------------------------------------------------------------------
ALTER TABLE User ADD COLUMN insertDate datetime null;
ALTER TABLE User ADD COLUMN updateDate datetime null;

-- -----------------------------------------------------------------------------
-- UserCategory
-- -----------------------------------------------------------------------------
ALTER TABLE UserCategory ADD COLUMN ownerId int null;
ALTER TABLE UserCategory ADD COLUMN departmentId int  null;
ALTER TABLE UserCategory ADD COLUMN insertDate datetime null;
ALTER TABLE UserCategory ADD COLUMN updateDate datetime null;


-- -----------------------------------------------------------------------------
-- OfferRejectReason
-- -----------------------------------------------------------------------------
create table OfferRejectReason (
  id int not null auto_increment,
  title varchar(128) not null,
  description varchar(1024),
  ownerId int null,
  departmentId int  null,
  insertDate datetime null,
  updateDate datetime null,
  primary key (id)
) ;


INSERT INTO OfferRejectReason (id,title,description,ownerId,departmentId,insertDate,updateDate) values (1,'Sin respuesta','El cliente no responde a la oferta',1,1,now(),now());
INSERT INTO OfferRejectReason (id,title,description,ownerId,departmentId,insertDate,updateDate) values (2,'Oferta cara','El cliente considera la oferta excesivamente cara',1,1,now(),now());
INSERT INTO OfferRejectReason (id,title,description,ownerId,departmentId,insertDate,updateDate) values (3,'Tecnología inadecuada','Tecnología escogida en la oferta inadecuada',1,1,now(),now());
INSERT INTO OfferRejectReason (id,title,description,ownerId,departmentId,insertDate,updateDate) values (4,'Proyecto retrasado','El proyecto para el cual se hizo la oferta ha sido retrasado',1,1,now(),now());
INSERT INTO OfferRejectReason (id,title,description,ownerId,departmentId,insertDate,updateDate) values (5,'Proyecto cancelado','El proyecto para el cual se hizo la oferta ha sido cancelado',1,1,now(),now());
-- -----------------------------------------------------------------------------
-- Script para migrar: Activity, Idea, Interaction, Objective, requestHoliday, Tutorial
-- -----------------------------------------------------------------------------


-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

-- Update version number
update Version set version='0.4';
