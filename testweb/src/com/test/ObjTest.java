package com.test;

import com.supermap.data.Datasource;
import com.supermap.data.DatasourceConnectionInfo;
import com.supermap.data.EngineType;
import com.supermap.data.Workspace;

public class ObjTest {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2015-8-12上午9:37:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Workspace workspace = new Workspace();
		// 初始化数据源连接信息
		DatasourceConnectionInfo info = new DatasourceConnectionInfo();
		info.setEngineType(EngineType.ORACLEPLUS);
		info.setServer("192.168.10.254/cloud");
		info.setUser("egisp");
		info.setPassword("supermap");
		// 打开数据源
		Datasource datasource = workspace.getDatasources().open(info);
		System.out.println(datasource);
	}

}
