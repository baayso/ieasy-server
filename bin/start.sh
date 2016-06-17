#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME."

PORT=$1
PROFILE=$2

if [ -z ${PORT} ]; then
    PORT="6666"
fi

if [ -z ${PROFILE} ]; then
    PROFILE="pro"
fi

JAVA_OPTS="-server -Xms2048m -Xmx2048m -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true"
echo ${JAVA_OPTS}

APP_OPTS="--server.port=${PORT} --spring.profiles.active=${PROFILE}"
echo ${APP_OPTS}

echo "nohup java ${JAVA_OPTS} -jar spring-boot-demo.jar ${APP_OPTS} > /dev/null 2>&1 &"

nohup java ${JAVA_OPTS} -jar spring-boot-demo.jar ${APP_OPTS} > /dev/null 2>&1 &
