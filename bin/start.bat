@echo off
echo [Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME.

set JAVA_OPTS=-server -Xms2048m -Xmx2048m
echo %JAVA_OPTS%

set APP_OPTS=-Djava.awt.headless=true -Djava.net.preferIPv4Stack=true
echo %APP_OPTS%

java %JAVA_OPTS% %APP_OPTS% -jar spring-boot-demo.jar

pause
