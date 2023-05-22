/* Country Table */
UPDATE `Country`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Country`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Country`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Country`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Province Table */
UPDATE `Province`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Province`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Province`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Province`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* DocumentCategory Table */
UPDATE `DocumentCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `DocumentCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `DocumentCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `DocumentCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* DocumentVersion Table */
UPDATE `DocumentVersion`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `DocumentVersion`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `DocumentVersion`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `DocumentVersion`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* DocumentCategoryDoc Table */
UPDATE `DocumentCategoryDoc`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `DocumentCategoryDoc`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `DocumentCategoryDoc`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `DocumentCategoryDoc`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* OrganizationType Table */
UPDATE `OrganizationType`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `OrganizationType`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `OrganizationType`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `OrganizationType`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* OrganizationISOCategory Table */
UPDATE `OrganizationISOCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `OrganizationISOCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `OrganizationISOCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `OrganizationISOCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* OrganizationDocCategory Table */
UPDATE `OrganizationDocCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `OrganizationDocCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `OrganizationDocCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `OrganizationDocCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Organization Table */
UPDATE `Organization`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Organization`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Organization`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Organization`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Department Table */
UPDATE `Department`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Department`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Department`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Department`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Contact Table */
UPDATE `Contact`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Contact`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Contact`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Contact`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Position Table */
UPDATE `Position`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Position`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Position`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Position`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Position_Department Table (Not containing the two columns initially) */
ALTER TABLE `Position_Department`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Position_Department`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Department_Organization Table (Not containing the two columns initially) */
ALTER TABLE `Department_Organization`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Department_Organization`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* ContactInfo Table (Not containing the two columns initially) */
ALTER TABLE `ContactInfo`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `ContactInfo`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Project Table */
UPDATE `Project`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Project`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Project`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Project`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* ProjectRole Table */
UPDATE `ProjectRole`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `ProjectRole`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `ProjectRole`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `ProjectRole`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* ProjectCost Table */
UPDATE `ProjectCost`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `ProjectCost`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `ProjectCost`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `ProjectCost`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Role Table */
UPDATE `Role`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Role`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Role`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Role`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* ContractType Table */
UPDATE `ContractType`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `ContractType`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `ContractType`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `ContractType`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* WorkingAgreement Table */
UPDATE `WorkingAgreement`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `WorkingAgreement`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `WorkingAgreement`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `WorkingAgreement`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* WorkingAgreementTerms Table (Not containing the two columns initially) */
ALTER TABLE `WorkingAgreementTerms`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `WorkingAgreementTerms`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* UserCategory Table */
UPDATE `UserCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `UserCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `UserCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `UserCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* User Table */
UPDATE `User`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `User`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `User`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `User`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* AnnualWorkSummary Table */
UPDATE `AnnualWorkSummary`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `AnnualWorkSummary`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `AnnualWorkSummary`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `AnnualWorkSummary`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* AnnualWorkSummaryJob Table (Not containing the two columns initially) */
ALTER TABLE `AnnualWorkSummaryJob`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `AnnualWorkSummaryJob`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Activity Table */
UPDATE `Activity`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Activity`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Activity`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Activity`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* AccountType Table */
UPDATE `AccountType`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `AccountType`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `AccountType`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `AccountType`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Account Table */
UPDATE `Account`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Account`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Account`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Account`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* AccountEntryGroup Table */
UPDATE `AccountEntryGroup`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `AccountEntryGroup`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `AccountEntryGroup`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `AccountEntryGroup`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* AccountEntryType Table */
UPDATE `AccountEntryType`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `AccountEntryType`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `AccountEntryType`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `AccountEntryType`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* AccountEntry Table */
UPDATE `AccountEntry`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `AccountEntry`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `AccountEntry`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `AccountEntry`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* IVAType Table */
UPDATE `IVAType`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `IVAType`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `IVAType`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `IVAType`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* IVAReason Table */
UPDATE `IVAReason`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `IVAReason`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `IVAReason`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `IVAReason`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* BillCategory Table */
UPDATE `BillCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `BillCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `BillCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `BillCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* RectifiedBillCategory Table */
UPDATE `RectifiedBillCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `RectifiedBillCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `RectifiedBillCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `RectifiedBillCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* BillRegime Table */
UPDATE `BillRegime`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `BillRegime`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `BillRegime`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `BillRegime`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Bill Table */
UPDATE `Bill`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Bill`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Bill`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Bill`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Bill_AccountEntry Table (Not containing the two columns initially) */
ALTER TABLE `Bill_AccountEntry`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Bill_AccountEntry`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* BillBreakDown Table */
UPDATE `BillBreakDown`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `BillBreakDown`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `BillBreakDown`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `BillBreakDown`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* BillPayment Table */
UPDATE `BillPayment`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `BillPayment`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `BillPayment`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `BillPayment`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* BulletinBoardCategory Table */
UPDATE `BulletinBoardCategory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `BulletinBoardCategory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `BulletinBoardCategory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `BulletinBoardCategory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CompanyState Table */
UPDATE `CompanyState`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CompanyState`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CompanyState`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CompanyState`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Idea Table */
UPDATE `Idea`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Idea`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Idea`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Idea`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Inventory Table */
UPDATE `Inventory`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Inventory`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Inventory`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Inventory`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Objective Table */
UPDATE `Objective`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Objective`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Objective`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Objective`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Magazine Table */
UPDATE `Magazine`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Magazine`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Magazine`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Magazine`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Tutorial Table */
UPDATE `Tutorial`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Tutorial`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Tutorial`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Tutorial`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Publication Table */
UPDATE `Publication`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Publication`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Publication`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Publication`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* BulletinBoard Table */
UPDATE `BulletinBoard`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `BulletinBoard`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `BulletinBoard`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `BulletinBoard`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Book Table */
UPDATE `Book`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Book`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Book`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Book`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Frequency Table */
UPDATE `Frequency`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Frequency`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Frequency`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Frequency`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* PeriodicalAccountEntry Table */
UPDATE `PeriodicalAccountEntry`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `PeriodicalAccountEntry`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `PeriodicalAccountEntry`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `PeriodicalAccountEntry`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Holiday Table */
UPDATE `Holiday`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Holiday`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Holiday`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Holiday`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* RequestHoliday Table */
UPDATE `RequestHoliday`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `RequestHoliday`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `RequestHoliday`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `RequestHoliday`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* OfferRejectReason Table */
UPDATE `OfferRejectReason`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `OfferRejectReason`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `OfferRejectReason`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `OfferRejectReason`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Offer Table */
UPDATE `Offer`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Offer`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Offer`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Offer`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* OfferRole Table */
UPDATE `OfferRole`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `OfferRole`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `OfferRole`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `OfferRole`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* OfferCost Table */
UPDATE `OfferCost`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `OfferCost`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `OfferCost`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `OfferCost`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* FinancialRatio Table */
UPDATE `FinancialRatio`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `FinancialRatio`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `FinancialRatio`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `FinancialRatio`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* InteractionType Table */
UPDATE `InteractionType`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `InteractionType`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `InteractionType`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `InteractionType`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Interaction Table */
UPDATE `Interaction`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Interaction`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Interaction`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Interaction`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Setting Table */
UPDATE `Setting`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Setting`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Setting`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Setting`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Occupation Table */
UPDATE `Occupation`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Occupation`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Occupation`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Occupation`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CreditTitle Table */
UPDATE `CreditTitle`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CreditTitle`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CreditTitle`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CreditTitle`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CreditTitle_Bill Table (Not containing the two columns initially) */
ALTER TABLE `CreditTitle_Bill`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `CreditTitle_Bill`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Collaborator Table */
UPDATE `Collaborator`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Collaborator`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Collaborator`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Collaborator`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Commissioning Table */
UPDATE `Commissioning`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Commissioning`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Commissioning`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Commissioning`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CommissioningDelay Table */
UPDATE `CommissioningDelay`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CommissioningDelay`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CommissioningDelay`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CommissioningDelay`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CommissioningPaymentData Table */
UPDATE `CommissioningPaymentData`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CommissioningPaymentData`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CommissioningPaymentData`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CommissioningPaymentData`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Commissioning_User Table (Not containing the two columns initially) */
ALTER TABLE `Commissioning_User`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Commissioning_User`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* CommissioningChange Table */
UPDATE `CommissioningChange`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CommissioningChange`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CommissioningChange`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CommissioningChange`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CommissioningChange Table */
UPDATE `CommissioningChange`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CommissioningChange`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CommissioningChange`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CommissioningChange`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* CommissioningFile Table */
UPDATE `CommissioningFile`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `CommissioningFile`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `CommissioningFile`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `CommissioningFile`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* ExternalActivity Table */
UPDATE `ExternalActivity`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `ExternalActivity`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `ExternalActivity`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `ExternalActivity`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* ActivityFile Table */
UPDATE `ActivityFile`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `ActivityFile`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `ActivityFile`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `ActivityFile`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Tag Table */
UPDATE `Tag`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Tag`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `Tag`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `Tag`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* Contact_Tag Table (Not containing the two columns initially) */
ALTER TABLE `Contact_Tag`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Contact_Tag`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Position_Tag Table (Not containing the two columns initially) */
ALTER TABLE `Position_Tag`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Position_Tag`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Department_Tag Table (Not containing the two columns initially) */
ALTER TABLE `Department_Tag`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Department_Tag`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Organization_Tag Table (Not containing the two columns initially) */
ALTER TABLE `Organization_Tag`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Organization_Tag`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* PositionChange Table */
UPDATE `PositionChange`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `PositionChange`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

UPDATE `PositionChange`
SET `updateDate` = NOW()
WHERE `updateDate` IS NULL;

ALTER TABLE `PositionChange`
    MODIFY COLUMN `updateDate` datetime NOT NULL DEFAULT NOW();

/* ContactOwner Table (Not containing the two columns initially) */
ALTER TABLE `ContactOwner`
    ADD `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `ContactOwner`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* EntityChange Table (only containing insertDate)*/
