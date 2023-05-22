UPDATE `Document`
    SET `name` = ""
    WHERE `name` IS NULL;

UPDATE `Document`
    SET `description` = ""
    WHERE `description` IS NULL;

UPDATE `Document`
    SET `creationDate` = `insertDate`
    WHERE `creationDate` IS NULL;

ALTER TABLE `Document`
    MODIFY `name`           VARCHAR(256)    NOT NULL,
    MODIFY `description`    VARCHAR(4096)   NOT NULL,
    MODIFY `creationDate`   DATETIME        NOT NULL;

UPDATE `OrganizationType`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `OrganizationType`
    MODIFY `description`    VARCHAR(1024)   NOT NULL;

UPDATE `OrganizationISOCategory`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `OrganizationISOCategory`
    MODIFY `description`    VARCHAR(1024)   NOT NULL;

UPDATE `WorkingAgreement`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `WorkingAgreement`
    MODIFY `description`    VARCHAR(2048)   NOT NULL;

UPDATE `Account`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `Account`
    MODIFY `description`    VARCHAR(2048)   NOT NULL;

UPDATE `AccountEntryGroup`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `AccountEntryGroup`
    MODIFY `description`    VARCHAR(2048)   NOT NULL;

UPDATE `AccountEntryType`
    SET `observations` = ""
    WHERE `observations` IS NULL;

ALTER TABLE `AccountEntryType`
    MODIFY `observations`   VARCHAR(1024)   NOT NULL;

UPDATE `AccountEntry`
    SET `observations` = ""
    WHERE `observations` IS NULL;

ALTER TABLE `AccountEntry`
    MODIFY `observations`   VARCHAR(1024)   NOT NULL;

UPDATE `IVAType`
    SET `iva` = 0
    WHERE `iva` IS NULL;

UPDATE `IVAType`
    SET `name` = ""
    WHERE `name` IS NULL;

ALTER TABLE `IVAType`
    MODIFY `iva`            DECIMAL(4,2)    NOT NULL,
    MODIFY `name`           VARCHAR(30)     NOT NULL;

UPDATE `Bill`
    SET `paymentMode` = "UNKNOWN"
    WHERE `paymentMode` IS NULL;

UPDATE `Bill`
    SET `observations` = ""
    WHERE `observations` IS NULL;

UPDATE `Bill`
    SET `orderNumber` = ""
    WHERE `orderNumber` IS NULL;

ALTER TABLE `Bill`
    MODIFY `paymentMode`    VARCHAR(16)     NOT NULL,
    MODIFY `observations`   VARCHAR(4096)   NOT NULL,
    MODIFY `orderNumber`    VARCHAR(64)     NOT NULL;

UPDATE `Objective`
    SET `state` = "PENDING"
    WHERE `state` IS NULL;

ALTER TABLE `Objective`
    MODIFY `state`          VARCHAR(16)     NOT NULL;

UPDATE `Magazine`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `Magazine`
    MODIFY `description`    VARCHAR(2048)   NOT NULL;

UPDATE `Tutorial`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `Tutorial`
    MODIFY `description`    VARCHAR(2048)   NOT NULL;

UPDATE `Publication`
    SET `accepted` = 0
    WHERE `accepted` IS NULL;

ALTER TABLE `Publication`
    MODIFY `accepted`       BOOLEAN         NOT NULL;

UPDATE `BulletinBoard`
    SET `documentPath` = ""
    WHERE `documentPath` IS NULL;

UPDATE `BulletinBoard`
    SET `documentContentType` = ""
    WHERE `documentContentType` IS NULL;

ALTER TABLE `BulletinBoard`
    MODIFY `documentPath`           VARCHAR(128)    NOT NULL,
    MODIFY `documentContentType`    VARCHAR(128)    NOT NULL;

UPDATE `Book`
    SET `author` = ""
    WHERE `author` IS NULL;

UPDATE `Book`
    SET `ISBN` = ""
    WHERE `ISBN` IS NULL;

