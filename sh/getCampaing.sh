#!/bin/bash
filename=$(date --date='1 days ago' +'%Y%m%d')
export LANG=zh_CN.UTF-8
cp /dev/null getCampaing.log
mv /tmp/baidusem/campaignData.txt /data/usereventlog/baidusem/$filename
rm -rf /tmp/baidusem/*
touch /tmp/baidusem/campaignData.txt
java -jar /data/usereventlog/UserLogBaiDuSEM.jar
