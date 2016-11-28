package com.test.testStringToFile;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletInputStream;

public class FileReceiveDelete {
	public static void main(String[] args) {
		String file="f:\\dd\\ee\\s.xs";
		System.out.println(file.substring(0, file.lastIndexOf(".")));
		
		File dir=new File("F:\\data\\egisp\\files\\egispservice_area\\export\\\\da44f9ce82044129b79883511712145c");
//		dir.delete();
		 if(dir.exists()&&dir.isDirectory()){//删除压缩文件原文件包
          	System.out.println("###删除文件包");
          	boolean x= deleteDir(dir);
          	System.out.println(x);
        }
		
	}
	
	private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
            	System.out.println("children:"+children[i]);
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
