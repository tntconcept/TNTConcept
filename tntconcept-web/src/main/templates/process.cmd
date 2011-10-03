@REM
@REM TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
@REM Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
@REM
@REM This program is free software: you can redistribute it and/or modify
@REM it under the terms of the GNU General Public License as published by
@REM the Free Software Foundation, either version 3 of the License.
@REM
@REM This program is distributed in the hope that it will be useful,
@REM but WITHOUT ANY WARRANTY; without even the implied warranty of
@REM MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
@REM GNU General Public License for more details.
@REM
@REM You should have received a copy of the GNU General Public License
@REM along with this program.  If not, see <http://www.gnu.org/licenses/>.
@REM

rem Initialize
setlocal
cls
set CLASSPATH=%CLASSPATH%;..\..\..\target\classes
move ..\..\..\target\classes\commons-logging.properties ..\..\..\target\classes\commons-logging.properties.bak
move ..\..\..\target\classes\log4j.properties ..\..\..\target\classes\log4j.properties.bak

rem Check arguments
if "%1"=="" goto :USAGE

rem Call stajanov
if %1==all goto :PROCESS_ALL
call stajanov stajanov.%1.properties %2 %3 %4 %5 %6 %7 %8 %9
goto :END

rem Call stajanov with all files
:PROCESS_ALL
for %%1 in (stajanov.*.properties) do call stajanov %%1 %2 %3 %4 %5 %6 %7 %8 %9
goto :END

rem Show usage
:USAGE
echo process ^<entity name^>^|all
goto :END

:END
rem Finalize
move ..\..\..\target\classes\commons-logging.properties.bak ..\..\..\target\classes\commons-logging.properties
move ..\..\..\target\classes\log4j.properties.bak ..\..\..\target\classes\log4j.properties
endlocal