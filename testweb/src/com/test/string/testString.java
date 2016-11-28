package com.test.string;


public class testString {
	public static void main(String[] args) {
		testString test = new testString();
        System.out.println(test.lpad(10, 23));
        
        System.out.println(fromatString("ss66",6,"0"));
    }

    /**
     * 补齐不足长度
     * @param length 长度
     * @param number 数字
     * @return
     */
    private String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
    
    
    public static String fromatString(String from,int length,String fix){
		StringBuilder sb=new StringBuilder(from);
		if(from!=null&&from.length()>0){
			for(int i=from.length();i<length;i++){
				sb.append(fix);
			}
		}
		return sb.toString();
	}
}
