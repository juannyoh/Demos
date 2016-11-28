package com.test;

public class test6 {
	
public static void main(String[] args) {
	String s1[]={"1","2"};
	
	String s2[]={"1","2","3"};
	
	System.out.println(compares(s1,s2));
	
}

public static boolean compares(String s11[],String s22[]){
	boolean flag=true;
	String s33[]={"1","2","3"};
	if(s11.length==s33.length){
		for(int i=0;i<s11.length;i++){
			if(!s11[i].equals(s33[i])){
				flag=false;
				break;
			}
		}
	}else flag=false;
	return flag;
}
}
