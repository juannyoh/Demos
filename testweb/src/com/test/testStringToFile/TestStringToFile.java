package com.test.testStringToFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletInputStream;

public class TestStringToFile {
	
	private void fileReceive(String res, String filePath) {  
        try {  
            //由于此处文件名称没有带过来，只能自己重新定义文件名称  
        	BufferedReader bufferedReader = new BufferedReader(new StringReader(res)); 
            File f = new File(filePath);  
            FileOutputStream fos = new FileOutputStream(f);  
            byte[] b = new byte[1024];  
            int n=0;  
           /* while((n = bufferedReader.read(b)) != -1){  
                fos.write(b,0,n);  
            } */ 
            fos.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }
	
	
	
	
	
	  /** 
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！) 
     * 
     * @param res            原字符串 
     * @param filePath 文件路径 
     * @return 成功标记 
     */ 
    public static boolean string2File(String res, String filePath) { 
            boolean flag = true; 
            BufferedReader bufferedReader = null; 
            BufferedWriter bufferedWriter = null; 
            try { 
                    File distFile = new File(filePath); 
                    if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs(); 
                    bufferedReader = new BufferedReader(new StringReader(res)); 
                    bufferedWriter = new BufferedWriter(new FileWriter(distFile)); 
                    char buf[] = new char[1024];         //字符缓冲区 
                    int len; 
                    while ((len = bufferedReader.read(buf)) != -1) { 
                            bufferedWriter.write(buf, 0, len); 
                    } 
                    bufferedWriter.flush(); 
                    bufferedReader.close(); 
                    bufferedWriter.close();
            } catch (Exception e) { 
                    e.printStackTrace(); 
                    flag = false; 
                    return flag; 
            } finally { 
                    if (bufferedReader != null) { 
                            try { 
                                    bufferedReader.close(); 
                            } catch (IOException e) { 
                                    e.printStackTrace(); 
                            } 
                    } 
            } 
            return flag; 
    }
    
    
   /* private static void zip(ZipOutputStream out, File f, String base)  
            throws Exception {  
        out.putNextEntry(new ZipEntry(base));  
        FileInputStream in = new FileInputStream(f);  
        int b;  
        while ((b = in.read()) != -1)  
            out.write(b);  
        in.close();  
    }  */
    
    
    /** 
     * 文本文件转换为指定编码的字符串 
     * 
     * @param file         文本文件 
     * @param encoding 编码类型 
     * @return 转换后的字符串 
     * @throws IOException 
     */ 
    public static String file2String(File file, String encoding) { 
            InputStreamReader reader = null; 
            StringWriter writer = new StringWriter(); 
            try { 
                    if (encoding != null && !"".equals(encoding.trim())) { 
                            reader = new InputStreamReader(new FileInputStream(file), encoding); 
                    } else { 
                            reader = new InputStreamReader(new FileInputStream(file)); 
                    } 
                    //将输入流写入输出流 
                    char[] buffer = new char[1024*1024]; 
                    int n = 0; 
                    while (-1 != (n = reader.read(buffer))) { 
                            writer.write(buffer, 0, n); 
                    } 
            } catch (Exception e) { 
                    e.printStackTrace(); 
                    return null; 
            } finally { 
                    if (reader != null) 
                            try { 
                                    reader.close(); 
                            } catch (IOException e) { 
                                    e.printStackTrace(); 
                            } 
            } 
            //返回转换结果 
            if (writer != null) 
                    return writer.toString(); 
            else return null; 
    }
    
    
    public static void main(String[] args) {
    	File f=new File("F:\\StringFile.zip");
		String s=file2String(f, null);
		byte[] b=s.getBytes();
		
		System.out.println(s);
		string2File(s, "F:\\StringFile22.zip");
	}
    
    
    public byte[] file2Byte(File file){
    	FileInputStream is;
    	byte[] b = new byte[1024];
		try {
			is = new FileInputStream(file);
	        int len = 0;
	        while((len=is.read(b))!=-1){
	            System.out.println(new String(b,0,len));
	        }
	        is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return b;
    }
}
