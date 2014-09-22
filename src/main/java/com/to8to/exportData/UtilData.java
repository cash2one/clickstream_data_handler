package com.to8to.exportData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Calendar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import com.to8to.commons.utils.Config;

public class UtilData
{
    public static void getTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DATE, 20);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DATE, 20);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
    }

    public static void upLoad2HDFS() throws FileNotFoundException, IOException
    {
        Config config = new Config("hdfs.properties");
        String LOCAL_SRC = config.get("LOCAL_SRC");
        String CLOUD_DEST = config.get("CLOUD_DEST");
        InputStream in = new BufferedInputStream(new FileInputStream(LOCAL_SRC));
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl",
                org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl",
                org.apache.hadoop.fs.LocalFileSystem.class.getName());
        FileSystem fs = FileSystem.get(URI.create(CLOUD_DEST), conf);
        OutputStream out = fs.create(new Path(CLOUD_DEST), new Progressable()
        {
            @Override
            public void progress()
            {
                System.out.println("upload file to hdfs");
            }
        });
        IOUtils.copyBytes(in, out, 1024, true);
    }
    
    public static void main(String[] args)
    {
        getTime();
    }
}
