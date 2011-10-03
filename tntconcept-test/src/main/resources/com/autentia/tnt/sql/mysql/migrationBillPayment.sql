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

CREATE DATABASE IF NOT EXISTS tntconceptBillPayment;
USE tntconceptBillPayment;

--
-- Definition of table `Account`
--

DROP TABLE IF EXISTS `Account`;
CREATE TABLE  `Account` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `number` varchar(20) collate utf8_spanish_ci NOT NULL COMMENT 'Account number',
  `accountTypeId` int(11) NOT NULL COMMENT 'Account type',
  `description` varchar(2048) collate utf8_spanish_ci default NULL COMMENT 'Account description',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_account_accountTypeId` (`accountTypeId`),
  CONSTRAINT `fk_account_accountTypeId` FOREIGN KEY (`accountTypeId`) REFERENCES `AccountType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las cuentas';

--
-- Dumping data for table `Account`
--

/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
LOCK TABLES `Account` WRITE;
INSERT INTO `Account` VALUES  (1,'Caja','0000000000000000000',1,NULL,NULL,NULL,NULL,NULL),
 (2,'Caja Madrid ','20380000001000000001',2,'Cuenta Corriente',1,1,'2009-04-06 12:08:01','2009-04-06 12:08:01'),
 (3,'Santander','00490000100000000002',2,'Cuenta corriente',1,1,'2009-04-06 12:08:35','2009-04-06 12:08:35');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;


--
-- Definition of table `AccountEntry`
--

