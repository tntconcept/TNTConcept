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


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema tntconcept
--

CREATE DATABASE IF NOT EXISTS tntconcept;
USE tntconcept;

--
-- Definition of table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `number` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account number',
  `accountTypeId` int(11) NOT NULL COMMENT 'Account type',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Account description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_account_accountTypeId` (`accountTypeId`),
  CONSTRAINT `fk_account_accountTypeId` FOREIGN KEY (`accountTypeId`) REFERENCES `accounttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las cuentas';

--
-- Dumping data for table `account`
--

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`,`name`,`number`,`accountTypeId`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Caja','0000000000000000000',1,NULL,NULL,NULL,NULL,NULL),
 (2,'Caja Madrid ','20380000001000000001',2,'Cuenta Corriente',1,1,'2009-04-06 12:08:01','2009-04-06 12:08:01'),
 (3,'Santander','00490000100000000002',2,'Cuenta corriente',1,1,'2009-04-06 12:08:35','2009-04-06 12:08:35'),
 (5,'Caja BBVA','123456789',2,'Cuenta de prueba',1,1,'2009-05-13 12:40:29','2009-05-13 12:40:29');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


--
-- Definition of table `accountentry`
--

DROP TABLE IF EXISTS `accountentry`;
CREATE TABLE `accountentry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'Account entry type',
  `entryDate` date NOT NULL COMMENT 'account entry date',
  `entryAmountDate` date NOT NULL COMMENT 'account entry amount date (fecha valor)',
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `amount` decimal(10,2) NOT NULL COMMENT 'account entry amount',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `entryNumber` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `docNumber` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_accountEntry_accountId` (`accountId`),
  KEY `ndx_accountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  CONSTRAINT `fk_accountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `accountentrytype` (`id`),
  CONSTRAINT `fk_accountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los movimientos';

--
-- Dumping data for table `accountentry`
--

/*!40000 ALTER TABLE `accountentry` DISABLE KEYS */;
INSERT INTO `accountentry` (`id`,`accountId`,`accountEntryTypeId`,`entryDate`,`entryAmountDate`,`concept`,`amount`,`observations`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`entryNumber`,`docNumber`) VALUES 
 (1,1,5,'2009-02-01','2009-02-01','Pago material de oficina','-112.35','',1,1,'2009-04-07 09:48:32','2009-04-07 09:48:52','',''),
 (2,3,16,'2009-02-05','2009-02-05','Pago factura gestoría mes de enero ','-290.00','',1,1,'2009-04-07 09:58:17','2009-04-07 09:58:17','',''),
 (3,3,16,'2009-03-05','2009-03-05','Pago factura gestoría mes de febrero ','-290.00','',1,1,'2009-04-07 09:58:51','2009-04-07 09:58:51','',''),
 (4,2,3,'2009-02-01','2009-02-01','Pago factura teléfono mes de enero ','-99.06','',1,1,'2009-04-07 10:10:16','2009-04-07 10:10:16','',''),
 (5,2,7,'2009-04-07','2009-04-07','Cobro del 50% factura 03','2088.00','',1,1,'2009-04-07 10:23:09','2009-04-07 10:23:09','',''),
 (6,2,8,'2009-02-28','2009-02-28','Pago factura alquiler oficina mes de febrero ','-812.00','',1,1,'2009-04-07 14:50:02','2009-04-07 14:50:02','',''),
 (7,2,8,'2009-03-31','2009-03-31','Pago factura alquiler mes de marzo ','-812.00','',1,1,'2009-04-07 14:50:29','2009-04-07 14:50:29','',''),
 (8,2,7,'2009-04-10','2009-04-10','Cobro factura 05','1740.00','',1,1,'2009-04-13 13:53:42','2009-04-13 13:53:42','',''),
 (10,1,1,'2009-05-13','2009-05-13','Telefono Movil','150.00','',1,1,'2009-05-13 12:40:43','2009-05-13 12:40:43','','');
/*!40000 ALTER TABLE `accountentry` ENABLE KEYS */;


--
-- Definition of table `accountentrygroup`
--

DROP TABLE IF EXISTS `accountentrygroup`;
CREATE TABLE `accountentrygroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account entry group descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='grupos de movimiento';

--
-- Dumping data for table `accountentrygroup`
--

/*!40000 ALTER TABLE `accountentrygroup` DISABLE KEYS */;
INSERT INTO `accountentrygroup` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Ingreso','Ingresos en cuenta',NULL,NULL,NULL,NULL),
 (2,'Gasto','Gastos en cuenta',NULL,NULL,NULL,NULL),
 (3,'Traspaso','Traspasos',NULL,NULL,NULL,NULL),
 (4,'Arranque anual','Movimiento que representa al arranque anual',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `accountentrygroup` ENABLE KEYS */;


--
-- Definition of table `accountentrytype`
--

DROP TABLE IF EXISTS `accountentrytype`;
CREATE TABLE `accountentrytype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountEntryGroupId` int(11) NOT NULL COMMENT 'Account entry group',
  `name` varchar(256) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `accountEntryTypeId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `customizableId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_accountEntryType_accountEntryGroupId` (`accountEntryGroupId`),
  KEY `FK_accountentrytype_accountEntryTypeId` (`accountEntryTypeId`),
  CONSTRAINT `fk_accountEntryType_accountEntryGroupId` FOREIGN KEY (`accountEntryGroupId`) REFERENCES `accountentrygroup` (`id`),
  CONSTRAINT `FK_accountentrytype_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `accountentrytype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de movimientos';

--
-- Dumping data for table `accountentrytype`
--

