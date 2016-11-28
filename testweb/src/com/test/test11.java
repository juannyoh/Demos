package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class test11 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2016-2-17下午2:52:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		
		try {
			System.out.println(sdf.parse("20160217145902"));
			
			String x="wo";
			String y="我们";
			System.out.println(x.length()+","+y.length());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
