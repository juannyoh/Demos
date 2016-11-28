package com.test;

public class test2 {
public static void main(String[] args) {
	int samecount=0;
	int differentcount=0;
	String excelcolnames[]={"aa","bb","ff"};
	String basenames[]={"aa","bb","cc","dd","ee","hh"};
	for(int i=0;i<excelcolnames.length;i++){
		for(int j=0;j<basenames.length;j++){
			if(excelcolnames[i].equals(basenames[j])){
				samecount++;
				continue;
			}
		}
	}
	System.out.println("+++"+samecount);
}
}
