package com.to8to.exportData;

public class UtilData
{
    /*    public static void getTime2()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
    }*/
    
    /*   public static void upLoad2HDFS() throws FileNotFoundException, IOException
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
    }*/
}
