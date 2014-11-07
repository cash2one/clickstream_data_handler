/**
 * @Title: CharacterUtil.java
 * @Package com.to8to.exportData
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:TO8TO
 *
 * @author JAMES-LIU
 * @date 2014年11月7日 下午2:29:23
 * @version V1.0
 */
package com.to8to.exportData;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.to8to.commons.utils.StringUtil;

/**
 * @ClassName: CharacterUtil
 * @Description: TODO
 * @author JAMES-LIU
 * @date 2014年11月7日 下午2:29:23
 *
 */
public class CharacterUtil
{
    public static Logger logger = LoggerFactory.getLogger(CharacterUtil.class);
    
    public static String getURLString(String URL,String encode1,String encode2)
    {
        String URLString =URL;
        try
        {
            URLString = URLDecoder.decode(URL, encode1);
        }
        catch(UnsupportedEncodingException e)
        {
            try
            {
                URLString = URLDecoder.decode(URL, encode2);
            }
            catch (UnsupportedEncodingException e2)
            {
                return URL;
            }
        }
        return URLString;
    }
    
    public static String getDecodeURL(String URL)
    {
        if(!StringUtil.isEmpty(URL))
        {
            URL = ("http://" + URL).toLowerCase();
            String URLString = URL;
            
            if (!StringUtil.isEmpty(URLString))
            {
                    if (URL.indexOf("utf-8") != -1 || URL.indexOf("utf8") != -1)
                    {
                        URLString = getURLString(URL,"UTF-8","GBK");
                    }
                    else if (URL.indexOf("gb2312") != -1)
                    {
                        URLString = getURLString(URL,"gb2312","UTF-8");
                    }
                    else if (URL.indexOf("gbk") != -1)
                    {
                        URLString = getURLString(URL,"gbk","UTF-8");
                    }
                    else if(URL.indexOf("baidu.com") != -1)
                    {
                        String word = getStrings(URL);
                        if (word != null && word.indexOf("%e") != -1)
                        {
                            try
                            {
                                URLString = (java.net.URLDecoder.decode(URLString, "UTF-8"));
                            }
                            catch (UnsupportedEncodingException e)
                            {
                                e.printStackTrace();
                                System.out.println("URL: "+URL);
                                return URL;
                            }
                        }
                        else
                        {
                            try
                            {
                                URLString = (java.net.URLDecoder.decode(URL, "GB2312"));
                            }
                            catch (UnsupportedEncodingException e)
                            {
                                e.printStackTrace();
                                System.out.println("URL: "+URL);
                                return URL;
                            }
                        }
                    }
                    else if (URL.indexOf("image.so") != -1
                            || URL.indexOf("so.com") != -1
                            || URL.indexOf("to8to.com") != -1)
                    {
                        try
                        {
                            URLString = (java.net.URLDecoder.decode(URL,"utf-8"));
                        }
                        catch (UnsupportedEncodingException e)
                        {
                            e.printStackTrace();
                            System.out.println("URL: "+URL);
                            return URL;
                        }
                    }
                    else if (URL.indexOf("www.sogou.com") != -1)
                    {
                        try
                        {
                            URLString = (java.net.URLDecoder.decode(URL,"gbk"));
                        }
                        catch (UnsupportedEncodingException e)
                        {
                            e.printStackTrace();
                            System.out.println("URL: "+URL);
                            return URL;
                        }
                    }
            }
            System.out.println("URL: "+URLString);
            return URLString;
        }
        return URL;
    }

