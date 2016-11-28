package com.test.readUDB;

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
import com.supermap.data.GeoRegion;
import com.supermap.data.Point2D;
import com.supermap.data.QueryParameter;
import com.supermap.data.Recordset;
import com.supermap.data.Workspace;

public class ReadUdb {
	
	static Datasets  APIdatasets;
	static DatasetVector APIdatasetVector;
	
	static String filepath="F:\\data\\QUANGUO_ADMIN\\QUANGUO_ADMIN\\POI_ADMINCODE.udb";
	static String datesetname="county";
	
	public static void main(String[] args) {
		read();
		String admincode="650000";
		  List resultList = null;
		  QueryParameter qp = new QueryParameter();
	      qp.setCursorType(CursorType.STATIC);
	      qp.setAttributeFilter("ADMINCODE  like '65%'");
	      qp.setGroupBy(new String[] { "CITY" });
	      qp.setResultFields(new String[] { "ABBREVIATION", "ADMINCODE" });
	      qp.setHasGeometry(false);
	      qp.setOrderBy(new String[]{"ADMINCODE"});
	      Recordset recordset = null;
	      try {
	        recordset = APIdatasetVector.query(qp);
	        if ((recordset != null) && (recordset.getRecordCount() > 0)) {
	          resultList = new ArrayList();
	          for (recordset.moveFirst(); !recordset.isEOF(); recordset.moveNext()) {
	            Map map = new HashMap();
	            map.put("ADMINCODE", recordset.getString("ADMINCODE"));
	            String adminAbbreviation = recordset.getString("ABBREVIATION");
	            String[] abbreviationItems = adminAbbreviation.split(",");
	            if (isAdminMunicipality(admincode))
	              map.put("CITY", abbreviationItems[0]);
	            else {
	              map.put("CITY", abbreviationItems[1]);
	            }
	            resultList.add(map);
	          }
	          System.out.println(resultList);
	        }
	      } catch (Exception e) {
	    	System.out.println(resultList);
	        e.printStackTrace();
	      } finally {
	    	  recordset.close();
	    	  APIdatasetVector.close();
	      }
	      
	}
	
	
	public static void read(){
		/*BasePathAnalystEngine.APIworkspace = new Workspace();
		WorkspaceConnectionInfo apiworkspaceConnectionInfo = new WorkspaceConnectionInfo();
		apiworkspaceConnectionInfo.setType(WorkspaceType.SMWU);
		apiworkspaceConnectionInfo.setServer(this.apiworkspacePath);
		BasePathAnalystEngine.APIworkspace.open(apiworkspaceConnectionInfo);
		if (BasePathAnalystEngine.APIworkspace.getDatasources().getCount() == 0) {
			logger.error("打开数据源失败");
			return false;
		}
		
		Datasource apidatasource = BasePathAnalystEngine.APIworkspace.getDatasources().get(0);
		*/
		Workspace workspace = new Workspace();
		DatasourceConnectionInfo datasourceconnection = new DatasourceConnectionInfo();
		datasourceconnection.setEngineType(EngineType.UDB);
		datasourceconnection.setServer(filepath);
		datasourceconnection.setReadOnly(true);
		Datasource apidatasource=workspace.getDatasources().open(datasourceconnection);
		
		System.out.println("apidatasource获取："+apidatasource.getAlias());
		APIdatasets = apidatasource.getDatasets();
		System.out.println("APIdatasets获取："+(APIdatasets==null?"失败":"成功"));
		System.out.println("APIdatasets::::"+APIdatasets.getCount()+"------"+APIdatasets.contains(datesetname));
		for(int i=0;i<APIdatasets.getCount();i++){
			System.out.println("APIdatasets---"+APIdatasets.get(i).getName()+"....."+APIdatasets.get(i).getTableName());
		}
		APIdatasetVector = (DatasetVector) APIdatasets.get(datesetname);
	}
	
	public static boolean isAdminMunicipality(String admincode)
	  {
	    return (admincode.startsWith("11")) || (admincode.startsWith("31")) || (admincode.startsWith("50")) || 
	      (admincode
	      .startsWith("12"));
	  }

}
