package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class ReadJson {

	/**
	 * @param args
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub

		// String s = ReadFile("./src/test.json");
		// System.out.println(s);

		/*JSONObject jsonObject = new JSONObject();
		jsonObject.put("1", "一");
		jsonObject.put("2", "二");
		jsonObject.put("3", "三");
		jsonObject.put("4", "四");
		jsonObject.put("5", "五");
		jsonObject.put("6", "六");
		jsonObject.put("7", "⑦");
		System.out.println(jsonObject);

		writeFile("E:/temp/test.json", jsonObject.toString());*/
		//65536
		List<String> path=new ArrayList<String>();
		for(int i=1;i<4220;i++){
			String result=ReadFile("E:/temp/result1/result1/result.txt"+i+".javascript");
			System.out.print(i+"--");
			try{
				//Object[] obj=getJsonToArray(result);
				JSONObject jsonObjectss =getJsonObject(result);
				
				JSONObject resultjson = (JSONObject) jsonObjectss.get("result");
				Object[] obj=getJsonToArray(resultjson.get("results").toString());
		       for(int j=0;j<obj.length;j++){
		    	   JSONObject jsonObjects =(JSONObject)obj[j];
		           //System.out.println(((Double)jsonObjects.get("pathLength"))+"-----"+((Double)jsonObjects.get("pathLength")).intValue());
		    	   //System.out.println(jsonObjects.get("ADDRESS"));
		    	   if(jsonObjects!=null&&jsonObjects.get("x")!=null&&!jsonObjects.get("y").equals("")){
		        	   String s=jsonObjects.get("x").toString()+";"+jsonObjects.get("y").toString();
		        	   path.add(s);
		        	   System.out.println(s);
		           }
		           else path.add("");
		       }
			}catch (Exception e) {
				// TODO: handle exception
				path.add("");
				continue;
			}
		}
		//writeExcel(path);
		
		 File filewrite=new File("E:/temp/temp20160624.xls");   
         filewrite.createNewFile();   
         OutputStream  os=new FileOutputStream(filewrite);
         writeExcel(os,path);
	}

	public static void writeFile(String filePath, String sets)
			throws IOException {
		FileWriter fw = new FileWriter(filePath);
		PrintWriter out = new PrintWriter(fw);
		out.write(sets);
		out.println();
		fw.close();
		out.close();
	}

	public static String ReadFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}
	
	
	
	/**
     * 从json数组中得到相应java数组
     * JSONArray下的toArray()方法的使用
     * @param str
     * @return
     */
     public static Object[] getJsonToArray(String str) {
         JSONArray jsonArray = JSONArray.fromObject(str);
         return jsonArray.toArray();
     }
     
     public static JSONObject getJsonObject(String str) {
    	 JSONObject jsonArray = JSONObject.fromObject(str);
         return jsonArray;
     }

     
     /**输出Excel*/  
     
	public static void writeExcel(List<String> path) {
		jxl.Workbook rwb = null;
		try {
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook
			// 从输入流创建Workbook
			InputStream is = new FileInputStream("E:/temp/temp.xlsx");
			rwb = Workbook.getWorkbook(is);
			// Sheet的下标是从0开始
			// 获取第一张Sheet表
			Sheet rs = rwb.getSheet(0);
			// 获取Sheet表中所包含的总列数
			int rsColumns = rs.getColumns();
			// 获取Sheet表中所包含的总行数
			int rsRows = rs.getRows();
			// 获取指定单元格的对象引用
			/*for (int i = 0; i < rsRows; i++) {
				for (int j = 0; j < rsColumns; j++) {
					Cell cell = rs.getCell(j, i);
					System.out.print(cell.getContents() + " ");
				}
				System.out.println();
			}*/
			// 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File("E:/temp/temp.xlsx"), rwb);
			// 读取第一张工作表
			jxl.write.WritableSheet ws = wwb.getSheet(0);
			// 获取指定单元格的对象引用
			for(int i=0;i<path.size();i++){
				Label label1 = new Label(10, i+1, path.get(i)); 
		        ws.addCell(label1); 
			}
				
			// 写入Excel对象
			// jxl.write.Number number = new jxl.write.Number(5, 4, 123);
			// ws.addCell(number);
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 操作完成时，关闭对象，释放占用的内存空间
			rwb.close();
		}
	}
	
	
	
	 /**输出Excel*/  
	  
    public static void writeExcel(OutputStream os,List<String> list)   
  
    {   
  
        try  
  
        {   
  
        	  
            WritableWorkbook wwb = Workbook.createWorkbook(os);   
            
            //创建Excel工作表 指定名称和位置   
  
            WritableSheet ws = wwb.createSheet("Test Sheet 1",0); 
            
            
        	
   /** 只能通过API提供的 工厂方法来创建Workbook，而不能使用WritableWorkbook的构造函数，因为类WritableWorkbook的构造函数为 protected类型：方法一：直接从目标文件中读取 WritableWorkbook wwb = Workbook.createWorkbook(new File(targetfile));方法 二：如下实例所示 将WritableWorkbook直接写入到输出流*/  
  
        	//List<HashMap<String,Object>> list=ExcelHandle.readExcel("E:/temp/测距测试数据-加坐标.xls");
        	
        	for(int i=0;i<list.size();i++){
        		Label label1 = new Label(10, i+1, list.get(i));
                ws.addCell(label1);  
        	}
  
            //7.写入工作表   
  
            wwb.write();   
  
            wwb.close();   
  
        }   
  
        catch(Exception e)   
  
        {   
  
            e.printStackTrace();   
  
        }   
  
    }   

 }   
 


