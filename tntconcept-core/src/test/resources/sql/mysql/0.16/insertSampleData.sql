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


INSERT INTO `Account` (`id`,`name`,`number`,`accountTypeId`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Caja','0000000000000000000',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Ingreso','Ingresos en cuenta',NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Gasto','Gastos en cuenta',NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Traspaso','Traspasos',NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryGroup` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Arranque anual','Movimiento que representa al arranque anual',NULL,NULL,NULL,NULL);
INSERT INTO `AccountEntryType` (`id`,`accountEntryGroupId`,`name`,`observations`,`accountEntryTypeId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`customizableId`) VALUES 
  (1,4,'Arranque inicial','Tipo de asiento que representa el arranque inicial de un año',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Caja',NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Cuenta corriente',NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Cuenta de crédito',NULL,NULL,NULL,NULL);
INSERT INTO `AccountType` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Depósito',NULL,NULL,NULL,NULL);
INSERT INTO `BulletinBoardCategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Pública',NULL,NULL,NULL,NULL);
INSERT INTO `BulletinBoardCategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'General',NULL,NULL,NULL,NULL);
INSERT INTO `Collaborator` (`id`,`userId`,`contactId`,`organizationId`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,NULL,NULL,1,1,1,'2009-04-13 15:32:01','2009-04-13 15:32:01');
INSERT INTO `Commissioning` (`id`,`requestDate`,`name`,`scope`,`content`,`products`,`deliveryDate`,`budget`,`notes`,`authorSignature`,`reviserSignature`,`adminSignature`,`justifyInformation`,`developedActivities`,`difficultiesAppeared`,`results`,`conclusions`,`evaluation`,`status`,`projectId`,`insertDate`,`updateDate`,`deleteDate`) VALUES 
  (2,'2009-04-13 00:00:00','qweeeeeeee','','','','2009-04-14 00:00:00','12345.00','',0,0,1,0,NULL,NULL,NULL,NULL,NULL,'ACCEPTED',4,'2009-04-13 15:32:27','2009-04-13 15:33:15',NULL);
INSERT INTO `CommissioningDelay` (`id`,`reason`,`originalDate`,`delayedToDate`,`commissioningId`,`insertDate`,`updateDate`,`status`) VALUES 
  (1,'saf asdf asfd ','2009-04-14 00:00:00','2009-04-14 00:00:00',2,'2009-04-13 15:33:15','2009-04-13 15:33:15','ACCEPTED');
INSERT INTO `CommissioningPaymentData` (`id`,`commissioningId`,`collaboratorId`,`paymentMode`,`bankAccount`,`billNumber`,`insertDate`,`updateDate`) VALUES 
  (1,2,1,'UNKNOWN','','',NULL,NULL);
INSERT INTO `Commissioning_User` (`id`,`commissioningId`,`userId`) VALUES 
  (5,2,1);