UPDATE `Book`
    SET `URL` = ""
    WHERE `URL` IS NULL;

UPDATE `Book`
    SET `price` = 0
    WHERE `price` IS NULL;

ALTER TABLE `Book`
    MODIFY `author`         VARCHAR(255) COLLATE utf8mb4_spanish_ci NOT NULL,
    MODIFY `ISBN`           VARCHAR(13)  COLLATE utf8mb4_spanish_ci NOT NULL,
    MODIFY `URL`            VARCHAR(255) COLLATE utf8mb4_spanish_ci NOT NULL,
    MODIFY `price`          DECIMAL(10,2)                           NOT NULL;

UPDATE `InteractionType`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `InteractionType`
    MODIFY `description`    VARCHAR(1024)   NOT NULL;

UPDATE `Interaction`
    SET `file` = ""
    WHERE `file` IS NULL;

UPDATE `Interaction`
    SET `fileMime` = ""
    WHERE `fileMime` IS NULL;

ALTER TABLE `Interaction`
    MODIFY `file`           VARCHAR(400)    NOT NULL,
    MODIFY `fileMime`       VARCHAR(128)    NOT NULL;

UPDATE `Setting`
    SET `value` = ""
    WHERE `value` IS NULL;

ALTER TABLE `Setting`
    MODIFY `value`          VARCHAR(4096) COLLATE utf8mb4_spanish_ci NOT NULL;

UPDATE `Occupation`
    SET `description` = ""
    WHERE `description` IS NULL;

ALTER TABLE `Occupation`
    MODIFY `description`    VARCHAR(1024)                           NOT NULL;

-- Country table

ALTER TABLE `Country`
    MODIFY `code`           smallint                                NOT NULL,
    MODIFY `iso3166a1`      char(2)      COLLATE utf8mb4_spanish_ci NOT NULL,
    MODIFY `iso3166a2`      char(3)      COLLATE utf8mb4_spanish_ci NOT NULL,
    MODIFY `name`           VARCHAR(128) COLLATE utf8mb4_spanish_ci NOT NULL;

-- Organization table

UPDATE `Organization`
SET `documentNumber` = ''
WHERE `documentNumber` IS NULL;

UPDATE `Organization`
SET `phone` = ''
WHERE `phone` IS NULL;

UPDATE `Organization`
SET `street` = ''
WHERE `street` IS NULL;

UPDATE `Organization`
SET `number` = ''
WHERE `number` IS NULL;

UPDATE `Organization`
SET `locator` = ''
WHERE `locator` IS NULL;

UPDATE `Organization`
SET `postalCode` = ''
WHERE `postalCode` IS NULL;

UPDATE `Organization`
SET `city` = ''
WHERE `city` IS NULL;

UPDATE `Organization`
SET `state` = ''
WHERE `state` IS NULL;

UPDATE `Organization`
SET `fax` = ''
WHERE `fax` IS NULL;

UPDATE `Organization`
SET `email` = ''
WHERE `email` IS NULL;

UPDATE `Organization`
SET `website` = ''
WHERE `website` IS NULL;

UPDATE `Organization`
SET `ftpsite` = ''
WHERE `ftpsite` IS NULL;

UPDATE `Organization`
SET `notes` = ''
WHERE `notes` IS NULL;

ALTER TABLE `Organization`
    MODIFY `documentNumber`     VARCHAR(50)         NOT NULL,
    MODIFY `phone`              VARCHAR(15)         NOT NULL,
    MODIFY `street`             VARCHAR(256)        NOT NULL,
    MODIFY `number`             VARCHAR(16)         NOT NULL    COMMENT 'Building number in street',
    MODIFY `locator`            VARCHAR(256)        NOT NULL    COMMENT 'Location information inside building',
    MODIFY `postalCode`         VARCHAR(32)         NOT NULL,
    MODIFY `city`               VARCHAR(256)        NOT NULL,
    MODIFY `state`              VARCHAR(256)        NOT NULL,
    MODIFY `fax`                VARCHAR(16)         NOT NULL,
    MODIFY `email`              VARCHAR(256)        NOT NULL,
    MODIFY `website`            VARCHAR(256)        NOT NULL,
    MODIFY `ftpsite`            VARCHAR(256)        NOT NULL,
    MODIFY `notes`              VARCHAR(1024)       NOT NULL;

