package com.test;

import java.util.ArrayList;
import java.util.List;


public class test13 {
	public static void main(String[] args) {
		//xxx();
		
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		List<String> templist=null;
		for(int i=0;i<list.size();i++){
			templist=list.subList(i, i+1);
			System.out.println(templist);
		}
	}
	
	public static void xxx () {
		try {
			int x=10/0;
		} catch (Exception e) {
			System.out.println(11);
		}
	}
}
