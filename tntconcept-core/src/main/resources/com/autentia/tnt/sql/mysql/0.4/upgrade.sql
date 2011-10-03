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

-- Adding fields to InteractionType
insert into InteractionType (`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) values ('Primer contacto','',null,null,now(),now());
insert into InteractionType (`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) values ('Confirmación de oferta','',null,null,now(),now());

-- -----------------------------------------------------------------------------
-- Offer
-- -----------------------------------------------------------------------------

create table `Offer` (
  `id` int not null auto_increment,
  `title` varchar(128) not null,
  `description` varchar(1024),
  `userId` int not null,
  `organizationId` int not null,
  `contactId` int not null,
  `creationDate` date not null,
  `validityDate` date null,
  `maturityDate` date null,
  `offerPotential` varchar(16) not null,
  `offerState` varchar(16) not null,
  `offerRejectReasonId` int null,
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,
  primary key (`id`),
  index `ndx_offer_organizationId` (`organizationId`),
  constraint `fk_offer_organizationId` foreign key (`organizationId`) references `Organization` (`id`),
  index `ndx_offer_contactId` (`contactId`),
  constraint `fk_offer_contactId` foreign key (`contactId`) references `Contact` (`id`),
  index `ndx_offer_offerRejectReasonId` (`offerRejectReasonId`),
  constraint `fk_offer_offerRejectReasonId` foreign key (`offerRejectReasonId`) references `OfferRejectReason` (`id`),
  index `ndx_offer_userId` (`userId`),
  constraint `fk_offer_userId` foreign key (`userId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Ofertas';


-- Adding offerId to Interaction
ALTER TABLE `Interaction` ADD COLUMN offerId int null;
ALTER TABLE `Interaction` ADD index `ndx_interaction_offerId` (`offerId`);
ALTER TABLE `Interaction` ADD constraint `fk_interaction_offerId` foreign key (`offerId`) references `Offer` (`id`);


-- -----------------------------------------------------------------------------
-- OfferRole
-- -----------------------------------------------------------------------------

-- Create table
create table `OfferRole` (
  `id` int not null auto_increment,
  `offerId` int not null,
  `name` varchar(128) not null,
  `costPerHour` decimal(10,2) not null,
  `expectedHours` int not null,
  `iva` decimal(4,2) not null default 16,
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,  
  primary key (`id`),
  index `ndx_offerRole_offerId` (`offerId`),
  constraint `fk_offerRole_offerId` foreign key (`offerId`) references `Offer` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci;


-- -----------------------------------------------------------------------------
-- OfferCost
-- -----------------------------------------------------------------------------

-- Create table
create table `OfferCost` (
  `id` int  not null auto_increment,
  `offerId` int not null,
  `name` varchar(128)  not null,
  `cost` decimal(10,2)  not null,
  `billable` boolean  not null default true,
  `iva` decimal(4,2) not null default 16,
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,  
  PRIMARY KEY(`id`),
  CONSTRAINT `fk_offerCost_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB default charset=utf8 collate=utf8_spanish_ci;

-- -----------------------------------------------------------------------------
-- FinancialRatio
-- -----------------------------------------------------------------------------
create table `FinancialRatio` (
  `id` int not null auto_increment,
  `title` varchar(128) not null,
  `ratioDate` date not null,
  `banksAccounts` decimal(10,2) not null,
  `customers` decimal(10,2) not null,
  `stocks` decimal(10,2) not null,
  `amortizations` decimal(10,2) not null,
  `infrastructures` decimal(10,2) not null,
  `shortTermLiability` decimal(10,2) not null,
  `obligationBond` decimal(10,2) not null,
  `capital` decimal(10,2) not null,
  `reserves` decimal(10,2) not null,
  `incomes` decimal(10,2) not null,
  `expenses` decimal(10,2) not null,  
  `otherExploitationExpenses` decimal(10,2) not null,
  `financialExpenses` decimal(10,2) not null,
  `taxes` decimal(10,2) not null,  
  `ownerId` int null,
  `departmentId` int unsigned null,
  `insertDate` datetime null,
  `updateDate` datetime null,
  primary key (`id`)  
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Ratios financieros';
-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------

ALTER TABLE `Offer` ADD COLUMN `number` varchar(64) not null;

-- Update version number
update Version set version='0.5';