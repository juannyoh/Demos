package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test9 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2015-12-7下午4:52:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String s="23:28";
		String reg="^([01]\\d|2[0123]):([0-5]\\d|59)$";
		String reg2="^((\\d)|(1\\d)|(2[0-3])):([0-5]\\d|59)$";
		//System.out.println(s.matches(reg));
		
		
		//String ids[]={"1","2","3"};
		//String ids[]=null;
		//List<String> x=Arrays.asList(ids);
		//System.out.println(x.size());
		
		List xx=null;
		//System.out.println(xx.isEmpty());
		
		long start=System.currentTimeMillis();
		List<Map> list=new ArrayList<Map>();
		Map m=null;
		for(int i=0;i<5000;i++){
			m=new HashMap();
			m.put("i", i);
			list.add(m);
		}
		long end=System.currentTimeMillis();
		System.out.println((end-start)+"-----"+list.toString());
	}

}
