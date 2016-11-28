package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 集合操作
 * @author intrl
 * @date 2010-12-15
 * @version 1.0
 */
public class TestListString {
 
    public static void main(String[] args) {
    	String ss="q!,w!,3!,4!,2!,";
    	List<String> sslist=Arrays.asList(ss.split("!,"));//String 转成list
        List<String> list=new ArrayList<String>();//List 转成String
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(listToString(list));//aaa,bbb,ccc
        System.out.println(listToString(sslist));//aaa,bbb,ccc
        ss="ss";
        System.out.println(ss);

    }
     
    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
    
   /* public List<String> updateString(List<String> sslist,String x){
    	for(String s:sslist){
    		if(){
    			
    		}
    	}
    }*/
}