UPDATE `EntityChange`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `EntityChange`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `EntityChange`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

/* Link Table (only containing insertDate)*/
UPDATE `Link`
SET `insertDate` = NOW()
WHERE `insertDate` IS NULL;

ALTER TABLE `Link`
    MODIFY COLUMN `insertDate` datetime NOT NULL DEFAULT NOW();

ALTER TABLE `Link`
    ADD `updateDate` datetime NOT NULL DEFAULT NOW();

CREATE TRIGGER updateAuditoryDateCountry BEFORE UPDATE ON Country
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDocument BEFORE UPDATE ON Document
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDocumentCategory BEFORE UPDATE ON DocumentCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDocumentVersion BEFORE UPDATE ON DocumentVersion
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDocumentCategoryDoc BEFORE UPDATE ON DocumentCategoryDoc
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOrganizationType BEFORE UPDATE ON OrganizationType
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOrganizationISOCategory BEFORE UPDATE ON OrganizationISOCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOrganizationDocCategory BEFORE UPDATE ON OrganizationDocCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOrganization BEFORE UPDATE ON Organization
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDepartment BEFORE UPDATE ON Department
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateContact BEFORE UPDATE ON Contact
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDatePosition BEFORE UPDATE ON Position
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDatePosition_Department BEFORE UPDATE ON Position_Department
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDepartment_Organization BEFORE UPDATE ON Department_Organization
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateContactInfo BEFORE UPDATE ON ContactInfo
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateProject BEFORE UPDATE ON Project
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateProjectRole BEFORE UPDATE ON ProjectRole
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateProjectCost BEFORE UPDATE ON ProjectCost
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateRole BEFORE UPDATE ON Role
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateContractType BEFORE UPDATE ON ContractType
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateWorkingAgreement BEFORE UPDATE ON WorkingAgreement
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateWorkingAgreementTerms BEFORE UPDATE ON WorkingAgreementTerms
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateUserCategory BEFORE UPDATE ON UserCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateUser BEFORE UPDATE ON User
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAnnualWorkSummary BEFORE UPDATE ON AnnualWorkSummary
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAnnualWorkSummaryJob BEFORE UPDATE ON AnnualWorkSummaryJob
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateActivity BEFORE UPDATE ON Activity
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAccountType BEFORE UPDATE ON AccountType
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAccount BEFORE UPDATE ON Account
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAccountEntryGroup BEFORE UPDATE ON AccountEntryGroup
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAccountEntryType BEFORE UPDATE ON AccountEntryType
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateAccountEntry BEFORE UPDATE ON AccountEntry
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateIVAType BEFORE UPDATE ON IVAType
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateIVAReason BEFORE UPDATE ON IVAReason
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBillCategory BEFORE UPDATE ON BillCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateRectifiedBillCategory BEFORE UPDATE ON RectifiedBillCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBillRegime BEFORE UPDATE ON BillRegime
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBill BEFORE UPDATE ON Bill
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBill_AccountEntry BEFORE UPDATE ON Bill_AccountEntry
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBillBreakDown BEFORE UPDATE ON BillBreakDown
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBillPayment BEFORE UPDATE ON BillPayment
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBulletinBoardCategory BEFORE UPDATE ON BulletinBoardCategory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCompanyState BEFORE UPDATE ON CompanyState
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateIdea BEFORE UPDATE ON Idea
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateInventory BEFORE UPDATE ON Inventory
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateObjective BEFORE UPDATE ON Objective
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateMagazine BEFORE UPDATE ON Magazine
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateTutorial BEFORE UPDATE ON Tutorial
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDatePublication BEFORE UPDATE ON Publication
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBulletinBoard BEFORE UPDATE ON BulletinBoard
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateBook BEFORE UPDATE ON Book
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateFrequency BEFORE UPDATE ON Frequency
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDatePeriodicalAccountEntry BEFORE UPDATE ON PeriodicalAccountEntry
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateHoliday BEFORE UPDATE ON Holiday
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateRequestHoliday BEFORE UPDATE ON RequestHoliday
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOfferRejectReason BEFORE UPDATE ON OfferRejectReason
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOffer BEFORE UPDATE ON Offer
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOfferRole BEFORE UPDATE ON OfferRole
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOfferCost BEFORE UPDATE ON OfferCost
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateFinancialRatio BEFORE UPDATE ON FinancialRatio
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateInteractionType BEFORE UPDATE ON InteractionType
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateInteraction BEFORE UPDATE ON Interaction
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateSetting BEFORE UPDATE ON Setting
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOccupation BEFORE UPDATE ON Occupation
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCreditTitle BEFORE UPDATE ON CreditTitle
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCreditTitle_Bill BEFORE UPDATE ON CreditTitle_Bill
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCollaborator BEFORE UPDATE ON Collaborator
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCommissioning BEFORE UPDATE ON Commissioning
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCommissioningDelay BEFORE UPDATE ON CommissioningDelay
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCommissioningPaymentData BEFORE UPDATE ON CommissioningPaymentData
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCommissioning_User BEFORE UPDATE ON Commissioning_User
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCommissioningChange BEFORE UPDATE ON CommissioningChange
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateCommissioningFile BEFORE UPDATE ON CommissioningFile
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateExternalActivity BEFORE UPDATE ON ExternalActivity
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateActivityFile BEFORE UPDATE ON ActivityFile
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateTag BEFORE UPDATE ON Tag
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateContact_Tag BEFORE UPDATE ON Contact_Tag
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDatePosition_Tag BEFORE UPDATE ON Position_Tag
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateDepartment_Tag BEFORE UPDATE ON Department_Tag
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateOrganization_Tag BEFORE UPDATE ON Organization_Tag
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDatePositionChange BEFORE UPDATE ON PositionChange
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateContactOwner BEFORE UPDATE ON ContactOwner
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateEntityChange BEFORE UPDATE ON EntityChange
    FOR EACH ROW SET NEW.updateDate = NOW();



CREATE TRIGGER updateAuditoryDateLink BEFORE UPDATE ON Link
    FOR EACH ROW SET NEW.updateDate = NOW();



