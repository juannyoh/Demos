package com.test.imgCompress;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class testbufferimg {
	public static void main(String[] args) throws Exception {
		//获取全屏截图，截图类型为BufferedImage
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		//创建储存图片二进制流的输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//创建ImageOutputStream流
		ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(baos);
		//将二进制数据写进ByteArrayOutputStream
		ImageIO.write(image, "jpg", imageOutputStream);
		//输出数组
		System.out.println(Arrays.toString(baos.toByteArray()));
		
		InputStream is= new ByteArrayInputStream(baos.toByteArray());
		
		System.out.println(is);
		
		
		/*ByteArrayOutputStream bs = new ByteArrayOutputStream();
	    ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
	    ImageIO.write(sourceImg,imgtype,imOut);
	    InputStream is= new ByteArrayInputStream(bs.toByteArray());*/
	}
}
