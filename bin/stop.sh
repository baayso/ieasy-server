#!/bin/bash

APP_NAME="ieasy-server"

PID=$(ps -ef | grep ${APP_NAME}.jar | grep -v grep | awk '{ print $2 }')

if [ -z "${PID}" ]; then
    echo "${APP_NAME}" is already stopped.
else
    echo kill "${PID}"
    kill "${PID}"
    echo "${APP_NAME}" stopped.
fi
