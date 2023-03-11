#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME."
echo ""

APP_NAME="ieasy-server"

PID=$(ps -ef | grep ${APP_NAME}.jar | grep -v grep | awk '{ print $2 }')

if [ -n "${PID}" ]; then
    echo "${APP_NAME}" is running. pid="${PID}".
    exit 0
fi

CURRENT_PATH=$(cd "$(dirname "$0")" || exit; pwd)

PORT=$1
PROFILE=$2

if [ -z "${PORT}" ]; then
    PORT="6666"
fi

if [ -z "${PROFILE}" ]; then
    PROFILE="pro"
fi

JAVA_OPTS="-server -Xms2048m -Xmx2048m -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
echo "${JAVA_OPTS}"
echo ""

APP_OPTS="--server.port=${PORT} --spring.profiles.active=${PROFILE}"
echo "${APP_OPTS}"
echo ""

cd "${CURRENT_PATH}" || exit

chmod u+x "${CURRENT_PATH}"/../target/${APP_NAME}.jar

echo "nohup java ${JAVA_OPTS} -jar ${CURRENT_PATH}/../target/${APP_NAME}.jar ${APP_OPTS} > /dev/null 2>&1 &"

nohup java ${JAVA_OPTS} -jar ${CURRENT_PATH}/../target/${APP_NAME}.jar ${APP_OPTS} > /dev/null 2>&1 &

echo ""
