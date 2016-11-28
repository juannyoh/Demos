package com.test;

import com.supermap.data.CursorType;
import com.supermap.data.Dataset;
import com.supermap.data.DatasetVector;
import com.supermap.data.Datasets;
import com.supermap.data.Datasource;
import com.supermap.data.DatasourceConnectionInfo;
import com.supermap.data.EngineType;
import com.supermap.data.Geometry;
import com.supermap.data.Point2D;
import com.supermap.data.Recordset;
import com.supermap.data.Workspace;

public class ReadTxt {
	public static void main(String[] args) {
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
		System.out.println(datas.getCount());
		
		for(int i=0;i<datas.getCount();i++){
			System.out.println(datas.get(i).getName());
		}
		
		DatasetVector dataset= (DatasetVector) datas.get("MD_PROVINCE");
		Recordset recordset = dataset.getRecordset(false, CursorType.STATIC);
		System.out.println(dataset.getRecordCount());
		for(recordset.moveFirst();!recordset.isEOF();recordset.moveNext()){
			Geometry geometry=recordset.getGeometry();
			Point2D point=new Point2D(13899956,5971039);
			System.out.println(recordset.getFieldValue("province")+":"+geometry.hitTest(point, 0));
		}
		
	}
	
	
	
	
	public String getProvice(Point2D point){
		String provice="";
		
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
			if(geometry.hitTest(point, 0)){
				provice=(String) recordset.getFieldValue("province");
				System.out.println(provice);
				break;
			}else continue;
		}
		
		return provice;
	}
	
	
	
}
