#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME."
echo "[Pre-Requirement] Makesure install Maven 3.2+ and set the PATH."

MVN="mvn"

${MVN} clean install
