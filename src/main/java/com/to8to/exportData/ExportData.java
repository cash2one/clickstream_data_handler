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
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.to8to.commons.utils.Config;
import com.to8to.commons.utils.StringUtil;

public class ExportData
{

    public static int count = 0;
    public static Logger logger = LoggerFactory.getLogger(ExportData.class);
    public static HashSet<String> cookiedSet = new HashSet<String>(); 
    public static int count2 = 0;
    

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
                        String user_id = "";
                        if (!StringUtil.isEmpty(putLogReq.getUid()))
                            user_id = putLogReq.getUid();

                        String cookie_id = "";
                        if (!StringUtil.isEmpty(putLogReq.getCid()))
                        {
                            cookie_id = putLogReq.getCid();
                            cookiedSet.add(cookie_id);
                        }
                        else
                        {
                            count2++;
                        }

                        String session_id = "";
                        if (!StringUtil.isEmpty(putLogReq.getSid()))
                            session_id = putLogReq.getSid();

                        String user_location = "";
                        if (!StringUtil.isEmpty(putLogReq.getUl()))
                            user_location = putLogReq.getUl();

                        String ip_adress = "";
                        if (!StringUtil.isEmpty(putLogReq.getIp()))
                            ip_adress = putLogReq.getIp();

                        String os_version = "";
                        if (!StringUtil.isEmpty(putLogReq.getOsv()))
                            os_version = putLogReq.getOsv();

                        String os_type = "";
                        if (!StringUtil.isEmpty(putLogReq.getOst()))
                            os_type = putLogReq.getOst();

                        String productName = "";
                        if (!StringUtil.isEmpty(putLogReq.getPn()))
                            productName = putLogReq.getPn();

                        String product_version = "";
                        if (!StringUtil.isEmpty(putLogReq.getPv()))
                            product_version = putLogReq.getPv();

                        String user_agent = "";
                        if (!StringUtil.isEmpty(putLogReq.getUa()))
                            user_agent = putLogReq.getUa();

                        String sp_type = "";
                        if (!StringUtil.isEmpty(putLogReq.getSt()))
                            sp_type = putLogReq.getSt();

                        String network_type = "";
                        if (!StringUtil.isEmpty(putLogReq.getNt()))
                            network_type = putLogReq.getNt();
                        
                        String visit_from = "";
                        if (!StringUtil.isEmpty(putLogReq.getVf()))
                            visit_from = decodeURL(putLogReq.getVf());

                        String device_type = "";
                        if (!StringUtil.isEmpty(putLogReq.getDt()))
                            device_type = putLogReq.getDt();

                        String device_id = "";
                        if (!StringUtil.isEmpty(putLogReq.getDi()))
                            device_id = putLogReq.getDi();

                        String display_solution = "";
                        if (!StringUtil.isEmpty(putLogReq.getDs()))
                            display_solution = putLogReq.getDs();

                        String leave_time = "";
                        if (!StringUtil.isEmpty(putLogReq.getLt()))
                            leave_time = putLogReq.getLt();

                        List<UserEvent> e = putLogReq.getE();
                        int size = e.size();
                        String parent_id = "";

