#!/bin/bash
now_date=$(date --date='1 days ago' +'%Y-%m-%d')
zipfileName="UserEventLog"$now_date".json.en.tar.gz"
unzipfileName="UserEventLog"$now_date".json.en"
unzipfileName2="UserEventLog"$now_date".json"
export LANG=zh_CN.UTF-8
cp /dev/null tohiveTime.log

zipfilePath="/data/usereventlog/zipfile/$zipfileName"

if [ ! -f "$zipfilePath" ]; then
    echo "$zipfilePath not exist"
    exit 0
else
    tar zxvf /data/usereventlog/zipfile/$zipfileName -C /data/usereventlog/decrypt
    mv /data/usereventlog/decrypt/data/usereventlog/$unzipfileName2 /data/usereventlog/decrypt/
    rm -rf /data/usereventlog/hivefile/clickstream_log.txt
    touch /data/usereventlog/hivefile/clickstream_log.txt
    dos2unix /data/usereventlog/hivefile/clickstream_log.txt
    java -jar /data/usereventlog/clickstream_data_handler.jar
    mv /data/usereventlog/zipfile/$zipfileName /data/usereventlog/bak
fi
