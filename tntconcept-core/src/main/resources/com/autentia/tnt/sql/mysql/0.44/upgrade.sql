-- -----------------------------------------------------------------------------
-- WorkingAgreement
-- -----------------------------------------------------------------------------
--
-- Set max default hours to work to 1765 hours in minutes
UPDATE WorkingAgreement
SET yearDuration = 105900;

-- -----------------------------------------------------------------------------
-- AnnualWorkSummary
-- -----------------------------------------------------------------------------
--
-- Create table to save annual work summary from user
CREATE TABLE AnnualWorkSummary
(
    userId            INT(10) NOT NULL,
    year              INT (4) NOT NULL,
    earnedVacations   INT (2) NOT NULL,
    acceptedVacations INT (2) NOT NULL,
    targetHours       DECIMAL(7, 2) NOT NULL,
    workedHours       DECIMAL(7, 2) NOT NULL,
    insertDate        DATETIME DEFAULT NULL,
    updateDate        DATETIME DEFAULT NULL,
    PRIMARY KEY (userId, year),
    CONSTRAINT fk_annualworksummary_user FOREIGN KEY (userId) REFERENCES User (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- -----------------------------------------------------------------------------
-- AnnualWorkSummaryJob
-- -----------------------------------------------------------------------------
--
-- Create table to save execution audit of task Job
CREATE TABLE AnnualWorkSummaryJob
(
    `id`          INT      NOT NULL AUTO_INCREMENT,
    started DATETIME NOT NULL,
    finished DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
UPDATE Version
SET version='0.45';
