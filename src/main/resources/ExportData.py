import os
import time
import datetime

todaytime = datetime.date.today()
timestamp_end=str(int(time.mktime(todaytime.timetuple())))+'000'
yesterday = todaytime - datetime.timedelta(days=1)
timestamp_begin = str(int(time.mktime(yesterday.timetuple())))+'000'
filename = "UserEventLog"+str(yesterday)+".json"
exportCommand = 'mongoexport --port 27001 --host mongo2 -d UserEventLog -c UserEventLog -q \'{"_ct":{"$gte":'+str(timestamp_begin)+',"$lte":'+str(timestamp_end)+'}}\' --out /data/usereventlo
g/'+filename
os.system(exportCommand)
exprotfilenamezip = filename+'en.tar.gz'
zipcommand = 'tar -zcvf /data/usereventlog/zipfile/'+exprotfilenamezip+' /data/usereventlog/'+filename;
os.system(zipcommand)