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
import java.util.Base64;
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

public class ExportData2
{

    public static int             count      = 0;
    public static Logger          logger     = LoggerFactory
                                                     .getLogger(ExportData2.class);
    public static HashSet<String> cookiedSet = new HashSet<String>();
    public static int             count2     = 0;

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
                        if (!StringUtil.isEmpty(putLogReq.getVf()))
                        {
                            String visit_from = decodeURL(putLogReq.getVf());
                            writer.write(visit_from.toString() + "\n");
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
                    else if (stirngurl.indexOf("baidu.com") != -1)
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
        if (str.indexOf("wd") != -1)
        {
            Pattern p = Pattern.compile("&wd=(.*?)&");
            Matcher m = p.matcher(str);
            if (m.find())
            {
                return m.group(1).toString();
            }
        }
        else if (str.indexOf("word") != -1)
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

    public static void main(String[] args) throws UnsupportedEncodingException
    {
/*        try
        {
            changeFile("D://test.txt", "D://1.txt");
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }*/
        
        String stirngurl = "a5s1xiwwfqk8b4llqcduw1gdnjfaagognlpy2odcqsylu8gogtipsycftrnrmoc4svn1kh0qjxknf/lze5wxtg==";

     /*   stirngurl = (java.net.URLDecoder.decode(
                stirngurl, "gbk"));*/
        System.out.println(stirngurl);
        
    }

}
