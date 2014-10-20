import sys
import os
import time
import datetime

yesterday = sys.argv[1]
unzipfilename = "UserEventLog"+str(yesterday)+".json.en.tar.gz"
filename = "UserEventLog"+str(yesterday)+".json.en"
unzipcommand =  "tar zxvf /data/usereventlog/"+unzipfilename+' -C /data/usereventlog/decrypt/'
os.system(unzipcommand);
mongocommad = 'mongoimport -d UserEventLog -c UserEventLog /data/usereventlog/decrypt/'+filename
os.system(mongocommad)
scpcommand='scp /data/usereventlog/'+unzipfilename+' root@192.168.3.62:/data/usereventlog/zipfile'
os.system(scpcommand)
