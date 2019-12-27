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

-- bugzilla:1860 - Crear administracion de colaboradores
CREATE TABLE Collaborator (
  id INTEGER NOT NULL AUTO_INCREMENT,
  userId INTEGER(10),
  contactId INTEGER(10),
  organizationId INTEGER(10),
  ownerId INTEGER NULL,
  departmentId INTEGER  NULL,
  insertDate DATETIME,
  updateDate DATETIME,
  PRIMARY KEY(id),
  CONSTRAINT fk_collaborator_user FOREIGN KEY (userId) REFERENCES User (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_collaborator_contact FOREIGN KEY (contactId) REFERENCES Contact (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_collaborator_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ;

CREATE TABLE  Commissioning (
  id int(11) NOT NULL AUTO_INCREMENT,
  requestDate datetime NOT NULL,
  name varchar(512)  NOT NULL,
  scope varchar(1024)  DEFAULT NULL,
  content varchar(1024)  DEFAULT NULL,
  products varchar(1024)  DEFAULT NULL,
  deliveryDate datetime NOT NULL,
  budget decimal(10,2) DEFAULT NULL,
  paymentMode varchar(32)  DEFAULT NULL,
  notes varchar(1024)  DEFAULT NULL,
  authorSignature tinyint(1) NOT NULL DEFAULT '0',
  reviserSignature tinyint(1) NOT NULL DEFAULT '0',
  adminSignature tinyint(1) NOT NULL DEFAULT '0',
  justifyInformation tinyint(1) NOT NULL DEFAULT '0',
  developedActivities varchar(1024)  DEFAULT NULL,
  difficultiesAppeared varchar(1024)  DEFAULT NULL,
  results varchar(1024)  DEFAULT NULL,
  conclusions varchar(1024)  DEFAULT NULL,
  evaluation varchar(1024)  DEFAULT NULL,
  status varchar(20)  DEFAULT NULL,
  projectId int(10) DEFAULT NULL,
  insertDate datetime DEFAULT NULL,
  updateDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_commissioning_project on (projectId),
  CONSTRAINT fk_commissioning_project FOREIGN KEY (projectId) REFERENCES Project (id)
) ;

CREATE TABLE  CommissioningDelay (
  id int(11) NOT NULL AUTO_INCREMENT,
  reason varchar(1024)  NOT NULL,
  originalDate datetime NOT NULL,
  delayedToDate datetime NOT NULL,
  commissioningId int(10) DEFAULT NULL,
  insertDate datetime DEFAULT NULL,
  updateDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_commissioningDelay_commissioning on (commissioningId),
  CONSTRAINT fk_commissioningDelay_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id)
) ;

CREATE TABLE Commissioning_Collaborator (
  id int(11) NOT NULL AUTO_INCREMENT,
  commissioningId int(10) NOT NULL,
  collaboratorId INTEGER NOT NULL,
  PRIMARY KEY (id),
  KEY fk_commissioning_collaborator_commissioning on (commissioningId),
  KEY fk_commissioning_collaborator_collaborator on (collaboratorId),
  CONSTRAINT fk_commissioning_collaborator_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
  CONSTRAINT fk_commissioning_collaborator_collaborator FOREIGN KEY (collaboratorId) REFERENCES Collaborator (id)
) ;

CREATE TABLE Commissioning_User (
  id int(11) NOT NULL AUTO_INCREMENT,
  commissioningId int(10) NOT NULL,
  userId int(10) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_commissioning_user_commissioning on (commissioningId),
  KEY fk_commissioning_user_user on (userId),
  CONSTRAINT fk_commissioning_user_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
  CONSTRAINT fk_commissioning_user_user FOREIGN KEY (userId) REFERENCES User (id)
) ;



-- bugzilla:
-- Alternative table name : ExtracurricularActivity
CREATE TABLE ExternalActivity (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name varchar(256),
	category varchar(256),
	startDate DATETIME NOT NULL,
	endDate DATETIME NOT NULL,
	location varchar(256),
	organizer varchar(256),
	comments varchar(2048),
	documentCategoryId INTEGER(10) ,
	ownerId INTEGER(10),
	departmentId INTEGER(10),
	insertDate DATETIME,
  	updateDate DATETIME,
  	PRIMARY KEY(id),
  	CONSTRAINT fk_externalactivity_documentcategory FOREIGN KEY (documentCategoryId) REFERENCES DocumentCategory (id)
	    ON DELETE RESTRICT
	    ON UPDATE RESTRICT
) ;

-- Update version number
update Version set version='0.14';