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

CREATE TABLE Position (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(256) NOT NULL,
  description varchar(1024) NOT NULL,
  ownerId INTEGER NULL,
  departmentId INTEGER unsigned NULL,
  insertDate datetime DEFAULT NULL,
  updateDate datetime DEFAULT NULL,
  deleteDate datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE Position_Department (
  id int(11) NOT NULL AUTO_INCREMENT,
  positionId int(11) NOT NULL,
  departmentId int unsigned NOT NULL,
  PRIMARY KEY (id),
  KEY fk_position_department_position (positionId),
  KEY fk_position_department_department (departmentId),
  CONSTRAINT fk_position_department_position FOREIGN KEY (positionId) REFERENCES Position (id),
  CONSTRAINT fk_position_department_department FOREIGN KEY (departmentId) REFERENCES Department (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE Department_Organization (
  id int(11) NOT NULL AUTO_INCREMENT,
  departmentId int unsigned NOT NULL,
  organizationId int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_department_organization_department (departmentId),
  KEY fk_department_organization_organization (organizationId),
  CONSTRAINT fk_department_organization_department FOREIGN KEY (departmentId) REFERENCES Department (id),
  CONSTRAINT fk_department_organization_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE ContactInfo (
  id int(11) NOT NULL AUTO_INCREMENT,
  contactId int NOT NULL,
  positionId int(11) NOT NULL,
  departmentId int unsigned NOT NULL,
  organizationId int NOT NULL,
  PRIMARY KEY (id),
  KEY fk_contactinfo_contact (contactId),
  KEY fk_contactinfo_position (positionId),
  KEY fk_contactinfo_department (departmentId),
  KEY fk_contactinfo_organization (organizationId),
  CONSTRAINT fk_contactinfo_contact FOREIGN KEY (contactId) REFERENCES Contact (id),
  CONSTRAINT fk_contactinfo_position FOREIGN KEY (positionId) REFERENCES Position (id),
  CONSTRAINT fk_contactinfo_department FOREIGN KEY (departmentId) REFERENCES Department (id),
  CONSTRAINT fk_contactinfo_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

ALTER TABLE Contact ADD COLUMN email2 varchar(128);
ALTER TABLE Contact ADD COLUMN phone2 varchar(15);
ALTER TABLE Contact ADD COLUMN fax varchar(15);
ALTER TABLE Contact ADD COLUMN address varchar(100);
ALTER TABLE Contact ADD COLUMN postalCode varchar(5);
ALTER TABLE Contact ADD COLUMN city varchar(100);
ALTER TABLE Contact ADD COLUMN country varchar(100);
ALTER TABLE Contact ADD COLUMN provinceId INTEGER;
ALTER TABLE Contact ADD COLUMN active TINYINT(1) default TRUE;
ALTER TABLE Contact ADD INDEX ndx_contact_provinceId (provinceId);
ALTER TABLE Contact ADD CONSTRAINT `fk_contact_province` foreign key (`provinceId`) REFERENCES Province (id);

INSERT INTO Position (name, description, ownerId, departmentId, insertDate, updateDate) VALUES ('Indefinido', 'Puesto sin definir', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO Department (name, description, ownerId, departmentId, insertDate, updateDate) VALUES ('Indefinido', 'Departamento sin definir', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO Organization (organizationTypeId, organizationISOCategoryId, name, provinceID, ownerId, departmentId, insertDate, updateDate) VALUES (1,1, 'Indefinida', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO Province (id,name) VALUES (53, 'Indefinido');

ALTER TABLE Department MODIFY name varchar(256);
ALTER TABLE Organization MODIFY name varchar(256);


UPDATE Contact SET position='Indefinido' WHERE position = '' OR position is null;

-- Migrate field 'Contact.Position' to table 'Position'
INSERT INTO Position (name, description, ownerId, departmentId, insertDate, updateDate) select distinct position, 'migrado de una versión anterior', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP from Contact where position <> 'Indefinido';

-- Migrate references from the 'Contact' to the 'ContactInfo' table
INSERT INTO ContactInfo (contactId, positionId, departmentId, organizationId)
select c.id as contactId, p.id as positionId, d.id as departmentId, o.id as organizationId from Contact as c, Position as p, Department as d, Organization as o
where c.position = p.name
and o.id = c.organizationId
and d.name = 'Indefinido';

-- Remove migrated columns
ALTER TABLE `Contact` DROP COLUMN `position`;
ALTER TABLE `Contact` 
DROP COLUMN `organizationId`, 
DROP INDEX `ndx_contact_organizationId`, 
DROP FOREIGN KEY `fk_contact_organizationId`;

CREATE TABLE Tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(1024) NOT NULL,
  description varchar(1024) NOT NULL,
  ownerId INTEGER NULL,
  departmentId INTEGER unsigned NULL,
  insertDate datetime DEFAULT NULL,
  updateDate datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE Contact_Tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  tagId int(11) NOT NULL,
  contactId integer NOT NULL,
  PRIMARY KEY (id),
  KEY fk_contact_tag_tag (tagId),
  KEY fk_contact_tag_contact (contactId),
  CONSTRAINT fk_contact_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
  CONSTRAINT fk_contact_tag_contact FOREIGN KEY (contactId) REFERENCES Contact (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE Position_Tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  tagId int(11) NOT NULL,
  positionId integer NOT NULL,
  PRIMARY KEY (id),
  KEY fk_position_tag_tag (tagId),
  KEY fk_position_tag_position (positionId),
  CONSTRAINT fk_position_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
  CONSTRAINT fk_position_tag_position FOREIGN KEY (positionId) REFERENCES Position (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE Department_Tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  tagId int(11) NOT NULL,
  departmentId integer unsigned NOT NULL,
  PRIMARY KEY (id),
  KEY fk_department_tag_tag (tagId),
  KEY fk_department_tag_department (departmentId),
  CONSTRAINT fk_department_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
  CONSTRAINT fk_department_tag_department FOREIGN KEY (departmentId) REFERENCES Department (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE Organization_Tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  tagId int(11) NOT NULL,
  organizationId integer NOT NULL,
  PRIMARY KEY (id),
  KEY fk_organization_tag_tag (tagId),
  KEY fk_organization_tag_organization (organizationId),
  CONSTRAINT fk_organization_tag_tag FOREIGN KEY (tagId) REFERENCES Tag (id),
  CONSTRAINT fk_organization_tag_organization FOREIGN KEY (organizationId) REFERENCES Organization (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE PositionChange (
  id int(11) NOT NULL AUTO_INCREMENT,
  field varchar(1024) NOT NULL,
  oldValue varchar(1024) NOT NULL,
  newValue varchar(1024) NOT NULL,
  positionId int(10) DEFAULT NULL,
  userId int(10) DEFAULT NULL,
  insertDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_positionChange_position (positionId),
  KEY fk_positionChange_user (userId),
  CONSTRAINT fk_positionChange_position FOREIGN KEY (positionId) REFERENCES Position (id),
  CONSTRAINT fk_positionChange_user FOREIGN KEY (userId) REFERENCES User(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE  ContactChange (
  id int(11) NOT NULL auto_increment,
  field varchar(1024) collate utf8_spanish_ci NOT NULL,
  oldValue varchar(1024) collate utf8_spanish_ci NOT NULL,
  newValue varchar(1024) collate utf8_spanish_ci NOT NULL,
  contactId int(10) default NULL,
  userId int(10) default NULL,
  insertDate datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY fk_contactChange_contact (contactId),
  KEY fk_contactChange_user (userId),
  CONSTRAINT fk_contactChange_contact FOREIGN KEY (contactId) REFERENCES Contact (id),
  CONSTRAINT fk_contactChange_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- adding new columns to Position
ALTER TABLE Position ADD COLUMN email varchar(128);
ALTER TABLE Position ADD COLUMN phone varchar(15);
ALTER TABLE Position ADD COLUMN fax varchar(15);
ALTER TABLE Position ADD COLUMN address varchar(100);
ALTER TABLE Position ADD COLUMN postalCode varchar(5);
ALTER TABLE Position ADD COLUMN city varchar(100);
ALTER TABLE Position ADD COLUMN country varchar(100);
ALTER TABLE Position ADD COLUMN provinceId INTEGER;
ALTER TABLE Position ADD INDEX ndx_position_provinceId (provinceId);
ALTER TABLE Position ADD CONSTRAINT `fk_position_province` foreign key (`provinceId`) REFERENCES Province (id);

-- removing publication constraint
ALTER TABLE Publication DROP FOREIGN KEY `fk_publicacion_tutorialId`;


-- table for contact owners
create table `ContactOwner` (
  `id` int not null auto_increment,
  `contactId` int not null,
  `userId` int not null,
  primary key (`id`),
  constraint `fk_contactowner_contactId` foreign key (`contactId`) references `Contact` (`id`),
  constraint `fk_contactowner_userId` foreign key (`userId`) references `User` (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Propietarios de contactos';

-- initially all owner will be into the owners list
insert into ContactOwner (contactId,userId) select id, coalesce(ownerId, 1) from Contact;

update Version set version='0.17';