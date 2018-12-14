@echo off

echo [Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME.

set CURRENT_PATH=%cd%

set PORT=%1%
set PROFILE=%2%

if "%1%" == "" (set PORT=6666)

if "%2%" == "" (set PROFILE=pro)

set JAVA_OPTS=-server -Xms2048m -Xmx2048m -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true
echo %JAVA_OPTS%

set APP_OPTS=--server.port=%PORT% --spring.profiles.active=%PROFILE%
echo %APP_OPTS%

cd %CURRENT_PATH%

echo java %JAVA_OPTS% -jar %CURRENT_PATH%/../target/spring-boot-demo.jar %APP_OPTS%

java %JAVA_OPTS% -jar %CURRENT_PATH%/../target/spring-boot-demo.jar %APP_OPTS%

pause
