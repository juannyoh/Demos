package com.test;

public class test8 {
public static void main(String[] args) {
	//String x="12964281.925530;4848774.045557";
	String x="254185.5;4848774.045557";
	String xy[]=x.split(";");
	for(String s:xy){
		double smx=Double.parseDouble(s);
	//System.out.println(smx);
	}
	
	
	Object t="";
	System.out.println(t.equals(""));
	
	String xs="5010000";
	System.out.println(xs.replaceAll("0+$", ""));
	
	
	System.out.println(System. currentTimeMillis());
}
}
