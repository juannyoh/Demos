package com.test;

public class 托尔斯泰 {
	public static void main(String[] args) {

		String f = "%1$03d";
		System.out.println(String.format(f, 21));

		int n = 6;
		String s = "abc";
		System.out.println("%1$0" + (n - s.length()) + "d");
		System.out.println(s
				+ String.format("%1$0" + (n - s.length()) + "d", 0));
		
		System.out.println(formatString("sssss",6));
		
		
		String ssss="abcdefghigklmnoopqrstuvwxyz";
		System.out.println(ssss.substring(0, 2));
		System.out.println(ssss.substring(1, 2));
	}

	public static String formatString(String s, int len) {
		if (s != null) {
			while (s.length() < len) {
				s = s + "0";
			}
		}
		return s;
	}
}
