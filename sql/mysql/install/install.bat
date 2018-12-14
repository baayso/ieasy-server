@echo off

set server=127.0.0.1
set /P server="Server [%server%]: "

set port=3306
set /P port="Port [%port%]: "

set username=root
set /P username="Username [%username%]: "

cd ./
set CURRENT_PATH=%cd%

cd %CURRENT_PATH%

mysql --host=%server% --port=%port% --user=%username% --password < %CURRENT_PATH%\install.sql

pause
