package com.to8to.exportData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.to8to.commons.utils.Config;
import com.to8to.commons.utils.StringUtil;

public class ExportData
{

    public static Logger logger = LoggerFactory.getLogger(ExportData.class);

    public static String stringToLongTime(String time)
    {
        if (!StringUtil.isEmpty(time))
        {
            String[] args = time.split("\\.");
            Calendar cal = Calendar.getInstance();

            logger.debug("args[0].length(): " + args[0].length() + " args[0]:"
                    + args[0]);

            if (args[0].length() == 14)
            {
                int miliseconde = 0;
                if (args.length > 2)
                {
                    miliseconde = Integer.parseInt(args[1]);
                }
                int year = Integer.parseInt(time.substring(0, 4));
                int month = Integer.parseInt(time.substring(4, 6)) - 1;
                int day = Integer.parseInt(time.substring(6, 8));
                int hour = Integer.parseInt(time.substring(8, 10));
                int miniute = Integer.parseInt(time.substring(10, 12));
                int sencode = Integer.parseInt(time.substring(12, 14));
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DATE, day);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.SECOND, sencode);
                cal.set(Calendar.MINUTE, miniute);
                cal.set(Calendar.MILLISECOND, miliseconde);
                return String.valueOf(cal.getTimeInMillis());
            }
        }
        return "0";
    }

    public static void changeFile(String source, String dest)
    {

        File file = new File(source);

        try
        {
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            FileWriter writer = null;
            String line;
            try
            {

                writer = new FileWriter(dest, false);

                while ((line = reader.readLine()) != null)
                {
                    logger.debug(line);

                    Gson gson = new Gson();
                    PutLogReq putLogReq = null;

                    try
                    {
                        putLogReq = gson.fromJson(line, PutLogReq.class);
                    }
                    catch (JsonSyntaxException e)
                    {
                        putLogReq = null;
                        logger.debug(e.getMessage(), e);
                        e.printStackTrace();
                    }

                    if (putLogReq != null)
                    {
                        String user_id = null;
                        if (!StringUtil.isEmpty(putLogReq.getUid()))
                            user_id = putLogReq.getUid();

                        String cookie_id = null;
                        if (!StringUtil.isEmpty(putLogReq.getCid()))
                            cookie_id = putLogReq.getCid();

                        String session_id = null;
                        if (!StringUtil.isEmpty(putLogReq.getSid()))
                            session_id = putLogReq.getSid();

                        String user_location = null;
                        if (!StringUtil.isEmpty(putLogReq.getUl()))
                            user_location = putLogReq.getUl();

                        String ip_adress = null;
                        if (!StringUtil.isEmpty(putLogReq.getIp()))
                            ip_adress = putLogReq.getIp();

                        String os_version = null;
                        if (!StringUtil.isEmpty(putLogReq.getOsv()))
                            os_version = putLogReq.getOsv();

                        String os_type = null;
                        if (!StringUtil.isEmpty(putLogReq.getOst()))
                            os_type = putLogReq.getOst();

                        String productName = null;
                        if (!StringUtil.isEmpty(putLogReq.getPn()))
                            productName = putLogReq.getPn();

                        String product_version = null;
                        if (!StringUtil.isEmpty(putLogReq.getPv()))
                            product_version = putLogReq.getPv();

                        String user_agent = null;
                        if (!StringUtil.isEmpty(putLogReq.getUa()))
                            user_agent = putLogReq.getUa();

                        String sp_type = null;
                        if (!StringUtil.isEmpty(putLogReq.getSt()))
                            sp_type = putLogReq.getSt();

                        String network_type = null;
                        if (!StringUtil.isEmpty(putLogReq.getNt()))
                            network_type = putLogReq.getNt();

                        String visit_from = null;
                        if (!StringUtil.isEmpty(putLogReq.getVf()))
                            visit_from = decodeURL(putLogReq.getVf());

                        String device_type = null;
                        if (!StringUtil.isEmpty(putLogReq.getDt()))
                            device_type = putLogReq.getDt();

                        String device_id = null;
                        if (!StringUtil.isEmpty(putLogReq.getDi()))
                            device_id = putLogReq.getDi();

                        String display_solution = null;
                        if (!StringUtil.isEmpty(putLogReq.getDs()))
                            display_solution = putLogReq.getDs();

                        String leave_time = null;
                        if (!StringUtil.isEmpty(putLogReq.getLt()))
                            leave_time = stringToLongTime(putLogReq.getLt());

                        List<UserEvent> e = putLogReq.getE();
                        int size = e.size();
                        String parent_id = null;

                        if (size > 0)
                        {
                            for (int j = 0; j < size; j++)
                            {
                                UserEvent ue = e.get(j);
                                if (ue.getEt().equals("1"))
                                {
                                    parent_id = ue.getCi();
                                }
                            }
                            for (int i = 0; i < size; i++)
                            {
                                try
                                {

                                    UserEvent ue = e.get(i);
                                    LogBean logbean = new LogBean();
                                    logbean.setLeave_time(leave_time);
                                    String visit_time = stringToLongTime(ue
                                            .getVt());
                                    logbean.setVisit_time(visit_time);

                                    if (Long.parseLong(leave_time) > Long
                                            .parseLong(visit_time))
                                    {
                                        logbean.setUser_id(user_id);
                                        logbean.setCookie_id(cookie_id);
                                        logbean.setSession_id(session_id);
                                        logbean.setUser_location(user_location);
                                        logbean.setIp_address(ip_adress);
                                        logbean.setOs_version(os_version);
                                        logbean.setOs_type(os_type);
                                        logbean.setProduct_name(productName);
                                        logbean.setProduct_version(product_version);
                                        logbean.setUser_agent(user_agent);
                                        logbean.setSp_type(sp_type);
                                        logbean.setNetwork_type(network_type);
                                        logbean.setVisit_from(visit_from);
                                        logbean.setDevice_type(device_type);
                                        logbean.setDevice_id(device_id);
                                        logbean.setDisplay_solution(display_solution);
                                        logbean.setEvent_type(ue.getEt());
                                        logbean.setEvent_name(ue.getEn());
                                        logbean.setVisit_resouce(decodeURL(ue
                                                .getVr()));
                                        logbean.setCurrent_id(ue.getCi());
                                        logbean.setParent_id(parent_id);
                                        writer.write(logbean.toString() + "\n");
                                        writer.flush();
                                    }
                                }
                                catch (Exception es)
                                {
                                    logger.error(es.getMessage(), es);
                                    es.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            LogBean logbean = new LogBean();
                            logbean.setLeave_time(leave_time);
                            logbean.setUser_id(user_id);
                            logbean.setCookie_id(cookie_id);
                            logbean.setSession_id(session_id);
                            logbean.setUser_location(user_location);
                            logbean.setIp_address(ip_adress);
                            logbean.setOs_version(os_version);
                            logbean.setOs_type(os_type);
                            logbean.setProduct_name(productName);
                            logbean.setProduct_version(product_version);
                            logbean.setUser_agent(user_agent);
                            logbean.setSp_type(sp_type);
                            logbean.setNetwork_type(network_type);
                            logbean.setVisit_from(visit_from);
                            logbean.setDevice_type(device_type);
                            logbean.setDevice_id(device_id);
                            logbean.setDisplay_solution(display_solution);
                            logbean.setParent_id(parent_id);
                            writer.write(logbean.toString() + "\n");
                            writer.flush();
                        }
                    }
                }
                writer.close();
                reader.close();
                read.close();
            }
            catch (IOException e)
            {
                logger.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static String decodeURL(String url)
    {
        String stirngurl = url;
        if (!StringUtil.isEmpty(url))
        {
            stirngurl = ("http://" + url).toLowerCase();
            try
            {
                if (url.indexOf("utf-8") != -1 || url.indexOf("utf8") != -1)
                {
                    stirngurl = URLDecoder.decode(stirngurl, "utf-8");
                }
                else if (url.indexOf("gb2312") != -1)
                {
                    stirngurl = URLDecoder.decode(stirngurl, "gb2312");
                }
                else if (url.indexOf("gbk") != -1)
                {
                    stirngurl = URLDecoder.decode(stirngurl, "gbk");
                }
                else
                {
                    if (stirngurl.indexOf("www.sogou.com") != -1)
                    {
                        stirngurl = (java.net.URLDecoder.decode(stirngurl,
                                "gbk"));
                    }
                    else if (stirngurl.indexOf("image.so") != -1
                            || stirngurl.indexOf("so.com") != -1
                            || stirngurl.indexOf("baidu.com") != -1
                            || stirngurl.indexOf("to8to.com") != -1)
                    {
                        stirngurl = (java.net.URLDecoder.decode(stirngurl,
                                "utf-8"));
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
                logger.error("decode url is: "+stirngurl);
                return stirngurl;
            }
            return stirngurl;
        }
        return stirngurl;
    }

    public static long getStartTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        logger.debug("cal.getTime: " + cal.getTime());
        return cal.getTimeInMillis();
    }

    public static void file2Hive(String yesterday) throws Exception
    {
        Config config = new Config("hive.properties");
        String hive_jdbc_url = config.get("hive_jdbc_url");
        String driver_name = config.get("driver_name");
        Class.forName(driver_name);
        Connection conn = DriverManager.getConnection(hive_jdbc_url);
        Statement stmt = conn.createStatement();
        String filepath = config.get("file_path");
        String sql = "load data local inpath '" + filepath
                + "' overwrite into table clickstream_log_new PARTITION (dt="
                + yesterday + ")";
        stmt.execute(sql);
    }

    public static void main(String[] args)
    {
/*        try
        {
            Config config = new Config("hdfs.properties");
            Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
            calendar.add(Calendar.DATE, -1); // 得到前一天
            String yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
                    .format(calendar.getTime());
            String yestedayDate2 = new SimpleDateFormat("yyyyMMdd")
                    .format(calendar.getTime());
            logger.debug("yestedayDate2: " + yestedayDate2);
            String fileName = "UserEventLog" + yestedayDate + ".json";
            logger.debug("filename: " + fileName);
            String sourcePath = config.get("LOCAL_SRC_JSON") + fileName;
            String destPath = config.get("LOCAL_SRC");
            logger.debug("sourcePath: " + sourcePath + "destPath: " + destPath);
            changeFile(sourcePath, destPath);
            file2Hive(yestedayDate2);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }*/
        try
        {
            String url = "http://nc.to8to.com/company/?utm_source=sogou&utm_medium=cpc&utm_keyword=1184102226&utm_term=%e5%8d%97%e6%98%8c%e8%a3%85%e4%bf%ae%e5%85%ac%e5%8f%b8%e6%8e%92%e5%90%8d&utm_content=04+%e8%a3%85%e4%bf%ae%e5%85%ac%e5%8f%b8%e6%8e%92%e5%90%8d%ef%bc%88%e7%b2%be%ef%bc%89&utm_campaign=%e5%8d%97%e6%98%8c&utm_content=04+%e8%a3%85%e4%bf%ae%e5%85%ac%e5%8f%b8%e6%8e%92%e5%90%8d%ef%bc%88%e7%b2%be%ef%bc%89&utm_content=04+%e8%a3%85%e4%bf%ae%e5%85%ac%e5%8f%b8%e6%8e%92%e5%90%8d%ef%bc%88%e7%b2%be%ef%bc%89&utm_content=04+%e8%a3%85%e4%bf%ae%e5%85%ac%e5%8f%b8%e6%8e%92%e5%90%8d%ef%bc%88%e7%b2%be%ef%bc%89&utm_content=04+%e8%a3%85%e4%bf%ae%e5%85%ac%e5%8f%b8%e6%8e%92%e5%90%8d%ef%bc%88%e7%b2%be%ef%&location=1#show_company";
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

}
