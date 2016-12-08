package com.test;

import java.util.Date;

public class Test14 {

	/**
	 *@param args
	 *2016-7-19下午4:12:07
	 *@author OuyangXiujuan
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Date d1=new Date();
//		Date d2=new Date();
//		
//		String ss="23424";
//		System.out.println(ss.split(",")[0]);
//		
//		String zz[]=new String[]{"x","t"};
//		StringBuilder sb=new StringBuilder();
//		for(String s:zz){
//			sb.append(s+",");
//		}
//		if(sb!=null&&sb.length()>0){
//			String s=sb.substring(0, sb.length()-1);
//			System.out.println(s);
//		}
		
		
		String a = "00011010";
		int d = Integer.parseInt(a, 2); // 2进制
		int o = Integer.parseInt(a, 8); // 8进制
		System.out.println(d);
		System.out.println(o);
		
		int x=11,y=3;
		System.out.println(x%y);
	}

}