-- Contact table

UPDATE `Contact`
SET `email` = ''
WHERE `email` IS NULL;

UPDATE `Contact`
SET `phone` = ''
WHERE `phone` IS NULL;

UPDATE `Contact`
SET `mobile` = ''
WHERE `mobile` IS NULL;

UPDATE `Contact`
SET `email2` = ''
WHERE `email2` IS NULL;

UPDATE `Contact`
SET `phone2` = ''
WHERE `phone2` IS NULL;

UPDATE `Contact`
SET `fax` = ''
WHERE `fax` IS NULL;

UPDATE `Contact`
SET `address` = ''
WHERE `address` IS NULL;

UPDATE `Contact`
SET `postalCode` = ''
WHERE `postalCode` IS NULL;

UPDATE `Contact`
SET `city` = ''
WHERE `city` IS NULL;

UPDATE `Contact`
SET `country` = ''
WHERE `country` IS NULL;

ALTER TABLE `Contact`
    MODIFY `email`          VARCHAR(128)    NOT NULL,
    MODIFY `phone`          VARCHAR(15)     NOT NULL,
    MODIFY `mobile`         VARCHAR(15)     NOT NULL,
    MODIFY `email2`         VARCHAR(128)    NOT NULL,
    MODIFY `phone2`         VARCHAR(15)     NOT NULL,
    MODIFY `fax`            VARCHAR(15)     NOT NULL,
    MODIFY `address`        VARCHAR(100)    NOT NULL,
    MODIFY `postalCode`     VARCHAR(5)      NOT NULL,
    MODIFY `city`           VARCHAR(100)    NOT NULL,
    MODIFY `country`        VARCHAR(100)    NOT NULL;

-- Position table

UPDATE `Position`
SET `email` = ''
WHERE `email` IS NULL;

UPDATE `Position`
SET `phone` = ''
WHERE `phone` IS NULL;

UPDATE `Position`
SET `fax` = ''
WHERE `fax` IS NULL;

UPDATE `Position`
SET `address` = ''
WHERE `address` IS NULL;

UPDATE `Position`
SET `postalCode` = ''
WHERE `postalCode` IS NULL;

UPDATE `Position`
SET `city` = ''
WHERE `city` IS NULL;

UPDATE `Position`
SET `country` = ''
WHERE `country` IS NULL;

ALTER TABLE `Position`
    MODIFY `email`          VARCHAR(128)    NOT NULL,
    MODIFY `phone`          VARCHAR(15)     NOT NULL,
    MODIFY `fax`            VARCHAR(15)     NOT NULL,
    MODIFY `address`        VARCHAR(100)    NOT NULL,
    MODIFY `postalCode`     VARCHAR(5)      NOT NULL,
    MODIFY `city`           VARCHAR(100)    NOT NULL,
    MODIFY `country`        VARCHAR(100)    NOT NULL;

-- User table

UPDATE `User`
SET `nif` = ''
WHERE `nif` IS NULL;

UPDATE `User`
SET `academicQualification` = ''
WHERE `academicQualification` IS NULL;

UPDATE `User`
SET `phone` = ''
WHERE `phone` IS NULL;

UPDATE `User`
SET `mobile` = ''
WHERE `mobile` IS NULL;

UPDATE `User`
SET `street` = ''
WHERE `street` IS NULL;

UPDATE `User`
SET `city` = ''
WHERE `city` IS NULL;

UPDATE `User`
SET `postalCode` = ''
WHERE `postalCode` IS NULL;

UPDATE `User`
SET `drivenLicenseType` = ''
WHERE `drivenLicenseType` IS NULL;

UPDATE `User`
SET `vehicleType` = ''
WHERE `vehicleType` IS NULL;

