 /**
  * @Title: GetKeyWord.java
  * @Package com.to8to.exportData
  * @Description: TODO
  * Copyright: Copyright (c) 2014
  * Company:TO8TO
  *
  * @author JAMES-LIU
  * @date 2014年11月1日 下午1:54:12
  * @version V1.0
  */
package com.to8to.exportData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 /**
 * @ClassName: GetKeyWord
 * @Description: TODO
 * @author JAMES-LIU
 * @date 2014年11月1日 下午1:54:12
 *
 */
public class GetKeyWord
{
    
    public static void changeFile(String source, String dest)
    {
        File file = new File(source);
        try
        {
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            FileWriter writer = null;
            try
            {
                
                writer = new FileWriter(dest, false);
                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);
                    line = getStrings(line);
                    if(line!=null)
                    {
                        writer.write(line+ "\n");
                        writer.flush();
                    }
                }
                writer.close();
                reader.close();
                read.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

    /**
     * @method: main
     * @Description: TODO
     * @param @param args    
     * @return void
     * @throws
     */
    public static void main(String[] args)
    {
        try
        {
            changeFile("E://1.txt", "E://2.txt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
