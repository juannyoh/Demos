package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test5 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2015-8-31下午2:50:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		List<String> slist=new ArrayList<String>();
		String a="a,b,c,d";
		String ar[]=a.split(",");
		int i=0;
	    for(String s:ar){
	    	slist.add(s);
	    	 Map<String,Object> map=new HashMap<String,Object>();
	    	 map.put("s"+i, s);
	    	 i++;
	    	 list.add(map);
	    }
	    
	    System.out.println(list.toString());
	    System.out.println(slist.toString());
	    
	   int x=1;
	    System.out.println("ss"+(x+1));
		
	}

}
