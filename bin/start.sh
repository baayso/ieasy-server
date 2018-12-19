#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME."

CURRENT_PATH=$(cd "$(dirname "$0")"; pwd)

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

cd ${CURRENT_PATH}

chmod u+x ${CURRENT_PATH}/../target/ieasy-server.jar

echo "java ${JAVA_OPTS} -jar ${CURRENT_PATH}/../target/ieasy-server.jar ${APP_OPTS}"

java ${JAVA_OPTS} -jar ${CURRENT_PATH}/../target/ieasy-server.jar ${APP_OPTS}