    private static String getStrings(String str)
    {
        if (str.indexOf("wd") != -1)
        {
            Pattern p = Pattern.compile("&wd=(.*?)&");
            Matcher m = p.matcher(str);
            if (m.find())
            {
                System.out.println(m.group(1).toString());
                return m.group(1).toString();
            }
            
            Pattern p2 = Pattern.compile("\\?wd=(.*?)&");
            Matcher m2 = p2.matcher(str);
            if (m2.find())
            {
                System.out.println(m2.group(1).toString());
                return m2.group(1).toString();
            }
            
        }
        else if (str.indexOf("word") != -1)
        {
            
            Pattern p = Pattern.compile("&word=(.*?)&");
            Matcher m = p.matcher(str);
            if (m.find())
            {
                System.out.println(m.group(1).toString());
                return m.group(1).toString();
            }
            
            Pattern p2 = Pattern.compile("\\?word=(.*?)&");
            Matcher m2 = p2.matcher(str);
            if (m2.find())
            {
                System.out.println(m2.group(1).toString());
                return m2.group(1).toString();
            }
        }
        return null;
    }
    
    public static PutLogReq getPutLogReq(String line)
    {
        PutLogReq putLogReq = null;
        try
        {
            putLogReq = new Gson().fromJson(line, PutLogReq.class);
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
        }
        return putLogReq;
    }

    public static String getParentId(PutLogReq putLogReq)
    {
        List<UserEvent> e = putLogReq.getE();
        String parent_id = "";
        for (int j = 0; j < e.size(); j++)
        {
            UserEvent ue = e.get(j);
            if (ue.getEt().equals("1"))
            {
                parent_id = DigestUtils.md5Hex(ue.getCi() + "-"
                        + putLogReq.getCid());
                return parent_id;
            }
        }
        return parent_id;
    }

    public static LogBean getLogBean(PutLogReq putLogReq, UserEvent ue,
            String parentId)
    {
        LogBean logbean = new LogBean();
        logbean.setUser_id(putLogReq.getUid());
        logbean.setLeave_time(putLogReq.getLt());
        logbean.setVisit_time(ue.getVt());
        logbean.setCookie_id(putLogReq.getCid());
        logbean.setSession_id(putLogReq.getSid());
        logbean.setUser_location(putLogReq.getUl());
        logbean.setIp_address(putLogReq.getIp());
        logbean.setOs_version(putLogReq.getOsv());
        logbean.setOs_type(putLogReq.getOst());
        logbean.setProduct_name(putLogReq.getPn());
        logbean.setProduct_version(putLogReq.getPv());
        logbean.setUser_agent(putLogReq.getUa());
        logbean.setSp_type(putLogReq.getSt());
        logbean.setNetwork_type(putLogReq.getNt());
        logbean.setDevice_type(putLogReq.getDt());
        logbean.setDevice_id(putLogReq.getDi());
        logbean.setDisplay_solution(putLogReq.getDs());
        logbean.setEvent_type(ue.getEt());
        logbean.setEvent_name(ue.getEn());
        try
        {
            
            logbean.setVisit_from(CharacterUtil.getDecodeURL(putLogReq.getVf().toString()));
        }
        catch(IllegalArgumentException iex)
        {
            logbean.setVisit_from(putLogReq.getVf().toString());
            iex.printStackTrace();
        }
        try
        {
            logbean.setVisit_resouce(CharacterUtil.getDecodeURL(ue.getVr()));
        }
        catch(IllegalArgumentException iex)
        {
            logbean.setVisit_resouce(ue.getVr());
            iex.printStackTrace();
        }
        logbean.setParent_id(parentId);
        if (ue.getEt().equals("1"))
        {
            logbean.setCurrent_id(parentId);
        }
        else
        {
            String current_id = DigestUtils.md5Hex(ue.getCi() + "-"
                    + putLogReq.getCid() + "-" + putLogReq.getSid()
                    + System.currentTimeMillis());
            logbean.setCurrent_id(current_id);
        }
        return logbean;
    }
    
    public static void main(String[] args)
    {
        String url = "http://m.baidu.com/from=1776a/s?word=%e4%b8%9c%e5%8d%97%e4%ba%9a%e5%8e%95%e6%89%80%e9%97%a8&ts=6406086&t_kt=211";
        getDecodeURL(url);
    }

}