UPDATE `User`
SET `licensePlate` = ''
WHERE `licensePlate` IS NULL;

UPDATE `User`
SET `socialSecurityNumber` = ''
WHERE `socialSecurityNumber` IS NULL;

UPDATE `User`
SET `bank` = ''
WHERE `bank` IS NULL;

UPDATE `User`
SET `account` = ''
WHERE `account` IS NULL;

UPDATE `User`
SET `travelAvailability` = ''
WHERE `travelAvailability` IS NULL;

UPDATE `User`
SET `email` = ''
WHERE `email` IS NULL;

UPDATE `User`
SET `genre` = 'MAN'
WHERE `genre` IS NULL;

UPDATE `User`
SET `salary` = 0
WHERE `salary` IS NULL;

UPDATE `User`
SET `salaryExtras` = 0
WHERE `salaryExtras` IS NULL;

UPDATE `User`
SET `securityCard` = ''
WHERE `securityCard` IS NULL;

UPDATE `User`
SET `healthInsurance` = ''
WHERE `healthInsurance` IS NULL;

UPDATE `User`
SET `notes` = ''
WHERE `notes` IS NULL;

UPDATE `User`
SET `photo` = ''
WHERE `photo` IS NULL;

UPDATE `User`
SET `contractObservations` = ''
WHERE `contractObservations` IS NULL;

ALTER TABLE `User`
    MODIFY  `nif`                       VARCHAR(16)         NOT NULL,
    MODIFY  `academicQualification`     VARCHAR(200)        NOT NULL,
    MODIFY  `phone`                     VARCHAR(12)         NOT NULL,
    MODIFY  `mobile`                    VARCHAR(12)         NOT NULL,
    MODIFY  `street`                    VARCHAR(100)        NOT NULL,
    MODIFY  `city`                      VARCHAR(100)        NOT NULL,
    MODIFY  `postalCode`                VARCHAR(5)          NOT NULL,
    MODIFY  `drivenLicenseType`         VARCHAR(10)         NOT NULL,
    MODIFY  `vehicleType`               VARCHAR(50)         NOT NULL,
    MODIFY  `licensePlate`              VARCHAR(45)         NOT NULL,
    MODIFY  `socialSecurityNumber`      VARCHAR(200)        NOT NULL,
    MODIFY  `bank`                      VARCHAR(100)        NOT NULL,
    MODIFY  `account`                   VARCHAR(34)         NOT NULL,
    MODIFY  `travelAvailability`        VARCHAR(128)        NOT NULL,
    MODIFY  `email`                     VARCHAR(128)        NOT NULL,
    MODIFY  `genre`                     VARCHAR(16)         NOT NULL,
    MODIFY  `salary`                    DECIMAL(10, 2)      NOT NULL,
    MODIFY  `salaryExtras`              DECIMAL(10, 2)      NOT NULL,
    MODIFY  `securityCard`              VARCHAR(64)         NOT NULL,
    MODIFY  `healthInsurance`           VARCHAR(64)         NOT NULL,
    MODIFY  `notes`                     VARCHAR(1024)       NOT NULL,
    MODIFY  `photo`                     VARCHAR(255)        NOT NULL,
    MODIFY  `contractObservations`      VARCHAR(2048)       NOT NULL;

-- OfferRejectReason table

UPDATE `OfferRejectReason`
SET `description` = ''
WHERE `description` IS NULL;

ALTER TABLE `OfferRejectReason`
    MODIFY `description` VARCHAR(1024) NOT NULL;

-- Offer table

UPDATE `Offer`
SET `description` = ''
WHERE `description` IS NULL;

UPDATE `Offer`
SET `observations` = ''
WHERE `observations` IS NULL;

ALTER TABLE `Offer`
    MODIFY `description`        VARCHAR(4096)       NOT NULL,
    MODIFY `observations`       VARCHAR(4096)       NOT NULL;

-- DocumentCategory table
UPDATE `DocumentCategory`
SET `description` = ''
WHERE `description` IS NULL;

