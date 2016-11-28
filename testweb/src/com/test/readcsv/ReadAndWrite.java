package com.test.readcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.supermap.cloud.base.util.sm.coordinate.CoordinateTranslator;
import com.supermap.convert.impl.BaiduCoordinateConvertImpl;
import com.supermap.convert.impl.SuperMapCoordinateConvertImpl;
import com.supermap.entity.Point;

public class ReadAndWrite {
	
	private static BaiduCoordinateConvertImpl bdConvert = new BaiduCoordinateConvertImpl();
	
    public static void main(String[] args) throws IOException { 
        String inString = "";
        File inFile = new File("E://in1.csv"); // 读取的CSV文件
        File outFile = new File("E://8a04a77b56469a0e0156785f694703b9-20160816(baiduMC).csv");//输出的CSV文
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            CsvReader creader = new CsvReader(reader, ',');
            CsvWriter cwriter = new CsvWriter(writer,',');
            while(creader.readRecord()){
                inString = creader.getRawRecord();//读取一行数据
                String ssmx=inString.split(",")[2];
                String ssmy=inString.split(",")[3];
                System.out.println(ssmx+","+ssmy);
                
                if(StringUtils.isNotEmpty(ssmx)&&StringUtils.isNotEmpty(ssmy)&&!ssmx.equals("smx")&&!ssmy.equals("smy")){
                	String xx=convertCoord(Double.parseDouble(ssmx),Double.parseDouble(ssmy),"SMC");
                	System.out.println("---"+xx);
                	if(StringUtils.isNotEmpty(xx)){
                		inString=inString+","+xx;
                	}
                }
                
                /*for(int i = 0;i < str.length;i++){
                    tmpString = inString.replace(str[i], "," + str[i] + ",");
                    inString = tmpString;
                }*/
                //第一个参数表示要写入的字符串数组，每一个元素占一个单元格，第二个参数为true时表示写完数据后自动换行
            cwriter.writeRecord(inString.split(","), true);
            //注意，此时再用cwriter.write(inString)方法写入数据将会看到只往第一个单元格写入了数据，“，”没起到调到下一个单元格的作用
            //如果用cwriter.write(String str)方法来写数据，则要用cwriter.endRecord()方法来实现换行
            //cwriter.endRecord();//换行
            cwriter.flush();//刷新数据
            }  
            creader.close();
            cwriter.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static String convertCTMC2BDMC(double smx,double smy){
    	String result="";
    	Point point=new Point(smx,smy);
    	point=SuperMapCoordinateConvertImpl.smMCToLatLon(point);
    	System.out.println("smMCToLatLon"+point);
    	point=bdConvert.convert(point);
    	System.out.println("convert"+point);
    	point=bdConvert.ll2mc(point);
    	System.out.println("ll2mc"+point);
    	if(point!=null){
    		result=point.getLon()+","+point.getLat();
    	}
    	return result;
    }
    
    
    public static Point realGPSPoint2BaiduGPS(Point realGPSPoint){
		String[] arr = com.supermap.convert.impl.BaiduCoordinateConvertImpl.bdWebAPI(realGPSPoint, 1, 5);
		double x = Double.parseDouble(arr[0]);
		double y = Double.parseDouble(arr[1]);
		Point baiduGPSPoint = new Point(x, y);
		return baiduGPSPoint;
	}
    
    
    /**
     * 根据type类型，将坐标转为百度经纬度
     * @param x
     * @param y
     * @return
     */
    public static String convertCoord(double x, double y,String type) {
        // 墨卡托转加偏经纬度
        CoordinateTranslator.Point point2d = new CoordinateTranslator.Point();
        point2d.setX(x);
        point2d.setY(y);
        
        type = "SMC";
        	
        com.utils.Point realGPSPoint = null;
        // 如果type为摩卡托，将其转为经纬度
        if("SMC".equalsIgnoreCase(type) || "SMLL".equalsIgnoreCase(type)){
        	
        	CoordinateTranslator.mercatorToLngLat(point2d);
        	
        	// 加偏经纬度转真实经纬度
        	com.utils.Point adjustedGPSPoint = new com.utils.Point(point2d.getX(), point2d.getY());
        	realGPSPoint = ConvertCoordTool.adjustedGPS2RealGPS(adjustedGPSPoint);
        }else if("GPS".equalsIgnoreCase(type)){
        	realGPSPoint = new com.utils.Point();
        	realGPSPoint.setLon(point2d.getX());
        	realGPSPoint.setLat(point2d.getY());
        }

        // 真实经纬度转百度经纬度
        Point realGPSPoint2 = new Point(realGPSPoint.getLon(), realGPSPoint.getLat());
        Point baiduGPSPoint = null;
        try {
            baiduGPSPoint = ConvertCoordTool.realGPSPoint2BaiduGPS(realGPSPoint2);
        } catch (Exception e) {
        }
        
        if(baiduGPSPoint!=null){
        	baiduGPSPoint=bdConvert.ll2mc(baiduGPSPoint);
        	return baiduGPSPoint.getLon()+","+baiduGPSPoint.getLat();
        }
        else return "";
    }
}