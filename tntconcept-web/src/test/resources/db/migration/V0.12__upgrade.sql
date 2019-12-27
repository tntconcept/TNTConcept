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

-- Adding a new field to store the number of units associated to an offer
ALTER TABLE OfferCost ADD COLUMN units int not null;
UPDATE OfferCost SET units = 1;

-- -----------------------------------------------------------------------------
-- CreditTitle
-- -----------------------------------------------------------------------------

-- Create table
create table CreditTitle (
  id int not null auto_increment,
  number varchar(64) not null,
  concept varchar(1024),
  amount decimal(10,2) not null,
  state varchar(16),
  type varchar(16),
  issueDate date not null,
  expirationDate date,
  organizationId int not null,
  observations varchar(1024),
  ownerId int null,
  departmentId int  null,
  insertDate datetime null,
  updateDate datetime null,
  primary key (id),
  constraint fk_credittitle_organizationId foreign key (organizationId) references Organization (id)
) ;


-- -----------------------------------------------------------------------------
-- Bill_AccountEntry
-- -----------------------------------------------------------------------------

-- Create table
create table CreditTitle_Bill (
  billId int not null,
  creditTitleId int not null,
  primary key (billId,creditTitleId),
  index ndx_creditTitle_Bill_billId on (billId),
  index ndx_creditTitle_Bill_creditTitleId on (creditTitleId),
  constraint fk_creditTitle_Bill_billId foreign key (billId) references Bill (id),
  constraint fk_creditTitle_Bill_creditTitleId foreign key (creditTitleId) references CreditTitle(id)
) ;




-- Update version number
update Version set version='0.12';