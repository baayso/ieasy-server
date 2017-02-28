#!/bin/bash

################################################
# Program:
#     执行install.sql文件。
# History:
#     2015/12/08 20:17    ChenFangjie    v1.0
################################################

log()
{
    echo "["$(date '+%Y-%m-%d %H:%M:%S')"] $*"
    echo "["$(date '+%Y-%m-%d %H:%M:%S')"] $*" >> ${CURRENT_DIR}/install.sql.log
}

#main()
CURRENT_DIR=$(cd "$(dirname "$0")"; pwd)

SERVER=$1
PORT=$2
DATABASE=$3
USERNAME=$4

if [ -z ${SERVER} ]; then
    SERVER="127.0.0.1"
fi

if [ -z ${PORT} ]; then
    PORT="3306"
fi

if [ -z ${DATABASE} ]; then
    DATABASE="springbootdemo"
fi

if [ -z ${USERNAME} ]; then
    USERNAME="root"
fi

log "mysql --host=${SERVER} --port=${PORT} --database=${DATABASE} --user=${USERNAME} --password < install.sql"

log "============================================================================================================="

mysql --host=${SERVER} --port=${PORT} --database=${DATABASE} --user=${USERNAME} --password < install.sql
