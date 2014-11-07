package com.to8to.exportData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.to8to.commons.utils.Config;

public class ExportData
{
    public static Logger logger = LoggerFactory.getLogger(ExportData.class);
    public static BufferedReader reader = null;
    public static FileWriter writer = null;
    public static String source = null;
    public static String dest = null;

    public static void writeLogBean(PutLogReq putLogReq)
    {
        if (putLogReq != null)
        {
            List<UserEvent> e = putLogReq.getE();
            if (e.size() > 0)
            {
                String parentId = CharacterUtil.getParentId(putLogReq);
                for (int i = 0; i < e.size(); i++)
                {
                    LogBean logbean = CharacterUtil.getLogBean(putLogReq, e.get(i), parentId);
                    try
                    {
                        writer.write(logbean.toString() + "\n");
                        writer.flush();
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    public static void changeFile()
    {
        File file = new File(source);
        String line = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
            writer = new FileWriter(dest, false);
            while ((line = reader.readLine()) != null)
            {
                writeLogBean(CharacterUtil.getPutLogReq(line));
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        List<String> list = UtilData.getDay(args);
        Config config = new Config("hdfs.properties");
        source = config.get("LOCAL_SRC_JSON") + "UserEventLog" + list.get(0) + ".json";
        dest = config.get("LOCAL_SRC");
        changeFile();
        UtilData.file2Hive(list.get(1));
    }

}