-- `code` breaks logic of creating user
-- `documentslastupdate` is equivalent to updateDate

ALTER TABLE `DocumentCategory`
    MODIFY COLUMN `description` VARCHAR(4096)       NOT NULL;

-- Project table
UPDATE `Project`
SET `description` = ''
WHERE `description` IS NULL;

UPDATE `Project`
SET `open` = FALSE
WHERE `open` IS NULL;

ALTER TABLE `Project`
    MODIFY COLUMN `description`         VARCHAR(4096)   NOT NULL,
    MODIFY COLUMN `open`                boolean         NOT NULL;

-- Activity table
UPDATE `Activity`
SET `description` = ''
WHERE `description` IS NULL;

ALTER TABLE `Activity`
    MODIFY COLUMN `description`         VARCHAR(2048)   NOT NULL;

-- Idea table
UPDATE `Idea`
SET `cost` = ''
WHERE `cost` IS NULL;

UPDATE `Idea`
SET `benefits` = ''
WHERE `benefits` IS NULL;

ALTER TABLE `Idea`
    MODIFY COLUMN `cost`        VARCHAR(500)    NOT NULL,
    MODIFY COLUMN `benefits`    VARCHAR(2048)   NOT NULL;

-- Inventory table
UPDATE `Inventory`
SET `cost` = 0
WHERE `cost` IS NULL;

UPDATE `Inventory`
SET `provider` = ''
WHERE `provider` IS NULL;

UPDATE `Inventory`
SET `trademark` = ''
WHERE `trademark` IS NULL;

UPDATE `Inventory`
SET `model` = ''
WHERE `model` IS NULL;

UPDATE `Inventory`
SET `speed` = ''
WHERE `speed` IS NULL;

UPDATE `Inventory`
SET `storage` = ''
WHERE `storage` IS NULL;

UPDATE `Inventory`
SET `ram` = ''
WHERE `ram` IS NULL;

UPDATE `Inventory`
SET `location` = ''
WHERE `location` IS NULL;

UPDATE `Inventory`
SET `description` = ''
WHERE `description` IS NULL;

ALTER TABLE `Inventory`
    MODIFY COLUMN `cost`            DECIMAL(10, 2)      NOT NULL,
    MODIFY COLUMN `provider`        VARCHAR(128)        NOT NULL,
    MODIFY COLUMN `trademark`       VARCHAR(128)        NOT NULL,
    MODIFY COLUMN `model`           VARCHAR(128)        NOT NULL,
    MODIFY COLUMN `speed`           VARCHAR(10)         NOT NULL,
    MODIFY COLUMN `storage`         VARCHAR(10)         NOT NULL,
    MODIFY COLUMN `ram`             VARCHAR(10)         NOT NULL,
    MODIFY COLUMN `location`        VARCHAR(128)        NOT NULL,
    MODIFY COLUMN `description`     VARCHAR(256)        NOT NULL;

-- Frequency table
UPDATE `Frequency`
SET `months` = 0
WHERE `months` IS NULL;

ALTER TABLE `Frequency`
    MODIFY COLUMN `months`          INTEGER             NOT NULL;

-- PeriodicalAccountEntry table
UPDATE `PeriodicalAccountEntry`
SET `rise` = 0
WHERE `rise` IS NULL;

UPDATE `PeriodicalAccountEntry`
SET `observations` = ''
WHERE `observations` IS NULL;

ALTER TABLE `PeriodicalAccountEntry`
    MODIFY COLUMN `rise`            DECIMAL(4, 2)       NOT NULL,
    MODIFY COLUMN `observations`    VARCHAR(1024)       NOT NULL;

-- RequestHoliday table
UPDATE `RequestHoliday`
SET `observations` = ''
WHERE `observations` IS NULL;

UPDATE `RequestHoliday`
SET `userComment` = ''
WHERE `userComment` IS NULL;

ALTER TABLE `RequestHoliday`
    MODIFY COLUMN `observations`    VARCHAR(1024)       NOT NULL,
    MODIFY COLUMN `userComment`     VARCHAR(1024)       NOT NULL;

