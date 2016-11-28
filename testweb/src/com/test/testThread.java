package com.test;

public class testThread{
	public static void main(String[] args) {
		boolean x=startxx();
		System.out.println(x);
	}
	
	public static boolean startxx(){
		boolean flag=false;
		Thread  t=new Thread(new myThread());
		t.start();
		while(true){
			if(t.isAlive()){
				//System.out.println(222);
			}else {
				System.out.println("ende");
				flag=true;
				return flag;
			}
		}
	}
}




class myThread implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++){
			System.out.println("20"+i);
		}
		
	}

}
