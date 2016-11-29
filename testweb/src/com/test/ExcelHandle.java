package com.test;

import jxl.*;   

import jxl.format.UnderlineStyle;   
  
import jxl.write.*;   
  
import jxl.write.Number;   
  
import jxl.write.Boolean;   
  
import jxl.Cell;   
  
    
  
import java.io.*;   
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.supermap.data.CursorType;
import com.supermap.data.DatasetVector;
import com.supermap.data.Datasets;
import com.supermap.data.Datasource;
import com.supermap.data.DatasourceConnectionInfo;
import com.supermap.data.EngineType;
import com.supermap.data.Geometry;
import com.supermap.data.Point2D;
import com.supermap.data.Recordset;
import com.supermap.data.Workspace;
  
    
  
public class ExcelHandle{   
  
    public ExcelHandle(){   
  
    }   
  
    
    /**
     * 获取point点
     * @param x
     * @param y
     * @return
     * @Author Juannyoh
     * 2015-8-12下午4:11:51
     */
    public static   Point2D getPoint(String x,String y){
    	return new Point2D(Double.parseDouble(x),Double.parseDouble(y));
    }
    
    
    public static List<HashMap<String,Object>> getProviceList(){
    	
    	List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
    	
		
		Workspace workspace = new Workspace();
		// 初始化数据源连接信息
		DatasourceConnectionInfo info = new DatasourceConnectionInfo();
		info.setEngineType(EngineType.ORACLEPLUS);
		info.setServer("192.168.10.254/cloud");
		info.setUser("egisp_midea");
		info.setPassword("supermap");
		// 打开数据源
		Datasource datasource = workspace.getDatasources().open(info);
		Datasets datas=datasource.getDatasets();
		//System.out.println(datas.getCount());
		
		/*for(int i=0;i<datas.getCount();i++){
			System.out.println(datas.get(i).getName());
		}*/
		
		DatasetVector dataset= (DatasetVector) datas.get("MD_PROVINCE");
		Recordset recordset = dataset.getRecordset(false, CursorType.STATIC);
		//System.out.println(dataset.getRecordCount());
		for(recordset.moveFirst();!recordset.isEOF();recordset.moveNext()){
			Geometry geometry=recordset.getGeometry();
			//Point2D point=new Point2D(13899956,5971039);
			/*if(geometry.hitTest(point, 0)){
				provice=(String) recordset.getFieldValue("province");
				System.out.println(provice);
				break;
			}else continue;*/
			HashMap<String,Object> m=new HashMap<String,Object>();
			m.put("geometry", geometry);
			m.put("provice", recordset.getFieldValue("province"));
			list.add(m);
		}
		
		return list;
	}
    
    
    public static String getProvice(Point2D point,List<HashMap<String,Object>> list){
    	String provice="";
    	for(int i=0;i<list.size();i++){
    		HashMap<String,Object> m=list.get(i);
    		Geometry geometry=(Geometry) m.get("geometry");
			//Point2D point=new Point2D(13899956,5971039);
			if(geometry.hitTest(point, 0)){
				provice=(String)m.get("provice");
				System.out.println(provice);
				break;
			}else continue;
    	}
    	return provice;
    }
    
    
    
    /***读取Excel*/  
  
    public static List<HashMap<String,Object>> readExcel(String filePath){   
  
    	List<HashMap<String,Object>> resultlist=new ArrayList<HashMap<String,Object>>();
    	try{   
  
        	List<HashMap<String,Object>> list = ExcelHandle.getProviceList();
        	
        	
        	 
        	
            InputStream is = new FileInputStream(filePath);   
  
            Workbook rwb = Workbook.getWorkbook(is);   
  
            //这里有两种方法获取sheet表:名字和下标（从0开始）   
  
            //Sheet st = rwb.getSheet("original");   
  
            //Sheet的下标是从0开始   
  
            //获取第一张Sheet表   
  
            Sheet rst = rwb.getSheet(0);   
  
            //获取Sheet表中所包含的总列数   
  
            int rsColumns = rst.getColumns();   
  
            //获取Sheet表中所包含的总行数   
  
            int rsRows = rst.getRows();   
  
            //获取指定单元格的对象引用   
  
            for (int i = 1; i < rsRows; i++){   
  
            	String x="";
            	String y="";
            	String provice="未对应-------";
                Cell cellx=rst.getCell(2, i); 
            	Cell celly=rst.getCell(3, i); 
            	if(!cellx.getContents().equals("")&&!celly.getContents().equals("")){
            		x=cellx.getContents().trim();
            		y=celly.getContents().trim();
            		Point2D point =ExcelHandle.getPoint(x, y);
            		provice=ExcelHandle.getProvice(point,list);
            	}
  
            	HashMap<String,Object> map=new HashMap<String,Object>();
            	
            	map.put("x", x);
            	map.put("y", y);
            	map.put("provice", provice);
            	resultlist.add(map);
            	//ExcelHandle.writeExcel(wwb,ws,i,x,y,provice); 
            }             
  
            //关闭   
  
            rwb.close();   
  
        }   
  
        catch(Exception e){   
  
            e.printStackTrace();   
  
        }  
        return resultlist;
  
    }   
  
    /**输出Excel*/  
  
    public static void writeExcel(OutputStream os){   
  
        try {   
  
        	  
            WritableWorkbook wwb = Workbook.createWorkbook(os);   
            
            //创建Excel工作表 指定名称和位置   
  
            WritableSheet ws = wwb.createSheet("Test Sheet 1",0); 
            
            
        	
   /** 只能通过API提供的 工厂方法来创建Workbook，而不能使用WritableWorkbook的构造函数，因为类WritableWorkbook的构造函数为 protected类型：方法一：直接从目标文件中读取 WritableWorkbook wwb = Workbook.createWorkbook(new File(targetfile));方法 二：如下实例所示 将WritableWorkbook直接写入到输出流*/  
  
        	List<HashMap<String,Object>> list=ExcelHandle.readExcel("F:/20150811.xlsx");
        	
        	for(int i=0;i<list.size();i++){
        		HashMap<String,Object> map=list.get(i);
        		String x=(String) map.get("x");
        		String y=(String) map.get("y");
        		String provice=(String) map.get("provice");

                Label label = new Label(0,i+1,x);   
      
                ws.addCell(label); 
                
                Label label2 = new Label(1,i+1,y);   
                
                ws.addCell(label2);   
                
                Label label3 = new Label(2,i+1,provice);   
                
                ws.addCell(label3);  
        	}
  
            //7.写入工作表   
  
            wwb.write();   
  
            wwb.close();   
  
        }   
  
        catch(Exception e){   
  
            e.printStackTrace();   
  
        }   
  
    }   
  
   
    //测试   
  
    public static void main(String args[]){   
  
        try{   
  
            //读EXCEL   
  
       // ExcelHandle.readExcel("F:/20150811.xls");   
        	
        	//输出EXCEL   
        	  
            File filewrite=new File("F:/20150811 - 副本1.xls");   
      
            filewrite.createNewFile();   
      
            OutputStream  os=new FileOutputStream(filewrite);
            ExcelHandle.writeExcel(os);
  
       // ExcelHandle.modifyExcel(new File("F:/BIZ_POINT(2) 20150811.xlsx"), new File("F:/BIZ_POINT(2) 20150811 - 副本.xlsx"));   
  
        }   
  
        catch(Exception e){   
  
        e.printStackTrace();   
  
        }   
  
    }
   
  
}   