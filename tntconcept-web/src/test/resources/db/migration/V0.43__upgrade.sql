
insert into User (name, login, password, roleId, categoryId, startDate, workingInClient, married, childrenNumber, email)
values ('User', 'user', 'dd94709528bb1c83d08f3088d4043f4742891f4f', 1, 1, now(), 0, 0, 0, 'user@user.com');

-- -----------------------------------------------------------------------------
-- ProjectRole
-- -----------------------------------------------------------------------------

ALTER TABLE ProjectRole ADD requireEvidence VARCHAR(10) NOT NULL DEFAULT 'NO';
UPDATE ProjectRole SET requireEvidence = 'WEEKLY' WHERE (id = 3);



-- -----------------------------------------------------------------------------
-- Version
-- -----------------------------------------------------------------------------
--
-- Update version number
update Version set version='0.43';