INSERT INTO `ContractType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Prácticas','Departamento de dirección.',NULL,NULL,NULL,NULL);
INSERT INTO `ContractType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Duración determinada','Departamento de dirección.',NULL,NULL,NULL,NULL);
INSERT INTO `ContractType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Indefinido','Departamento de dirección.',NULL,NULL,NULL,NULL);
INSERT INTO `Department` (`id`,`parentId`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,NULL,'Dirección','Departamento de dirección.',NULL,NULL,NULL,NULL);
INSERT INTO `Department` (`id`,`parentId`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,1,'I+D+I','Departamento de I+D+I.',NULL,NULL,NULL,NULL);
INSERT INTO `Department` (`id`,`parentId`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,1,'Consultoría','Departamento de Consultoría.',NULL,NULL,NULL,NULL);
INSERT INTO `Department` (`id`,`parentId`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,NULL,'Indefinido','Departamento sin definir',1,1,'2009-04-06 17:20:58','2009-04-06 17:20:58');
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Documentos de Calidad','Documentos de calidad','CALIDAD',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Otros Documentos','Otros documentos no clasificados',' ',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Curriculum Vitae','','',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Documentos de Usuarios','','',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (10,'(I1-PC01) Lista de distribución de la documentación','','I1-PC01',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (11,'(I1-PC02) Acta de reunión de revisión del sistema','','I1-PC02',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (12,'(I1-PC08) Informe de auditoría interna','','I1-PC08',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (13,'(I2-PC02) Planificación de objetivos de calidad','','I2-PC02',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (14,'(I2-PC08) Informe de no conformidad/reclamación del cliente','','I2-PC08',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (15,'(I3-PC08) Informe de acción correctiva/preventiva','','I3-PC08',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (16,'(PC01) Sistema de Gestión de la Calidad','Documento inicial de descripción','PC01',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (17,'(PC02) Responsabilidad de la Dirección','','PC02',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (18,'(PC03) Gestión de los Recursos','','PC03',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (19,'(PC04) Procesos relacionados con los clientes','','PC04',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (20,'(PC05) Gestión de compras','','PC05',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (21,'(PC06) Evaluación de proveedores y subcontratistas','','PC06',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (22,'(PC07) Prestación del servicio','','PC07',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (23,'(PC08) Medición análisis y mejora','','PC08',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (24,'Control documentación entregada y recibida','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (25,'Criterio de evaluación y seguimiento de procesos','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (26,'Cuestionario de satisfacción del cliente','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (27,'E-mail aprobación documentación','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (28,'E-mail de comunicaciones','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (29,'Ficha de mantenimiento de equipos','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (30,'Índice de ediciones de documentos','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (31,'Índice de no conformidades','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (32,'Inventario de recursos','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (33,'Listado de documentación externa en vigor','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (34,'Listado de proveedores y subcontratistas evaluados','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (35,'Listado de registros','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (36,'Manual de Gestión de la Calidad (MGC)','','MGC',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (37,'Perfil del empleado','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (38,'Perfil puesto trabajo','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (39,'Plan de auditoría anual','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (40,'Plan de formación anual','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (41,'Política de Calidad','','',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (42,'(I5-PC03) Registro perfil del empleado','','I5-PC03',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (43,'(I8-PC03) Cuestionario de satisfacción laboral','','I8-PC03',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `DocumentCategory` (`id`,`name`,`description`,`code`,`categoryid`,`documentslastupdate`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (44,'(I6-PC08) Evaluación de satisfacción del cliente','','I6-PC08',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Mensual',1,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Trimestral',3,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Semestral',6,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Anual',12,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (5,'Bimensual',2,NULL,NULL,NULL,NULL);
INSERT INTO `Frequency` (`id`,`name`,`months`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (6,'Ocasional',0,NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'No conformidad','',NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Accion comercial','',NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Envío de oferta','',NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Envío de factura','',NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (5,'Accion administrativa','',NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (6,'No definida','',NULL,NULL,NULL,NULL);
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (7,'Primer contacto','',NULL,NULL,'2009-04-06 17:20:57','2009-04-06 17:20:57');
INSERT INTO `InteractionType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (8,'Confirmación de oferta','',NULL,NULL,'2009-04-06 17:20:57','2009-04-06 17:20:57');
INSERT INTO `Objective` (`id`,`userId`,`projectId`,`startDate`,`endDate`,`state`,`name`,`log`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,1,4,'2009-04-13','2009-04-14','PENDING','Entrega del pedido qweeeeeeee',NULL,1,'2009-04-13 15:32:39','2009-04-13 15:32:39');
INSERT INTO `Objective` (`id`,`userId`,`projectId`,`startDate`,`endDate`,`state`,`name`,`log`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,1,4,'2009-04-13','2009-04-14','PENDING','Entrega del pedido qweeeeeeee',NULL,1,'2009-04-13 15:33:15','2009-04-13 15:33:15');
INSERT INTO `OfferRejectReason` (`id`,`title`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Sin respuesta','El cliente no responde a la oferta',1,1,'2009-04-06 17:20:56','2009-04-06 17:20:56');
INSERT INTO `OfferRejectReason` (`id`,`title`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Oferta cara','El cliente considera la oferta excesivamente cara',1,1,'2009-04-06 17:20:56','2009-04-06 17:20:56');
INSERT INTO `OfferRejectReason` (`id`,`title`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Tecnología inadecuada','Tecnología escogida en la oferta inadecuada',1,1,'2009-04-06 17:20:56','2009-04-06 17:20:56');
INSERT INTO `OfferRejectReason` (`id`,`title`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Proyecto retrasado','El proyecto para el cual se hizo la oferta ha sido retrasado',1,1,'2009-04-06 17:20:56','2009-04-06 17:20:56');
INSERT INTO `OfferRejectReason` (`id`,`title`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (5,'Proyecto cancelado','El proyecto para el cual se hizo la oferta ha sido cancelado',1,1,'2009-04-06 17:20:56','2009-04-06 17:20:56');
INSERT INTO `Organization` (`id`,`organizationTypeId`,`organizationISOCategoryId`,`name`,`cif`,`phone`,`street`,`number`,`locator`,`postalCode`,`city`,`provinceId`,`state`,`country`,`fax`,`email`,`website`,`ftpsite`,`notes`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,2,1,'Nuestra empresa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `Organization` (`id`,`organizationTypeId`,`organizationISOCategoryId`,`name`,`cif`,`phone`,`street`,`number`,`locator`,`postalCode`,`city`,`provinceId`,`state`,`country`,`fax`,`email`,`website`,`ftpsite`,`notes`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,1,1,'Indefinida',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'2009-04-06 17:20:58','2009-04-06 17:20:58');
INSERT INTO `OrganizationISOCategory` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'A','Proveedor / Subcontratista habitual.',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationISOCategory` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'B','Proveedor / Subcontratista recomendado.',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationISOCategory` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'C','Proveedor / Subcontratista no habitual.',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationISOCategory` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'D','Proveedor / Subcontratista que haya tenido disconformidades.',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Cliente','',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Proveedor','',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Cliente y proveedor','',NULL,NULL,NULL,NULL);
INSERT INTO `OrganizationType` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Prospecto','Posible cliente',NULL,NULL,NULL,NULL);
INSERT INTO `Position` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`deleteDate`) VALUES 
  (1,'Indefinido','Puesto sin definir',1,1,'2009-04-06 17:20:58','2009-04-06 17:20:58',NULL);
INSERT INTO `Project` (`id`,`organizationId`,`startDate`,`endDate`,`open`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`billable`) VALUES 
  (1,1,'2009-04-06',NULL,1,'Vacaciones',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `Project` (`id`,`organizationId`,`startDate`,`endDate`,`open`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`billable`) VALUES 
  (2,1,'2009-04-06',NULL,1,'Permiso extraordinario',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `Project` (`id`,`organizationId`,`startDate`,`endDate`,`open`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`billable`) VALUES 
  (3,1,'2009-04-06',NULL,1,'Baja por enfermedad',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `Project` (`id`,`organizationId`,`startDate`,`endDate`,`open`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`billable`) VALUES 
  (4,1,'2009-04-06',NULL,1,'Auto-formación',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `Project` (`id`,`organizationId`,`startDate`,`endDate`,`open`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`billable`) VALUES 
  (5,1,'2009-04-06',NULL,1,'Histórico',NULL,NULL,NULL,NULL,NULL,1);
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`,`costPerHour`,`expectedHours`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,1,'Vacaciones','0.00',0,NULL,NULL,NULL,NULL);
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`,`costPerHour`,`expectedHours`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,2,'Permiso extraordinario','0.00',0,NULL,NULL,NULL,NULL);
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`,`costPerHour`,`expectedHours`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,3,'Baja por enfermedad','0.00',0,NULL,NULL,NULL,NULL);
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`,`costPerHour`,`expectedHours`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,4,'Auto-formación','0.00',0,NULL,NULL,NULL,NULL);
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`,`costPerHour`,`expectedHours`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (5,5,'Histórico','0.00',0,NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Alava',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Albacete',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Alicante',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Almería',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (5,'Avila',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (6,'Badajoz',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (7,'Balears, Illes',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (8,'Barcelona',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (9,'Burgos',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (10,'Cáceres',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (11,'Cádiz',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (12,'Castellón',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (13,'Ciudad Real',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (14,'Córdoba',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (15,'Coruña, A',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (16,'Cuenca',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (17,'Girona',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (18,'Granada',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (19,'Guadalajara',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (20,'Guipúzcoa',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (21,'Huelva',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (22,'Huesca',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (23,'Jaén',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (24,'León',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (25,'Lleida',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (26,'Rioja, La',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (27,'Lugo',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (28,'Madrid',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (29,'Málaga',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (30,'Murcia',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (31,'Navarra',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (32,'Ourense',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (33,'Asturias',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (34,'Palencia',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (35,'Palmas, Las',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (36,'Pontevedra',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (37,'Salamanca',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (38,'Santa Cruz de Tenerife',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (39,'Cantabria',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (40,'Segovia',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (41,'Sevilla',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (42,'Soria',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (43,'Tarragona',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (44,'Teruel',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (45,'Toledo',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (46,'Valencia',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (47,'Valladolid',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (48,'Vizcaya',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (49,'Zamora',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (50,'Zaragoza',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (51,'Ceuta',NULL,NULL,NULL,NULL);
INSERT INTO `Province` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (52,'Melilla',NULL,NULL,NULL,NULL);
INSERT INTO `Role` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Administrador',NULL,NULL,NULL,NULL);
INSERT INTO `Role` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Supervisor',NULL,NULL,NULL,NULL);
INSERT INTO `Role` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Usuario',NULL,NULL,NULL,NULL);
INSERT INTO `Role` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Staff',NULL,NULL,NULL,NULL);
INSERT INTO `Role` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (5,'Cliente',NULL,NULL,NULL,NULL);
INSERT INTO `Tag` (`id`,`name`,`description`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`deleteDate`) VALUES 
  (2,'fds','asdf',1,1,'2009-04-13 09:29:22','2009-04-13 09:31:58','2009-04-13 09:31:58');
INSERT INTO `User` (`id`,`login`,`password`,`roleId`,`active`,`name`,`nif`,`birthDate`,`academicQualification`,`phone`,`mobile`,`street`,`city`,`postalCode`,`provinceId`,`married`,`childrenNumber`,`drivenLicenseType`,`vehicleType`,`licensePlate`,`startDate`,`categoryId`,`socialSecurityNumber`,`bank`,`account`,`travelAvailability`,`workingInClient`,`email`,`genre`,`salary`,`salaryExtras`,`documentCategoryId`,`securityCard`,`healthInsurance`,`notes`,`photo`,`endTestPeriodDate`,`endContractDate`,`departmentId`,`contractTypeId`,`contractObservations`,`insertDate`,`updateDate`,`dayDuration`,`agreementId`,`agreementYearDuration`) VALUES 
  (1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f',1,1,'Administrador',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,'2009-04-06',1,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,480,1,NULL);
INSERT INTO `UserCategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (1,'Socio',NULL,NULL,NULL,NULL);
INSERT INTO `UserCategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (2,'Becario',NULL,NULL,NULL,NULL);
INSERT INTO `UserCategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (3,'Administrativo',NULL,NULL,NULL,NULL);
INSERT INTO `UserCategory` (`id`,`name`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES 
  (4,'Gerente',NULL,NULL,NULL,NULL);
INSERT INTO `Version` (`version`) VALUES 
  ('0.17');
INSERT INTO `WorkingAgreement` (`id`,`name`,`description`,`holidays`,`ownerId`,`departmentId`,`insertDate`,`updateDate`,`yearDuration`) VALUES 
  (1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22,NULL,NULL,NULL,NULL,0);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
