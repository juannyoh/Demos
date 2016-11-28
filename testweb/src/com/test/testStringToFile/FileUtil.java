package com.test.testStringToFile;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class FileUtil {  
    /** 
     * Logger for this class 
     */  
    private static final Logger logger = Logger.getLogger(FileUtil.class);  
  
    /** 
     * 将文件流发送至另外服务器的方法（这里有个fileName 
     * 本来是想将文件名放在流里面一起带过去的后来出现问题，如果有朋友知道在这种方法里面怎么把fileName 传过去，麻烦告知一下，万分感谢） 
     *  
     * @param bytes 
     * @param fileName 
     * @return 从服务器端 响应的流 可通过 new String(bytes); 转换 
     */  
    public byte[] httpPost(byte[] bytes, String fileName) {  
        try {  
            String url = "";  
            URL console = new URL(url);  
            HttpURLConnection conn = (HttpURLConnection) console  
                    .openConnection();  
            conn.setConnectTimeout(30000);  
            conn.setReadTimeout(30000);  
            conn.setUseCaches(false);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setRequestMethod("POST");  
            conn.connect();  
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());  
            out.write(bytes);  
            // 刷新、关闭  
            out.flush();  
            out.close();  
            InputStream is = conn.getInputStream();  
            if (is != null) {  
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
                byte[] buffer = new byte[1024];  
                int len = 0;  
                while ((len = is.read(buffer)) != -1) {  
                    outStream.write(buffer, 0, len);  
                }  
                is.close();  
                return outStream.toByteArray();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.info("文件发送失败++++++++++++++++++++++++++");  
        }  
        return null;  
    }  
  
    /** 
     * 将文件转换成byte[] 
     *  
     * @param filePath 
     * @return 
     */  
    public byte[] getBytes(String filePath) {  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
  
    public static void main(String[] args) {  
        String filePath="d:/demo/demo.zip";  
        FileUtil fileUtil=new FileUtil();  
        byte[] bytes = fileUtil.getBytes(filePath);  
        fileUtil.httpPost(bytes, filePath);  
    } 
    
    
    
  
}