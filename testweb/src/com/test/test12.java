package com.test;

import java.util.ArrayList;
import java.util.List;

public class test12 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2016-4-14下午3:49:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xx="col1,col2,col3,col4";
		
		System.out.println(xx.indexOf("col11"));
		xx=xx.replaceAll("col1", "");
		System.out.println(xx);
		
		List<String> re=new ArrayList<String>();
		re.add("xx");
		re.add("yy");
		re.add("zz");
		System.out.println(re.toString());
	}

}