-- CreditTitle table
UPDATE `CreditTitle`
SET `concept` = ''
WHERE `concept` IS NULL;

UPDATE `CreditTitle`
SET `state` = ''
WHERE `state` IS NULL;

UPDATE `CreditTitle`
SET `type` = ''
WHERE `type` IS NULL;

UPDATE `CreditTitle`
SET `observations` = ''
WHERE `observations` IS NULL;

ALTER TABLE `CreditTitle`
    MODIFY COLUMN `concept`         VARCHAR(1024)       NOT NULL,
    MODIFY COLUMN `state`           VARCHAR(16)         NOT NULL,
    MODIFY COLUMN `type`            VARCHAR(16)         NOT NULL,
    MODIFY COLUMN `observations`    VARCHAR(1024)       NOT NULL;

-- Commissioning table
UPDATE `Commissioning`
SET `scope` = ''
WHERE `scope` IS NULL;

UPDATE `Commissioning`
SET `content` = ''
WHERE `content` IS NULL;

UPDATE `Commissioning`
SET `products` = ''
WHERE `products` IS NULL;

UPDATE `Commissioning`
SET `budget` = 0
WHERE `budget` IS NULL;

UPDATE `Commissioning`
SET `notes` = ''
WHERE `notes` IS NULL;

UPDATE `Commissioning`
SET `developedActivities` = ''
WHERE `developedActivities` IS NULL;

UPDATE `Commissioning`
SET `difficultiesAppeared` = ''
WHERE `difficultiesAppeared` IS NULL;

UPDATE `Commissioning`
SET `results` = ''
WHERE `results` IS NULL;

UPDATE `Commissioning`
SET `conclusions` = ''
WHERE `conclusions` IS NULL;

UPDATE `Commissioning`
SET `evaluation` = ''
WHERE `evaluation` IS NULL;

UPDATE `Commissioning`
SET `status` = ''
WHERE `status` IS NULL;

ALTER TABLE `Commissioning`
    MODIFY COLUMN `scope`                   VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `content`                 VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `products`                VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `budget`                  DECIMAL(10, 2)          NOT NULL,
    MODIFY COLUMN `notes`                   VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `developedActivities`     VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `difficultiesAppeared`    VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `results`                 VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `conclusions`             VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `evaluation`              VARCHAR(1024)           NOT NULL,
    MODIFY COLUMN `status`                  VARCHAR(20)             NOT NULL;

-- CommissioningDelay table
UPDATE `CommissioningDelay`
SET `status` = ''
WHERE `status` IS NULL;

ALTER TABLE `CommissioningDelay`
    MODIFY COLUMN `status`          VARCHAR(20)             NOT NULL;

-- CommissioningPaymentData table
UPDATE `CommissioningPaymentData`
SET `paymentMode` = ''
WHERE `paymentMode` IS NULL;

UPDATE `CommissioningPaymentData`
SET `bankAccount` = ''
WHERE `bankAccount` IS NULL;

UPDATE `CommissioningPaymentData`
SET `billNumber` = ''
WHERE `billNumber` IS NULL;

ALTER TABLE `CommissioningPaymentData`
    MODIFY COLUMN `paymentMode`     VARCHAR(32)         NOT NULL,
    MODIFY COLUMN `bankAccount`     VARCHAR(50)         NOT NULL,
    MODIFY COLUMN `billNumber`      VARCHAR(50)         NOT NULL;

-- No null values in live database
ALTER TABLE `Link`
    MODIFY COLUMN `user`            VARCHAR(128)        NOT NULL,
    MODIFY COLUMN `link`            VARCHAR(128)        NOT NULL;


-- CommissioningFile table

UPDATE `CommissioningFile`
SET `fileMime` = ''
WHERE `fileMime` IS NULL;

ALTER TABLE `CommissioningFile`
    MODIFY COLUMN `fileMime`        VARCHAR(128)        NOT NULL;

