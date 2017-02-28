@echo off

set server=127.0.0.1
set /P server="Server [%server%]: "

set port=3306
set /P port="Port [%port%]: "

set database=springbootdemo
set /P database="Database [%database%]: "

set username=root
set /P username="Username [%username%]: "

cd ./
set CURR_PATH=%cd%

mysql --host=%server% --port=%port% --database=%database% --user=%username% --password < install.sql

pause
