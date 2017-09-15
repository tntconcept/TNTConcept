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

-- Create table Setting
CREATE TABLE  Setting (
  id int NOT NULL auto_increment,
  type varchar(64)  NOT NULL,
  name varchar(1024)  NOT NULL,
  value varchar(4096)  default NULL,
  ownerId int null,
  departmentId int  null,
  insertDate datetime null,
  updateDate datetime null,
  PRIMARY KEY  (id)
) ;

-- Update version number
update Version set version='0.6';