/*!40000 ALTER TABLE `accountentrytype` DISABLE KEYS */;
INSERT INTO `accountentrytype` (`id`,`accountEntryGroupId`,`name`,`observations`,`accountEntryTypeId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`customizableId`) VALUES 
 (1,4,'Arranque inicial','Tipo de asiento que representa el arranque inicial de un año',NULL,NULL,NULL,NULL,NULL,NULL),
 (2,2,'Teléfono ','',NULL,1,1,'2009-04-06 11:51:42','2009-04-06 11:51:42',NULL),
 (3,2,'Teléfono fijo','',2,1,1,'2009-04-06 11:51:55','2009-04-06 11:51:55',NULL),
 (4,2,'Teléfono móvil ','',2,1,1,'2009-04-06 11:52:07','2009-04-06 11:52:07',NULL),
 (5,2,'Material oficina','',NULL,1,1,'2009-04-06 11:52:37','2009-04-06 11:52:37',NULL),
 (6,2,'Pago a proveedores','',NULL,1,1,'2009-04-06 11:52:57','2009-04-06 11:52:57',NULL),
 (7,1,'Cobro de clientes','',NULL,1,1,'2009-04-06 11:53:06','2009-04-06 11:53:06',NULL),
 (8,2,'Alquiler','',NULL,1,1,'2009-04-06 11:53:32','2009-04-06 11:53:32',NULL),
 (9,2,'Alquiler oficina','',8,1,1,'2009-04-06 11:53:44','2009-04-06 11:53:44',NULL),
 (10,2,'Alquiler coches','',8,1,1,'2009-04-06 11:53:58','2009-04-06 11:53:58',NULL),
 (11,2,'Alquiler equipos informaticos','',8,1,1,'2009-04-06 11:54:13','2009-04-06 11:54:13',NULL),
 (12,2,'Comisiones bancarias','',NULL,1,1,'2009-04-06 11:54:28','2009-04-06 11:54:28',NULL),
 (13,1,'Ingresos bancarios','',NULL,1,1,'2009-04-06 11:54:39','2009-04-06 11:54:39',NULL),
 (14,2,'Suministros','',NULL,1,1,'2009-04-07 09:54:10','2009-04-07 09:54:10',NULL),
 (15,2,'Luz','',14,1,1,'2009-04-07 09:54:19','2009-04-07 09:54:19',NULL),
 (16,2,'Pago a gestoría','',6,1,1,'2009-04-07 09:55:48','2009-04-07 09:55:48',NULL),
 (18,4,'Asiento Temporal','Este asiento es temporal',NULL,1,1,'2009-05-13 12:38:12','2009-05-13 12:38:12',0);
/*!40000 ALTER TABLE `accountentrytype` ENABLE KEYS */;


--
-- Definition of table `accounttype`
--

DROP TABLE IF EXISTS `accounttype`;
CREATE TABLE `accounttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account type descriptive name',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='lmacenan las cuentas';

--
-- Dumping data for table `accounttype`
--

/*!40000 ALTER TABLE `accounttype` DISABLE KEYS */;
INSERT INTO `accounttype` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Caja',NULL,NULL,NULL,NULL),
 (2,'Cuenta corriente',NULL,NULL,NULL,NULL),
 (3,'Cuenta de crédito',NULL,NULL,NULL,NULL),
 (4,'Depósito',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `accounttype` ENABLE KEYS */;


--
-- Definition of table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `startDate` datetime DEFAULT '0000-00-00 00:00:00',
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `roleId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_activity_userId` (`userId`),
  KEY `fk_activity_roleId` (`roleId`),
  CONSTRAINT `fk_activity_roleId` FOREIGN KEY (`roleId`) REFERENCES `projectrole` (`id`),
  CONSTRAINT `fk_activity_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Activityes de los Users';

--
-- Dumping data for table `activity`
--

/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` (`id`,`userId`,`startDate`,`duration`,`description`,`billable`,`roleId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,1,'2009-04-01 08:00:00',360,'Labores administrativas (contabilizar facturas recibidas y emitidas)',0,9,1,'2009-04-07 11:17:33','2009-04-07 11:17:44'),
 (2,1,'2009-04-02 08:00:00',360,'Laboresa administrativas (control TNT, ir al banco a ingresar talón, recepción de llamadas)',0,9,1,'2009-04-07 11:18:25','2009-04-07 11:18:25'),
 (3,1,'2009-04-03 08:00:00',360,'Labores administrativas (compra de material de oficina, contabilizar asientos y asociarlos)',0,9,1,'2009-04-07 11:18:58','2009-04-07 11:18:58'),
 (4,1,'2009-04-06 08:00:00',360,'Labores administrativas (comprobar estado de las facturas y seguimieno de las facturas pendientes de cobrar y de emitir)',0,9,1,'2009-04-13 13:37:33','2009-04-13 13:37:33'),
 (5,1,'2009-04-07 08:00:00',360,'Labores administrativas (cuadrar con la asesoríalos pagos de IVA, y de IRPF, comprobar el estado de las facturas recibidas, cuál están pagadas y cuáles no)',0,9,1,'2009-04-13 13:41:26','2009-04-13 13:41:26'),
 (6,1,'2009-04-08 08:00:00',360,'Permiso extraordinario (asistencia al especialista)',0,2,1,'2009-04-13 13:42:11','2009-04-13 13:42:11'),
 (7,1,'2009-04-13 08:00:00',360,'Labores administrativas (control TNT, compra de bebida para la empresa, compra de regalos de cumpleaños para empleados)',0,2,1,'2009-04-13 13:42:59','2009-04-13 13:43:06');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;


--
-- Definition of table `activityfile`
--

DROP TABLE IF EXISTS `activityfile`;
CREATE TABLE `activityfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalActivityId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `insertDate` datetime NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci NOT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_activityFile_externalActivity` (`externalActivityId`),
  KEY `fk_activityFile_user` (`userId`),
  CONSTRAINT `fk_activityFile_externalActivity` FOREIGN KEY (`externalActivityId`) REFERENCES `externalactivity` (`id`),
  CONSTRAINT `fk_activityFile_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `activityfile`
--

/*!40000 ALTER TABLE `activityfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `activityfile` ENABLE KEYS */;


--
-- Definition of table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationDate` date NOT NULL,
  `expiration` smallint(6) DEFAULT NULL,
  `paymentMode` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `file` varchar(512) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fileMime` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observations` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectId` int(11) NOT NULL DEFAULT '5' COMMENT 'project id',
  `startBillDate` date NOT NULL DEFAULT '1980-01-01',
  `endBillDate` date NOT NULL DEFAULT '1980-01-01',
  `billType` varchar(16) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'ISSUED',
  `orderNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  `providerId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `bookNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_bill_projectId` (`projectId`),
  KEY `ndx_bill_contactId` (`contactId`),
  KEY `ndx_bill_providerId` (`providerId`),
  CONSTRAINT `fk_bill_contactId` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_bill_projectId` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`),
  CONSTRAINT `fk_bill_providerId` FOREIGN KEY (`providerId`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `bill`
--

/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`id`,`creationDate`,`expiration`,`paymentMode`,`state`,`number`,`name`,`file`,`fileMime`,`observations`,`projectId`,`startBillDate`,`endBillDate`,`billType`,`orderNumber`,`contactId`,`providerId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`bookNumber`) VALUES 
 (1,'2009-03-01',31,'TRANSFER','EMITTED','01','Curso de formación básica impartido por nuestros técnicos en el mes de febrero',NULL,NULL,'',7,'2009-02-01','2009-02-28','ISSUED','',1,NULL,1,1,'2009-04-07 09:44:07','2009-04-07 09:44:07',NULL),
 (2,'2009-03-02',60,'TRANSFER','EMITTED','01/09','Material de oficina (folios, toner para la impresora)',NULL,NULL,'',8,'2009-03-02','2009-03-02','RECIEVED','',NULL,2,1,1,'2009-04-07 09:46:39','2009-04-07 09:46:39',''),
 (3,'2009-02-01',31,'MONEY','PAID','0101A/09','Compra de bolígrafos, post-it, archivadores.',NULL,NULL,'',8,'2009-02-01','2009-02-01','RECIEVED','',NULL,3,1,1,'2009-04-07 09:47:59','2009-04-07 09:49:16',''),
 (4,'2009-04-01',31,'TRANSFER','EMITTED','A03/09','Servicios prestados mes de marzo ',NULL,NULL,'',8,'2009-03-01','2009-03-31','RECIEVED','',NULL,9,1,1,'2009-04-07 09:56:34','2009-04-07 09:57:35',''),
 (5,'2009-02-01',31,'UNKNOWN','PAID','A01/09','Servicios prestados mes de enero ',NULL,NULL,'',8,'2009-01-01','2009-01-31','RECIEVED','',NULL,9,1,1,'2009-04-07 10:00:21','2009-04-07 10:01:25',''),
 (6,'2009-03-01',31,'UNKNOWN','PAID','A02/09','Servicios prestados mes de febrero ',NULL,NULL,'',8,'2009-02-01','2009-02-28','RECIEVED','',NULL,9,1,1,'2009-04-07 10:01:06','2009-04-07 10:01:44',''),
 (7,'2009-02-01',31,'UNKNOWN','PAID','T00001/09','Factura teléfono mes de enero ',NULL,NULL,'',8,'2009-01-01','2009-01-31','RECIEVED','',NULL,7,1,1,'2009-04-07 10:09:37','2009-04-07 10:10:31',''),
 (8,'2009-03-01',60,'PROMISSORYNOTE','IN_CREDITTITLE','02','Realización de nuestros servicios de consultoriía en sus oficinas en el mes de febrero',NULL,NULL,'',10,'2009-02-01','2009-02-28','ISSUED','',NULL,NULL,1,1,'2009-04-07 10:19:31','2009-04-14 10:32:07',NULL),
 (9,'2009-04-01',31,'UNKNOWN','EMITTED','03','Servicios de consultoría mes de febrero y marzo ',NULL,NULL,'Con fecha 7 de abril hacen el pago del 50% de la factura (2088euros)',11,'2009-02-01','2009-03-31','ISSUED','',NULL,NULL,1,1,'2009-04-07 10:22:01','2009-04-07 10:23:50',NULL),
 (10,'2009-04-07',31,'UNKNOWN','EMITTED','L9/09','Suministro de luz mes de marzo ',NULL,NULL,'',8,'2009-03-01','2009-03-31','RECIEVED','',NULL,8,1,1,'2009-04-07 10:25:13','2009-04-07 10:25:13',''),
 (11,'2009-04-01',45,'UNKNOWN','EMITTED','04','Desarrollo de proyecto según oferta 09012001',NULL,NULL,'',6,'2009-01-01','2009-03-31','ISSUED','',NULL,NULL,1,1,'2009-04-07 11:25:52','2009-04-07 11:25:52',NULL),
 (12,'2009-02-01',31,'UNKNOWN','PAID','0109','Alquiler oficina mes de febrero ',NULL,NULL,'',8,'2009-02-01','2009-02-28','RECIEVED','',NULL,11,1,1,'2009-04-07 14:46:57','2009-04-07 15:14:53',''),
 (13,'2009-03-01',31,'UNKNOWN','PAID','0209','Alquiler oficina mes de marzo ',NULL,NULL,'',8,'2009-03-01','2009-03-31','RECIEVED','',NULL,11,1,1,'2009-04-07 14:47:54','2009-04-07 15:15:29',''),
 (14,'2009-04-01',31,'UNKNOWN','EMITTED','0309','Alquiler oficina mes de abril ',NULL,NULL,'',8,'2009-04-01','2009-04-07','RECIEVED','',NULL,11,1,1,'2009-04-07 14:48:57','2009-04-07 14:48:57',''),
 (15,'2009-04-02',31,'UNKNOWN','PAID','05','Instalación del programa .',NULL,NULL,'',15,'2009-04-02','2009-04-02','ISSUED','',4,NULL,1,1,'2009-04-13 13:53:11','2009-04-13 13:53:55',NULL),
 (17,'2009-05-12',31,'UNKNOWN','EMITTED','001','Esto es una migracion de datos',NULL,NULL,'',19,'2009-05-12','2009-05-12','ISSUED','',6,NULL,1,1,'2009-05-13 12:41:31','2009-05-13 12:41:34',NULL);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;


--
-- Definition of table `bill_accountentry`
--

DROP TABLE IF EXISTS `bill_accountentry`;
CREATE TABLE `bill_accountentry` (
  `billId` int(11) NOT NULL COMMENT 'bill id',
  `accountEntryId` int(11) NOT NULL COMMENT 'account entry id',
  `observations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`billId`,`accountEntryId`),
  KEY `ndx_bill_AccountEntry_billId` (`billId`),
  KEY `ndx_bill_AccountEntry_accountEntryId` (`accountEntryId`),
  CONSTRAINT `fk_billAccountEntry_accountEntryId` FOREIGN KEY (`accountEntryId`) REFERENCES `accountentry` (`id`),
  CONSTRAINT `fk_billAccountEntry_billId` FOREIGN KEY (`billId`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='relaciona n m Facturas y movimientos';

--
-- Dumping data for table `bill_accountentry`
--

/*!40000 ALTER TABLE `bill_accountentry` DISABLE KEYS */;
INSERT INTO `bill_accountentry` (`billId`,`accountEntryId`,`observations`) VALUES 
 (3,1,NULL),
 (5,2,NULL),
 (6,3,NULL),
 (7,4,NULL),
 (12,6,NULL),
 (13,7,NULL),
 (15,8,NULL),
 (17,10,NULL);
/*!40000 ALTER TABLE `bill_accountentry` ENABLE KEYS */;


--
-- Definition of table `billbreakdown`
--

DROP TABLE IF EXISTS `billbreakdown`;
CREATE TABLE `billbreakdown` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `billId` int(11) NOT NULL,
  `concept` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `units` decimal(10,2) NOT NULL DEFAULT '1.00',
  `amount` decimal(10,2) NOT NULL,
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_billBreakDown_bill` (`billId`),
  CONSTRAINT `fk_billBreakDown_bill` FOREIGN KEY (`billId`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `billbreakdown`
--

/*!40000 ALTER TABLE `billbreakdown` DISABLE KEYS */;
INSERT INTO `billbreakdown` (`id`,`billId`,`concept`,`units`,`amount`,`iva`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,1,'Curso de formación mes de marzo ','1.00','3000.00','16.00',NULL,NULL,NULL,NULL),
 (2,2,'Material de oficina','1.00','225.15','16.00',NULL,NULL,NULL,NULL),
 (3,3,'Compra de bolígrafos, post-it, archivadores.','1.00','96.85','16.00',NULL,NULL,NULL,NULL),
 (4,4,'Servicios prestados mes de marzo ','1.00','250.00','16.00',NULL,NULL,NULL,NULL),
 (5,5,'Servicios prestados mes de enero ','1.00','250.00','16.00',NULL,NULL,NULL,NULL),
 (6,6,'Servicios prestados mes de febrero ','1.00','250.00','16.00',NULL,NULL,NULL,NULL),
 (7,7,'Factura teléfono mes de enero ','1.00','85.40','16.00',NULL,NULL,NULL,NULL),
 (8,8,'Servicios de consultoría mes de febrero ','1.00','600.00','16.00',NULL,NULL,NULL,NULL),
 (9,9,'Servicios de consultoría mes de febrero y  marzo ','1.00','3600.00','16.00',NULL,NULL,NULL,NULL),
 (10,10,'Suministro de luz mes de marzo ','1.00','36.00','16.00',NULL,NULL,NULL,NULL),
 (11,11,'Desarrollo de proyecto según oferta 09022001','1.00','6200.00','16.00',NULL,NULL,NULL,NULL),
 (12,12,'Alquiler oficina mes de febrero ','1.00','700.00','16.00',NULL,NULL,NULL,NULL),
 (13,13,'Alquiler oficina mes de marzo ','1.00','700.00','16.00',NULL,NULL,NULL,NULL),
 (14,14,'Alquiler oficina mes de abril ','1.00','700.00','16.00',NULL,NULL,NULL,NULL),
 (15,15,'Instalación del programa','1.00','1500.00','16.00',NULL,NULL,NULL,NULL),
 (17,17,'Creacion del proyecto','1.00','100.00','16.00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `billbreakdown` ENABLE KEYS */;


--
-- Definition of table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ISBN` varchar(13) COLLATE utf8_spanish_ci DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `purchaseDate` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Book_userId` (`userId`),
  CONSTRAINT `FK_Book_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `book`
--

/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`,`name`,`author`,`ISBN`,`URL`,`price`,`purchaseDate`,`userId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Guía de Estilos, protocolo y etiqueta en la empresa','Pilar Benito San Cristobal','9788493602871','','30.00','2009-04-14 00:00:00',2,1,1,'2009-04-14 12:12:28','2009-04-14 12:12:28'),
 (2,'Leyendas urbanas en España','Antonio Ortí y Josep Samperez','8427025661','',NULL,'2009-04-14 00:00:00',1,1,1,'2009-04-14 12:13:53','2009-04-14 12:13:53'),
 (3,'Liderazgo estratégico','Guillem Bou Bauzá','8436819098','',NULL,'2009-04-14 00:00:00',1,1,1,'2009-04-14 12:14:27','2009-04-14 12:14:27');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


--
-- Definition of table `bulletinboard`
--

DROP TABLE IF EXISTS `bulletinboard`;
CREATE TABLE `bulletinboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `message` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `documentPath` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `documentContentType` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_bulletinboard_categoryId` (`categoryId`),
  KEY `ndx_bulletinboard_userId` (`userId`),
  CONSTRAINT `fk_bulletinboard_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `bulletinboardcategory` (`id`),
  CONSTRAINT `fk_bulletinboard_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='mensajes del tabln';

--
-- Dumping data for table `bulletinboard`
--

/*!40000 ALTER TABLE `bulletinboard` DISABLE KEYS */;
INSERT INTO `bulletinboard` (`id`,`categoryId`,`userId`,`creationDate`,`message`,`title`,`documentPath`,`documentContentType`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,1,1,'2009-05-05 00:00:00','Liberada nueva versión de TNT Concept','Liberada versión 016.1',NULL,NULL,1,'2009-05-05 10:26:14','2009-05-05 10:30:01');
/*!40000 ALTER TABLE `bulletinboard` ENABLE KEYS */;


--
-- Definition of table `bulletinboardcategory`
--

DROP TABLE IF EXISTS `bulletinboardcategory`;
CREATE TABLE `bulletinboardcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las categoras';

--
-- Dumping data for table `bulletinboardcategory`
--

/*!40000 ALTER TABLE `bulletinboardcategory` DISABLE KEYS */;
INSERT INTO `bulletinboardcategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Pública',NULL,NULL,NULL,NULL),
 (2,'General',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bulletinboardcategory` ENABLE KEYS */;


--
-- Definition of table `collaborator`
--

DROP TABLE IF EXISTS `collaborator`;
CREATE TABLE `collaborator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(10) DEFAULT NULL,
  `contactId` int(10) DEFAULT NULL,
  `organizationId` int(10) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_collaborator_user` (`userId`),
  KEY `fk_collaborator_contact` (`contactId`),
  KEY `fk_collaborator_organization` (`organizationId`),
  CONSTRAINT `fk_collaborator_contact` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_collaborator_organization` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`),
  CONSTRAINT `fk_collaborator_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `collaborator`
--

/*!40000 ALTER TABLE `collaborator` DISABLE KEYS */;
INSERT INTO `collaborator` (`id`,`userId`,`contactId`,`organizationId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,NULL,1,NULL,1,1,'2009-04-13 14:17:49','2009-04-13 14:17:49'),
 (2,NULL,NULL,13,1,1,'2009-04-13 14:30:01','2009-04-13 14:30:01'),
 (3,NULL,NULL,14,1,1,'2009-04-13 15:07:39','2009-04-13 15:07:39'),
 (5,4,NULL,NULL,1,1,'2009-05-13 12:40:07','2009-05-13 12:40:07');
/*!40000 ALTER TABLE `collaborator` ENABLE KEYS */;


--
-- Definition of table `commissioning`
--

DROP TABLE IF EXISTS `commissioning`;
CREATE TABLE `commissioning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requestDate` datetime NOT NULL,
  `name` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `scope` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `content` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `products` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `deliveryDate` datetime NOT NULL,
  `budget` decimal(10,2) DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `authorSignature` tinyint(1) NOT NULL DEFAULT '0',
  `reviserSignature` tinyint(1) NOT NULL DEFAULT '0',
  `adminSignature` tinyint(1) NOT NULL DEFAULT '0',
  `justifyInformation` tinyint(1) NOT NULL DEFAULT '0',
  `developedActivities` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `difficultiesAppeared` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `results` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `conclusions` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `evaluation` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioning_project` (`projectId`),
  CONSTRAINT `fk_commissioning_project` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `commissioning`
--

/*!40000 ALTER TABLE `commissioning` DISABLE KEYS */;
INSERT INTO `commissioning` (`id`,`requestDate`,`name`,`scope`,`content`,`products`,`deliveryDate`,`budget`,`notes`,`authorSignature`,`reviserSignature`,`adminSignature`,`justifyInformation`,`developedActivities`,`difficultiesAppeared`,`results`,`conclusions`,`evaluation`,`status`,`projectId`,`insertDate`,`updateDate`,`deleteDate`) VALUES 
 (1,'2009-04-01 00:00:00','ISO 9001','Obtención del certificado de calidad ISO 9001','Documentación del sistema de gestión de calidad, procedimientos y dos auditorías internas.','','2009-06-01 00:00:00','5900.00','',0,0,1,0,NULL,NULL,NULL,NULL,NULL,'ACCEPTED',8,'2009-04-13 14:34:54','2009-04-13 14:39:52','2009-04-13 14:39:52'),
 (2,'2009-04-01 00:00:00','Pagina WEB','Creación página web de nuestra empresa','','Pagina web','2009-05-15 00:00:00','2800.00','',1,0,1,0,NULL,NULL,NULL,NULL,NULL,'CONFIRMED',17,'2009-04-13 15:08:41','2009-04-13 15:12:34',NULL),
 (3,'2009-05-13 00:00:00','Migración SW','','Migrar todo el SW y base de datos','','2009-05-15 00:00:00','0.00','',0,0,0,0,NULL,NULL,NULL,NULL,NULL,'CREATED',4,'2009-05-13 12:42:01','2009-05-13 12:42:07','2009-05-13 12:42:07'),
 (4,'2009-05-14 00:00:00','Migración SW','','','','2009-05-15 00:00:00','0.00','',0,0,0,0,NULL,NULL,NULL,NULL,NULL,'CREATED',4,'2009-05-14 13:18:47','2009-05-14 13:21:18','2009-05-14 13:21:18'),
 (5,'2009-05-14 00:00:00','Migración SW','','','','2009-05-15 00:00:00','0.00','',0,0,0,0,NULL,NULL,NULL,NULL,NULL,'CREATED',4,'2009-05-14 13:20:42','2009-05-14 13:21:00',NULL),
 (6,'2009-05-14 00:00:00','Migración SW','','','','2009-05-15 00:00:00','0.00','',0,0,0,0,NULL,NULL,NULL,NULL,NULL,'CREATED',4,'2009-05-14 13:27:28','2009-05-14 13:27:28',NULL),
 (7,'2009-05-14 00:00:00','Migración SW','','','','2009-05-15 00:00:00','0.00','',0,0,0,0,NULL,NULL,NULL,NULL,NULL,'CREATED',4,'2009-05-14 13:29:53','2009-05-14 13:29:55',NULL);
/*!40000 ALTER TABLE `commissioning` ENABLE KEYS */;


--
-- Definition of table `commissioning_user`
--

DROP TABLE IF EXISTS `commissioning_user`;
CREATE TABLE `commissioning_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commissioningId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioning_user_commissioning` (`commissioningId`),
  KEY `fk_commissioning_user_user` (`userId`),
  CONSTRAINT `fk_commissioning_user_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `commissioning` (`id`),
  CONSTRAINT `fk_commissioning_user_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `commissioning_user`
--

/*!40000 ALTER TABLE `commissioning_user` DISABLE KEYS */;
INSERT INTO `commissioning_user` (`id`,`commissioningId`,`userId`) VALUES 
 (9,1,1),
 (10,1,3),
 (18,2,1),
 (19,3,1),
 (20,4,1),
 (21,5,4),
 (22,5,1),
 (23,6,4),
 (24,6,1),
 (25,7,4),
 (26,7,1);
/*!40000 ALTER TABLE `commissioning_user` ENABLE KEYS */;


--
-- Definition of table `commissioningchange`
--

DROP TABLE IF EXISTS `commissioningchange`;
CREATE TABLE `commissioningchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `commissioningId` int(10) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioningChange_commissioning` (`commissioningId`),
  KEY `fk_commissioningChange_user` (`userId`),
  CONSTRAINT `fk_commissioningChange_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `commissioning` (`id`),
  CONSTRAINT `fk_commissioningChange_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `commissioningchange`
--

/*!40000 ALTER TABLE `commissioningchange` DISABLE KEYS */;
INSERT INTO `commissioningchange` (`id`,`field`,`oldValue`,`newValue`,`commissioningId`,`userId`,`insertDate`,`updateDate`,`status`,`deleteDate`) VALUES 
 (1,'commissioning.authorSignature','false','true',2,1,'2009-04-13 15:12:34',NULL,'CONFIRMED',NULL);
/*!40000 ALTER TABLE `commissioningchange` ENABLE KEYS */;


--
-- Definition of table `commissioningdelay`
--

DROP TABLE IF EXISTS `commissioningdelay`;
CREATE TABLE `commissioningdelay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `originalDate` datetime NOT NULL,
  `delayedToDate` datetime NOT NULL,
  `commissioningId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioningDelay_commissioning` (`commissioningId`),
  CONSTRAINT `fk_commissioningDelay_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `commissioning` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `commissioningdelay`
--

/*!40000 ALTER TABLE `commissioningdelay` DISABLE KEYS */;
INSERT INTO `commissioningdelay` (`id`,`reason`,`originalDate`,`delayedToDate`,`commissioningId`,`insertDate`,`updateDate`,`status`) VALUES 
 (1,'acumulación de tareas','2009-06-01 00:00:00','2009-06-01 00:00:00',1,'2009-04-13 14:38:11','2009-04-13 14:38:11','ACCEPTED'),
 (2,'Acumulación de tareas','2009-05-15 00:00:00','2009-05-15 00:00:00',2,'2009-04-13 15:09:25','2009-04-13 15:09:25','APROVED');
/*!40000 ALTER TABLE `commissioningdelay` ENABLE KEYS */;


--
-- Definition of table `commissioningfile`
--

DROP TABLE IF EXISTS `commissioningfile`;
CREATE TABLE `commissioningfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commissioningId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `insertDate` datetime NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci NOT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioningFile_commissioning` (`commissioningId`),
  KEY `fk_commissioningFile_user` (`userId`),
  CONSTRAINT `fk_commissioningFile_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `commissioning` (`id`),
  CONSTRAINT `fk_commissioningFile_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `commissioningfile`
--

/*!40000 ALTER TABLE `commissioningfile` DISABLE KEYS */;
INSERT INTO `commissioningfile` (`id`,`commissioningId`,`userId`,`insertDate`,`updateDate`,`file`,`fileMime`) VALUES 
 (1,5,1,'2009-05-14 13:21:00',NULL,'insertCommissioning.html','text/html'),
 (2,7,1,'2009-05-14 13:29:55',NULL,'DocumentExample.TXT','application/octet-stream');
/*!40000 ALTER TABLE `commissioningfile` ENABLE KEYS */;


--
-- Definition of table `commissioningpaymentdata`
--

DROP TABLE IF EXISTS `commissioningpaymentdata`;
CREATE TABLE `commissioningpaymentdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commissioningId` int(10) NOT NULL,
  `collaboratorId` int(11) NOT NULL,
  `paymentMode` varchar(32) COLLATE utf8_spanish_ci DEFAULT NULL,
  `bankAccount` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `billNumber` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioning_collaborator_commissioning` (`commissioningId`),
  KEY `fk_commissioning_collaborator_collaborator` (`collaboratorId`),
  CONSTRAINT `fk_commissioning_collaborator_collaborator` FOREIGN KEY (`collaboratorId`) REFERENCES `collaborator` (`id`),
  CONSTRAINT `fk_commissioning_collaborator_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `commissioning` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `commissioningpaymentdata`
--

/*!40000 ALTER TABLE `commissioningpaymentdata` DISABLE KEYS */;
INSERT INTO `commissioningpaymentdata` (`id`,`commissioningId`,`collaboratorId`,`paymentMode`,`bankAccount`,`billNumber`,`insertDate`,`updateDate`) VALUES 
 (1,1,2,'UNKNOWN','','',NULL,NULL),
 (2,2,3,'MONEY','','01AB',NULL,NULL),
 (3,3,5,'UNKNOWN','','',NULL,NULL),
 (4,4,5,'UNKNOWN','','',NULL,NULL);
/*!40000 ALTER TABLE `commissioningpaymentdata` ENABLE KEYS */;


--
-- Definition of table `companystate`
--

DROP TABLE IF EXISTS `companystate`;
CREATE TABLE `companystate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT 'aplicacin que manda el mail',
  `creationDate` datetime NOT NULL,
  `description` longtext COLLATE utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_companystate_userId` (`userId`),
  CONSTRAINT `fk_companystate_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='comentario del director de empresa';

--
-- Dumping data for table `companystate`
--

/*!40000 ALTER TABLE `companystate` DISABLE KEYS */;
INSERT INTO `companystate` (`id`,`userId`,`creationDate`,`description`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,1,'2009-04-01 00:00:00','Liberada versión TNT CONCEPT',1,'2009-05-05 10:29:07','2009-05-05 10:29:07');
/*!40000 ALTER TABLE `companystate` ENABLE KEYS */;


--
-- Definition of table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notified` tinyint(1) NOT NULL DEFAULT '0',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `email2` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone2` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_contact_provinceId` (`provinceId`),
  CONSTRAINT `fk_contact_province` FOREIGN KEY (`provinceId`) REFERENCES `province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos de las Organizationes';

--
-- Dumping data for table `contact`
--

/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`,`name`,`email`,`phone`,`mobile`,`notified`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`email2`,`phone2`,`fax`,`address`,`postalCode`,`city`,`country`,`provinceId`,`active`) VALUES 
 (1,'Ana Sánchez ','ana.s@atrilcb.es','91','91',0,1,1,'2009-04-06 12:05:32','2009-04-06 12:05:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (2,'Antonio Ruiz-S','a.ruiz@tecnologiasespaña.es','93','93',0,1,1,'2009-04-06 12:06:19','2009-04-06 12:06:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (3,'Pilar M. de Sancho','pilar.me@suministros.es','91','91',0,1,1,'2009-04-06 12:07:22','2009-04-06 12:07:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (4,'Ignacio Pérez Cuerda','ignacio.perez@fundación.es','921','600',0,1,1,'2009-04-13 13:48:59','2009-04-13 13:48:59',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),
 (6,'Víctor Madrid','','','',0,1,1,'2009-05-13 12:39:21','2009-05-13 12:39:21','','','','','','','',1,1);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;


--
-- Definition of table `contact_tag`
--

DROP TABLE IF EXISTS `contact_tag`;
CREATE TABLE `contact_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contact_tag_tag` (`tagId`),
  KEY `fk_contact_tag_contact` (`contactId`),
  CONSTRAINT `fk_contact_tag_contact` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_contact_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `contact_tag`
--

/*!40000 ALTER TABLE `contact_tag` DISABLE KEYS */;
INSERT INTO `contact_tag` (`id`,`tagId`,`contactId`) VALUES 
 (1,3,6);
/*!40000 ALTER TABLE `contact_tag` ENABLE KEYS */;


--
-- Definition of table `contactchange`
--

DROP TABLE IF EXISTS `contactchange`;
CREATE TABLE `contactchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `contactId` int(10) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contactChange_contact` (`contactId`),
  KEY `fk_contactChange_user` (`userId`),
  CONSTRAINT `fk_contactChange_contact` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_contactChange_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `contactchange`
--

/*!40000 ALTER TABLE `contactchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactchange` ENABLE KEYS */;


--
-- Definition of table `contacthistory`
--

DROP TABLE IF EXISTS `contacthistory`;
CREATE TABLE `contacthistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactId` int(11) NOT NULL,
  `name` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notified` tinyint(1) NOT NULL DEFAULT '0',
  `email2` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone2` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `province` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `position` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `department` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `organization` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contacthistory_contact` (`contactId`),
  KEY `fk_contacthistory_user` (`userId`),
  CONSTRAINT `fk_contacthistory_contact` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_contacthistory_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Histórico de cambios de Contactos';

--
-- Dumping data for table `contacthistory`
--

/*!40000 ALTER TABLE `contacthistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacthistory` ENABLE KEYS */;


--
-- Definition of table `contactinfo`
--

DROP TABLE IF EXISTS `contactinfo`;
CREATE TABLE `contactinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contactinfo_contact` (`contactId`),
  KEY `fk_contactinfo_position` (`positionId`),
  KEY `fk_contactinfo_department` (`departmentId`),
  KEY `fk_contactinfo_organization` (`organizationId`),
  CONSTRAINT `fk_contactinfo_contact` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_contactinfo_department` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_contactinfo_organization` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`),
  CONSTRAINT `fk_contactinfo_position` FOREIGN KEY (`positionId`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `contactinfo`
--

/*!40000 ALTER TABLE `contactinfo` DISABLE KEYS */;
INSERT INTO `contactinfo` (`id`,`contactId`,`positionId`,`departmentId`,`organizationId`) VALUES 
 (1,1,2,4,5),
 (2,2,3,4,4),
 (3,3,4,4,3),
 (4,4,5,4,12),
 (8,6,9,7,17);
/*!40000 ALTER TABLE `contactinfo` ENABLE KEYS */;


--
-- Definition of table `contactowner`
--

DROP TABLE IF EXISTS `contactowner`;
CREATE TABLE `contactowner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contactowner_contactId` (`contactId`),
  KEY `fk_contactowner_userId` (`userId`),
  CONSTRAINT `fk_contactowner_contactId` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_contactowner_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Propietarios de contactos';

--
-- Dumping data for table `contactowner`
--

/*!40000 ALTER TABLE `contactowner` DISABLE KEYS */;
INSERT INTO `contactowner` (`id`,`contactId`,`userId`) VALUES 
 (1,1,1),
 (2,2,1),
 (3,3,1),
 (4,4,1),
 (5,6,1);
/*!40000 ALTER TABLE `contactowner` ENABLE KEYS */;


--
-- Definition of table `contracttype`
--

DROP TABLE IF EXISTS `contracttype`;
CREATE TABLE `contracttype` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `contracttype`
--

/*!40000 ALTER TABLE `contracttype` DISABLE KEYS */;
INSERT INTO `contracttype` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Prácticas','Departamento de dirección.',NULL,NULL,NULL,NULL),
 (2,'Duración determinada','Departamento de dirección.',NULL,NULL,NULL,NULL),
 (3,'Indefinido','Departamento de dirección.',NULL,NULL,NULL,NULL),
 (5,'Obra','El tiempo que dure la obra',1,1,'2009-05-13 12:38:17','2009-05-13 12:38:17');
/*!40000 ALTER TABLE `contracttype` ENABLE KEYS */;


--
-- Definition of table `credittitle`
--

DROP TABLE IF EXISTS `credittitle`;
CREATE TABLE `credittitle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `concept` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `type` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `issueDate` date NOT NULL,
  `expirationDate` date DEFAULT NULL,
  `organizationId` int(11) NOT NULL,
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_credittitle_organizationId` (`organizationId`),
  CONSTRAINT `fk_credittitle_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `credittitle`
--

/*!40000 ALTER TABLE `credittitle` DISABLE KEYS */;
INSERT INTO `credittitle` (`id`,`number`,`concept`,`amount`,`state`,`type`,`issueDate`,`expirationDate`,`organizationId`,`observations`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'S001S002','Cobro factura 02','696.00','EMITTED','ISSUED','2009-04-04','2009-04-15',6,'',1,1,'2009-04-07 11:21:25','2009-04-07 11:21:38'),
 (3,'Pagare Mensual','Varios','150.00','EMITTED','ISSUED','2009-05-29','2009-05-27',5,'',1,1,'2009-05-13 12:41:07','2009-05-13 12:41:42');
/*!40000 ALTER TABLE `credittitle` ENABLE KEYS */;


--
-- Definition of table `credittitle_bill`
--

DROP TABLE IF EXISTS `credittitle_bill`;
CREATE TABLE `credittitle_bill` (
  `billId` int(11) NOT NULL,
  `creditTitleId` int(11) NOT NULL,
  PRIMARY KEY (`billId`,`creditTitleId`),
  KEY `ndx_creditTitle_Bill_billId` (`billId`),
  KEY `ndx_creditTitle_Bill_creditTitleId` (`creditTitleId`),
  CONSTRAINT `fk_creditTitle_Bill_billId` FOREIGN KEY (`billId`) REFERENCES `bill` (`id`),
  CONSTRAINT `fk_creditTitle_Bill_creditTitleId` FOREIGN KEY (`creditTitleId`) REFERENCES `credittitle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `credittitle_bill`
--

/*!40000 ALTER TABLE `credittitle_bill` DISABLE KEYS */;
INSERT INTO `credittitle_bill` (`billId`,`creditTitleId`) VALUES 
 (8,1),
 (17,3);
/*!40000 ALTER TABLE `credittitle_bill` ENABLE KEYS */;


--
-- Definition of table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parentId` int(10) unsigned DEFAULT NULL,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_department_department` (`parentId`),
  CONSTRAINT `fk_department_department` FOREIGN KEY (`parentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`,`parentId`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,NULL,'Dirección','Departamento de dirección.',NULL,NULL,NULL,NULL),
 (2,1,'I+D+I','Departamento de I+D+I.',NULL,NULL,NULL,NULL),
 (3,1,'Consultoría','Departamento de Consultoría.',NULL,NULL,NULL,NULL),
 (4,NULL,'Indefinido','Departamento sin definir',1,1,'2009-05-08 11:43:39','2009-05-08 11:43:39'),
 (7,3,'Open Source','Departamento de código libre',1,1,'2009-05-13 12:36:59','2009-05-13 12:36:59');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Definition of table `department_organization`
--

DROP TABLE IF EXISTS `department_organization`;
CREATE TABLE `department_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentId` int(10) unsigned NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department_organization_department` (`departmentId`),
  KEY `fk_department_organization_organization` (`organizationId`),
  CONSTRAINT `fk_department_organization_department` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_department_organization_organization` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `department_organization`
--

/*!40000 ALTER TABLE `department_organization` DISABLE KEYS */;
INSERT INTO `department_organization` (`id`,`departmentId`,`organizationId`) VALUES 
 (1,7,17);
/*!40000 ALTER TABLE `department_organization` ENABLE KEYS */;


--
-- Definition of table `department_tag`
--

DROP TABLE IF EXISTS `department_tag`;
CREATE TABLE `department_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department_tag_tag` (`tagId`),
  KEY `fk_department_tag_department` (`departmentId`),
  CONSTRAINT `fk_department_tag_department` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_department_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `department_tag`
--

/*!40000 ALTER TABLE `department_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_tag` ENABLE KEYS */;


--
-- Definition of table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `document`
--

/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;


--
-- Definition of table `documentcategory`
--

DROP TABLE IF EXISTS `documentcategory`;
CREATE TABLE `documentcategory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `code` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `categoryid` int(10) unsigned DEFAULT NULL,
  `documentslastupdate` datetime DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `documentcategory`
--

/*!40000 ALTER TABLE `documentcategory` DISABLE KEYS */;
INSERT INTO `documentcategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Documentos de Calidad','Documentos de calidad','CALIDAD',NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Otros Documentos','Otros documentos no clasificados',' ',NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'Curriculum Vitae','','',NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'Documentos de Usuarios','','',NULL,NULL,NULL,NULL,NULL,NULL),
 (10,'(I1-PC01) Lista de distribución de la documentación','','I1-PC01',1,'2009-05-13 12:42:31',NULL,NULL,NULL,NULL),
 (11,'(I1-PC02) Acta de reunión de revisión del sistema','','I1-PC02',1,NULL,NULL,NULL,NULL,NULL),
 (12,'(I1-PC08) Informe de auditoría interna','','I1-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (13,'(I2-PC02) Planificación de objetivos de calidad','','I2-PC02',1,NULL,NULL,NULL,NULL,NULL),
 (14,'(I2-PC08) Informe de no conformidad/reclamación del cliente','','I2-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (15,'(I3-PC08) Informe de acción correctiva/preventiva','','I3-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (16,'(PC01) Sistema de Gestión de la Calidad','Documento inicial de descripción','PC01',1,NULL,NULL,NULL,NULL,NULL),
 (17,'(PC02) Responsabilidad de la Dirección','','PC02',1,NULL,NULL,NULL,NULL,NULL),
 (18,'(PC03) Gestión de los Recursos','','PC03',1,NULL,NULL,NULL,NULL,NULL),
 (19,'(PC04) Procesos relacionados con los clientes','','PC04',1,NULL,NULL,NULL,NULL,NULL),
 (20,'(PC05) Gestión de compras','','PC05',1,NULL,NULL,NULL,NULL,NULL),
 (21,'(PC06) Evaluación de proveedores y subcontratistas','','PC06',1,NULL,NULL,NULL,NULL,NULL),
 (22,'(PC07) Prestación del servicio','','PC07',1,NULL,NULL,NULL,NULL,NULL),
 (23,'(PC08) Medición análisis y mejora','','PC08',1,NULL,NULL,NULL,NULL,NULL),
 (24,'Control documentación entregada y recibida','','',1,NULL,NULL,NULL,NULL,NULL),
 (25,'Criterio de evaluación y seguimiento de procesos','','',1,NULL,NULL,NULL,NULL,NULL),
 (26,'Cuestionario de satisfacción del cliente','','',1,NULL,NULL,NULL,NULL,NULL),
 (27,'E-mail aprobación documentación','','',1,NULL,NULL,NULL,NULL,NULL),
 (28,'E-mail de comunicaciones','','',1,NULL,NULL,NULL,NULL,NULL),
 (29,'Ficha de mantenimiento de equipos','','',1,NULL,NULL,NULL,NULL,NULL),
 (30,'Índice de ediciones de documentos','','',1,NULL,NULL,NULL,NULL,NULL),
 (31,'Índice de no conformidades','','',1,NULL,NULL,NULL,NULL,NULL),
 (32,'Inventario de recursos','','',1,NULL,NULL,NULL,NULL,NULL),
 (33,'Listado de documentación externa en vigor','','',1,NULL,NULL,NULL,NULL,NULL),
 (34,'Listado de proveedores y subcontratistas evaluados','','',1,NULL,NULL,NULL,NULL,NULL),
 (35,'Listado de registros','','',1,NULL,NULL,NULL,NULL,NULL),
 (36,'Manual de Gestión de la Calidad (MGC)','','MGC',1,NULL,NULL,NULL,NULL,NULL),
 (37,'Perfil del empleado','','',1,NULL,NULL,NULL,NULL,NULL),
 (38,'Perfil puesto trabajo','','',1,NULL,NULL,NULL,NULL,NULL),
 (39,'Plan de auditoría anual','','',1,NULL,NULL,NULL,NULL,NULL),
 (40,'Plan de formación anual','','',1,NULL,NULL,NULL,NULL,NULL),
 (41,'Política de Calidad','','',1,NULL,NULL,NULL,NULL,NULL),
 (42,'(I5-PC03) Registro perfil del empleado','','I5-PC03',1,NULL,NULL,NULL,NULL,NULL),
 (43,'(I8-PC03) Cuestionario de satisfacción laboral','','I8-PC03',1,NULL,NULL,NULL,NULL,NULL),
 (44,'(I6-PC08) Evaluación de satisfacción del cliente','','I6-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (45,'Jesus Sánchez Gómez','Jesus Sánchez Gómez',NULL,4,NULL,NULL,NULL,NULL,NULL),
 (46,'Natalia Rincón San García','Natalia Rincón San García',NULL,4,NULL,NULL,NULL,NULL,NULL),
 (47,'Jornada de LOPD','Jornada de LOPD',NULL,4,NULL,NULL,NULL,NULL,NULL),
 (48,'Daniel Madrid Triviño','Daniel Madrid Triviño',NULL,4,'2009-05-13 12:36:13',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `documentcategory` ENABLE KEYS */;


--
-- Definition of table `documentcategorydoc`
--

DROP TABLE IF EXISTS `documentcategorydoc`;
CREATE TABLE `documentcategorydoc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categoryid` int(10) unsigned NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentCategorydoc_category` (`categoryid`),
  KEY `FK_DocumentCategorydoc_docu` (`documentid`),
  CONSTRAINT `FK_DocumentCategorydoc_category` FOREIGN KEY (`categoryid`) REFERENCES `documentcategory` (`id`),
  CONSTRAINT `FK_DocumentCategorydoc_docu` FOREIGN KEY (`documentid`) REFERENCES `document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documentcategorydoc`
--

/*!40000 ALTER TABLE `documentcategorydoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `documentcategorydoc` ENABLE KEYS */;


--
-- Definition of table `documentversion`
--

DROP TABLE IF EXISTS `documentversion`;
CREATE TABLE `documentversion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `documentPath` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `creationDate` datetime NOT NULL,
  `version` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentVersion_document_id` (`documentid`),
  CONSTRAINT `FK_DocumentVersion_document_id` FOREIGN KEY (`documentid`) REFERENCES `document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `documentversion`
--

/*!40000 ALTER TABLE `documentversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `documentversion` ENABLE KEYS */;


--
-- Definition of table `externalactivity`
--

DROP TABLE IF EXISTS `externalactivity`;
CREATE TABLE `externalactivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `category` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `location` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `organizer` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `comments` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `documentCategoryId` int(10) unsigned DEFAULT NULL,
  `ownerId` int(10) DEFAULT NULL,
  `departmentId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_externalactivity_documentcategory` (`documentCategoryId`),
  CONSTRAINT `fk_externalactivity_documentcategory` FOREIGN KEY (`documentCategoryId`) REFERENCES `documentcategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `externalactivity`
--

/*!40000 ALTER TABLE `externalactivity` DISABLE KEYS */;
INSERT INTO `externalactivity` (`id`,`name`,`category`,`startDate`,`endDate`,`location`,`organizer`,`comments`,`documentCategoryId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Jornada de LOPD','Charla','2009-04-06 17:00:00','2009-04-06 19:00:00','Centro Puerta de Toledo en Madrid.','Asociación Jóvenes Empresarios de Madrid','Observaciones',47,1,1,'2009-04-13 13:38:40','2009-04-13 13:38:40');
/*!40000 ALTER TABLE `externalactivity` ENABLE KEYS */;


--
-- Definition of table `financialratio`
--

DROP TABLE IF EXISTS `financialratio`;
CREATE TABLE `financialratio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `ratioDate` date NOT NULL,
  `banksAccounts` decimal(10,2) NOT NULL,
  `customers` decimal(10,2) NOT NULL,
  `stocks` decimal(10,2) NOT NULL,
  `amortizations` decimal(10,2) NOT NULL,
  `infrastructures` decimal(10,2) NOT NULL,
  `shortTermLiability` decimal(10,2) NOT NULL,
  `obligationBond` decimal(10,2) NOT NULL,
  `capital` decimal(10,2) NOT NULL,
  `reserves` decimal(10,2) NOT NULL,
  `incomes` decimal(10,2) NOT NULL,
  `expenses` decimal(10,2) NOT NULL,
  `otherExploitationExpenses` decimal(10,2) NOT NULL,
  `financialExpenses` decimal(10,2) NOT NULL,
  `taxes` decimal(10,2) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ratios financieros';

--
-- Dumping data for table `financialratio`
--

/*!40000 ALTER TABLE `financialratio` DISABLE KEYS */;
/*!40000 ALTER TABLE `financialratio` ENABLE KEYS */;


--
-- Definition of table `frequency`
--

DROP TABLE IF EXISTS `frequency`;
CREATE TABLE `frequency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `months` int(10) unsigned DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Frecuencia de los asientos contables';

--
-- Dumping data for table `frequency`
--

/*!40000 ALTER TABLE `frequency` DISABLE KEYS */;
INSERT INTO `frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Mensual',1,NULL,NULL,NULL,NULL),
 (2,'Trimestral',3,NULL,NULL,NULL,NULL),
 (3,'Semestral',6,NULL,NULL,NULL,NULL),
 (4,'Anual',12,NULL,NULL,NULL,NULL),
 (5,'Bimensual',2,NULL,NULL,NULL,NULL),
 (6,'Ocasional',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `frequency` ENABLE KEYS */;


--
-- Definition of table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `date` datetime NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `holiday`
--

/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
INSERT INTO `holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Reyes','2009-01-06 00:00:00',1,1,'2009-04-06 12:10:09','2009-04-06 12:10:09'),
 (2,'San Jose Obrero (día del padre)','2009-03-19 00:00:00',1,1,'2009-04-06 12:10:30','2009-04-06 12:10:30'),
 (3,'Jueves Santo','2009-04-09 00:00:00',1,1,'2009-04-06 12:10:44','2009-04-06 12:10:44'),
 (4,'Viernes Santo','2009-04-10 00:00:00',1,1,'2009-04-06 12:11:01','2009-04-06 12:11:01'),
 (5,'Día del trabajador','2009-05-01 00:00:00',1,1,'2009-04-06 12:11:36','2009-04-06 12:11:36'),
 (6,'Comunidad de Madrid','2009-05-02 00:00:00',1,1,'2009-04-06 12:11:47','2009-04-06 12:11:47'),
 (7,'San Isidro','2005-05-15 00:00:00',1,1,'2009-04-06 12:11:56','2009-04-06 12:11:56'),
 (8,'Corpus Christi','2009-06-11 00:00:00',1,1,'2009-04-06 12:12:15','2009-04-06 12:12:15'),
 (9,'Hispanidad','2009-10-12 00:00:00',1,1,'2009-04-06 12:12:43','2009-04-06 12:12:43'),
 (10,'La Almudena','2009-11-09 00:00:00',1,1,'2009-04-06 12:13:05','2009-04-06 12:13:05'),
 (11,'La Inmaculada','2009-12-08 00:00:00',1,1,'2009-04-06 12:13:19','2009-04-06 12:13:19'),
 (12,'Navidad','2009-12-25 00:00:00',1,1,'2009-04-06 12:13:29','2009-04-06 12:13:29'),
 (13,'Fiesta Nacional','2009-10-12 00:00:00',1,1,'2009-04-06 12:16:37','2009-04-06 12:16:37'),
 (14,'Asunción de La Virgen','2009-05-15 00:00:00',1,1,'2009-04-06 12:18:27','2009-04-06 12:18:27');
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;


--
-- Definition of table `idea`
--

DROP TABLE IF EXISTS `idea`;
CREATE TABLE `idea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `cost` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `benefits` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(300) COLLATE utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_idea_userId` (`userId`),
  CONSTRAINT `fk_idea_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ideas de los empleados';

--
-- Dumping data for table `idea`
--

/*!40000 ALTER TABLE `idea` DISABLE KEYS */;
/*!40000 ALTER TABLE `idea` ENABLE KEYS */;


--
-- Definition of table `interaction`
--

DROP TABLE IF EXISTS `interaction`;
CREATE TABLE `interaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL DEFAULT '5' COMMENT 'project id',
  `userId` int(11) NOT NULL DEFAULT '1' COMMENT 'user id',
  `interactionTypeId` int(11) NOT NULL DEFAULT '6' COMMENT 'type id',
  `creationDate` datetime NOT NULL,
  `interest` varchar(16) COLLATE utf8_spanish_ci NOT NULL COMMENT 'enum InteractionInterest',
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `offerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_interaction_projectId` (`projectId`),
  KEY `ndx_interaction_userId` (`userId`),
  KEY `ndx_interaction_interactionTypeId` (`interactionTypeId`),
  KEY `ndx_interaction_offerId` (`offerId`),
  CONSTRAINT `fk_interaction_interactionTypeId` FOREIGN KEY (`interactionTypeId`) REFERENCES `interactiontype` (`id`),
  CONSTRAINT `fk_interaction_offerId` FOREIGN KEY (`offerId`) REFERENCES `offer` (`id`),
  CONSTRAINT `fk_interaction_projectId` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`),
  CONSTRAINT `fk_interaction_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos que se mantienen con ';

--
-- Dumping data for table `interaction`
--

/*!40000 ALTER TABLE `interaction` DISABLE KEYS */;
INSERT INTO `interaction` (`id`,`projectId`,`userId`,`interactionTypeId`,`creationDate`,`interest`,`description`,`file`,`fileMime`,`departmentId`,`insertDate`,`updateDate`,`offerId`) VALUES 
 (1,7,1,3,'2009-04-06 00:00:00','HIGH','Envío de oferta',NULL,NULL,1,'2009-04-06 13:08:09','2009-04-06 13:08:09',NULL),
 (2,7,1,5,'2009-04-06 00:00:00','HIGH','Contacto con dicha empresa, para tratar el tema de la facturación, si será a 30 ó 60 dias.',NULL,NULL,1,'2009-04-06 13:09:20','2009-04-06 13:09:20',NULL),
 (3,6,1,8,'2009-02-01 00:00:00','HIGH','Nos han dado el visto bueno a la oferta',NULL,NULL,1,'2009-04-06 13:10:11','2009-04-06 13:10:31',NULL),
 (4,14,1,3,'2009-04-07 00:00:00','HIGH','Envío de oferta',NULL,NULL,1,'2009-04-07 11:28:43','2009-04-07 11:28:43',1),
 (5,14,1,3,'2009-04-01 00:00:00','HIGH','Envío de oferta',NULL,NULL,1,'2009-04-13 13:50:47','2009-04-13 13:50:47',2),
 (6,13,1,8,'2009-04-05 00:00:00','HIGH','Confirmación de oferta',NULL,NULL,1,'2009-04-15 10:42:17','2009-04-15 10:42:17',2);
/*!40000 ALTER TABLE `interaction` ENABLE KEYS */;


--
-- Definition of table `interactiontype`
--

DROP TABLE IF EXISTS `interactiontype`;
CREATE TABLE `interactiontype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Interaction type descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Interaction type description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de interacciones';

--
-- Dumping data for table `interactiontype`
--

/*!40000 ALTER TABLE `interactiontype` DISABLE KEYS */;
INSERT INTO `interactiontype` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'No conformidad','',NULL,NULL,NULL,NULL),
 (2,'Accion comercial','',NULL,NULL,NULL,NULL),
 (3,'Envío de oferta','',NULL,NULL,NULL,NULL),
 (4,'Envío de factura','',NULL,NULL,NULL,NULL),
 (5,'Accion administrativa','',NULL,NULL,NULL,NULL),
 (6,'No definida','',NULL,NULL,NULL,NULL),
 (7,'Primer contacto','',NULL,NULL,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (8,'Confirmación de oferta','',NULL,NULL,'2009-04-02 08:38:02','2009-04-02 08:38:02');
/*!40000 ALTER TABLE `interactiontype` ENABLE KEYS */;


--
-- Definition of table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyDate` date DEFAULT NULL,
  `asignedToId` int(11) DEFAULT NULL,
  `renting` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'De renting (1) o no (0)',
  `cost` decimal(10,2) DEFAULT NULL,
  `amortizable` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Amortizable (1) o no (0)consumible',
  `serialNumber` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `type` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `provider` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `trademark` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `model` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `speed` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `storage` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ram` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `location` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_invetory_userId` (`asignedToId`),
  CONSTRAINT `fk_inventory_userId` FOREIGN KEY (`asignedToId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Inventory de mquinas';

--
-- Dumping data for table `inventory`
--

/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` (`id`,`buyDate`,`asignedToId`,`renting`,`cost`,`amortizable`,`serialNumber`,`type`,`provider`,`trademark`,`model`,`speed`,`storage`,`ram`,`location`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'2008-02-15',3,0,'1300.00',0,'NS22220000','LAPTOP','PC City Parque Corredor','Packard Bell','Easy Note','','','','','Ordenador portáitl ',1,1,'2009-04-06 13:01:51','2009-04-06 13:01:51'),
 (2,'2008-02-02',1,0,'425.00',0,'NS 000A1111B','PC','El Corte Inglés','Impresora HP','1001','','','','','Impresora',1,1,'2009-04-06 13:03:06','2009-04-06 13:03:06');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;


--
-- Definition of table `magazine`
--

DROP TABLE IF EXISTS `magazine`;
CREATE TABLE `magazine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Magazines a las que se envan';

--
-- Dumping data for table `magazine`
--

/*!40000 ALTER TABLE `magazine` DISABLE KEYS */;
INSERT INTO `magazine` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (2,'Revista','Ejemplo de revista',1,1,'2009-05-13 12:38:20','2009-05-13 12:38:20');
/*!40000 ALTER TABLE `magazine` ENABLE KEYS */;


--
-- Definition of table `objective`
--

DROP TABLE IF EXISTS `objective`;
CREATE TABLE `objective` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `log` longtext COLLATE utf8_spanish_ci,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_objective_userId` (`userId`),
  KEY `ndx_objective_projectId` (`projectId`),
  CONSTRAINT `fk_objective_projectId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_objective_userId` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan los Objectives';

--
-- Dumping data for table `objective`
--

/*!40000 ALTER TABLE `objective` DISABLE KEYS */;
INSERT INTO `objective` (`id`,`userId`,`projectId`,`startDate`,`endDate`,`state`,`name`,`log`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,1,13,'2009-04-07','2009-04-10','PENDING','Ingreso de talones en el banco ',NULL,1,'2009-04-07 11:19:37','2009-04-07 11:19:37'),
 (2,1,13,'2009-04-07','2009-04-10','PENDING','Control TNT',NULL,1,'2009-04-07 11:19:47','2009-04-07 11:19:47'),
 (3,1,8,'2009-04-01','2009-05-15','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:35:43','2009-04-13 14:35:43'),
 (4,1,8,'2009-04-01','2009-05-15','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:35:43','2009-04-13 14:35:43'),
 (5,1,8,'2009-04-01','2009-06-01','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:38:11','2009-04-13 14:38:11'),
 (6,1,8,'2009-04-01','2009-06-01','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:38:11','2009-04-13 14:38:11'),
 (7,1,17,'2009-04-01','2009-05-01','PENDING','Entrega del pedido Pagina WEB',NULL,1,'2009-04-13 15:08:56','2009-04-13 15:08:56'),
 (8,1,17,'2009-04-01','2009-05-15','PENDING','Entrega del pedido Pagina WEB',NULL,1,'2009-04-13 15:09:25','2009-04-13 15:09:25');
/*!40000 ALTER TABLE `objective` ENABLE KEYS */;


--
-- Definition of table `occupation`
--

DROP TABLE IF EXISTS `occupation`;
CREATE TABLE `occupation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_occupation_userId` (`userId`),
  KEY `ndx_occupation_projectId` (`projectId`),
  CONSTRAINT `fk_occupation_projectId` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`),
  CONSTRAINT `fk_occupation_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Future occupations of Users';

--
-- Dumping data for table `occupation`
--

/*!40000 ALTER TABLE `occupation` DISABLE KEYS */;
INSERT INTO `occupation` (`id`,`projectId`,`userId`,`startDate`,`endDate`,`description`,`duration`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,10,2,'2009-04-06','2009-04-30','',480,1,1,'2009-04-14 12:10:16','2009-04-14 12:10:16');
/*!40000 ALTER TABLE `occupation` ENABLE KEYS */;


--
-- Definition of table `offer`
--

DROP TABLE IF EXISTS `offer`;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  `creationDate` date NOT NULL,
  `validityDate` date DEFAULT NULL,
  `maturityDate` date DEFAULT NULL,
  `offerPotential` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `offerState` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `offerRejectReasonId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `observations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `showIvaIntoReport` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_offer_organizationId` (`organizationId`),
  KEY `ndx_offer_contactId` (`contactId`),
  KEY `ndx_offer_offerRejectReasonId` (`offerRejectReasonId`),
  KEY `ndx_offer_userId` (`userId`),
  CONSTRAINT `fk_offer_contactId` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `fk_offer_offerRejectReasonId` FOREIGN KEY (`offerRejectReasonId`) REFERENCES `offerrejectreason` (`id`),
  CONSTRAINT `fk_offer_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`),
  CONSTRAINT `fk_offer_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ofertas';

--
-- Dumping data for table `offer`
--

/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` (`id`,`title`,`description`,`userId`,`organizationId`,`contactId`,`creationDate`,`validityDate`,`maturityDate`,`offerPotential`,`offerState`,`offerRejectReasonId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`number`,`observations`,`showIvaIntoReport`) VALUES 
 (1,'Desarrollo proyecto','Según la conversación mantenida el día 15 de febrero entre los directivos, se acuerdo el desarrollo del proyecto x',1,4,2,'2009-04-07','2009-02-17','2009-03-16','HIGH','OPEN',NULL,1,1,'2009-04-07 11:27:29','2009-04-07 11:27:29','09012001','',1),
 (2,'Instalación TNT','Según reunión en sus oficinas le proponemos la instalación del programa de gestión ',1,12,4,'2009-04-13','2009-04-01','2009-05-01','HIGH','OPEN',NULL,1,1,'2009-04-13 13:50:22','2009-04-13 13:50:22','09041302','',1),
 (4,'Oferta Crisis','Esto es una oferta',1,17,6,'2009-05-13','2009-05-12','2009-05-14','HIGH','OPEN',NULL,1,1,'2009-05-13 12:39:59','2009-05-13 12:39:59','0001','',1);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;


--
-- Definition of table `offercost`
--

DROP TABLE IF EXISTS `offercost`;
CREATE TABLE `offercost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `units` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `fk_offerCost_offerId` (`offerId`),
  CONSTRAINT `fk_offerCost_offerId` FOREIGN KEY (`offerId`) REFERENCES `offer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `offercost`
--

/*!40000 ALTER TABLE `offercost` DISABLE KEYS */;
INSERT INTO `offercost` (`id`,`offerId`,`name`,`cost`,`billable`,`iva`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`units`) VALUES 
 (1,4,'Ordebadires','1000.00',1,'16.00',NULL,NULL,NULL,NULL,'2.00');
/*!40000 ALTER TABLE `offercost` ENABLE KEYS */;


--
-- Definition of table `offerrejectreason`
--

DROP TABLE IF EXISTS `offerrejectreason`;
CREATE TABLE `offerrejectreason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Causas rechazo de ofertas';

--
-- Dumping data for table `offerrejectreason`
--

/*!40000 ALTER TABLE `offerrejectreason` DISABLE KEYS */;
INSERT INTO `offerrejectreason` (`id`,`title`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Sin respuesta','El cliente no responde a la oferta',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (2,'Oferta cara','El cliente considera la oferta excesivamente cara',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (3,'Tecnología inadecuada','Tecnología escogida en la oferta inadecuada',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (4,'Proyecto retrasado','El proyecto para el cual se hizo la oferta ha sido retrasado',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (5,'Proyecto cancelado','El proyecto para el cual se hizo la oferta ha sido cancelado',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02');
/*!40000 ALTER TABLE `offerrejectreason` ENABLE KEYS */;


--
-- Definition of table `offerrole`
--

DROP TABLE IF EXISTS `offerrole`;
CREATE TABLE `offerrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_offerRole_offerId` (`offerId`),
  CONSTRAINT `fk_offerRole_offerId` FOREIGN KEY (`offerId`) REFERENCES `offer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `offerrole`
--

/*!40000 ALTER TABLE `offerrole` DISABLE KEYS */;
INSERT INTO `offerrole` (`id`,`offerId`,`name`,`costPerHour`,`expectedHours`,`iva`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,4,'Programador Junior','10.00',10,'16.00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `offerrole` ENABLE KEYS */;


--
-- Definition of table `organization`
--

DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationTypeId` int(11) NOT NULL DEFAULT '1' COMMENT 'organization type id',
  `organizationISOCategoryId` int(11) NOT NULL DEFAULT '1' COMMENT 'iso category id',
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cif` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `street` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `number` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Building number in street',
  `locator` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Location information inside building',
  `postalCode` varchar(32) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) NOT NULL,
  `state` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `website` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ftpsite` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_organization_organizationTypeId` (`organizationTypeId`),
  KEY `ndx_organization_isoOrganizationCategoryId` (`organizationISOCategoryId`),
  CONSTRAINT `fk_organization_isoOrganizationCategoryId` FOREIGN KEY (`organizationISOCategoryId`) REFERENCES `organizationisocategory` (`id`),
  CONSTRAINT `fk_organization_organizationTypeId` FOREIGN KEY (`organizationTypeId`) REFERENCES `organizationtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los clientes';

--
-- Dumping data for table `organization`
--

/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`id`,`organizationTypeId`,`organizationISOCategoryId`,`name`,`cif`,`phone`,`street`,`number`,`locator`,`postalCode`,`city`,`provinceId`,`state`,`country`,`fax`,`email`,`website`,`ftpsite`,`notes`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,2,1,'Nuestra empresa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,2,3,'PC City SL','B0000000','902000000','Parque Corredor','','','28850','Torrejón de Ardoz',28,'Madrid','España ','','','','','',1,1,'2009-04-06 12:00:29','2009-04-06 12:00:29'),
 (3,2,3,'Suministros de Material de Oficina SA','A9999999','','Jazmines','3','Bajo Derecha','28001','Madrid',28,'Madrid','España ','','','','','',1,1,'2009-04-06 12:01:30','2009-04-06 12:01:30'),
 (4,1,3,'Tecnologías de España SL','B141414141','93','San Luis ','296','3º planta izquierda','08000','Barcelona',8,'Cataluña','España','93','','','','',1,1,'2009-04-06 12:02:47','2009-04-06 12:02:47'),
 (5,1,1,'A.T.R.I.L CB','E11111111','91','Avenida de Portugal','3','oficina 23F','28000','Pinto',28,'Madrid','España ','91','','','','',1,1,'2009-04-06 12:04:47','2009-04-06 12:04:47'),
 (6,1,1,'Grupo M5, SA','A999999999','91','Los Madroños','35','','28850','Torrejón de Ardoz',28,'Madrid','España ','91','','','','',1,1,'2009-04-07 09:50:39','2009-04-07 09:50:39'),
 (7,2,3,'Telefónica de España ','A82018474','900101010','Gran Via','28','','28013 ','Madrid',28,'Madrid ','España ','','','','','',1,1,'2009-04-07 09:52:24','2009-04-07 09:52:24'),
 (8,2,1,'Iberdrola','A95075578','901202020','Gardoki','8','','','Bilbao',20,'Pais Vasco','España ','','','','','',1,1,'2009-04-07 09:53:51','2009-04-07 09:53:51'),
 (9,2,3,'Grupo Gestoría Avanza SL','B66666666','91','Otoño','3','Bajo Derecha','28830 ','San Fernando de Henares ',28,'Madrid','España ','91','','','','',1,1,'2009-04-07 09:55:28','2009-04-07 09:55:28'),
 (10,1,1,'Laboratorios Salgado SL','B88888888','91','Francicos de Guzman','34','Edificio Buena Vista','28000','Madrid',28,'Madrid','España ','91','','','','',1,1,'2009-04-07 10:04:28','2009-04-07 10:04:28'),
 (11,2,3,'Alquileres Grupo SL','B22222222','91','Las Encinas','3','Oficina C','28200','Madrid',28,'Madrid','España','91','','','','',1,1,'2009-04-07 14:44:58','2009-04-07 14:44:58'),
 (12,1,1,'Fundación Hispánica SL','B10000001','921','San Cristobal','4','','45000','Segovia',40,'Castilla y León ','España ','921','','','','',1,1,'2009-04-13 13:46:47','2009-04-13 13:46:47'),
 (13,2,3,'Calidad SA','A00000066','91','San Ignacio ','3','','28830','San Fernando de Henares ',28,'Madrid','España ','91','','','','',1,1,'2009-04-13 14:29:47','2009-04-13 14:29:47'),
 (14,2,3,'Imagen WEB ','01010101A','91','Petunias','3','Planta baja','28000','Madrid',28,'Madrid','España','91','','','','',1,1,'2009-04-13 15:05:21','2009-04-13 15:05:21'),
 (15,1,1,'Indefinida',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'2009-05-08 11:43:39','2009-05-08 11:43:39'),
 (17,1,1,'Autentia','','','','','','','',1,'','','','','','','',1,1,'2009-05-13 12:39:03','2009-05-13 12:39:03');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;


--
-- Definition of table `organization_tag`
--

DROP TABLE IF EXISTS `organization_tag`;
CREATE TABLE `organization_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_organization_tag_tag` (`tagId`),
  KEY `fk_organization_tag_organization` (`organizationId`),
  CONSTRAINT `fk_organization_tag_organization` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`),
  CONSTRAINT `fk_organization_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `organization_tag`
--

/*!40000 ALTER TABLE `organization_tag` DISABLE KEYS */;
INSERT INTO `organization_tag` (`id`,`tagId`,`organizationId`) VALUES 
 (1,3,17);
/*!40000 ALTER TABLE `organization_tag` ENABLE KEYS */;


--
-- Definition of table `organizationisocategory`
--

DROP TABLE IF EXISTS `organizationisocategory`;
CREATE TABLE `organizationisocategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'ISO Organization Category descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'ISO Organization Category description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones segun ISO';

--
-- Dumping data for table `organizationisocategory`
--

/*!40000 ALTER TABLE `organizationisocategory` DISABLE KEYS */;
INSERT INTO `organizationisocategory` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'A','Proveedor / Subcontratista habitual.',NULL,NULL,NULL,NULL),
 (2,'B','Proveedor / Subcontratista recomendado.',NULL,NULL,NULL,NULL),
 (3,'C','Proveedor / Subcontratista no habitual.',NULL,NULL,NULL,NULL),
 (4,'D','Proveedor / Subcontratista que haya tenido disconformidades.',NULL,NULL,NULL,NULL),
 (6,'E','Categoria Temporal',1,1,'2009-05-13 12:38:14','2009-05-13 12:38:14');
/*!40000 ALTER TABLE `organizationisocategory` ENABLE KEYS */;


--
-- Definition of table `organizationtype`
--

DROP TABLE IF EXISTS `organizationtype`;
CREATE TABLE `organizationtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Organization type descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Organization type description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones';

--
-- Dumping data for table `organizationtype`
--

/*!40000 ALTER TABLE `organizationtype` DISABLE KEYS */;
INSERT INTO `organizationtype` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Cliente','',NULL,NULL,NULL,NULL),
 (2,'Proveedor','',NULL,NULL,NULL,NULL),
 (3,'Cliente y proveedor','',NULL,NULL,NULL,NULL),
 (4,'Prospecto','Posible cliente',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `organizationtype` ENABLE KEYS */;


--
-- Definition of table `periodicalaccountentry`
--

DROP TABLE IF EXISTS `periodicalaccountentry`;
CREATE TABLE `periodicalaccountentry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'entry type',
  `frequencyId` int(11) NOT NULL COMMENT 'entry frequency',
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `entryDate` date NOT NULL COMMENT 'entry date',
  `amount` decimal(10,2) NOT NULL COMMENT 'entry amount',
  `rise` decimal(4,2) DEFAULT NULL COMMENT 'account entry rise',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_periodicalAccountEntry_accountId` (`accountId`),
  KEY `ndx_periodicalAccountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  KEY `ndx_periodicalAccountEntry_frequencyId` (`frequencyId`),
  CONSTRAINT `fk_periodicalAccountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `accountentrytype` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_frequencyId` FOREIGN KEY (`frequencyId`) REFERENCES `frequency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='asientos contables periodicos';

--
-- Dumping data for table `periodicalaccountentry`
--

/*!40000 ALTER TABLE `periodicalaccountentry` DISABLE KEYS */;
INSERT INTO `periodicalaccountentry` (`id`,`accountId`,`accountEntryTypeId`,`frequencyId`,`concept`,`entryDate`,`amount`,`rise`,`observations`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,2,3,1,'Pago factura teléfono mes de enero ','2009-02-01','-99.06',NULL,'',1,1,'2009-04-07 10:10:43','2009-04-07 10:10:43'),
 (2,2,14,1,'Pago recibo luz','2009-04-20','-41.76',NULL,'',1,1,'2009-04-07 10:26:00','2009-04-07 10:26:00'),
 (3,2,8,1,'Pago factura alquiler mes de marzo ','2009-03-31','-812.00',NULL,'',1,1,'2009-04-07 14:50:40','2009-04-07 14:50:40'),
 (5,1,1,4,'Internet','2009-05-29','50.00','0.00','',1,1,'2009-05-13 12:40:57','2009-05-13 12:40:57'),
 (6,1,1,4,'Telefono Movil','2009-05-13','150.00','0.00','',1,1,'2009-05-13 12:41:45','2009-05-13 12:41:45');
/*!40000 ALTER TABLE `periodicalaccountentry` ENABLE KEYS */;


--
-- Definition of table `position`
--

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_position_provinceId` (`provinceId`),
  CONSTRAINT `fk_position_province` FOREIGN KEY (`provinceId`) REFERENCES `province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`deleteDate`,`email`,`phone`,`fax`,`address`,`postalCode`,`city`,`country`,`provinceId`) VALUES 
 (1,'Indefinido','Puesto sin definir',1,1,'2009-05-08 11:43:39','2009-05-08 11:43:39',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Responsable de dirección ','migrado de una versión anterior',1,1,'2009-05-08 11:43:40','2009-05-08 11:43:40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'Dirección de proyectos','migrado de una versión anterior',1,1,'2009-05-08 11:43:40','2009-05-08 11:43:40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'Responsable de facturación ','migrado de una versión anterior',1,1,'2009-05-08 11:43:40','2009-05-08 11:43:40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'Administración y dirección ','migrado de una versión anterior',1,1,'2009-05-08 11:43:40','2009-05-08 11:43:40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (9,'Becario','',1,1,'2009-05-13 12:36:38','2009-05-13 12:37:08','2009-05-13 12:37:08','','','','','','','',NULL),
 (10,'Becario','Es el becario',1,1,'2009-05-13 12:38:43','2009-05-13 12:38:49','2009-05-13 12:38:49','','','','','','','',NULL),
 (11,'Becario','',1,1,'2009-05-13 12:38:51','2009-05-13 12:38:51',NULL,'','','','','','','',NULL);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


--
-- Definition of table `position_department`
--

DROP TABLE IF EXISTS `position_department`;
CREATE TABLE `position_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `positionId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_position_department_position` (`positionId`),
  KEY `fk_position_department_department` (`departmentId`),
  CONSTRAINT `fk_position_department_department` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_position_department_position` FOREIGN KEY (`positionId`) REFERENCES `position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `position_department`
--

/*!40000 ALTER TABLE `position_department` DISABLE KEYS */;
INSERT INTO `position_department` (`id`,`positionId`,`departmentId`) VALUES 
 (3,9,7);
/*!40000 ALTER TABLE `position_department` ENABLE KEYS */;


--
-- Definition of table `position_tag`
--

DROP TABLE IF EXISTS `position_tag`;
CREATE TABLE `position_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_position_tag_tag` (`tagId`),
  KEY `fk_position_tag_position` (`positionId`),
  CONSTRAINT `fk_position_tag_position` FOREIGN KEY (`positionId`) REFERENCES `position` (`id`),
  CONSTRAINT `fk_position_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `position_tag`
--

/*!40000 ALTER TABLE `position_tag` DISABLE KEYS */;
INSERT INTO `position_tag` (`id`,`tagId`,`positionId`) VALUES 
 (1,3,11);
/*!40000 ALTER TABLE `position_tag` ENABLE KEYS */;


--
-- Definition of table `positionchange`
--

DROP TABLE IF EXISTS `positionchange`;
CREATE TABLE `positionchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `positionId` int(10) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_positionChange_position` (`positionId`),
  KEY `fk_positionChange_user` (`userId`),
  CONSTRAINT `fk_positionChange_position` FOREIGN KEY (`positionId`) REFERENCES `position` (`id`),
  CONSTRAINT `fk_positionChange_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `positionchange`
--

/*!40000 ALTER TABLE `positionchange` DISABLE KEYS */;
INSERT INTO `positionchange` (`id`,`field`,`oldValue`,`newValue`,`positionId`,`userId`,`insertDate`,`deleteDate`) VALUES 
 (1,'position.description','','Es el becario',10,1,'2009-05-13 12:38:46',NULL);
/*!40000 ALTER TABLE `positionchange` ENABLE KEYS */;


--
-- Definition of table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `open` tinyint(1) DEFAULT '0',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_project_organizationId` (`organizationId`),
  CONSTRAINT `fk_project_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Projects';

--
-- Dumping data for table `project`
--

/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`id`,`organizationId`,`startDate`,`endDate`,`open`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`billable`) VALUES 
 (1,1,'2009-04-02',NULL,1,'Vacaciones',NULL,NULL,NULL,NULL,NULL,1),
 (2,1,'2009-04-02',NULL,1,'Permiso extraordinario',NULL,NULL,NULL,NULL,NULL,1),
 (3,1,'2009-04-02',NULL,1,'Baja por enfermedad',NULL,NULL,NULL,NULL,NULL,1),
 (4,1,'2009-04-02',NULL,1,'Auto-formación',NULL,NULL,NULL,NULL,NULL,1),
 (5,1,'2009-04-02',NULL,1,'Histórico',NULL,NULL,NULL,NULL,NULL,1),
 (6,4,'2009-04-06',NULL,1,'Proyecto AB','Creación del proyecto',1,1,'2009-04-06 13:06:54','2009-04-07 10:14:33',1),
 (7,5,'2009-04-06',NULL,1,'Curso de formación ','Curso de formación ',1,1,'2009-04-06 13:07:44','2009-04-06 13:07:44',1),
 (8,1,'2009-01-01',NULL,1,'Facturas recibidas','',1,1,'2009-04-07 09:44:57','2009-04-07 11:15:43',1),
 (10,6,'2009-03-01',NULL,1,'Proyecto comercial ','',1,1,'2009-04-07 10:15:13','2009-04-07 10:15:13',1),
 (11,10,'2009-03-01',NULL,1,'Proyecto comercial ','',1,1,'2009-04-07 10:15:47','2009-04-07 10:15:47',1),
 (12,5,'2009-05-01',NULL,1,'Proyecto comercial','',1,1,'2009-04-07 10:17:21','2009-04-07 10:17:21',1),
 (13,1,'2009-01-01',NULL,1,'Labores administrativas','',1,1,'2009-04-07 11:14:02','2009-04-07 11:16:49',0),
 (14,1,'2009-01-01',NULL,1,'Labores comerciales/ofertas','',1,1,'2009-04-07 11:28:20','2009-04-07 11:28:20',0),
 (15,12,'2009-03-01',NULL,1,'TNT','Instalación de TNT',1,1,'2009-04-13 13:47:38','2009-04-13 13:47:38',1),
 (16,1,'2009-04-01',NULL,1,'calidad','',1,1,'2009-04-13 14:41:42','2009-04-13 14:41:42',0),
 (17,14,'2009-04-01',NULL,1,'Creación página web','',1,1,'2009-04-13 15:07:20','2009-04-13 15:07:20',1),
 (19,17,'2009-05-13','2009-05-30',1,'Migración Datos','',1,1,'2009-05-13 12:39:37','2009-05-13 12:39:40',1),
 (20,17,'2009-05-13','2009-05-13',1,'PROY 1','',1,1,'2009-05-13 15:03:04','2009-05-13 15:03:47',1),
 (21,13,'2009-05-13',NULL,0,'PROY 2','',1,1,'2009-05-13 15:04:43','2009-05-13 15:04:43',1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


--
-- Definition of table `projectcost`
--

DROP TABLE IF EXISTS `projectcost`;
CREATE TABLE `projectcost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_projectCost_projectId` (`projectId`),
  CONSTRAINT `fk_projectCost_projectId` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `projectcost`
--

/*!40000 ALTER TABLE `projectcost` DISABLE KEYS */;
INSERT INTO `projectcost` (`id`,`projectId`,`name`,`cost`,`billable`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,6,'Fotocopias','200.00',1,NULL,NULL,NULL,NULL),
 (2,7,'Material','300.00',1,NULL,NULL,NULL,NULL),
 (3,19,'Ordenadores','1000.00',1,NULL,NULL,NULL,NULL),
 (4,21,'asdf','12.00',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `projectcost` ENABLE KEYS */;


--
-- Definition of table `projectrole`
--

DROP TABLE IF EXISTS `projectrole`;
CREATE TABLE `projectrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectRole_projectId` (`projectId`),
  CONSTRAINT `fk_projectRole_projectId` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `projectrole`
--

/*!40000 ALTER TABLE `projectrole` DISABLE KEYS */;
INSERT INTO `projectrole` (`id`,`projectId`,`name`,`costPerHour`,`expectedHours`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,1,'Vacaciones','0.00',0,NULL,NULL,NULL,NULL),
 (2,2,'Permiso extraordinario','0.00',0,NULL,NULL,NULL,NULL),
 (3,3,'Baja por enfermedad','0.00',0,NULL,NULL,NULL,NULL),
 (4,4,'Auto-formación','0.00',0,NULL,NULL,NULL,NULL),
 (5,5,'Histórico','0.00',0,NULL,NULL,NULL,NULL),
 (6,6,'Tecnico','50.00',15,NULL,NULL,NULL,NULL),
 (7,7,'Formador','60.00',45,NULL,NULL,NULL,NULL),
 (8,8,'Labores admtivas','0.00',0,NULL,NULL,NULL,NULL),
 (9,13,'Lab. admitivas','0.00',0,NULL,NULL,NULL,NULL),
 (10,19,'Analista Programador','10.00',20,NULL,NULL,NULL,NULL),
 (11,19,'Programador Junior','10.00',15,NULL,NULL,NULL,NULL),
 (12,21,'asdf','12.00',5,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `projectrole` ENABLE KEYS */;


--
-- Definition of table `province`
--

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos códigos fijos',
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con las Provinces';

--
-- Dumping data for table `province`
--

/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Alava',NULL,NULL,NULL,NULL),
 (2,'Albacete',NULL,NULL,NULL,NULL),
 (3,'Alicante',NULL,NULL,NULL,NULL),
 (4,'Almería',NULL,NULL,NULL,NULL),
 (5,'Avila',NULL,NULL,NULL,NULL),
 (6,'Badajoz',NULL,NULL,NULL,NULL),
 (7,'Balears, Illes',NULL,NULL,NULL,NULL),
 (8,'Barcelona',NULL,NULL,NULL,NULL),
 (9,'Burgos',NULL,NULL,NULL,NULL),
 (10,'Cáceres',NULL,NULL,NULL,NULL),
 (11,'Cádiz',NULL,NULL,NULL,NULL),
 (12,'Castellón',NULL,NULL,NULL,NULL),
 (13,'Ciudad Real',NULL,NULL,NULL,NULL),
 (14,'Córdoba',NULL,NULL,NULL,NULL),
 (15,'Coruña, A',NULL,NULL,NULL,NULL),
 (16,'Cuenca',NULL,NULL,NULL,NULL),
 (17,'Girona',NULL,NULL,NULL,NULL),
 (18,'Granada',NULL,NULL,NULL,NULL),
 (19,'Guadalajara',NULL,NULL,NULL,NULL),
 (20,'Guipúzcoa',NULL,NULL,NULL,NULL),
 (21,'Huelva',NULL,NULL,NULL,NULL),
 (22,'Huesca',NULL,NULL,NULL,NULL),
 (23,'Jaén',NULL,NULL,NULL,NULL),
 (24,'León',NULL,NULL,NULL,NULL),
 (25,'Lleida',NULL,NULL,NULL,NULL),
 (26,'Rioja, La',NULL,NULL,NULL,NULL),
 (27,'Lugo',NULL,NULL,NULL,NULL),
 (28,'Madrid',NULL,NULL,NULL,NULL),
 (29,'Málaga',NULL,NULL,NULL,NULL),
 (30,'Murcia',NULL,NULL,NULL,NULL),
 (31,'Navarra',NULL,NULL,NULL,NULL),
 (32,'Ourense',NULL,NULL,NULL,NULL),
 (33,'Asturias',NULL,NULL,NULL,NULL),
 (34,'Palencia',NULL,NULL,NULL,NULL),
 (35,'Palmas, Las',NULL,NULL,NULL,NULL),
 (36,'Pontevedra',NULL,NULL,NULL,NULL),
 (37,'Salamanca',NULL,NULL,NULL,NULL),
 (38,'Santa Cruz de Tenerife',NULL,NULL,NULL,NULL),
 (39,'Cantabria',NULL,NULL,NULL,NULL),
 (40,'Segovia',NULL,NULL,NULL,NULL),
 (41,'Sevilla',NULL,NULL,NULL,NULL),
 (42,'Soria',NULL,NULL,NULL,NULL),
 (43,'Tarragona',NULL,NULL,NULL,NULL),
 (44,'Teruel',NULL,NULL,NULL,NULL),
 (45,'Toledo',NULL,NULL,NULL,NULL),
 (46,'Valencia',NULL,NULL,NULL,NULL),
 (47,'Valladolid',NULL,NULL,NULL,NULL),
 (48,'Vizcaya',NULL,NULL,NULL,NULL),
 (49,'Zamora',NULL,NULL,NULL,NULL),
 (50,'Zaragoza',NULL,NULL,NULL,NULL),
 (51,'Ceuta',NULL,NULL,NULL,NULL),
 (52,'Melilla',NULL,NULL,NULL,NULL),
 (53,'Indefinido',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `province` ENABLE KEYS */;


--
-- Definition of table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `magazineId` int(11) NOT NULL,
  `magazineDeliveryDate` datetime DEFAULT NULL COMMENT 'Fecha de entrega a la Magazine',
  `magazinePublicationDate` date DEFAULT NULL,
  `ownPublicationDate` date DEFAULT NULL COMMENT 'Fecha de publicacin en Adictos',
  `accepted` tinyint(1) DEFAULT NULL COMMENT 'Indicador de si el Tutorial ha sido aceptado (1) o no (0)',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_publication_tutorialId` (`id`),
  KEY `fk_publication_magazineId` (`magazineId`),
  CONSTRAINT `fk_publication_magazineId` FOREIGN KEY (`magazineId`) REFERENCES `magazine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='publicaciones de tutoriales';

--
-- Dumping data for table `publication`
--

/*!40000 ALTER TABLE `publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `publication` ENABLE KEYS */;


--
-- Definition of table `requestholiday`
--

DROP TABLE IF EXISTS `requestholiday`;
CREATE TABLE `requestholiday` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `beginDate` datetime NOT NULL,
  `finalDate` datetime NOT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `userComment` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `chargeYear` date NOT NULL DEFAULT '2007-01-01',
  PRIMARY KEY (`id`),
  KEY `fk_requestHoliday_userId` (`userId`),
  CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `requestholiday`
--

/*!40000 ALTER TABLE `requestholiday` DISABLE KEYS */;
INSERT INTO `requestholiday` (`id`,`beginDate`,`finalDate`,`state`,`userId`,`observations`,`departmentId`,`insertDate`,`updateDate`,`userComment`,`chargeYear`) VALUES 
 (1,'2009-04-13 00:00:00','2009-04-17 00:00:00','PENDING',1,NULL,1,'2009-04-06 12:09:36','2009-04-06 12:09:36','Vacaciones Semana Santa','2009-01-01'),
 (2,'2009-08-10 00:00:00','2009-08-31 00:00:00','PENDING',1,NULL,1,'2009-04-06 13:00:30','2009-04-06 13:00:45','Vacaciones de verano ','2009-01-01');
/*!40000 ALTER TABLE `requestholiday` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Roles de la aplicacin';

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Administrador',NULL,NULL,NULL,NULL),
 (2,'Supervisor',NULL,NULL,NULL,NULL),
 (3,'Usuario',NULL,NULL,NULL,NULL),
 (4,'Staff',NULL,NULL,NULL,NULL),
 (5,'Cliente',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `setting`
--

DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `value` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='User settings';

--
-- Dumping data for table `setting`
--

/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` (`id`,`type`,`name`,`value`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'BOOLEAN','bitacore.last.billable','false',1,1,'2009-04-07 11:17:33','2009-04-13 13:43:06'),
 (2,'INT','bitacore.last.roleId','2',1,1,'2009-04-07 11:17:33','2009-04-13 13:43:06'),
 (3,'INT','objective.last.projectId','19',1,1,'2009-04-07 11:19:37','2009-05-13 12:44:15');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;


--
-- Definition of table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `tag`
--

/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (3,'Clave Principal','',1,1,'2009-05-13 12:38:41','2009-05-13 12:38:41');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;


--
-- Definition of table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `maxDeliveryDate` datetime NOT NULL,
  `endDate` datetime DEFAULT NULL COMMENT 'Fecha de finalizacin del Tutorial',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_tutorial_userId` (`userId`),
  CONSTRAINT `fk_tutorial_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tutoriales que son enviado a la';

--
-- Dumping data for table `tutorial`
--

/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `roleId` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'User activo',
  `name` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `nif` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `academicQualification` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `street` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  `married` tinyint(1) NOT NULL COMMENT 'Casado (1) o no (0)',
  `childrenNumber` tinyint(4) NOT NULL,
  `drivenLicenseType` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `vehicleType` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `licensePlate` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `startDate` date NOT NULL COMMENT 'fecha de alta en la empresa',
  `categoryId` int(11) NOT NULL,
  `socialSecurityNumber` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `bank` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `account` varchar(34) COLLATE utf8_spanish_ci DEFAULT NULL,
  `travelAvailability` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Disponibilidad para viajar',
  `workingInClient` tinyint(1) NOT NULL COMMENT 'Si esta tabajando en el cliente',
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `genre` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `salaryExtras` decimal(10,2) DEFAULT NULL,
  `documentCategoryId` int(10) unsigned DEFAULT NULL,
  `securityCard` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `healthInsurance` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `endTestPeriodDate` date DEFAULT NULL,
  `endContractDate` date DEFAULT NULL,
  `departmentId` int(10) unsigned NOT NULL DEFAULT '1',
  `contractTypeId` int(10) unsigned DEFAULT NULL,
  `contractObservations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `dayDuration` int(11) DEFAULT NULL,
  `agreementId` int(11) NOT NULL DEFAULT '1',
  `agreementYearDuration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_user_roleId` (`roleId`),
  KEY `ndx_user_provinceId` (`provinceId`),
  KEY `ndx_user_categoryId` (`categoryId`),
  KEY `ndx_user_documentCategoryId` (`documentCategoryId`),
  KEY `ndx_user_departmentId` (`departmentId`),
  KEY `ndx_user_contractTypeId` (`contractTypeId`),
  KEY `ndx_user_agreementId` (`agreementId`),
  CONSTRAINT `fk_user_agreementId` FOREIGN KEY (`agreementId`) REFERENCES `workingagreement` (`id`),
  CONSTRAINT `fk_user_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `usercategory` (`id`),
  CONSTRAINT `fk_user_contractTypeId` FOREIGN KEY (`contractTypeId`) REFERENCES `contracttype` (`id`),
  CONSTRAINT `fk_user_departmentId` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_user_documentCategoryId` FOREIGN KEY (`documentCategoryId`) REFERENCES `documentcategory` (`id`),
  CONSTRAINT `fk_user_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `province` (`id`),
  CONSTRAINT `fk_user_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Users de la aplicacin';

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`login`,`password`,`roleId`,`active`,`name`,`nif`,`birthDate`,`academicQualification`,`phone`,`mobile`,`street`,`city`,`postalCode`,`provinceId`,`married`,`childrenNumber`,`drivenLicenseType`,`vehicleType`,`licensePlate`,`startDate`,`categoryId`,`socialSecurityNumber`,`bank`,`account`,`travelAvailability`,`workingInClient`,`email`,`genre`,`salary`,`salaryExtras`,`documentCategoryId`,`securityCard`,`healthInsurance`,`notes`,`photo`,`endTestPeriodDate`,`endContractDate`,`departmentId`,`contractTypeId`,`contractObservations`,`insertDate`,`updateDate`,`dayDuration`,`agreementId`,`agreementYearDuration`) VALUES 
 (1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f',1,1,'Administrador',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,'2009-04-02',1,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'2009-05-13 12:36:27',480,1,NULL),
 (2,'Jesus','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',3,1,'Jesus Sánchez Gómez','','1983-11-11','Titulado universitario','918888888','600123456','Las Rosas 256','Madrid','28003',28,0,0,'B1','Ford Mondeo','0000AAAA','2009-01-25',3,'281234567890','Caja Madrid','2038000000000000000001','Ocasionalmente',0,'jesus.sanchez@admin.es','MAN','25000.00',NULL,45,'','','',NULL,'2009-03-24',NULL,3,3,'','2009-04-06 11:46:26','2009-04-06 11:46:26',480,1,NULL),
 (3,'Natalia','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',4,1,'Natalia Rincón San García','','1978-06-05','FP II','917001200','690000100','Oviedo 239','Alcalá de Henares','28803',28,1,1,'B1','Mercedes 220','1111BBB','2008-02-10',3,'289876543210','Santander Central Hispano','00490000001000000000','No',0,'natalia.rincon@admin.es','WOMAN','20000.00',NULL,46,'','','',NULL,NULL,NULL,1,3,'','2009-04-06 11:49:33','2009-04-06 11:49:41',480,1,NULL),
 (4,'Daniel','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',3,1,'Daniel Madrid Triviño','12345678Z',NULL,'','','','','','',28,0,0,'','','','2007-05-04',2,'','','','',0,'','MAN','0.00','0.00',48,'','','',NULL,NULL,NULL,3,2,'','2009-05-13 12:36:04','2009-05-13 12:36:25',480,1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `usercategory`
--

DROP TABLE IF EXISTS `usercategory`;
CREATE TABLE `usercategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan las categorias';

--
-- Dumping data for table `usercategory`
--

/*!40000 ALTER TABLE `usercategory` DISABLE KEYS */;
INSERT INTO `usercategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
 (1,'Socio',NULL,NULL,NULL,NULL),
 (2,'Becario',NULL,NULL,NULL,NULL),
 (3,'Administrativo',NULL,NULL,NULL,NULL),
 (4,'Gerente',NULL,NULL,NULL,NULL),
 (6,'Director General',1,1,'2009-05-13 12:36:35','2009-05-13 12:36:35');
/*!40000 ALTER TABLE `usercategory` ENABLE KEYS */;


--
-- Definition of table `version`
--

DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `version` varchar(32) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='version de la base de datos';

--
-- Dumping data for table `version`
--

/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` (`version`) VALUES 
 ('0.17');
/*!40000 ALTER TABLE `version` ENABLE KEYS */;


--
-- Definition of table `workingagreement`
--

DROP TABLE IF EXISTS `workingagreement`;
CREATE TABLE `workingagreement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `holidays` int(11) NOT NULL DEFAULT '22',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `yearDuration` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Convenios laborales';

--
-- Dumping data for table `workingagreement`
--

/*!40000 ALTER TABLE `workingagreement` DISABLE KEYS */;
INSERT INTO `workingagreement` (`id`,`name`,`description`,`holidays`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`yearDuration`) VALUES 
 (1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `workingagreement` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
