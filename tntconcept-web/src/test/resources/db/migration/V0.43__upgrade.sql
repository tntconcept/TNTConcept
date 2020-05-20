
-- -----------------------------------------------------------------------------
-- ProjectRole
-- -----------------------------------------------------------------------------

ALTER TABLE ProjectRole ADD requireEvidence BOOLEAN DEFAULT FALSE;
UPDATE ProjectRole SET requireEvidence = true WHERE (id = 3);


-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
update Version set version='0.43';
