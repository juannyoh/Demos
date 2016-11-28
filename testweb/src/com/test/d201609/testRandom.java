package com.test.d201609;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class testRandom {
	public static void main(String[] args) {
		java.util.Random r = new java.util.Random();
		for (int i = 0; i < 5; i++) {
			System.out.println(r.nextInt());
		}

		System.out.println("----------------");
		java.util.Random r2 = new java.util.Random(10);
		for (int i = 0; i < 5; i++) {
			System.out.println(r2.nextInt());
		}
		
		System.out.println("-----------");
		tst();
	}

	public static void  tst(){
		 // 产生5位长度的随机字符串，中文环境下是乱码  
        String r = RandomStringUtils.random(5);  
        System.out.println(r);  
  
        // 使用指定的字符生成5位长度的随机字符串  
        r = RandomStringUtils.random(5, new char[] { 'a', 'b', 'c', 'd', 'e',  
                'f', '1', '2', '3' });  
        System.out.println(r);  
  
        // 生成指定长度的字母和数字的随机组合字符串  
        r = RandomStringUtils.randomAlphanumeric(6);  
        System.out.println(r);  
  
        // 生成随机数字字符串  
        r = RandomStringUtils.randomNumeric(5);  
        System.out.println(r);  
  
        // 生成随机[a-z]字符串，包含大小写  
        r = RandomStringUtils.randomAlphabetic(5);  
        System.out.println(r);  
  
        // 生成从ASCII 32到126组成的随机字符串  
        r = RandomStringUtils.randomAscii(4);  
        System.out.println(r);  
        
        for(int i=0;i<10;i++){
        	System.out.println(RandomUtils.nextLong());
        }
        
	}

}


