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

DELIMITER $$
DROP PROCEDURE IF EXISTS migrate_interaction$$
CREATE PROCEDURE migrate_interaction()
BEGIN
DECLARE endOfTable INT DEFAULT 0;
DECLARE var_id int;
DECLARE var_why varchar(500);
DECLARE var_description varchar(2048);
DECLARE var_observations varchar(2048);
DECLARE var_total varchar(5000);

DECLARE message varchar(2048);
DECLARE cur1 CURSOR FOR SELECT id, why, description, observations FROM Interaction;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET endOfTable = 1;

OPEN cur1;

	interactionTable: loop
		FETCH cur1
		INTO var_id, var_why, var_description, var_observations;
		IF endOfTable THEN
			leave interactionTable;
		END IF;
    IF var_why is not NULL AND trim(var_why) <> "" THEN
        SELECT CONCAT('---- Motivo ----', char(13), var_why) INTO var_total;
    END IF;
    IF var_description is not NULL AND trim(var_description) <> "" THEN
        SELECT CONCAT(var_total, char(13), '---- Descripción ----', char(13), var_description) INTO var_total;
    END IF;
    IF var_observations is not NULL AND trim(var_observations) <> "" THEN
        SELECT CONCAT(var_total, char(13), '---- Observaciones ----', char(13), var_observations) INTO var_total;
    END IF;
    IF var_total is not NULL AND trim(var_total) <> "" THEN
			UPDATE Interaction set description = SUBSTRING(var_total, 1, 2048) where id = var_id;
		END IF;
	end loop;


CLOSE cur1;
END$$
call migrate_interaction()$$
drop procedure if exists migrate_interaction$$
DELIMITER ;

-- Eliminando el campo why
ALTER TABLE Interaction DROP COLUMN why;
-- Eliminando el campo observations
 ALTER TABLE Interaction DROP COLUMN observations;


-- Update version number
update Version set version='0.10';