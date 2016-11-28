package com.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class test3 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2015-8-25上午10:07:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s[]={1,2,3,4,5,0};
		int m[]={1,4,0,6,7};
		String erro="";
		for(int i=0;i<s.length;i++){
			for(int j=0;j<m.length;j++){
				try{
					System.out.println(i+"-----"+s[i]+"%"+m[j]+"="+s[i]%m[j]);
					
				}catch(Exception e){
					System.out.println(e.getMessage());
					erro+=i+"-----"+s[i]+"%"+m[j]+"="+e.getMessage()+"\r\n";
					j++;
				}
			}
		}
		System.out.println(erro);
		
		
		/*Map<Integer,Object> headerMap=new HashMap<Integer,Object>();
		headerMap.put(1, "xxx");
		headerMap.put(2, "cc");
		headerMap.put(3, "vv");
		headerMap.put(4, "bb");
		String a[]=new String[headerMap.values().size()];
		headerMap.values().toArray(a);
		System.out.println(a[3]);*/
		
		Map<Integer,Object> headerMap=new HashMap<Integer,Object>();
		headerMap.put(1, "xxx");
		headerMap.put(2, "cc");
		headerMap.put(3, "vv");
		headerMap.put(4, "bb");
		System.out.println(headerMap);
		
		Map<String,Object> resultObj = new HashMap<String,Object>();
		resultObj.put("info", "1");
		resultObj.put("result", "2");
		resultObj.put("isSuccess", "3");
		System.out.println(resultObj);
		String a[]=new test3().ChangMapToStringArray(headerMap.values());
		System.out.println(a[2]);
		
	}
	
	public String[] ChangMapToStringArray(Collection collection){
		String result[]=new String[collection.size()];
		collection.toArray(result);
		return result;
	}

}
