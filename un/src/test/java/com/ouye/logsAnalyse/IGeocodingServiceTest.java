package com.ouye.logsAnalyse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ouye.service.IGeocodingService;
import com.supermap.convert.impl.SuperMapCoordinateConvertImpl;
import com.supermap.entity.Point;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class IGeocodingServiceTest {

	@Resource
	IGeocodingService geocodingService;
	
	@Test
	public void TestWriteApiLogFile(){
		try {
			List<APIFendanEntity> apiloglist=ReadLogFiles.readLogs("2016-11-22", 13, "8a04a77b4d7a6206014ddb66af2f01a2");
			for(int i=0;i<apiloglist.size();i++){
				APIFendanEntity fendan=apiloglist.get(i);
				double x=fendan.getSmx().doubleValue();
				double y=fendan.getSmy().doubleValue();
				if(x!=0&&y!=0){
					if(x>0D&&x<180D&&y>-90D&&y<90D){
						Point p = new Point(x,y);
						// 如果需要返回的坐标类型为经纬度，则进行坐标转换。
						p = SuperMapCoordinateConvertImpl.smLL2MC(p);
						x=p.getLon();
						y=p.getLat();
					}
					Map<String,String> map=this.geocodingService.searchForCounty(x, y);
					if(map!=null){
						fendan.setProvince(map.get("PROVINCE"));
						fendan.setCity(map.get("CITY2"));
						fendan.setCounty(map.get("COUNTY"));
					}
				}
			}
			
			System.out.println("API条数："+apiloglist.size());
			
			File filewrite=new File("E:/分单API-"+System.currentTimeMillis()+".xlsx");   
            filewrite.createNewFile();   
            OutputStream  os=new FileOutputStream(filewrite);
            ExportExcel excel=new ExportExcel();
            String[] headers={"key","分单时间","运单号","地址","省","市","区","区划名称","X","Y","结果类型"};
            excel.exportPointExcel("分单API", headers,apiloglist , os, "yyyy-MM-dd HH:mm:ss");
            os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}
