package com.test;

import java.util.HashSet;
import java.util.Set;

public class test4 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2015-8-26上午9:51:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//用于存放数组中出现相同的元素
		Set<String> set= new HashSet<String>();
		String strs[]={"月亮","太阳","月亮","星星","太阳"};
		boolean re=compare(strs,set);
		System.out.println(re);
		System.out.println(set);
		
	}
	
	
	//写一个方法把数组和set作为参数传过去
	public static boolean compare(String[] strs,Set set){
	    boolean result = false;
	   //从第一个元素开始比较元素是不是有相同的出现
	   for(int i=0;i<strs.length;i++){
	        for(int j=i+1;j<strs.length;j++){
	            //如果元素相同，保存到set中
	            if(strs[i].equals(strs[j])){
	                 set.add(strs[i]);
	                 result = true;
	            }
	       }
	   }
	return result;
	}

}
