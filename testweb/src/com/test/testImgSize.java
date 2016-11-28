package com.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class testImgSize {
public static void main(String[] args) throws Exception {
	InputStream is = new FileInputStream("D:/img.jpg");
	if(is!=null){
		BufferedImage sourceImg = javax.imageio.ImageIO.read(is); 
		int width=sourceImg.getWidth();
		int height=sourceImg.getHeight();
		System.out.println("width:"+width+"---height:"+height);
		if(width>60||height>60){
			throw new Exception("上传图片尺寸最大不超过60*60");
		}
	}
	is.close();
}
}
