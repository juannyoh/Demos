package com.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class test10 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2016-1-20下午3:14:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap m=new HashMap();
		m.put("a1", 1);
		m.put("a2", 2);
		m.put("a3", 3);
		m.put("a4", 4);
		
		Set keys = m.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if(key.equals("a1")){
				System.out.println("a1");
			}else if(key.equals("a2")){
				System.out.println("a2");
			}else if(key.equals("a3")){
				System.out.println("a3");
			}else if(key.equals("a4")){
				System.out.println("a4");
			}
		}
	}

}
