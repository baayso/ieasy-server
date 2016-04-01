@echo off
echo [Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME.

set PROFILE=%1%

if "%1%" == "" (set PROFILE=dev)

set JAVA_OPTS=-server -Xms2048m -Xmx2048m -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true
echo %JAVA_OPTS%

set APP_OPTS=-spring.profiles.active=%PROFILE%
echo %APP_OPTS%

echo java %JAVA_OPTS% -jar spring-boot-demo.jar %APP_OPTS%

java %JAVA_OPTS% -jar spring-boot-demo.jar %APP_OPTS%

pause
