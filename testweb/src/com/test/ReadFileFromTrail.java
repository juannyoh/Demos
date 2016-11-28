package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.ByteArrayInputStream;

class Reader implements Runnable {
	Reader(String filename) {
		this.filename = filename;
	}

	private String filename;
	private long filelength = 0;
	private int count = 0;

	public void run() {
		while (true) {
			try {
				File f = new File(filename);
				long nowlength = f.length();
				long readlength = nowlength - filelength;
				if (readlength == 0) {
					Thread.sleep(1000);
					continue;
				}

				RandomAccessFile rf = new RandomAccessFile(f, "r");
				// 移动文件指针到上次读的最后
				rf.seek(filelength);

				filelength = nowlength;

				byte[] b = new byte[(int) readlength];
				rf.read(b, 0, b.length);
				rf.close();

				BufferedReader br = new BufferedReader(new InputStreamReader(
						new ByteArrayInputStream(b)));
				String str = null;
				count++;
				System.out.println("第" + count + "次读到的内容:");
				String key=null;
				String uri=null;
				String eid=null;
				String deptid=null;
				String dcode=null;
				String param=null;
				String result=null;
				String completeTime=null;
				String requestTime=null;
				String consumeTime=null;
				while ((str = br.readLine()) != null) {
					System.out.println(str);
					if(str.contains("uri=")){
						uri=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("key=")){
						key=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("eid=")){
						eid=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("deptId=")){
						deptid=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("dcode=")){
						dcode=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("param=")){
						param=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("result=")){
						result=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("completeTime=")){
						completeTime=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("requestTime=")){
						requestTime=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					else if(str.contains("consumeTime=")){
						consumeTime=str.substring(str.lastIndexOf("=")+1, str.length());
					}
					if(str.contains("------")){
						System.out.println(key+uri+result);
						System.out.println("行结束");
					}
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

/*class Writer implements Runnable {
	Writer(String filename) {
		this.filename = filename;
	}

	private String filename;
	private int count = 0;

	public void run() {
		while (count++ < 100) {
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(filename, true));
				pw.append("" + count).append("\t")
						.append("" + System.currentTimeMillis())
						.append("写入的内容").append("\r\n");
				pw.close();
				Thread.sleep(100);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}*/

public class ReadFileFromTrail {
	public static void main(String[] args) {
		Reader reader = new Reader("d:\\temp\\statistics.log");
		//Writer writer = new Writer("d:\\temp\\statistics.log");
		new Thread(reader).start();
		//new Thread(writer).start();
	}

}