DROP TABLE IF EXISTS `AccountEntry`;
CREATE TABLE  `AccountEntry` (
  `id` int(11) NOT NULL auto_increment,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'Account entry type',
  `entryDate` date NOT NULL COMMENT 'account entry date',
  `entryAmountDate` date NOT NULL COMMENT 'account entry amount date (fecha valor)',
  `concept` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `amount` decimal(10,2) NOT NULL COMMENT 'account entry amount',
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `entryNumber` varchar(16) collate utf8_spanish_ci default NULL,
  `docNumber` varchar(50) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_accountEntry_accountId` (`accountId`),
  KEY `ndx_accountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  CONSTRAINT `fk_accountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_accountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los movimientos';

--
-- Dumping data for table `AccountEntry`
--

/*!40000 ALTER TABLE `AccountEntry` DISABLE KEYS */;
LOCK TABLES `AccountEntry` WRITE;
INSERT INTO `AccountEntry` VALUES  (1,1,5,'2009-02-01','2009-02-01','Pago material de oficina','-112.35','',1,1,'2009-04-07 09:48:32','2009-04-07 09:48:52','',''),
 (2,3,16,'2009-02-05','2009-02-05','Pago factura gestoría mes de enero ','-290.00','',1,1,'2009-04-07 09:58:17','2009-04-07 09:58:17','',''),
 (3,3,16,'2009-03-05','2009-03-05','Pago factura gestoría mes de febrero ','-290.00','',1,1,'2009-04-07 09:58:51','2009-04-07 09:58:51','',''),
 (4,2,3,'2009-02-01','2009-02-01','Pago factura teléfono mes de enero ','-99.06','',1,1,'2009-04-07 10:10:16','2009-04-07 10:10:16','',''),
 (5,2,7,'2009-04-07','2009-04-07','Cobro del 50% factura 03','2088.00','',1,1,'2009-04-07 10:23:09','2009-04-07 10:23:09','',''),
 (6,2,8,'2009-02-28','2009-02-28','Pago factura alquiler oficina mes de febrero ','-812.00','',1,1,'2009-04-07 14:50:02','2009-04-07 14:50:02','',''),
 (7,2,8,'2009-03-31','2009-03-31','Pago factura alquiler mes de marzo ','-812.00','',1,1,'2009-04-07 14:50:29','2009-04-07 14:50:29','',''),
 (8,2,7,'2009-04-10','2009-04-10','Cobro factura 05','1740.00','',1,1,'2009-04-13 13:53:42','2009-04-13 13:53:42','','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `AccountEntry` ENABLE KEYS */;


--
-- Definition of table `AccountEntryGroup`
--

DROP TABLE IF EXISTS `AccountEntryGroup`;
CREATE TABLE  `AccountEntryGroup` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL COMMENT 'Account entry group descriptive name',
  `description` varchar(1024) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='grupos de movimiento';

--
-- Dumping data for table `AccountEntryGroup`
--

/*!40000 ALTER TABLE `AccountEntryGroup` DISABLE KEYS */;
LOCK TABLES `AccountEntryGroup` WRITE;
INSERT INTO `AccountEntryGroup` VALUES  (1,'Ingreso','Ingresos en cuenta',NULL,NULL,NULL,NULL),
 (2,'Gasto','Gastos en cuenta',NULL,NULL,NULL,NULL),
 (3,'Traspaso','Traspasos',NULL,NULL,NULL,NULL),
 (4,'Arranque anual','Movimiento que representa al arranque anual',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `AccountEntryGroup` ENABLE KEYS */;


--
-- Definition of table `AccountEntryType`
--

DROP TABLE IF EXISTS `AccountEntryType`;
CREATE TABLE  `AccountEntryType` (
  `id` int(11) NOT NULL auto_increment,
  `accountEntryGroupId` int(11) NOT NULL COMMENT 'Account entry group',
  `name` varchar(256) collate utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  `accountEntryTypeId` int(11) default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `customizableId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_accountEntryType_accountEntryGroupId` (`accountEntryGroupId`),
  KEY `FK_accountentrytype_accountEntryTypeId` (`accountEntryTypeId`),
  CONSTRAINT `fk_accountEntryType_accountEntryGroupId` FOREIGN KEY (`accountEntryGroupId`) REFERENCES `AccountEntryGroup` (`id`),
  CONSTRAINT `FK_accountentrytype_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de movimientos';

--
-- Dumping data for table `AccountEntryType`
--

/*!40000 ALTER TABLE `AccountEntryType` DISABLE KEYS */;
LOCK TABLES `AccountEntryType` WRITE;
INSERT INTO `AccountEntryType` VALUES  (1,4,'Arranque inicial','Tipo de asiento que representa el arranque inicial de un año',NULL,NULL,NULL,NULL,NULL,NULL),
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
 (12,2,'Comisiones bancarias','',NULL,1,1,'2009-04-06 11:54:28','2009-04-06 11:54:28',NULL);
INSERT INTO `AccountEntryType` VALUES  (13,1,'Ingresos bancarios','',NULL,1,1,'2009-04-06 11:54:39','2009-04-06 11:54:39',NULL),
 (14,2,'Suministros','',NULL,1,1,'2009-04-07 09:54:10','2009-04-07 09:54:10',NULL),
 (15,2,'Luz','',14,1,1,'2009-04-07 09:54:19','2009-04-07 09:54:19',NULL),
 (16,2,'Pago a gestoría','',6,1,1,'2009-04-07 09:55:48','2009-04-07 09:55:48',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `AccountEntryType` ENABLE KEYS */;


--
-- Definition of table `AccountType`
--

DROP TABLE IF EXISTS `AccountType`;
CREATE TABLE  `AccountType` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL COMMENT 'Account type descriptive name',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='lmacenan las cuentas';

--
-- Dumping data for table `AccountType`
--

/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;
LOCK TABLES `AccountType` WRITE;
INSERT INTO `AccountType` VALUES  (1,'Caja',NULL,NULL,NULL,NULL),
 (2,'Cuenta corriente',NULL,NULL,NULL,NULL),
 (3,'Cuenta de crédito',NULL,NULL,NULL,NULL),
 (4,'Depósito',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;


--
-- Definition of table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
CREATE TABLE  `Activity` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `startDate` datetime default '0000-00-00 00:00:00',
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `description` varchar(2048) collate utf8_spanish_ci default NULL,
  `billable` tinyint(1) NOT NULL default '1',
  `roleId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_activity_userId` (`userId`),
  KEY `fk_activity_roleId` (`roleId`),
  CONSTRAINT `fk_activity_roleId` FOREIGN KEY (`roleId`) REFERENCES `ProjectRole` (`id`),
  CONSTRAINT `fk_activity_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Activityes de los Users';

--
-- Dumping data for table `Activity`
--

/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
LOCK TABLES `Activity` WRITE;
INSERT INTO `Activity` VALUES  (1,1,'2009-04-01 08:00:00',360,'Labores administrativas (contabilizar facturas recibidas y emitidas)',0,9,1,'2009-04-07 11:17:33','2009-04-07 11:17:44'),
 (2,1,'2009-04-02 08:00:00',360,'Laboresa administrativas (control TNT, ir al banco a ingresar talón, recepción de llamadas)',0,9,1,'2009-04-07 11:18:25','2009-04-07 11:18:25'),
 (3,1,'2009-04-03 08:00:00',360,'Labores administrativas (compra de material de oficina, contabilizar asientos y asociarlos)',0,9,1,'2009-04-07 11:18:58','2009-04-07 11:18:58'),
 (4,1,'2009-04-06 08:00:00',360,'Labores administrativas (comprobar estado de las facturas y seguimieno de las facturas pendientes de cobrar y de emitir)',0,9,1,'2009-04-13 13:37:33','2009-04-13 13:37:33'),
 (5,1,'2009-04-07 08:00:00',360,'Labores administrativas (cuadrar con la asesoríalos pagos de IVA, y de IRPF, comprobar el estado de las facturas recibidas, cuál están pagadas y cuáles no)',0,9,1,'2009-04-13 13:41:26','2009-04-13 13:41:26'),
 (6,1,'2009-04-08 08:00:00',360,'Permiso extraordinario (asistencia al especialista)',0,2,1,'2009-04-13 13:42:11','2009-04-13 13:42:11');
INSERT INTO `Activity` VALUES  (7,1,'2009-04-13 08:00:00',360,'Labores administrativas (control TNT, compra de bebida para la empresa, compra de regalos de cumpleaños para empleados)',0,2,1,'2009-04-13 13:42:59','2009-04-13 13:43:06');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;


--
-- Definition of table `ActivityFile`
--

DROP TABLE IF EXISTS `ActivityFile`;
CREATE TABLE  `ActivityFile` (
  `id` int(11) NOT NULL auto_increment,
  `externalActivityId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `insertDate` datetime NOT NULL,
  `updateDate` datetime default NULL,
  `file` varchar(400) collate utf8_spanish_ci NOT NULL,
  `fileMime` varchar(128) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_activityFile_externalActivity` (`externalActivityId`),
  KEY `fk_activityFile_user` (`userId`),
  CONSTRAINT `fk_activityFile_externalActivity` FOREIGN KEY (`externalActivityId`) REFERENCES `ExternalActivity` (`id`),
  CONSTRAINT `fk_activityFile_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ActivityFile`
--

/*!40000 ALTER TABLE `ActivityFile` DISABLE KEYS */;
LOCK TABLES `ActivityFile` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `ActivityFile` ENABLE KEYS */;


--
-- Definition of table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
CREATE TABLE  `Bill` (
  `id` int(11) NOT NULL auto_increment,
  `creationDate` date NOT NULL,
  `expiration` smallint(6) default NULL,
  `paymentMode` varchar(16) collate utf8_spanish_ci default NULL,
  `state` varchar(16) collate utf8_spanish_ci NOT NULL,
  `number` varchar(64) collate utf8_spanish_ci NOT NULL,
  `name` varchar(4096) collate utf8_spanish_ci NOT NULL,
  `file` varchar(512) collate utf8_spanish_ci default NULL,
  `fileMime` varchar(64) collate utf8_spanish_ci default NULL,
  `observations` varchar(4096) collate utf8_spanish_ci default NULL,
  `projectId` int(11) NOT NULL default '5' COMMENT 'project id',
  `startBillDate` date NOT NULL default '1980-01-01',
  `endBillDate` date NOT NULL default '1980-01-01',
  `billType` varchar(16) collate utf8_spanish_ci NOT NULL default 'ISSUED',
  `orderNumber` varchar(64) collate utf8_spanish_ci default NULL,
  `contactId` int(11) default NULL,
  `providerId` int(11) default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `bookNumber` varchar(64) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_bill_projectId` (`projectId`),
  KEY `ndx_bill_contactId` (`contactId`),
  KEY `ndx_bill_providerId` (`providerId`),
  CONSTRAINT `fk_bill_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_bill_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_bill_providerId` FOREIGN KEY (`providerId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Bill`
--

/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
LOCK TABLES `Bill` WRITE;
INSERT INTO `Bill` VALUES  (1,'2009-03-01',31,'TRANSFER','EMITTED','2009/100','Curso de formación básica impartido por nuestros técnicos en el mes de febrero',NULL,NULL,'',7,'2009-02-01','2009-02-28','ISSUED','',1,NULL,1,1,'2009-04-07 09:44:07','2009-04-07 09:44:07',NULL),
 (2,'2009-03-02',60,'TRANSFER','EMITTED','01/09','Material de oficina (folios, toner para la impresora)',NULL,NULL,'',8,'2009-03-02','2009-03-02','RECIEVED','',NULL,2,1,1,'2009-04-07 09:46:39','2009-07-09 14:26:26',''),
 (3,'2009-02-01',31,'MONEY','PAID','0101A/09','Compra de bolígrafos, post-it, archivadores.',NULL,NULL,'',8,'2009-02-01','2009-02-01','RECIEVED','',NULL,3,1,1,'2009-04-07 09:47:59','2009-07-09 14:26:47',''),
 (4,'2009-04-01',31,'TRANSFER','EMITTED','A03/09','Servicios prestados mes de marzo ',NULL,NULL,'',8,'2009-03-01','2009-03-31','RECIEVED','',NULL,9,1,1,'2009-04-07 09:56:34','2009-04-07 09:57:35',''),
 (5,'2009-02-01',31,'UNKNOWN','PAID','A01/09','Servicios prestados mes de enero ',NULL,NULL,'',8,'2009-01-01','2009-01-31','RECIEVED','',NULL,9,1,1,'2009-04-07 10:00:21','2009-04-07 10:01:25',''),
 (6,'2009-03-01',31,'UNKNOWN','PAID','A02/09','Servicios prestados mes de febrero ',NULL,NULL,'',8,'2009-02-01','2009-02-28','RECIEVED','',NULL,9,1,1,'2009-04-07 10:01:06','2009-04-07 10:01:44','');
INSERT INTO `Bill` VALUES  (7,'2009-02-01',31,'UNKNOWN','PAID','T00001/09','Factura teléfono mes de enero ',NULL,NULL,'',8,'2009-01-01','2009-01-31','RECIEVED','',NULL,7,1,1,'2009-04-07 10:09:37','2009-04-07 10:10:31',''),
 (8,'2009-03-01',60,'PROMISSORYNOTE','IN_CREDITTITLE','2009/101','Realización de nuestros servicios de consultoriía en sus oficinas en el mes de febrero',NULL,NULL,'',10,'2009-02-01','2009-02-28','ISSUED','',NULL,NULL,1,1,'2009-04-07 10:19:31','2009-04-14 10:32:07',NULL),
 (9,'2009-04-01',31,'UNKNOWN','EMITTED','2009/102','Servicios de consultoría mes de febrero y marzo ',NULL,NULL,'Con fecha 7 de abril hacen el pago del 50% de la factura (2088euros)',11,'2009-02-01','2009-03-31','ISSUED','',NULL,NULL,1,1,'2009-04-07 10:22:01','2009-04-07 10:23:50',NULL),
 (10,'2009-04-07',31,'UNKNOWN','EMITTED','L9/09','Suministro de luz mes de marzo ',NULL,NULL,'',8,'2009-03-01','2009-03-31','RECIEVED','',NULL,8,1,1,'2009-04-07 10:25:13','2009-04-07 10:25:13',''),
 (11,'2009-04-01',45,'UNKNOWN','EMITTED','2009/103','Desarrollo de proyecto según oferta 09012001',NULL,NULL,'',6,'2009-01-01','2009-03-31','ISSUED','',NULL,NULL,1,1,'2009-04-07 11:25:52','2009-04-07 11:25:52',NULL);
INSERT INTO `Bill` VALUES  (12,'2009-02-01',31,'UNKNOWN','PAID','0109','Alquiler oficina mes de febrero ',NULL,NULL,'',8,'2009-02-01','2009-02-28','RECIEVED','',NULL,11,1,1,'2009-04-07 14:46:57','2009-07-09 14:27:07',''),
 (13,'2009-03-01',31,'UNKNOWN','PAID','0209','Alquiler oficina mes de marzo ',NULL,NULL,'',8,'2009-03-01','2009-03-31','RECIEVED','',NULL,11,1,1,'2009-04-07 14:47:54','2009-04-07 15:15:29',''),
 (14,'2009-04-01',31,'UNKNOWN','EMITTED','0309','Alquiler oficina mes de abril ',NULL,NULL,'',8,'2009-04-01','2009-04-07','RECIEVED','',NULL,11,1,1,'2009-04-07 14:48:57','2009-04-07 14:48:57',''),
 (15,'2009-04-02',31,'UNKNOWN','PAID','2009/104','Instalación del programa .',NULL,NULL,'',15,'2009-04-02','2009-04-02','ISSUED','',4,NULL,1,1,'2009-04-13 13:53:11','2009-04-13 13:53:55',NULL),
 (16,'2009-07-06',20,'UNKNOWN','EMITTED','1122','Arranque del proyecto',NULL,NULL,'',18,'2009-07-06','2009-07-06','ISSUED','',NULL,NULL,1,1,'2009-07-07 10:17:14','2009-07-07 11:55:52',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;


--
-- Definition of table `BillBreakDown`
--

DROP TABLE IF EXISTS `BillBreakDown`;
CREATE TABLE  `BillBreakDown` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `billId` int(11) NOT NULL,
  `concept` varchar(4096) collate utf8_spanish_ci NOT NULL,
  `units` decimal(10,2) NOT NULL default '1.00',
  `amount` decimal(10,2) NOT NULL,
  `iva` decimal(4,2) NOT NULL default '16.00',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_billBreakDown_bill` (`billId`),
  CONSTRAINT `fk_billBreakDown_bill` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `BillBreakDown`
--

/*!40000 ALTER TABLE `BillBreakDown` DISABLE KEYS */;
LOCK TABLES `BillBreakDown` WRITE;
INSERT INTO `BillBreakDown` VALUES  (1,1,'Curso de formación mes de marzo ','1.00','3002.16','16.00',NULL,NULL,NULL,NULL),
 (2,2,'Material de oficina','1.00','225.15','7.00',NULL,NULL,NULL,NULL),
 (3,3,'Compra de bolígrafos, post-it, archivadores.','1.00','96.85','4.00',NULL,NULL,NULL,NULL),
 (4,4,'Servicios prestados mes de marzo ','1.00','250.00','16.00',NULL,NULL,NULL,NULL),
 (5,5,'Servicios prestados mes de enero ','1.00','250.00','16.00',NULL,NULL,NULL,NULL),
 (6,6,'Servicios prestados mes de febrero ','1.00','250.00','16.00',NULL,NULL,NULL,NULL),
 (7,7,'Factura teléfono mes de enero ','1.00','85.40','16.00',NULL,NULL,NULL,NULL),
 (8,8,'Servicios de consultoría mes de febrero ','1.00','600.00','16.00',NULL,NULL,NULL,NULL),
 (9,9,'Servicios de consultoría mes de febrero y  marzo ','1.00','3600.00','16.00',NULL,NULL,NULL,NULL),
 (10,10,'Suministro de luz mes de marzo ','1.00','36.00','16.00',NULL,NULL,NULL,NULL),
 (11,11,'Desarrollo de proyecto según oferta 09022001','1.00','6200.00','16.00',NULL,NULL,NULL,NULL),
 (12,12,'Alquiler oficina mes de febrero ','1.00','700.00','16.00',NULL,NULL,NULL,NULL);
INSERT INTO `BillBreakDown` VALUES  (13,13,'Alquiler oficina mes de marzo ','1.00','700.00','16.00',NULL,NULL,NULL,NULL),
 (14,14,'Alquiler oficina mes de abril ','1.00','700.00','16.00',NULL,NULL,NULL,NULL),
 (15,15,'Instalación del programa','1.00','1500.00','16.00',NULL,NULL,NULL,NULL),
 (16,16,'Horas de análisis','15.00','60.00','16.00',NULL,NULL,NULL,NULL),
 (20,16,'Servidor de producción','1.00','1200.00','7.00',NULL,NULL,NULL,NULL),
 (21,16,'Alfombrillas para el ratón','15.00','1.00','0.00',NULL,NULL,NULL,NULL),
 (22,16,'Proyector para realizar la demo del producto','2.00','3000.00','4.00',NULL,NULL,NULL,NULL),
 (23,16,'Horas de desarrollo','50.00','40.00','16.00',NULL,NULL,NULL,NULL),
 (24,12,'Consumo energético extra','1.00','100.00','0.00',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `BillBreakDown` ENABLE KEYS */;


--
-- Definition of table `Bill_AccountEntry`
--

DROP TABLE IF EXISTS `Bill_AccountEntry`;
CREATE TABLE  `Bill_AccountEntry` (
  `billId` int(11) NOT NULL COMMENT 'bill id',
  `accountEntryId` int(11) NOT NULL COMMENT 'account entry id',
  `observations` varchar(2048) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`billId`,`accountEntryId`),
  KEY `ndx_bill_AccountEntry_billId` (`billId`),
  KEY `ndx_bill_AccountEntry_accountEntryId` (`accountEntryId`),
  CONSTRAINT `fk_billAccountEntry_accountEntryId` FOREIGN KEY (`accountEntryId`) REFERENCES `AccountEntry` (`id`),
  CONSTRAINT `fk_billAccountEntry_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='relaciona n m Facturas y movimientos';

--
-- Dumping data for table `Bill_AccountEntry`
--

/*!40000 ALTER TABLE `Bill_AccountEntry` DISABLE KEYS */;
LOCK TABLES `Bill_AccountEntry` WRITE;
INSERT INTO `Bill_AccountEntry` VALUES  (3,1,NULL),
 (5,2,NULL),
 (6,3,NULL),
 (7,4,NULL),
 (12,6,NULL),
 (13,7,NULL),
 (15,8,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bill_AccountEntry` ENABLE KEYS */;


--
-- Definition of table `Book`
--

DROP TABLE IF EXISTS `Book`;
CREATE TABLE  `Book` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(255) collate utf8_spanish_ci NOT NULL,
  `author` varchar(255) collate utf8_spanish_ci default NULL,
  `ISBN` varchar(13) collate utf8_spanish_ci default NULL,
  `URL` varchar(255) collate utf8_spanish_ci default NULL,
  `price` decimal(10,2) default NULL,
  `purchaseDate` datetime default NULL,
  `userId` int(11) default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Book_userId` (`userId`),
  CONSTRAINT `FK_Book_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Book`
--

/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
LOCK TABLES `Book` WRITE;
INSERT INTO `Book` VALUES  (1,'Guía de Estilos, protocolo y etiqueta en la empresa','Pilar Benito San Cristobal','9788493602871','','30.00','2009-04-14 00:00:00',2,1,1,'2009-04-14 12:12:28','2009-04-14 12:12:28'),
 (2,'Leyendas urbanas en España','Antonio Ortí y Josep Samperez','8427025661','',NULL,'2009-04-14 00:00:00',1,1,1,'2009-04-14 12:13:53','2009-04-14 12:13:53'),
 (3,'Liderazgo estratégico','Guillem Bou Bauzá','8436819098','',NULL,'2009-04-14 00:00:00',1,1,1,'2009-04-14 12:14:27','2009-04-14 12:14:27');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;


--
-- Definition of table `BulletinBoard`
--

DROP TABLE IF EXISTS `BulletinBoard`;
CREATE TABLE  `BulletinBoard` (
  `id` int(11) NOT NULL auto_increment,
  `categoryId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `message` varchar(2048) collate utf8_spanish_ci NOT NULL,
  `title` varchar(128) collate utf8_spanish_ci NOT NULL,
  `documentPath` varchar(128) collate utf8_spanish_ci default NULL,
  `documentContentType` varchar(128) collate utf8_spanish_ci default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_bulletinboard_categoryId` (`categoryId`),
  KEY `ndx_bulletinboard_userId` (`userId`),
  CONSTRAINT `fk_bulletinboard_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `BulletinBoardCategory` (`id`),
  CONSTRAINT `fk_bulletinboard_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='mensajes del tabln';

--
-- Dumping data for table `BulletinBoard`
--

/*!40000 ALTER TABLE `BulletinBoard` DISABLE KEYS */;
LOCK TABLES `BulletinBoard` WRITE;
INSERT INTO `BulletinBoard` VALUES  (1,1,1,'2009-05-05 00:00:00','Liberada nueva versión de TNT Concept','Liberada versión 016.1',NULL,NULL,1,'2009-05-05 10:26:14','2009-05-05 10:30:01');
UNLOCK TABLES;
/*!40000 ALTER TABLE `BulletinBoard` ENABLE KEYS */;


--
-- Definition of table `BulletinBoardCategory`
--

DROP TABLE IF EXISTS `BulletinBoardCategory`;
CREATE TABLE  `BulletinBoardCategory` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las categoras';

--
-- Dumping data for table `BulletinBoardCategory`
--

/*!40000 ALTER TABLE `BulletinBoardCategory` DISABLE KEYS */;
LOCK TABLES `BulletinBoardCategory` WRITE;
INSERT INTO `BulletinBoardCategory` VALUES  (1,'Pública',NULL,NULL,NULL,NULL),
 (2,'General',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `BulletinBoardCategory` ENABLE KEYS */;


--
-- Definition of table `Collaborator`
--

DROP TABLE IF EXISTS `Collaborator`;
CREATE TABLE  `Collaborator` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(10) default NULL,
  `contactId` int(10) default NULL,
  `organizationId` int(10) default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_collaborator_user` (`userId`),
  KEY `fk_collaborator_contact` (`contactId`),
  KEY `fk_collaborator_organization` (`organizationId`),
  CONSTRAINT `fk_collaborator_contact` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_collaborator_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_collaborator_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Collaborator`
--

/*!40000 ALTER TABLE `Collaborator` DISABLE KEYS */;
LOCK TABLES `Collaborator` WRITE;
INSERT INTO `Collaborator` VALUES  (1,NULL,1,NULL,1,1,'2009-04-13 14:17:49','2009-04-13 14:17:49'),
 (2,NULL,NULL,13,1,1,'2009-04-13 14:30:01','2009-04-13 14:30:01'),
 (3,NULL,NULL,14,1,1,'2009-04-13 15:07:39','2009-04-13 15:07:39');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Collaborator` ENABLE KEYS */;


--
-- Definition of table `Commissioning`
--

DROP TABLE IF EXISTS `Commissioning`;
CREATE TABLE  `Commissioning` (
  `id` int(11) NOT NULL auto_increment,
  `requestDate` datetime NOT NULL,
  `name` varchar(512) collate utf8_spanish_ci NOT NULL,
  `scope` varchar(1024) collate utf8_spanish_ci default NULL,
  `content` varchar(1024) collate utf8_spanish_ci default NULL,
  `products` varchar(1024) collate utf8_spanish_ci default NULL,
  `deliveryDate` datetime NOT NULL,
  `budget` decimal(10,2) default NULL,
  `notes` varchar(1024) collate utf8_spanish_ci default NULL,
  `authorSignature` tinyint(1) NOT NULL default '0',
  `reviserSignature` tinyint(1) NOT NULL default '0',
  `adminSignature` tinyint(1) NOT NULL default '0',
  `justifyInformation` tinyint(1) NOT NULL default '0',
  `developedActivities` varchar(1024) collate utf8_spanish_ci default NULL,
  `difficultiesAppeared` varchar(1024) collate utf8_spanish_ci default NULL,
  `results` varchar(1024) collate utf8_spanish_ci default NULL,
  `conclusions` varchar(1024) collate utf8_spanish_ci default NULL,
  `evaluation` varchar(1024) collate utf8_spanish_ci default NULL,
  `status` varchar(20) collate utf8_spanish_ci default NULL,
  `projectId` int(10) default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `deleteDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_commissioning_project` (`projectId`),
  CONSTRAINT `fk_commissioning_project` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Commissioning`
--

/*!40000 ALTER TABLE `Commissioning` DISABLE KEYS */;
LOCK TABLES `Commissioning` WRITE;
INSERT INTO `Commissioning` VALUES  (1,'2009-04-01 00:00:00','ISO 9001','Obtención del certificado de calidad ISO 9001','Documentación del sistema de gestión de calidad, procedimientos y dos auditorías internas.','','2009-06-01 00:00:00','5900.00','',0,0,1,0,NULL,NULL,NULL,NULL,NULL,'ACCEPTED',8,'2009-04-13 14:34:54','2009-04-13 14:39:52','2009-04-13 14:39:52'),
 (2,'2009-04-01 00:00:00','Pagina WEB','Creación página web de nuestra empresa','','Pagina web','2009-05-15 00:00:00','2800.00','',1,0,1,0,NULL,NULL,NULL,NULL,NULL,'CONFIRMED',17,'2009-04-13 15:08:41','2009-04-13 15:12:34',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Commissioning` ENABLE KEYS */;


--
-- Definition of table `CommissioningChange`
--

DROP TABLE IF EXISTS `CommissioningChange`;
CREATE TABLE  `CommissioningChange` (
  `id` int(11) NOT NULL auto_increment,
  `field` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `commissioningId` int(10) default NULL,
  `userId` int(10) default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `status` varchar(20) collate utf8_spanish_ci default NULL,
  `deleteDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_commissioningChange_commissioning` (`commissioningId`),
  KEY `fk_commissioningChange_user` (`userId`),
  CONSTRAINT `fk_commissioningChange_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`),
  CONSTRAINT `fk_commissioningChange_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `CommissioningChange`
--

/*!40000 ALTER TABLE `CommissioningChange` DISABLE KEYS */;
LOCK TABLES `CommissioningChange` WRITE;
INSERT INTO `CommissioningChange` VALUES  (1,'commissioning.authorSignature','false','true',2,1,'2009-04-13 15:12:34',NULL,'CONFIRMED',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `CommissioningChange` ENABLE KEYS */;


--
-- Definition of table `CommissioningDelay`
--

DROP TABLE IF EXISTS `CommissioningDelay`;
CREATE TABLE  `CommissioningDelay` (
  `id` int(11) NOT NULL auto_increment,
  `reason` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `originalDate` datetime NOT NULL,
  `delayedToDate` datetime NOT NULL,
  `commissioningId` int(10) default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `status` varchar(20) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_commissioningDelay_commissioning` (`commissioningId`),
  CONSTRAINT `fk_commissioningDelay_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `CommissioningDelay`
--

/*!40000 ALTER TABLE `CommissioningDelay` DISABLE KEYS */;
LOCK TABLES `CommissioningDelay` WRITE;
INSERT INTO `CommissioningDelay` VALUES  (1,'acumulación de tareas','2009-06-01 00:00:00','2009-06-01 00:00:00',1,'2009-04-13 14:38:11','2009-04-13 14:38:11','ACCEPTED'),
 (2,'Acumulación de tareas','2009-05-15 00:00:00','2009-05-15 00:00:00',2,'2009-04-13 15:09:25','2009-04-13 15:09:25','APROVED');
UNLOCK TABLES;
/*!40000 ALTER TABLE `CommissioningDelay` ENABLE KEYS */;


--
-- Definition of table `CommissioningFile`
--

DROP TABLE IF EXISTS `CommissioningFile`;
CREATE TABLE  `CommissioningFile` (
  `id` int(11) NOT NULL auto_increment,
  `commissioningId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `insertDate` datetime NOT NULL,
  `updateDate` datetime default NULL,
  `file` varchar(400) collate utf8_spanish_ci NOT NULL,
  `fileMime` varchar(128) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_commissioningFile_commissioning` (`commissioningId`),
  KEY `fk_commissioningFile_user` (`userId`),
  CONSTRAINT `fk_commissioningFile_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`),
  CONSTRAINT `fk_commissioningFile_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `CommissioningFile`
--

/*!40000 ALTER TABLE `CommissioningFile` DISABLE KEYS */;
LOCK TABLES `CommissioningFile` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `CommissioningFile` ENABLE KEYS */;


--
-- Definition of table `CommissioningPaymentData`
--

DROP TABLE IF EXISTS `CommissioningPaymentData`;
CREATE TABLE  `CommissioningPaymentData` (
  `id` int(11) NOT NULL auto_increment,
  `commissioningId` int(10) NOT NULL,
  `collaboratorId` int(11) NOT NULL,
  `paymentMode` varchar(32) collate utf8_spanish_ci default NULL,
  `bankAccount` varchar(50) collate utf8_spanish_ci default NULL,
  `billNumber` varchar(50) collate utf8_spanish_ci default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_commissioning_collaborator_commissioning` (`commissioningId`),
  KEY `fk_commissioning_collaborator_collaborator` (`collaboratorId`),
  CONSTRAINT `fk_commissioning_collaborator_collaborator` FOREIGN KEY (`collaboratorId`) REFERENCES `Collaborator` (`id`),
  CONSTRAINT `fk_commissioning_collaborator_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `CommissioningPaymentData`
--

/*!40000 ALTER TABLE `CommissioningPaymentData` DISABLE KEYS */;
LOCK TABLES `CommissioningPaymentData` WRITE;
INSERT INTO `CommissioningPaymentData` VALUES  (1,1,2,'UNKNOWN','','',NULL,NULL),
 (2,2,3,'MONEY','','01AB',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `CommissioningPaymentData` ENABLE KEYS */;


--
-- Definition of table `Commissioning_User`
--

DROP TABLE IF EXISTS `Commissioning_User`;
CREATE TABLE  `Commissioning_User` (
  `id` int(11) NOT NULL auto_increment,
  `commissioningId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_commissioning_user_commissioning` (`commissioningId`),
  KEY `fk_commissioning_user_user` (`userId`),
  CONSTRAINT `fk_commissioning_user_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`),
  CONSTRAINT `fk_commissioning_user_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Commissioning_User`
--

/*!40000 ALTER TABLE `Commissioning_User` DISABLE KEYS */;
LOCK TABLES `Commissioning_User` WRITE;
INSERT INTO `Commissioning_User` VALUES  (9,1,1),
 (10,1,3),
 (18,2,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Commissioning_User` ENABLE KEYS */;


--
-- Definition of table `CompanyState`
--

DROP TABLE IF EXISTS `CompanyState`;
CREATE TABLE  `CompanyState` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL COMMENT 'aplicacin que manda el mail',
  `creationDate` datetime NOT NULL,
  `description` longtext collate utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_companystate_userId` (`userId`),
  CONSTRAINT `fk_companystate_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='comentario del director de empresa';

--
-- Dumping data for table `CompanyState`
--

/*!40000 ALTER TABLE `CompanyState` DISABLE KEYS */;
LOCK TABLES `CompanyState` WRITE;
INSERT INTO `CompanyState` VALUES  (1,1,'2009-04-01 00:00:00','Liberada versión TNT CONCEPT',1,'2009-05-05 10:29:07','2009-05-05 10:29:07');
UNLOCK TABLES;
/*!40000 ALTER TABLE `CompanyState` ENABLE KEYS */;


--
-- Definition of table `Contact`
--

DROP TABLE IF EXISTS `Contact`;
CREATE TABLE  `Contact` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(150) collate utf8_spanish_ci NOT NULL,
  `email` varchar(128) collate utf8_spanish_ci default NULL,
  `phone` varchar(15) collate utf8_spanish_ci default NULL,
  `mobile` varchar(15) collate utf8_spanish_ci default NULL,
  `notified` tinyint(1) NOT NULL default '0',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `email2` varchar(128) collate utf8_spanish_ci default NULL,
  `phone2` varchar(15) collate utf8_spanish_ci default NULL,
  `fax` varchar(15) collate utf8_spanish_ci default NULL,
  `address` varchar(100) collate utf8_spanish_ci default NULL,
  `postalCode` varchar(5) collate utf8_spanish_ci default NULL,
  `city` varchar(100) collate utf8_spanish_ci default NULL,
  `country` varchar(100) collate utf8_spanish_ci default NULL,
  `provinceId` int(11) default NULL,
  `active` tinyint(1) default '1',
  PRIMARY KEY  (`id`),
  KEY `ndx_contact_provinceId` (`provinceId`),
  CONSTRAINT `fk_contact_province` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos de las Organizationes';

--
-- Dumping data for table `Contact`
--

/*!40000 ALTER TABLE `Contact` DISABLE KEYS */;
LOCK TABLES `Contact` WRITE;
INSERT INTO `Contact` VALUES  (1,'Ana Sánchez ','ana.s@atrilcb.es','91','91',0,1,1,'2009-04-06 12:05:32','2009-06-26 10:00:09','','','','','','','',1,1),
 (2,'Antonio Ruiz-Sánchez','a.ruiz@tecnologiasespaña.es','93','93',0,1,1,'2009-04-06 12:06:19','2009-06-26 10:00:29','','','','','','','',1,1),
 (3,'Pilar M. de Sancho','pilar.me@suministros.es','91','91',0,1,1,'2009-04-06 12:07:22','2009-06-26 10:21:22','','','','','','','',1,1),
 (4,'Ignacio Pérez Cuerda','ignacio.perez@fundación.es','921','600',0,1,1,'2009-04-13 13:48:59','2009-06-26 10:00:52','','','','','','','',1,1),
 (5,'Roberto Canales','rcanales@autentia.com','916753306','666999888',0,1,1,'2009-07-08 09:07:25','2009-07-08 10:12:56','','','','','','','',28,1),
 (6,'Gema Pérez','gema.perez@autentia.com','916753306','666111222',0,1,1,'2009-07-08 09:41:09','2009-07-08 09:41:09','','','','','','','',1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Contact` ENABLE KEYS */;


--
-- Definition of table `ContactInfo`
--

DROP TABLE IF EXISTS `ContactInfo`;
CREATE TABLE  `ContactInfo` (
  `id` int(11) NOT NULL auto_increment,
  `contactId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_contactinfo_contact` (`contactId`),
  KEY `fk_contactinfo_position` (`positionId`),
  KEY `fk_contactinfo_department` (`departmentId`),
  KEY `fk_contactinfo_organization` (`organizationId`),
  CONSTRAINT `fk_contactinfo_contact` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_contactinfo_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_contactinfo_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_contactinfo_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ContactInfo`
--

/*!40000 ALTER TABLE `ContactInfo` DISABLE KEYS */;
LOCK TABLES `ContactInfo` WRITE;
INSERT INTO `ContactInfo` VALUES  (1,1,2,4,5),
 (2,2,3,4,2),
 (3,3,4,4,3),
 (4,4,5,4,12),
 (5,1,3,1,5),
 (6,2,8,3,9),
 (7,5,5,1,16),
 (8,6,5,1,16);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ContactInfo` ENABLE KEYS */;


--
-- Definition of table `ContactOwner`
--

DROP TABLE IF EXISTS `ContactOwner`;
CREATE TABLE  `ContactOwner` (
  `id` int(11) NOT NULL auto_increment,
  `contactId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_contactowner_contactId` (`contactId`),
  KEY `fk_contactowner_userId` (`userId`),
  CONSTRAINT `fk_contactowner_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_contactowner_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Propietarios de contactos';

--
-- Dumping data for table `ContactOwner`
--

/*!40000 ALTER TABLE `ContactOwner` DISABLE KEYS */;
LOCK TABLES `ContactOwner` WRITE;
INSERT INTO `ContactOwner` VALUES  (1,1,1),
 (2,2,1),
 (3,3,1),
 (4,4,1),
 (5,5,1),
 (6,6,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ContactOwner` ENABLE KEYS */;


--
-- Definition of table `Contact_Tag`
--

DROP TABLE IF EXISTS `Contact_Tag`;
CREATE TABLE  `Contact_Tag` (
  `id` int(11) NOT NULL auto_increment,
  `tagId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_contact_tag_tag` (`tagId`),
  KEY `fk_contact_tag_contact` (`contactId`),
  CONSTRAINT `fk_contact_tag_contact` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_contact_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Contact_Tag`
--

/*!40000 ALTER TABLE `Contact_Tag` DISABLE KEYS */;
LOCK TABLES `Contact_Tag` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Contact_Tag` ENABLE KEYS */;


--
-- Definition of table `ContractType`
--

DROP TABLE IF EXISTS `ContractType`;
CREATE TABLE  `ContractType` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ContractType`
--

/*!40000 ALTER TABLE `ContractType` DISABLE KEYS */;
LOCK TABLES `ContractType` WRITE;
INSERT INTO `ContractType` VALUES  (1,'Prácticas','Departamento de dirección.',NULL,NULL,NULL,NULL),
 (2,'Duración determinada','Departamento de dirección.',NULL,NULL,NULL,NULL),
 (3,'Indefinido','Departamento de dirección.',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ContractType` ENABLE KEYS */;


--
-- Definition of table `CreditTitle`
--

DROP TABLE IF EXISTS `CreditTitle`;
CREATE TABLE  `CreditTitle` (
  `id` int(11) NOT NULL auto_increment,
  `number` varchar(64) collate utf8_spanish_ci NOT NULL,
  `concept` varchar(1024) collate utf8_spanish_ci default NULL,
  `amount` decimal(10,2) NOT NULL,
  `state` varchar(16) collate utf8_spanish_ci default NULL,
  `type` varchar(16) collate utf8_spanish_ci default NULL,
  `issueDate` date NOT NULL,
  `expirationDate` date default NULL,
  `organizationId` int(11) NOT NULL,
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_credittitle_organizationId` (`organizationId`),
  CONSTRAINT `fk_credittitle_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `CreditTitle`
--

/*!40000 ALTER TABLE `CreditTitle` DISABLE KEYS */;
LOCK TABLES `CreditTitle` WRITE;
INSERT INTO `CreditTitle` VALUES  (1,'S001S002','Cobro factura 02','696.00','EMITTED','RECEIVED','2009-04-04','2009-04-15',6,'',1,1,'2009-04-07 11:21:25','2009-07-13 10:21:21'),
 (2,'S001S003','Pago factura','400.00','EMITTED','RECEIVED','2009-01-01','2009-12-31',2,'',1,1,'2009-07-13 09:32:17','2009-07-13 09:32:31');
UNLOCK TABLES;
/*!40000 ALTER TABLE `CreditTitle` ENABLE KEYS */;


--
-- Definition of table `CreditTitle_Bill`
--

DROP TABLE IF EXISTS `CreditTitle_Bill`;
CREATE TABLE  `CreditTitle_Bill` (
  `billId` int(11) NOT NULL,
  `creditTitleId` int(11) NOT NULL,
  PRIMARY KEY  (`billId`,`creditTitleId`),
  KEY `ndx_creditTitle_Bill_billId` (`billId`),
  KEY `ndx_creditTitle_Bill_creditTitleId` (`creditTitleId`),
  CONSTRAINT `fk_creditTitle_Bill_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_creditTitle_Bill_creditTitleId` FOREIGN KEY (`creditTitleId`) REFERENCES `CreditTitle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `CreditTitle_Bill`
--

/*!40000 ALTER TABLE `CreditTitle_Bill` DISABLE KEYS */;
LOCK TABLES `CreditTitle_Bill` WRITE;
INSERT INTO `CreditTitle_Bill` VALUES  (8,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `CreditTitle_Bill` ENABLE KEYS */;


--
-- Definition of table `Department`
--

DROP TABLE IF EXISTS `Department`;
CREATE TABLE  `Department` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `parentId` int(10) unsigned default NULL,
  `name` varchar(256) collate utf8_spanish_ci default NULL,
  `description` varchar(2048) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_department_department` (`parentId`),
  CONSTRAINT `fk_department_department` FOREIGN KEY (`parentId`) REFERENCES `Department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Department`
--

/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
LOCK TABLES `Department` WRITE;
INSERT INTO `Department` VALUES  (1,3,'Dirección','Departamento de dirección.',NULL,NULL,NULL,'2009-06-26 09:58:43'),
 (2,1,'I+D+I','Departamento de I+D+I.',NULL,NULL,NULL,'2009-06-26 09:59:00'),
 (3,1,'Consultoría','Departamento de Consultoría.',NULL,NULL,NULL,'2009-06-26 09:58:23'),
 (4,NULL,'Indefinido','Departamento sin definir',1,1,'2009-06-09 15:46:59','2009-06-09 15:46:59');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;


--
-- Definition of table `Department_Organization`
--

DROP TABLE IF EXISTS `Department_Organization`;
CREATE TABLE  `Department_Organization` (
  `id` int(11) NOT NULL auto_increment,
  `departmentId` int(10) unsigned NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_department_organization_department` (`departmentId`),
  KEY `fk_department_organization_organization` (`organizationId`),
  CONSTRAINT `fk_department_organization_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_department_organization_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Department_Organization`
--

/*!40000 ALTER TABLE `Department_Organization` DISABLE KEYS */;
LOCK TABLES `Department_Organization` WRITE;
INSERT INTO `Department_Organization` VALUES  (1,2,5),
 (2,1,5),
 (3,1,11),
 (4,3,11),
 (5,2,13),
 (6,1,13),
 (7,3,13),
 (8,3,12),
 (9,1,9),
 (10,3,9),
 (11,1,8),
 (12,2,14),
 (13,3,14),
 (14,1,10),
 (15,3,10),
 (16,2,1),
 (17,4,1),
 (18,3,1),
 (19,2,2),
 (20,1,2),
 (21,3,2),
 (22,4,3),
 (23,1,3),
 (24,2,4),
 (25,3,4),
 (26,2,7),
 (27,4,7),
 (28,1,7),
 (29,3,7),
 (30,2,16),
 (31,1,16);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Department_Organization` ENABLE KEYS */;


--
-- Definition of table `Department_Tag`
--

DROP TABLE IF EXISTS `Department_Tag`;
CREATE TABLE  `Department_Tag` (
  `id` int(11) NOT NULL auto_increment,
  `tagId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_department_tag_tag` (`tagId`),
  KEY `fk_department_tag_department` (`departmentId`),
  CONSTRAINT `fk_department_tag_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_department_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Department_Tag`
--

/*!40000 ALTER TABLE `Department_Tag` DISABLE KEYS */;
LOCK TABLES `Department_Tag` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Department_Tag` ENABLE KEYS */;


--
-- Definition of table `Document`
--

DROP TABLE IF EXISTS `Document`;
CREATE TABLE  `Document` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `creationDate` datetime default NULL,
  `name` varchar(256) collate utf8_spanish_ci default NULL,
  `description` varchar(4096) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Document`
--

/*!40000 ALTER TABLE `Document` DISABLE KEYS */;
LOCK TABLES `Document` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Document` ENABLE KEYS */;


--
-- Definition of table `DocumentCategory`
--

DROP TABLE IF EXISTS `DocumentCategory`;
CREATE TABLE  `DocumentCategory` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(255) collate utf8_spanish_ci NOT NULL,
  `description` varchar(4096) collate utf8_spanish_ci default NULL,
  `code` varchar(45) collate utf8_spanish_ci default NULL,
  `categoryid` int(10) unsigned default NULL,
  `documentslastupdate` datetime default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `DocumentCategory`
--

/*!40000 ALTER TABLE `DocumentCategory` DISABLE KEYS */;
LOCK TABLES `DocumentCategory` WRITE;
INSERT INTO `DocumentCategory` VALUES  (1,'Documentos de Calidad','Documentos de calidad','CALIDAD',NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Otros Documentos','Otros documentos no clasificados',' ',NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'Curriculum Vitae','','',NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'Documentos de Usuarios','','',NULL,NULL,NULL,NULL,NULL,NULL),
 (10,'(I1-PC01) Lista de distribución de la documentación','','I1-PC01',1,NULL,NULL,NULL,NULL,NULL),
 (11,'(I1-PC02) Acta de reunión de revisión del sistema','','I1-PC02',1,NULL,NULL,NULL,NULL,NULL),
 (12,'(I1-PC08) Informe de auditoría interna','','I1-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (13,'(I2-PC02) Planificación de objetivos de calidad','','I2-PC02',1,NULL,NULL,NULL,NULL,NULL),
 (14,'(I2-PC08) Informe de no conformidad/reclamación del cliente','','I2-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (15,'(I3-PC08) Informe de acción correctiva/preventiva','','I3-PC08',1,NULL,NULL,NULL,NULL,NULL),
 (16,'(PC01) Sistema de Gestión de la Calidad','Documento inicial de descripción','PC01',1,NULL,NULL,NULL,NULL,NULL),
 (17,'(PC02) Responsabilidad de la Dirección','','PC02',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES  (18,'(PC03) Gestión de los Recursos','','PC03',1,NULL,NULL,NULL,NULL,NULL),
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
 (30,'Índice de ediciones de documentos','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES  (31,'Índice de no conformidades','','',1,NULL,NULL,NULL,NULL,NULL),
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
 (44,'(I6-PC08) Evaluación de satisfacción del cliente','','I6-PC08',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` VALUES  (45,'Jesus Sánchez Gómez','Jesus Sánchez Gómez',NULL,4,NULL,NULL,NULL,NULL,NULL),
 (46,'Natalia Rincón San García','Natalia Rincón San García',NULL,4,NULL,NULL,NULL,NULL,NULL),
 (47,'Jornada de LOPD','Jornada de LOPD',NULL,4,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `DocumentCategory` ENABLE KEYS */;


--
-- Definition of table `DocumentCategoryDoc`
--

DROP TABLE IF EXISTS `DocumentCategoryDoc`;
CREATE TABLE  `DocumentCategoryDoc` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `categoryid` int(10) unsigned NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_DocumentCategorydoc_category` (`categoryid`),
  KEY `FK_DocumentCategorydoc_docu` (`documentid`),
  CONSTRAINT `FK_DocumentCategorydoc_category` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `FK_DocumentCategorydoc_docu` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DocumentCategoryDoc`
--

/*!40000 ALTER TABLE `DocumentCategoryDoc` DISABLE KEYS */;
LOCK TABLES `DocumentCategoryDoc` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `DocumentCategoryDoc` ENABLE KEYS */;


--
-- Definition of table `DocumentVersion`
--

DROP TABLE IF EXISTS `DocumentVersion`;
CREATE TABLE  `DocumentVersion` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `documentPath` varchar(255) collate utf8_spanish_ci NOT NULL,
  `creationDate` datetime NOT NULL,
  `version` varchar(255) collate utf8_spanish_ci NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_DocumentVersion_document_id` (`documentid`),
  CONSTRAINT `FK_DocumentVersion_document_id` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `DocumentVersion`
--

/*!40000 ALTER TABLE `DocumentVersion` DISABLE KEYS */;
LOCK TABLES `DocumentVersion` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `DocumentVersion` ENABLE KEYS */;


--
-- Definition of table `EntityChange`
--

DROP TABLE IF EXISTS `EntityChange`;
CREATE TABLE  `EntityChange` (
  `id` int(11) NOT NULL auto_increment,
  `field` varchar(1024) character set utf8 collate utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) character set utf8 collate utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) character set utf8 collate utf8_spanish_ci NOT NULL,
  `userId` int(10) default NULL,
  `insertDate` datetime default NULL,
  `entityId` int(11) NOT NULL,
  `entityName` varchar(1024) character set utf8 collate utf8_spanish_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_entityChange_user` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `EntityChange`
--

/*!40000 ALTER TABLE `EntityChange` DISABLE KEYS */;
LOCK TABLES `EntityChange` WRITE;
INSERT INTO `EntityChange` VALUES  (4,'contact.name','Antonio Ruiz-S','Antonio Ruiz-Sánchez',1,'2009-06-26 08:41:14',2,'com.autentia.tnt.businessobject.Contact'),
 (5,'contact.province','','Alava',1,'2009-06-26 09:59:54',1,'com.autentia.tnt.businessobject.Contact'),
 (6,'contact.position','','Dirección de proyectos',1,'2009-06-26 10:00:03',1,'com.autentia.tnt.businessobject.Contact'),
 (7,'contact.position','','Desarrollador',1,'2009-06-26 10:00:25',2,'com.autentia.tnt.businessobject.Contact'),
 (8,'contact.province','','Alava',1,'2009-06-26 10:00:35',4,'com.autentia.tnt.businessobject.Contact'),
 (9,'contact.province','','Alava',1,'2009-06-26 10:21:12',3,'com.autentia.tnt.businessobject.Contact'),
 (10,'contact.mobile','','666999888',1,'2009-07-08 10:12:56',5,'com.autentia.tnt.businessobject.Contact'),
 (11,'contact.phone','','916753306',1,'2009-07-08 10:12:56',5,'com.autentia.tnt.businessobject.Contact');
UNLOCK TABLES;
/*!40000 ALTER TABLE `EntityChange` ENABLE KEYS */;


--
-- Definition of table `ExternalActivity`
--

DROP TABLE IF EXISTS `ExternalActivity`;
CREATE TABLE  `ExternalActivity` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(256) collate utf8_spanish_ci default NULL,
  `category` varchar(256) collate utf8_spanish_ci default NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `location` varchar(256) collate utf8_spanish_ci default NULL,
  `organizer` varchar(256) collate utf8_spanish_ci default NULL,
  `comments` varchar(2048) collate utf8_spanish_ci default NULL,
  `documentCategoryId` int(10) unsigned default NULL,
  `ownerId` int(10) default NULL,
  `departmentId` int(10) default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_externalactivity_documentcategory` (`documentCategoryId`),
  CONSTRAINT `fk_externalactivity_documentcategory` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ExternalActivity`
--

/*!40000 ALTER TABLE `ExternalActivity` DISABLE KEYS */;
LOCK TABLES `ExternalActivity` WRITE;
INSERT INTO `ExternalActivity` VALUES  (1,'Jornada de LOPD','Charla','2009-04-06 17:00:00','2009-04-06 19:00:00','Centro Puerta de Toledo en Madrid.','Asociación Jóvenes Empresarios de Madrid','Observaciones',47,1,1,'2009-04-13 13:38:40','2009-04-13 13:38:40');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ExternalActivity` ENABLE KEYS */;


--
-- Definition of table `FinancialRatio`
--

DROP TABLE IF EXISTS `FinancialRatio`;
CREATE TABLE  `FinancialRatio` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(128) collate utf8_spanish_ci NOT NULL,
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
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ratios financieros';

--
-- Dumping data for table `FinancialRatio`
--

/*!40000 ALTER TABLE `FinancialRatio` DISABLE KEYS */;
LOCK TABLES `FinancialRatio` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `FinancialRatio` ENABLE KEYS */;


--
-- Definition of table `Frequency`
--

DROP TABLE IF EXISTS `Frequency`;
CREATE TABLE  `Frequency` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_spanish_ci NOT NULL,
  `months` int(10) unsigned default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Frecuencia de los asientos contables';

--
-- Dumping data for table `Frequency`
--

/*!40000 ALTER TABLE `Frequency` DISABLE KEYS */;
LOCK TABLES `Frequency` WRITE;
INSERT INTO `Frequency` VALUES  (1,'Mensual',1,NULL,NULL,NULL,NULL),
 (2,'Trimestral',3,NULL,NULL,NULL,NULL),
 (3,'Semestral',6,NULL,NULL,NULL,NULL),
 (4,'Anual',12,NULL,NULL,NULL,NULL),
 (5,'Bimensual',2,NULL,NULL,NULL,NULL),
 (6,'Ocasional',0,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Frequency` ENABLE KEYS */;


--
-- Definition of table `Holiday`
--

DROP TABLE IF EXISTS `Holiday`;
CREATE TABLE  `Holiday` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `date` datetime NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Holiday`
--

/*!40000 ALTER TABLE `Holiday` DISABLE KEYS */;
LOCK TABLES `Holiday` WRITE;
INSERT INTO `Holiday` VALUES  (1,'Reyes','2009-01-06 00:00:00',1,1,'2009-04-06 12:10:09','2009-04-06 12:10:09'),
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
 (12,'Navidad','2009-12-25 00:00:00',1,1,'2009-04-06 12:13:29','2009-04-06 12:13:29');
INSERT INTO `Holiday` VALUES  (13,'Fiesta Nacional','2009-10-12 00:00:00',1,1,'2009-04-06 12:16:37','2009-04-06 12:16:37'),
 (14,'Asunción de La Virgen','2009-05-15 00:00:00',1,1,'2009-04-06 12:18:27','2009-04-06 12:18:27');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Holiday` ENABLE KEYS */;


--
-- Definition of table `Idea`
--

DROP TABLE IF EXISTS `Idea`;
CREATE TABLE  `Idea` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci NOT NULL,
  `cost` varchar(500) collate utf8_spanish_ci default NULL,
  `benefits` varchar(2048) collate utf8_spanish_ci default NULL,
  `name` varchar(300) collate utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_idea_userId` (`userId`),
  CONSTRAINT `fk_idea_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ideas de los empleados';

--
-- Dumping data for table `Idea`
--

/*!40000 ALTER TABLE `Idea` DISABLE KEYS */;
LOCK TABLES `Idea` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Idea` ENABLE KEYS */;


--
-- Definition of table `Interaction`
--

DROP TABLE IF EXISTS `Interaction`;
CREATE TABLE  `Interaction` (
  `id` int(11) NOT NULL auto_increment,
  `projectId` int(11) NOT NULL default '5' COMMENT 'project id',
  `userId` int(11) NOT NULL default '1' COMMENT 'user id',
  `interactionTypeId` int(11) NOT NULL default '6' COMMENT 'type id',
  `creationDate` datetime NOT NULL,
  `interest` varchar(16) collate utf8_spanish_ci NOT NULL COMMENT 'enum InteractionInterest',
  `description` varchar(2048) collate utf8_spanish_ci NOT NULL,
  `file` varchar(400) collate utf8_spanish_ci default NULL,
  `fileMime` varchar(128) collate utf8_spanish_ci default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `offerId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_interaction_projectId` (`projectId`),
  KEY `ndx_interaction_userId` (`userId`),
  KEY `ndx_interaction_interactionTypeId` (`interactionTypeId`),
  KEY `ndx_interaction_offerId` (`offerId`),
  CONSTRAINT `fk_interaction_interactionTypeId` FOREIGN KEY (`interactionTypeId`) REFERENCES `InteractionType` (`id`),
  CONSTRAINT `fk_interaction_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_interaction_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_interaction_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos que se mantienen con ';

--
-- Dumping data for table `Interaction`
--

/*!40000 ALTER TABLE `Interaction` DISABLE KEYS */;
LOCK TABLES `Interaction` WRITE;
INSERT INTO `Interaction` VALUES  (1,7,1,3,'2009-04-06 00:00:00','HIGH','Envío de oferta',NULL,NULL,1,'2009-04-06 13:08:09','2009-04-06 13:08:09',NULL),
 (2,7,1,5,'2009-04-06 00:00:00','HIGH','Contacto con dicha empresa, para tratar el tema de la facturación, si será a 30 ó 60 dias.',NULL,NULL,1,'2009-04-06 13:09:20','2009-04-06 13:09:20',NULL),
 (3,6,1,8,'2009-02-01 00:00:00','HIGH','Nos han dado el visto bueno a la oferta',NULL,NULL,1,'2009-04-06 13:10:11','2009-04-06 13:10:31',NULL),
 (4,14,1,3,'2009-04-07 00:00:00','HIGH','Envío de oferta',NULL,NULL,1,'2009-04-07 11:28:43','2009-04-07 11:28:43',1),
 (5,14,1,3,'2009-04-01 00:00:00','HIGH','Envío de oferta',NULL,NULL,1,'2009-04-13 13:50:47','2009-04-13 13:50:47',2),
 (6,13,1,8,'2009-04-05 00:00:00','HIGH','Confirmación de oferta',NULL,NULL,1,'2009-04-15 10:42:17','2009-04-15 10:42:17',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Interaction` ENABLE KEYS */;


--
-- Definition of table `InteractionType`
--

DROP TABLE IF EXISTS `InteractionType`;
CREATE TABLE  `InteractionType` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL COMMENT 'Interaction type descriptive name',
  `description` varchar(1024) collate utf8_spanish_ci default NULL COMMENT 'Interaction type description',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de interacciones';

--
-- Dumping data for table `InteractionType`
--

/*!40000 ALTER TABLE `InteractionType` DISABLE KEYS */;
LOCK TABLES `InteractionType` WRITE;
INSERT INTO `InteractionType` VALUES  (1,'No conformidad','',NULL,NULL,NULL,NULL),
 (2,'Accion comercial','',NULL,NULL,NULL,NULL),
 (3,'Envío de oferta','',NULL,NULL,NULL,NULL),
 (4,'Envío de factura','',NULL,NULL,NULL,NULL),
 (5,'Accion administrativa','',NULL,NULL,NULL,NULL),
 (6,'No definida','',NULL,NULL,NULL,NULL),
 (7,'Primer contacto','',NULL,NULL,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (8,'Confirmación de oferta','',NULL,NULL,'2009-04-02 08:38:02','2009-04-02 08:38:02');
UNLOCK TABLES;
/*!40000 ALTER TABLE `InteractionType` ENABLE KEYS */;


--
-- Definition of table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
CREATE TABLE  `Inventory` (
  `id` int(11) NOT NULL auto_increment,
  `buyDate` date default NULL,
  `asignedToId` int(11) default NULL,
  `renting` tinyint(1) NOT NULL default '0' COMMENT 'De renting (1) o no (0)',
  `cost` decimal(10,2) default NULL,
  `amortizable` tinyint(1) NOT NULL default '0' COMMENT 'Amortizable (1) o no (0)consumible',
  `serialNumber` varchar(30) collate utf8_spanish_ci NOT NULL,
  `type` varchar(16) collate utf8_spanish_ci NOT NULL,
  `provider` varchar(128) collate utf8_spanish_ci default NULL,
  `trademark` varchar(128) collate utf8_spanish_ci default NULL,
  `model` varchar(128) collate utf8_spanish_ci default NULL,
  `speed` varchar(10) collate utf8_spanish_ci default NULL,
  `storage` varchar(10) collate utf8_spanish_ci default NULL,
  `ram` varchar(10) collate utf8_spanish_ci default NULL,
  `location` varchar(128) collate utf8_spanish_ci default NULL,
  `description` varchar(256) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_invetory_userId` (`asignedToId`),
  CONSTRAINT `fk_inventory_userId` FOREIGN KEY (`asignedToId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Inventory de mquinas';

--
-- Dumping data for table `Inventory`
--

/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
LOCK TABLES `Inventory` WRITE;
INSERT INTO `Inventory` VALUES  (1,'2008-02-15',3,0,'1300.00',0,'NS22220000','LAPTOP','PC City Parque Corredor','Packard Bell','Easy Note','','','','','Ordenador portáitl ',1,1,'2009-04-06 13:01:51','2009-04-06 13:01:51'),
 (2,'2008-02-02',1,0,'425.00',0,'NS 000A1111B','PC','El Corte Inglés','Impresora HP','1001','','','','','Impresora',1,1,'2009-04-06 13:03:06','2009-04-06 13:03:06');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;


--
-- Definition of table `Magazine`
--

DROP TABLE IF EXISTS `Magazine`;
CREATE TABLE  `Magazine` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Magazines a las que se envan';

--
-- Dumping data for table `Magazine`
--

/*!40000 ALTER TABLE `Magazine` DISABLE KEYS */;
LOCK TABLES `Magazine` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Magazine` ENABLE KEYS */;


--
-- Definition of table `Objective`
--

DROP TABLE IF EXISTS `Objective`;
CREATE TABLE  `Objective` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date default NULL,
  `state` varchar(16) collate utf8_spanish_ci default NULL,
  `name` varchar(512) collate utf8_spanish_ci NOT NULL,
  `log` longtext collate utf8_spanish_ci,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_objective_userId` (`userId`),
  KEY `ndx_objective_projectId` (`projectId`),
  CONSTRAINT `fk_objective_projectId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_objective_userId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan los Objectives';

--
-- Dumping data for table `Objective`
--

/*!40000 ALTER TABLE `Objective` DISABLE KEYS */;
LOCK TABLES `Objective` WRITE;
INSERT INTO `Objective` VALUES  (1,1,13,'2009-04-07','2009-04-10','PENDING','Ingreso de talones en el banco ',NULL,1,'2009-04-07 11:19:37','2009-04-07 11:19:37'),
 (2,1,13,'2009-04-07','2009-04-10','PENDING','Control TNT',NULL,1,'2009-04-07 11:19:47','2009-04-07 11:19:47'),
 (3,1,8,'2009-04-01','2009-05-15','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:35:43','2009-04-13 14:35:43'),
 (4,1,8,'2009-04-01','2009-05-15','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:35:43','2009-04-13 14:35:43'),
 (5,1,8,'2009-04-01','2009-06-01','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:38:11','2009-04-13 14:38:11'),
 (6,1,8,'2009-04-01','2009-06-01','PENDING','Entrega del pedido ISO 9001',NULL,1,'2009-04-13 14:38:11','2009-04-13 14:38:11'),
 (7,1,17,'2009-04-01','2009-05-01','PENDING','Entrega del pedido Pagina WEB',NULL,1,'2009-04-13 15:08:56','2009-04-13 15:08:56'),
 (8,1,17,'2009-04-01','2009-05-15','PENDING','Entrega del pedido Pagina WEB',NULL,1,'2009-04-13 15:09:25','2009-04-13 15:09:25');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Objective` ENABLE KEYS */;


--
-- Definition of table `Occupation`
--

DROP TABLE IF EXISTS `Occupation`;
CREATE TABLE  `Occupation` (
  `id` int(11) NOT NULL auto_increment,
  `projectId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `description` varchar(1024) collate utf8_spanish_ci default NULL,
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_occupation_userId` (`userId`),
  KEY `ndx_occupation_projectId` (`projectId`),
  CONSTRAINT `fk_occupation_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_occupation_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Future occupations of Users';

--
-- Dumping data for table `Occupation`
--

/*!40000 ALTER TABLE `Occupation` DISABLE KEYS */;
LOCK TABLES `Occupation` WRITE;
INSERT INTO `Occupation` VALUES  (1,10,2,'2009-04-06','2009-04-30','',480,1,1,'2009-04-14 12:10:16','2009-04-14 12:10:16');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Occupation` ENABLE KEYS */;


--
-- Definition of table `Offer`
--

DROP TABLE IF EXISTS `Offer`;
CREATE TABLE  `Offer` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci default NULL,
  `userId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  `creationDate` date NOT NULL,
  `validityDate` date default NULL,
  `maturityDate` date default NULL,
  `offerPotential` varchar(16) collate utf8_spanish_ci NOT NULL,
  `offerState` varchar(16) collate utf8_spanish_ci NOT NULL,
  `offerRejectReasonId` int(11) default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `number` varchar(64) collate utf8_spanish_ci NOT NULL,
  `observations` varchar(2048) collate utf8_spanish_ci default NULL,
  `showIvaIntoReport` tinyint(1) default '1',
  PRIMARY KEY  (`id`),
  KEY `ndx_offer_organizationId` (`organizationId`),
  KEY `ndx_offer_contactId` (`contactId`),
  KEY `ndx_offer_offerRejectReasonId` (`offerRejectReasonId`),
  KEY `ndx_offer_userId` (`userId`),
  CONSTRAINT `fk_offer_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_offer_offerRejectReasonId` FOREIGN KEY (`offerRejectReasonId`) REFERENCES `OfferRejectReason` (`id`),
  CONSTRAINT `fk_offer_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_offer_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ofertas';

--
-- Dumping data for table `Offer`
--

/*!40000 ALTER TABLE `Offer` DISABLE KEYS */;
LOCK TABLES `Offer` WRITE;
INSERT INTO `Offer` VALUES  (1,'Desarrollo proyecto','Según la conversación mantenida el día 15 de febrero entre los directivos, se acuerdo el desarrollo del proyecto x',1,4,2,'2009-04-07','2009-02-17','2009-03-16','HIGH','OPEN',NULL,1,1,'2009-04-07 11:27:29','2009-04-07 11:27:29','09012001','',1),
 (2,'Instalación TNT','Según reunión en sus oficinas le proponemos la instalación del programa de gestión ',1,12,4,'2009-04-13','2009-04-01','2009-05-01','HIGH','OPEN',NULL,1,1,'2009-04-13 13:50:22','2009-04-13 13:50:22','09041302','',1),
 (3,'Oferta interna','Se propone a los empleados el realizar una serie de cambios en la oficina fuera del horario laboral por el cual cobren el esfuerzo realizado según la actividad que desempeñen',1,16,5,'2009-07-08','2009-07-01','2010-07-31','HIGH','OPEN',NULL,1,1,'2009-07-08 09:12:08','2009-07-08 17:15:56','1123','',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Offer` ENABLE KEYS */;


--
-- Definition of table `OfferCost`
--

DROP TABLE IF EXISTS `OfferCost`;
CREATE TABLE  `OfferCost` (
  `id` int(11) NOT NULL auto_increment,
  `offerId` int(11) NOT NULL,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL default '1',
  `iva` decimal(4,2) NOT NULL default '16.00',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `units` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`id`),
  KEY `fk_offerCost_offerId` (`offerId`),
  CONSTRAINT `fk_offerCost_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `OfferCost`
--

/*!40000 ALTER TABLE `OfferCost` DISABLE KEYS */;
LOCK TABLES `OfferCost` WRITE;
INSERT INTO `OfferCost` VALUES  (1,3,'Metros de mampara con cristales, aluminio y madera que servirán para sustituir las anteriores.','50.00',1,'4.00',NULL,NULL,NULL,NULL,'30.00'),
 (2,3,'Botes de pintura de la mejor marca para poder pintar la oficina y conseguir un efecto cálido','10.00',1,'4.00',NULL,NULL,NULL,NULL,'5.00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `OfferCost` ENABLE KEYS */;


--
-- Definition of table `OfferRejectReason`
--

DROP TABLE IF EXISTS `OfferRejectReason`;
CREATE TABLE  `OfferRejectReason` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(1024) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Causas rechazo de ofertas';

--
-- Dumping data for table `OfferRejectReason`
--

/*!40000 ALTER TABLE `OfferRejectReason` DISABLE KEYS */;
LOCK TABLES `OfferRejectReason` WRITE;
INSERT INTO `OfferRejectReason` VALUES  (1,'Sin respuesta','El cliente no responde a la oferta',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (2,'Oferta cara','El cliente considera la oferta excesivamente cara',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (3,'Tecnología inadecuada','Tecnología escogida en la oferta inadecuada',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (4,'Proyecto retrasado','El proyecto para el cual se hizo la oferta ha sido retrasado',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02'),
 (5,'Proyecto cancelado','El proyecto para el cual se hizo la oferta ha sido cancelado',1,1,'2009-04-02 08:38:02','2009-04-02 08:38:02');
UNLOCK TABLES;
/*!40000 ALTER TABLE `OfferRejectReason` ENABLE KEYS */;


--
-- Definition of table `OfferRole`
--

DROP TABLE IF EXISTS `OfferRole`;
CREATE TABLE  `OfferRole` (
  `id` int(11) NOT NULL auto_increment,
  `offerId` int(11) NOT NULL,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `iva` decimal(4,2) NOT NULL default '16.00',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_offerRole_offerId` (`offerId`),
  CONSTRAINT `fk_offerRole_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `OfferRole`
--

/*!40000 ALTER TABLE `OfferRole` DISABLE KEYS */;
LOCK TABLES `OfferRole` WRITE;
INSERT INTO `OfferRole` VALUES  (1,3,'Pintor','20.00',15,'16.00',NULL,NULL,NULL,NULL),
 (2,3,'Transportista','15.00',50,'4.00',NULL,NULL,NULL,NULL),
 (3,3,'Mamparista','35.00',30,'0.00',NULL,NULL,NULL,NULL),
 (4,3,'Decorador','30.00',5,'7.00',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `OfferRole` ENABLE KEYS */;


--
-- Definition of table `Organization`
--

DROP TABLE IF EXISTS `Organization`;
CREATE TABLE  `Organization` (
  `id` int(11) NOT NULL auto_increment,
  `organizationTypeId` int(11) NOT NULL default '1' COMMENT 'organization type id',
  `organizationISOCategoryId` int(11) NOT NULL default '1' COMMENT 'iso category id',
  `name` varchar(256) collate utf8_spanish_ci default NULL,
  `cif` varchar(50) collate utf8_spanish_ci default NULL,
  `phone` varchar(15) collate utf8_spanish_ci default NULL,
  `street` varchar(256) collate utf8_spanish_ci default NULL,
  `number` varchar(16) collate utf8_spanish_ci default NULL COMMENT 'Building number in street',
  `locator` varchar(256) collate utf8_spanish_ci default NULL COMMENT 'Location information inside building',
  `postalCode` varchar(32) collate utf8_spanish_ci default NULL,
  `city` varchar(256) collate utf8_spanish_ci default NULL,
  `provinceId` int(11) NOT NULL,
  `state` varchar(256) collate utf8_spanish_ci default NULL,
  `country` varchar(256) collate utf8_spanish_ci default NULL,
  `fax` varchar(16) collate utf8_spanish_ci default NULL,
  `email` varchar(256) collate utf8_spanish_ci default NULL,
  `website` varchar(256) collate utf8_spanish_ci default NULL,
  `ftpsite` varchar(256) collate utf8_spanish_ci default NULL,
  `notes` varchar(1024) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_organization_organizationTypeId` (`organizationTypeId`),
  KEY `ndx_organization_isoOrganizationCategoryId` (`organizationISOCategoryId`),
  CONSTRAINT `fk_organization_isoOrganizationCategoryId` FOREIGN KEY (`organizationISOCategoryId`) REFERENCES `OrganizationISOCategory` (`id`),
  CONSTRAINT `fk_organization_organizationTypeId` FOREIGN KEY (`organizationTypeId`) REFERENCES `OrganizationType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los clientes';

--
-- Dumping data for table `Organization`
--

/*!40000 ALTER TABLE `Organization` DISABLE KEYS */;
LOCK TABLES `Organization` WRITE;
INSERT INTO `Organization` VALUES  (1,2,1,'Nuestra empresa','','','','','','','',28,'','','','','','','',NULL,NULL,NULL,'2009-06-26 09:52:19'),
 (2,2,3,'PC City SL','B0000000','902000000','Parque Corredor','','','28850','Torrejón de Ardoz',28,'Madrid','España ','','','','','',1,1,'2009-04-06 12:00:29','2009-06-26 09:52:36'),
 (3,2,3,'Suministros de Material de Oficina SA','A9999999','','Jazmines','3','Bajo Derecha','28001','Madrid',28,'Madrid','España ','','','','','',1,1,'2009-04-06 12:01:30','2009-06-26 09:52:52'),
 (4,1,3,'Tecnologías de España SL','B141414141','93','San Luis ','296','3º planta izquierda','08000','Barcelona',8,'Cataluña','España','93','','','','',1,1,'2009-04-06 12:02:47','2009-06-26 09:53:09'),
 (5,1,1,'A.T.R.I.L CB','E11111111','91','Avenida de Portugal','3','oficina 23F','28000','Pinto',28,'Madrid','España ','91','','','','',1,1,'2009-04-06 12:04:47','2009-06-26 09:50:12'),
 (6,1,1,'Grupo M5, SA','A999999999','91','Los Madroños','35','','28850','Torrejón de Ardoz',28,'Madrid','España ','91','','','','',1,1,'2009-04-07 09:50:39','2009-04-07 09:50:39'),
 (7,2,3,'Telefónica de España ','A82018474','900101010','Gran Via','28','','28013 ','Madrid',28,'Madrid ','España ','','','','','',1,1,'2009-04-07 09:52:24','2009-06-26 09:53:33');
INSERT INTO `Organization` VALUES  (8,2,1,'Iberdrola','A95075578','901202020','Gardoki','8','','','Bilbao',20,'Pais Vasco','España ','','','','','',1,1,'2009-04-07 09:53:51','2009-06-26 09:51:26'),
 (9,2,3,'Grupo Gestoría Avanza SL','B66666666','91','Otoño','3','Bajo Derecha','28830 ','San Fernando de Henares ',28,'Madrid','España ','91','','','','',1,1,'2009-04-07 09:55:28','2009-06-26 09:51:12'),
 (10,1,1,'Laboratorios Salgado SL','B88888888','91','Francicos de Guzman','34','Edificio Buena Vista','28000','Madrid',28,'Madrid','España ','91','','','','',1,1,'2009-04-07 10:04:28','2009-06-26 09:52:02'),
 (11,2,3,'Alquileres Grupo SL','B22222222','91','Las Encinas','3','Oficina C','28200','Madrid',28,'Madrid','España','91','','','','',1,1,'2009-04-07 14:44:58','2009-06-26 09:50:28'),
 (12,1,1,'Fundación Hispánica SL','B10000001','921','San Cristobal','4','','45000','Segovia',40,'Castilla y León ','España ','921','','','','',1,1,'2009-04-13 13:46:47','2009-06-26 09:50:57'),
 (13,2,3,'Calidad SA','A00000066','91','San Ignacio ','3','','28830','San Fernando de Henares ',28,'Madrid','España ','91','','','','',1,1,'2009-04-13 14:29:47','2009-06-26 09:50:46');
INSERT INTO `Organization` VALUES  (14,2,3,'Imagen WEB ','01010101A','91','Petunias','3','Planta baja','28000','Madrid',28,'Madrid','España','91','','','','',1,1,'2009-04-13 15:05:21','2009-06-26 09:51:43'),
 (15,1,1,'Indefinida',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'2009-06-09 15:46:59','2009-06-09 15:46:59'),
 (16,1,1,'Autentia Real Business Solutions S.L. (adictosaltrabajo.com)','12345678','','Avda. Castilla','1','Edificio Best Point, 2ª Planta, Local 21 B','28830','San Fernando de Henares',28,'Madrid','España','','','','','',1,1,'2009-07-07 10:13:55','2009-07-08 10:19:44');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Organization` ENABLE KEYS */;


--
-- Definition of table `OrganizationISOCategory`
--

DROP TABLE IF EXISTS `OrganizationISOCategory`;
CREATE TABLE  `OrganizationISOCategory` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL COMMENT 'ISO Organization Category descriptive name',
  `description` varchar(1024) collate utf8_spanish_ci default NULL COMMENT 'ISO Organization Category description',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones segun ISO';

--
-- Dumping data for table `OrganizationISOCategory`
--

/*!40000 ALTER TABLE `OrganizationISOCategory` DISABLE KEYS */;
LOCK TABLES `OrganizationISOCategory` WRITE;
INSERT INTO `OrganizationISOCategory` VALUES  (1,'A','Proveedor / Subcontratista habitual.',NULL,NULL,NULL,NULL),
 (2,'B','Proveedor / Subcontratista recomendado.',NULL,NULL,NULL,NULL),
 (3,'C','Proveedor / Subcontratista no habitual.',NULL,NULL,NULL,NULL),
 (4,'D','Proveedor / Subcontratista que haya tenido disconformidades.',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `OrganizationISOCategory` ENABLE KEYS */;


--
-- Definition of table `OrganizationType`
--

DROP TABLE IF EXISTS `OrganizationType`;
CREATE TABLE  `OrganizationType` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL COMMENT 'Organization type descriptive name',
  `description` varchar(1024) collate utf8_spanish_ci default NULL COMMENT 'Organization type description',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones';

--
-- Dumping data for table `OrganizationType`
--

/*!40000 ALTER TABLE `OrganizationType` DISABLE KEYS */;
LOCK TABLES `OrganizationType` WRITE;
INSERT INTO `OrganizationType` VALUES  (1,'Cliente','',NULL,NULL,NULL,NULL),
 (2,'Proveedor','',NULL,NULL,NULL,NULL),
 (3,'Cliente y proveedor','',NULL,NULL,NULL,NULL),
 (4,'Prospecto','Posible cliente',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `OrganizationType` ENABLE KEYS */;


--
-- Definition of table `Organization_Tag`
--

DROP TABLE IF EXISTS `Organization_Tag`;
CREATE TABLE  `Organization_Tag` (
  `id` int(11) NOT NULL auto_increment,
  `tagId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_organization_tag_tag` (`tagId`),
  KEY `fk_organization_tag_organization` (`organizationId`),
  CONSTRAINT `fk_organization_tag_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_organization_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Organization_Tag`
--

/*!40000 ALTER TABLE `Organization_Tag` DISABLE KEYS */;
LOCK TABLES `Organization_Tag` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Organization_Tag` ENABLE KEYS */;


--
-- Definition of table `PeriodicalAccountEntry`
--

DROP TABLE IF EXISTS `PeriodicalAccountEntry`;
CREATE TABLE  `PeriodicalAccountEntry` (
  `id` int(11) NOT NULL auto_increment,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'entry type',
  `frequencyId` int(11) NOT NULL COMMENT 'entry frequency',
  `concept` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `entryDate` date NOT NULL COMMENT 'entry date',
  `amount` decimal(10,2) NOT NULL COMMENT 'entry amount',
  `rise` decimal(4,2) default NULL COMMENT 'account entry rise',
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_periodicalAccountEntry_accountId` (`accountId`),
  KEY `ndx_periodicalAccountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  KEY `ndx_periodicalAccountEntry_frequencyId` (`frequencyId`),
  CONSTRAINT `fk_periodicalAccountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_frequencyId` FOREIGN KEY (`frequencyId`) REFERENCES `Frequency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='asientos contables periodicos';

--
-- Dumping data for table `PeriodicalAccountEntry`
--

/*!40000 ALTER TABLE `PeriodicalAccountEntry` DISABLE KEYS */;
LOCK TABLES `PeriodicalAccountEntry` WRITE;
INSERT INTO `PeriodicalAccountEntry` VALUES  (1,2,3,1,'Pago factura teléfono mes de enero ','2009-02-01','-99.06',NULL,'',1,1,'2009-04-07 10:10:43','2009-04-07 10:10:43'),
 (2,2,14,1,'Pago recibo luz','2009-04-20','-41.76',NULL,'',1,1,'2009-04-07 10:26:00','2009-04-07 10:26:00'),
 (3,2,8,1,'Pago factura alquiler mes de marzo ','2009-03-31','-812.00',NULL,'',1,1,'2009-04-07 14:50:40','2009-04-07 14:50:40');
UNLOCK TABLES;
/*!40000 ALTER TABLE `PeriodicalAccountEntry` ENABLE KEYS */;


--
-- Definition of table `Position`
--

DROP TABLE IF EXISTS `Position`;
CREATE TABLE  `Position` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(256) collate utf8_spanish_ci NOT NULL,
  `description` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `deleteDate` datetime default NULL,
  `email` varchar(128) collate utf8_spanish_ci default NULL,
  `phone` varchar(15) collate utf8_spanish_ci default NULL,
  `fax` varchar(15) collate utf8_spanish_ci default NULL,
  `address` varchar(100) collate utf8_spanish_ci default NULL,
  `postalCode` varchar(5) collate utf8_spanish_ci default NULL,
  `city` varchar(100) collate utf8_spanish_ci default NULL,
  `country` varchar(100) collate utf8_spanish_ci default NULL,
  `provinceId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_position_provinceId` (`provinceId`),
  CONSTRAINT `fk_position_province` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Position`
--

/*!40000 ALTER TABLE `Position` DISABLE KEYS */;
LOCK TABLES `Position` WRITE;
INSERT INTO `Position` VALUES  (1,'Indefinido','Puesto sin definir',1,1,'2009-06-09 15:46:59','2009-06-09 15:46:59',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Responsable de dirección ','migrado de una versión anterior',1,1,'2009-06-09 15:47:00','2009-06-09 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'Dirección de proyectos','migrado de una versión anterior',1,1,'2009-06-09 15:47:00','2009-06-09 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'Responsable de facturación ','migrado de una versión anterior',1,1,'2009-06-09 15:47:00','2009-06-09 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'Administración y dirección ','migrado de una versión anterior',1,1,'2009-06-09 15:47:00','2009-06-09 15:47:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (6,'Becario','Estudiante en prácticas',1,1,'2009-06-26 09:55:31','2009-06-26 09:55:57',NULL,'','','','','','','',NULL),
 (7,'Investigador de nuevas tecnologías','Conocedor de las tecnologías de vanguardia',1,1,'2009-06-26 09:56:19','2009-06-26 09:56:53',NULL,'','','','','','','',NULL),
 (8,'Desarrollador','Programador de aplicaciones',1,1,'2009-06-26 09:57:12','2009-06-26 09:57:12',NULL,'','','','','','','',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Position` ENABLE KEYS */;


--
-- Definition of table `PositionChange`
--

DROP TABLE IF EXISTS `PositionChange`;
CREATE TABLE  `PositionChange` (
  `id` int(11) NOT NULL auto_increment,
  `field` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `positionId` int(10) default NULL,
  `userId` int(10) default NULL,
  `insertDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_positionChange_position` (`positionId`),
  KEY `fk_positionChange_user` (`userId`),
  CONSTRAINT `fk_positionChange_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`),
  CONSTRAINT `fk_positionChange_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `PositionChange`
--

/*!40000 ALTER TABLE `PositionChange` DISABLE KEYS */;
LOCK TABLES `PositionChange` WRITE;
INSERT INTO `PositionChange` VALUES  (1,'position.name','Dirección de proyectos','Director de proyectos',3,1,'2009-06-18 10:45:45'),
 (2,'position.description','','Estudiante en prácticas',6,1,'2009-06-26 09:55:57'),
 (3,'position.name','Investigador','Investigador de nuevas tecnologías',7,1,'2009-06-26 09:56:53'),
 (4,'position.description','','Conocedor de las tecnologías de vanguardia',7,1,'2009-06-26 09:56:53');
UNLOCK TABLES;
/*!40000 ALTER TABLE `PositionChange` ENABLE KEYS */;


--
-- Definition of table `Position_Department`
--

DROP TABLE IF EXISTS `Position_Department`;
CREATE TABLE  `Position_Department` (
  `id` int(11) NOT NULL auto_increment,
  `positionId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_position_department_position` (`positionId`),
  KEY `fk_position_department_department` (`departmentId`),
  CONSTRAINT `fk_position_department_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_position_department_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Position_Department`
--

/*!40000 ALTER TABLE `Position_Department` DISABLE KEYS */;
LOCK TABLES `Position_Department` WRITE;
INSERT INTO `Position_Department` VALUES  (1,8,3),
 (2,6,3),
 (3,3,3),
 (4,7,3),
 (5,6,1),
 (6,3,1),
 (7,5,1),
 (8,8,2),
 (9,6,2),
 (10,7,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Position_Department` ENABLE KEYS */;


--
-- Definition of table `Position_Tag`
--

DROP TABLE IF EXISTS `Position_Tag`;
CREATE TABLE  `Position_Tag` (
  `id` int(11) NOT NULL auto_increment,
  `tagId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_position_tag_tag` (`tagId`),
  KEY `fk_position_tag_position` (`positionId`),
  CONSTRAINT `fk_position_tag_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`),
  CONSTRAINT `fk_position_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Position_Tag`
--

/*!40000 ALTER TABLE `Position_Tag` DISABLE KEYS */;
LOCK TABLES `Position_Tag` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Position_Tag` ENABLE KEYS */;


--
-- Definition of table `Project`
--

DROP TABLE IF EXISTS `Project`;
CREATE TABLE  `Project` (
  `id` int(11) NOT NULL auto_increment,
  `organizationId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date default NULL,
  `open` tinyint(1) default '0',
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `billable` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `ndx_project_organizationId` (`organizationId`),
  CONSTRAINT `fk_project_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Projects';

--
-- Dumping data for table `Project`
--

/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
LOCK TABLES `Project` WRITE;
INSERT INTO `Project` VALUES  (1,1,'2009-04-02',NULL,1,'Vacaciones',NULL,NULL,NULL,NULL,NULL,1),
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
 (13,1,'2009-01-01',NULL,1,'Labores administrativas','',1,1,'2009-04-07 11:14:02','2009-04-07 11:16:49',0);
INSERT INTO `Project` VALUES  (14,1,'2009-01-01',NULL,1,'Labores comerciales/ofertas','',1,1,'2009-04-07 11:28:20','2009-04-07 11:28:20',0),
 (15,12,'2009-03-01',NULL,1,'TNT','Instalación de TNT',1,1,'2009-04-13 13:47:38','2009-04-13 13:47:38',1),
 (16,1,'2009-04-01',NULL,1,'calidad','',1,1,'2009-04-13 14:41:42','2009-04-13 14:41:42',0),
 (17,14,'2009-04-01',NULL,1,'Creación página web','',1,1,'2009-04-13 15:07:20','2009-04-13 15:07:20',1),
 (18,16,'2009-07-07','2010-07-31',1,'Nuevas funcionalidades para TNTConcept','Desarrollo de nuevos módulos para TNTConcept',1,1,'2009-07-07 10:15:37','2009-07-07 10:15:58',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;


--
-- Definition of table `ProjectCost`
--

DROP TABLE IF EXISTS `ProjectCost`;
CREATE TABLE  `ProjectCost` (
  `id` int(11) NOT NULL auto_increment,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL default '1',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_projectCost_projectId` (`projectId`),
  CONSTRAINT `fk_projectCost_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ProjectCost`
--

/*!40000 ALTER TABLE `ProjectCost` DISABLE KEYS */;
LOCK TABLES `ProjectCost` WRITE;
INSERT INTO `ProjectCost` VALUES  (1,6,'Fotocopias','200.00',1,NULL,NULL,NULL,NULL),
 (2,7,'Material','300.00',1,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ProjectCost` ENABLE KEYS */;


--
-- Definition of table `ProjectRole`
--

DROP TABLE IF EXISTS `ProjectRole`;
CREATE TABLE  `ProjectRole` (
  `id` int(11) NOT NULL auto_increment,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_projectRole_projectId` (`projectId`),
  CONSTRAINT `fk_projectRole_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ProjectRole`
--

/*!40000 ALTER TABLE `ProjectRole` DISABLE KEYS */;
LOCK TABLES `ProjectRole` WRITE;
INSERT INTO `ProjectRole` VALUES  (1,1,'Vacaciones','0.00',0,NULL,NULL,NULL,NULL),
 (2,2,'Permiso extraordinario','0.00',0,NULL,NULL,NULL,NULL),
 (3,3,'Baja por enfermedad','0.00',0,NULL,NULL,NULL,NULL),
 (4,4,'Auto-formación','0.00',0,NULL,NULL,NULL,NULL),
 (5,5,'Histórico','0.00',0,NULL,NULL,NULL,NULL),
 (6,6,'Tecnico','50.00',15,NULL,NULL,NULL,NULL),
 (7,7,'Formador','60.00',45,NULL,NULL,NULL,NULL),
 (8,8,'Labores admtivas','0.00',0,NULL,NULL,NULL,NULL),
 (9,13,'Lab. admitivas','0.00',0,NULL,NULL,NULL,NULL),
 (10,18,'Analista','60.00',40,NULL,NULL,NULL,NULL),
 (11,18,'Desarrollador','40.00',200,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ProjectRole` ENABLE KEYS */;


--
-- Definition of table `Province`
--

DROP TABLE IF EXISTS `Province`;
CREATE TABLE  `Province` (
  `id` int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos códigos fijos',
  `name` varchar(64) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con las Provinces';

--
-- Dumping data for table `Province`
--

/*!40000 ALTER TABLE `Province` DISABLE KEYS */;
LOCK TABLES `Province` WRITE;
INSERT INTO `Province` VALUES  (1,'Alava',NULL,NULL,NULL,NULL),
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
 (27,'Lugo',NULL,NULL,NULL,NULL);
INSERT INTO `Province` VALUES  (28,'Madrid',NULL,NULL,NULL,NULL),
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
UNLOCK TABLES;
/*!40000 ALTER TABLE `Province` ENABLE KEYS */;


--
-- Definition of table `Publication`
--

DROP TABLE IF EXISTS `Publication`;
CREATE TABLE  `Publication` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `magazineId` int(11) NOT NULL,
  `magazineDeliveryDate` datetime default NULL COMMENT 'Fecha de entrega a la Magazine',
  `magazinePublicationDate` date default NULL,
  `ownPublicationDate` date default NULL COMMENT 'Fecha de publicacin en Adictos',
  `accepted` tinyint(1) default NULL COMMENT 'Indicador de si el Tutorial ha sido aceptado (1) o no (0)',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_publication_tutorialId` (`id`),
  KEY `fk_publication_magazineId` (`magazineId`),
  CONSTRAINT `fk_publication_magazineId` FOREIGN KEY (`magazineId`) REFERENCES `Magazine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='publicaciones de tutoriales';

--
-- Dumping data for table `Publication`
--

/*!40000 ALTER TABLE `Publication` DISABLE KEYS */;
LOCK TABLES `Publication` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Publication` ENABLE KEYS */;


--
-- Definition of table `RequestHoliday`
--

DROP TABLE IF EXISTS `RequestHoliday`;
CREATE TABLE  `RequestHoliday` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `beginDate` datetime NOT NULL,
  `finalDate` datetime NOT NULL,
  `state` varchar(16) collate utf8_spanish_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `observations` varchar(1024) collate utf8_spanish_ci default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `userComment` varchar(1024) collate utf8_spanish_ci default NULL,
  `chargeYear` date NOT NULL default '2007-01-01',
  PRIMARY KEY  (`id`),
  KEY `fk_requestHoliday_userId` (`userId`),
  CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `RequestHoliday`
--

/*!40000 ALTER TABLE `RequestHoliday` DISABLE KEYS */;
LOCK TABLES `RequestHoliday` WRITE;
INSERT INTO `RequestHoliday` VALUES  (1,'2009-04-13 00:00:00','2009-04-17 00:00:00','PENDING',1,NULL,1,'2009-04-06 12:09:36','2009-04-06 12:09:36','Vacaciones Semana Santa','2009-01-01'),
 (2,'2009-08-10 00:00:00','2009-08-31 00:00:00','PENDING',1,NULL,1,'2009-04-06 13:00:30','2009-04-06 13:00:45','Vacaciones de verano ','2009-01-01');
UNLOCK TABLES;
/*!40000 ALTER TABLE `RequestHoliday` ENABLE KEYS */;


--
-- Definition of table `Role`
--

DROP TABLE IF EXISTS `Role`;
CREATE TABLE  `Role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Roles de la aplicacin';

--
-- Dumping data for table `Role`
--

/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
LOCK TABLES `Role` WRITE;
INSERT INTO `Role` VALUES  (1,'Administrador',NULL,NULL,NULL,NULL),
 (2,'Supervisor',NULL,NULL,NULL,NULL),
 (3,'Usuario',NULL,NULL,NULL,NULL),
 (4,'Staff',NULL,NULL,NULL,NULL),
 (5,'Cliente',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;


--
-- Definition of table `Setting`
--

DROP TABLE IF EXISTS `Setting`;
CREATE TABLE  `Setting` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(64) collate utf8_spanish_ci NOT NULL,
  `name` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `value` varchar(4096) collate utf8_spanish_ci default NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='User settings';

--
-- Dumping data for table `Setting`
--

/*!40000 ALTER TABLE `Setting` DISABLE KEYS */;
LOCK TABLES `Setting` WRITE;
INSERT INTO `Setting` VALUES  (1,'BOOLEAN','bitacore.last.billable','false',1,1,'2009-04-07 11:17:33','2009-04-13 13:43:06'),
 (2,'INT','bitacore.last.roleId','2',1,1,'2009-04-07 11:17:33','2009-04-13 13:43:06'),
 (3,'INT','objective.last.projectId','13',1,1,'2009-04-07 11:19:37','2009-04-07 11:19:47');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Setting` ENABLE KEYS */;


--
-- Definition of table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
CREATE TABLE  `Tag` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `description` varchar(1024) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `Tag`
--

/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
LOCK TABLES `Tag` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;


--
-- Definition of table `Tutorial`
--

DROP TABLE IF EXISTS `Tutorial`;
CREATE TABLE  `Tutorial` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `maxDeliveryDate` datetime NOT NULL,
  `endDate` datetime default NULL COMMENT 'Fecha de finalizacin del Tutorial',
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_tutorial_userId` (`userId`),
  CONSTRAINT `fk_tutorial_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tutoriales que son enviado a la';

--
-- Dumping data for table `Tutorial`
--

/*!40000 ALTER TABLE `Tutorial` DISABLE KEYS */;
LOCK TABLES `Tutorial` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Tutorial` ENABLE KEYS */;


--
-- Definition of table `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE  `User` (
  `id` int(11) NOT NULL auto_increment,
  `login` varchar(50) collate utf8_spanish_ci NOT NULL,
  `password` varchar(50) collate utf8_spanish_ci NOT NULL,
  `roleId` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL default '1' COMMENT 'User activo',
  `name` varchar(200) collate utf8_spanish_ci NOT NULL,
  `nif` varchar(16) collate utf8_spanish_ci default NULL,
  `birthDate` date default NULL,
  `academicQualification` varchar(200) collate utf8_spanish_ci default NULL,
  `phone` varchar(12) collate utf8_spanish_ci default NULL,
  `mobile` varchar(12) collate utf8_spanish_ci default NULL,
  `street` varchar(100) collate utf8_spanish_ci default NULL,
  `city` varchar(100) collate utf8_spanish_ci default NULL,
  `postalCode` varchar(5) collate utf8_spanish_ci default NULL,
  `provinceId` int(11) default NULL,
  `married` tinyint(1) NOT NULL COMMENT 'Casado (1) o no (0)',
  `childrenNumber` tinyint(4) NOT NULL,
  `drivenLicenseType` varchar(10) collate utf8_spanish_ci default NULL,
  `vehicleType` varchar(50) collate utf8_spanish_ci default NULL,
  `licensePlate` varchar(45) collate utf8_spanish_ci default NULL,
  `startDate` date NOT NULL COMMENT 'fecha de alta en la empresa',
  `categoryId` int(11) NOT NULL,
  `socialSecurityNumber` varchar(45) collate utf8_spanish_ci default NULL,
  `bank` varchar(100) collate utf8_spanish_ci default NULL,
  `account` varchar(34) collate utf8_spanish_ci default NULL,
  `travelAvailability` varchar(128) collate utf8_spanish_ci default NULL COMMENT 'Disponibilidad para viajar',
  `workingInClient` tinyint(1) NOT NULL COMMENT 'Si esta tabajando en el cliente',
  `email` varchar(128) collate utf8_spanish_ci default NULL,
  `genre` varchar(16) collate utf8_spanish_ci default NULL,
  `salary` decimal(10,2) default NULL,
  `salaryExtras` decimal(10,2) default NULL,
  `documentCategoryId` int(10) unsigned default NULL,
  `securityCard` varchar(64) collate utf8_spanish_ci default NULL,
  `healthInsurance` varchar(64) collate utf8_spanish_ci default NULL,
  `notes` varchar(1024) collate utf8_spanish_ci default NULL,
  `photo` varchar(255) collate utf8_spanish_ci default NULL,
  `endTestPeriodDate` date default NULL,
  `endContractDate` date default NULL,
  `departmentId` int(10) unsigned NOT NULL default '1',
  `contractTypeId` int(10) unsigned default NULL,
  `contractObservations` varchar(2048) collate utf8_spanish_ci default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `dayDuration` int(11) default NULL,
  `agreementId` int(11) NOT NULL default '1',
  `agreementYearDuration` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `ndx_user_roleId` (`roleId`),
  KEY `ndx_user_provinceId` (`provinceId`),
  KEY `ndx_user_categoryId` (`categoryId`),
  KEY `ndx_user_documentCategoryId` (`documentCategoryId`),
  KEY `ndx_user_departmentId` (`departmentId`),
  KEY `ndx_user_contractTypeId` (`contractTypeId`),
  KEY `ndx_user_agreementId` (`agreementId`),
  CONSTRAINT `fk_user_agreementId` FOREIGN KEY (`agreementId`) REFERENCES `WorkingAgreement` (`id`),
  CONSTRAINT `fk_user_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `UserCategory` (`id`),
  CONSTRAINT `fk_user_contractTypeId` FOREIGN KEY (`contractTypeId`) REFERENCES `ContractType` (`id`),
  CONSTRAINT `fk_user_departmentId` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_user_documentCategoryId` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `fk_user_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`),
  CONSTRAINT `fk_user_roleId` FOREIGN KEY (`roleId`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Users de la aplicacin';

--
-- Dumping data for table `User`
--

/*!40000 ALTER TABLE `User` DISABLE KEYS */;
LOCK TABLES `User` WRITE;
INSERT INTO `User` VALUES  (1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f',1,1,'Administrador',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,'2009-04-02',1,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,480,1,NULL),
 (2,'Jesus','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',3,1,'Jesus Sánchez Gómez','','1983-11-11','Titulado universitario','918888888','600123456','Las Rosas 256','Madrid','28003',28,0,0,'B1','Ford Mondeo','0000AAAA','2009-01-25',3,'281234567890','Caja Madrid','2038000000000000000001','Ocasionalmente',0,'jesus.sanchez@admin.es','MAN','25000.00',NULL,45,'','','',NULL,'2009-03-24',NULL,3,3,'','2009-04-06 11:46:26','2009-04-06 11:46:26',480,1,NULL),
 (3,'Natalia','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',4,1,'Natalia Rincón San García','','1978-06-05','FP II','917001200','690000100','Oviedo 239','Alcalá de Henares','28803',28,1,1,'B1','Mercedes 220','1111BBB','2008-02-10',3,'289876543210','Santander Central Hispano','00490000001000000000','No',0,'natalia.rincon@admin.es','WOMAN','20000.00',NULL,46,'','','',NULL,NULL,NULL,1,3,'','2009-04-06 11:49:33','2009-04-06 11:49:41',480,1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;


--
-- Definition of table `UserCategory`
--

DROP TABLE IF EXISTS `UserCategory`;
CREATE TABLE  `UserCategory` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) collate utf8_spanish_ci NOT NULL,
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan las categorias';

--
-- Dumping data for table `UserCategory`
--

/*!40000 ALTER TABLE `UserCategory` DISABLE KEYS */;
LOCK TABLES `UserCategory` WRITE;
INSERT INTO `UserCategory` VALUES  (1,'Socio',NULL,NULL,NULL,NULL),
 (2,'Becario',NULL,NULL,NULL,NULL),
 (3,'Administrativo',NULL,NULL,NULL,NULL),
 (4,'Gerente',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `UserCategory` ENABLE KEYS */;


--
-- Definition of table `Version`
--

DROP TABLE IF EXISTS `Version`;
CREATE TABLE  `Version` (
  `version` varchar(32) collate utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='version de la base de datos';

--
-- Dumping data for table `Version`
--

/*!40000 ALTER TABLE `Version` DISABLE KEYS */;
LOCK TABLES `Version` WRITE;
INSERT INTO `Version` VALUES  ('0.18');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Version` ENABLE KEYS */;


--
-- Definition of table `WorkingAgreement`
--

DROP TABLE IF EXISTS `WorkingAgreement`;
CREATE TABLE  `WorkingAgreement` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) collate utf8_spanish_ci NOT NULL,
  `description` varchar(2048) collate utf8_spanish_ci default NULL,
  `holidays` int(11) NOT NULL default '22',
  `ownerId` int(11) default NULL,
  `departmentId` int(10) unsigned default NULL,
  `insertDate` datetime default NULL,
  `updateDate` datetime default NULL,
  `yearDuration` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Convenios laborales';

--
-- Dumping data for table `WorkingAgreement`
--

/*!40000 ALTER TABLE `WorkingAgreement` DISABLE KEYS */;
LOCK TABLES `WorkingAgreement` WRITE;
INSERT INTO `WorkingAgreement` VALUES  (1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22,NULL,NULL,NULL,NULL,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `WorkingAgreement` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
