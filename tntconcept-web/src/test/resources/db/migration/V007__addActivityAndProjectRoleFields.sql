/* Activity Table */
ALTER TABLE Activity
    RENAME COLUMN startDate TO start;

ALTER TABLE Activity
    ADD end datetime;

UPDATE Activity
    SET end = DATE_ADD(start, interval duration minute);

ALTER TABLE Activity
    MODIFY end datetime NOT NULL;

ALTER TABLE Activity
    ADD approvalState varchar(64) NOT NULL DEFAULT 'NA';

ALTER TABLE Activity
    MODIFY approvalState varchar(64) NOT NULL;

ALTER TABLE Activity
    RENAME COLUMN hasImage TO hasEvidences;

/* ProjectRole Table */

ALTER TABLE ProjectRole
    MODIFY requireEvidence varchar(10);

UPDATE ProjectRole SET requireEvidence = CASE
    WHEN requireEvidence = '0' THEN 'NO'
    WHEN requireEvidence = '1' THEN 'WEEKLY'
END;

ALTER TABLE ProjectRole
    MODIFY requireEvidence varchar(10) NOT NULL;

ALTER TABLE ProjectRole
    ADD timeUnit varchar(10) NOT NULL DEFAULT 'MINUTES';

ALTER TABLE ProjectRole
    MODIFY timeUnit varchar(10) NOT NULL;

ALTER TABLE ProjectRole
    ADD isWorkingTime boolean NOT NULL DEFAULT 1;

ALTER TABLE ProjectRole
    MODIFY isWorkingTime boolean NOT NULL;

ALTER TABLE ProjectRole
    ADD isApprovalRequired boolean NOT NULL DEFAULT 0;

ALTER TABLE ProjectRole
    MODIFY isApprovalRequired boolean NOT NULL;
