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

-- Agregar horas maximas de convenio a la tabla de usuario
alter table User add column agreementYearDuration integer(11);
-- Eliminar restriccion de jornada laboral obligatoria
alter table User modify column dayDuration integer(11);

-- Agregar horas máximas a la tabla de convenio
alter table WorkingAgreement add column yearDuration integer(11) not null;


ALTER TABLE `CommissioningDelay` ADD COLUMN `status` varchar(20)  DEFAULT NULL;
ALTER TABLE `Commissioning` ADD COLUMN `deleteDate` datetime  DEFAULT NULL;

CREATE TABLE  CommissioningChange (
  id int(11) NOT NULL AUTO_INCREMENT,
  field varchar(1024) NOT NULL,
  oldValue varchar(1024) NOT NULL,
  newValue varchar(1024) NOT NULL,
  commissioningId int(10) DEFAULT NULL,
  userId int(10) DEFAULT NULL,
  insertDate datetime DEFAULT NULL,
  updateDate datetime DEFAULT NULL,
  status varchar(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_commissioningChange_commissioning (commissioningId),
  KEY fk_commissioningChange_user (userId),
  CONSTRAINT fk_commissioningChange_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
  CONSTRAINT fk_commissioningChange_user FOREIGN KEY (userId) REFERENCES User(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE  CommissioningFile (
  id int(11) NOT NULL AUTO_INCREMENT,
  commissioningId int(10) NOT NULL,
  userId int(10) NOT NULL,
  insertDate datetime NOT NULL,
  updateDate datetime default NULL,
  file varchar(400)  NOT NULL,
  fileMime varchar(128)  DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_commissioningFile_commissioning (commissioningId),
  KEY fk_commissioningFile_user (userId),
  CONSTRAINT fk_commissioningFile_commissioning FOREIGN KEY (commissioningId) REFERENCES Commissioning (id),
  CONSTRAINT fk_commissioningFile_user FOREIGN KEY (userId) REFERENCES User(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE  ActivityFile (
  id int(11) NOT NULL AUTO_INCREMENT,
  externalActivityId int(10) NOT NULL,
  userId int(10) NOT NULL,
  insertDate datetime NOT NULL,
  updateDate datetime default NULL,
  file varchar(400)  NOT NULL,
  fileMime varchar(128)  DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_activityFile_externalActivity (externalActivityId),
  KEY fk_activityFile_user (userId),
  CONSTRAINT fk_activityFile_externalActivity FOREIGN KEY (externalActivityId) REFERENCES ExternalActivity (id),
  CONSTRAINT fk_activityFile_user FOREIGN KEY (userId) REFERENCES User(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Aumentar tamaño de descripcion (name) y observaciones en Facturas (bill)
alter table Bill modify column name varchar(4096) not null;
alter table Bill modify column observations varchar(4096);

-- Aumentar el tamaño del concepto en el desglose de factura (BillBreakdown)
alter table BillBreakDown modify column concept varchar(4096) not null;

-- Update version number
update Version set version='0.15';