                        if (size > 0)
                        {
                            for (int j = 0; j < size; j++)
                            {
                                UserEvent ue = e.get(j);
                                if (ue.getEt().equals("1"))
                                {
                                    parent_id = ue.getCi();
                                    parent_id = parent_id + "-" + cookie_id;
                                    parent_id = DigestUtils.md5Hex(parent_id);
                                }
                            }
                            for (int i = 0; i < size; i++)
                            {
                                try
                                {

                                    UserEvent ue = e.get(i);
                                    LogBean logbean = new LogBean();
                                    logbean.setLeave_time(leave_time);
                                    String visit_time = ue.getVt();
                                    logbean.setVisit_time(visit_time);
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
                                    logbean.setVisit_resouce(decodeURL(ue.getVr()));
                                    
                                    if (ue.getEt().equals("1"))
                                    {
                                        logbean.setCurrent_id(parent_id);
                                        logbean.setParent_id(parent_id);
                                    }
                                    else
                                    {
                                        String current_id = "";
                                        if (!StringUtil.isEmpty(ue.getCi()))
                                        {
                                            current_id = ue.getCi();
                                        }
                                        current_id = current_id + "-"+ cookie_id + "-" + session_id+ System.currentTimeMillis();
                                        current_id = DigestUtils.md5Hex(current_id);
                                        logbean.setCurrent_id(current_id);
                                        logbean.setParent_id(parent_id);
                                    }
                                    writer.write(logbean.toString() + "\n");
                                    writer.flush();
                                    count++;
                                    logger.info("=====================================write a message: "+logbean.toString());
                                    logger.info("=============================================count: "+count);
                                }
                                catch (Exception es)
                                {
                                    logger.error(es.getMessage(), es);
                                    es.printStackTrace();
                                }
                            }
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

    public static void file2Hive(String yesterday) throws Exception
    {
        logger.info("===================begin file2hive=========================");
        Config config = new Config("hive.properties");
        String hive_jdbc_url = config.get("hive_jdbc_url");
        String driver_name = config.get("driver_name");
        Class.forName(driver_name);
        Connection conn = DriverManager.getConnection(hive_jdbc_url);
        Statement stmt = conn.createStatement();
        String filepath = config.get("file_path");
        String sql = "load data local inpath '" + filepath
                + "' overwrite into table clickstream PARTITION (dt="
                + yesterday + ")";
        stmt.execute(sql);
        logger.info("===================end file2hive=========================");
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
                    else if(stirngurl.indexOf("baidu.com") != -1)
                    {
                        String word = getStrings(stirngurl);
                        if (word != null)
                        {

                            if (word.indexOf("%e") != -1)
                            {
                                try
                                {
                                    stirngurl = (java.net.URLDecoder.decode(
                                            stirngurl, "UTF-8"));
                                }
                                catch(UnsupportedEncodingException ue)
                                {
                                    ue.printStackTrace();
                                    try
                                    {
                                        stirngurl = (java.net.URLDecoder.decode(
                                                stirngurl, "GB2312"));
                                    }
                                    catch(UnsupportedEncodingException ue2)
                                    {
                                        ue2.printStackTrace();
                                    }
                                }
                            }
                            else
                            {
                                try
                                {
                                    stirngurl = (java.net.URLDecoder.decode(
                                            stirngurl, "GB2312"));
                                }
                                catch(UnsupportedEncodingException ue3)
                                {
                                    ue3.printStackTrace();
                                    try
                                    {
                                        stirngurl = (java.net.URLDecoder.decode(
                                                stirngurl, "UTF-8"));
                                    }
                                    catch(UnsupportedEncodingException ue4)
                                    {
                                        ue4.printStackTrace();
                                    }
                                }

                            }
                        }
                    }
                    else if (stirngurl.indexOf("image.so") != -1
                            || stirngurl.indexOf("so.com") != -1
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
                return stirngurl;
            }
            return stirngurl;
        }
        return stirngurl;
    }
    
    private static String getStrings(String str)
    {
        if(str.indexOf("wd")!=-1)
        {
            Pattern p = Pattern.compile("&wd=(.*?)&");
            Matcher m = p.matcher(str);
            if (m.find())
            {
                return m.group(1).toString();
            }
        }
        else if(str.indexOf("word")!=-1)
        {
            Pattern p = Pattern.compile("&word=(.*?)&");
            Matcher m = p.matcher(str);
            if (m.find())
            {
                return m.group(1).toString();
            } 
        }
        return null;
    }


    public static void main(String[] args)
    {
        try
        {
            logger.info("=============================begin=============================");
            String fileName = "";
            String yestedayDate2 = "";
            String yestedayDate = "";

            if (args.length == 0)
            {
                Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
                calendar.add(Calendar.DATE, -1); // 得到前一天
                yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
                        .format(calendar.getTime());
                yestedayDate2 = new SimpleDateFormat("yyyyMMdd")
                        .format(calendar.getTime());
                logger.debug("yestedayDate2: " + yestedayDate2);
                fileName = "UserEventLog" + yestedayDate + ".json";
                logger.debug("filename: " + fileName);
            }
            else if (args.length == 2)
            {
                yestedayDate = args[0];
                yestedayDate2 = args[1];
                logger.debug("else yestedayDate2: " + yestedayDate2);
                fileName = "UserEventLog" + yestedayDate + ".json";
                logger.debug("else filename: " + fileName);
            }
            Config config = new Config("hdfs.properties");
            String sourcePath = config.get("LOCAL_SRC_JSON") + fileName;
            String destPath = config.get("LOCAL_SRC");
            logger.debug("sourcePath: " + sourcePath + "destPath: " + destPath);
            changeFile(sourcePath, destPath);
            file2Hive(yestedayDate2);
            logger.info("========================over=============================");
            logger.info("==============================cookiedSet size: "+cookiedSet.size());
            logger.info("==============================count2 size: "+count2);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
    }

}
