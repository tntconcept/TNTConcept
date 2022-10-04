-- -----------------------------------------------------------------------------
-- WorkingAgreementTerms
-- -----------------------------------------------------------------------------
--
-- Create table to save working agreement terms
create table if not exists WorkingAgreementTerms
(
    id                 int      not null AUTO_INCREMENT,
    effectiveFrom      date     not null,
    vacation           int      not null,
    annualWorkingTime  int      not null,
    workingAgreementId int      not null,
    PRIMARY KEY (id),
CONSTRAINT uk_effective_from_agreement_id unique (effectiveFrom, workingAgreementId),
CONSTRAINT fk_WorkingAgreementTerms_WorkingAgreement FOREIGN KEY (workingAgreementId) references WorkingAgreement (id)
ON UPDATE CASCADE ON DELETE RESTRICT
);

-- -----------------------------------------------------------------------------
-- WorkingAgreementTerms
-- -----------------------------------------------------------------------------
--
-- insert current working agreement terms

insert into WorkingAgreementTerms (effectiveFrom, vacation, annualWorkingTime, workingAgreementId)
values ('1970-01-01 00:00:00', 22, 105900, (select w.id from WorkingAgreement w limit 0, 1)),
('2022-07-22 00:00:00', 23, 105900, (select w.id from WorkingAgreement w limit 0, 1));

-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
UPDATE Version
SET version='0.47';
