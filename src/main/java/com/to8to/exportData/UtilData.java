package com.to8to.exportData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
    }
    
    public static List<String> getDay(String[] args)
    {

        List<String> list = new ArrayList<String>();
        if (args.length == 0)
        {
            Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
            calendar.add(Calendar.DATE, -1); // 得到前一天
            list.add(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
            list.add(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()));
        }
        else if (args.length == 1)
        {
            list.add(args[0].substring(0, 4) + "-"+ args[0].substring(4, 6) + "-" + args[0].substring(6, 8));
            list.add(args[0]);
        }
        return list;
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
    
    public static void file2Hive(String yesterday)
    {
        Config config = new Config("hive.properties");
        String hive_jdbc_url = config.get("hive_jdbc_url");
        String driver_name = config.get("driver_name");
        Connection conn;
        Statement stmt;
        try
        {
            Class.forName(driver_name);
            conn = DriverManager.getConnection(hive_jdbc_url);
            stmt = conn.createStatement();
            String filepath = config.get("file_path");
            String sql = "load data local inpath '" + filepath + "' overwrite into table clickstream PARTITION (dt="+ yesterday + ")";
            stmt.execute(sql);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        getTime();
    }
}
