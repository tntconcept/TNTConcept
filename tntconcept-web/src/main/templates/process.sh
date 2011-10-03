#!/bin/sh
#
# TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
# Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

trap "finalize; exit" 2

# Finalize method
finalize()
{
  mv ../../../target/classes/commons-logging.properties.bak ../../../target/classes/commons-logging.properties
  mv ../../../target/classes/log4j.properties.bak ../../../target/classes/log4j.properties
}

# Initialize
export CLASSPATH=$CLASSPATH:../../../target/classes
mv ../../../target/classes/commons-logging.properties ../../../target/classes/commons-logging.properties.bak
mv ../../../target/classes/log4j.properties ../../../target/classes/log4j.properties.bak

# Check arguments
if [ "$1" == "" ]
then
  echo "usage: process <entity name>|all"
  finalize
  exit 1
fi

# Call stajanov
if [ "$1" == "all" ]
then
  for i in stajanov.*.properties
  do
    echo ======================================================================
    echo $i
    echo ======================================================================
    stajanov $i $2 $3 $4 $5 $6 $7 $8 $9
  done
else
  stajanov stajanov.$1.properties $2 $3 $4 $5 $6 $7 $8 $9
fi

finalize
