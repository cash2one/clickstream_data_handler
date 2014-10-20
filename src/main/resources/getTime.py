#!/usr/bin/python2.7
import time
import datetime
today = datetime.date.today() 
todayunixtime = time.mktime(today.timetuple())
print todayunixtime
yesterday = today - datetime.timedelta(days=1)
nextunixtime = time.mktime(yesterday.timetuple())
print nextunixtime
