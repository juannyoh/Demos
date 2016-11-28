package com.ouye.excelAdmincode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ouye.service.IGeocodingService;

/**
 * 操作Excel表格的功能类
 */
public class ExcelReaderWrite {
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;

    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle(InputStream is) {
        try {
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }
    
    /**
     * 获取整理的区划excel
     * @param is
     * @return
     * @Author Juannyoh
     * 2016-11-23上午11:25:50
     */
    public List<AreaExcel> readAreaExcelContent(InputStream is) {
    	List<AreaExcel> arealist=new ArrayList<AreaExcel>();
    	try {
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
//        int colNum = row.getPhysicalNumberOfCells();
        //正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            AreaExcel area=new AreaExcel();
            area.setAreacode(row.getCell(0).getRawValue());
            area.setAreaname(row.getCell(1).getStringCellValue());
            area.setCity(row.getCell(3)==null?null:row.getCell(3).getStringCellValue());
            area.setClevel((int)row.getCell(6).getNumericCellValue());
            area.setCounty(row.getCell(4)==null?null:row.getCell(4).getStringCellValue());
            area.setProvice(row.getCell(2)==null?null:row.getCell(2).getStringCellValue());
            area.setTown(row.getCell(5)==null?null:row.getCell(5).getStringCellValue());
            arealist.add(area);
        }
        return arealist;
    }
    
    
    public void writeExcelContent(InputStream is,OutputStream out,List<String> admins) {
        try {
        	 wb = new XSSFWorkbook(is);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getLastCellNum();
        Cell cell=row.createCell(colNum);
		cell.setCellValue("admincode");
        
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            cell=row.createCell(colNum);
    		cell.setCellValue(admins.get(i-1));
        }
        try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    @SuppressWarnings({ "unused", "deprecation" })
	private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case XSSFCell.CELL_TYPE_NUMERIC:
            case XSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static void main(String[] args) {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
    	
        try {
            // 对读取Excel表格标题测试
            InputStream is = new FileInputStream("E:\\111.xlsx");
            ExcelReaderWrite excelReader = new ExcelReaderWrite();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }
            is.close();

            // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream("E:\\111.xlsx");
            Map<Integer, String> map = excelReader.readExcelContent(is2);
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
                
            }
            is2.close();
            
            //读取区划的excel
            InputStream areais = new FileInputStream("E:\\行政区划生成模版.xlsx");
            List<AreaExcel> arealist=excelReader.readAreaExcelContent(areais);
            areais.close();
            
            IGeocodingService geocodingService=context.getBean(IGeocodingService.class);
            
            List<String> adminlist=null;
            if(arealist!=null&&arealist.size()>0){
            	adminlist=new ArrayList<String>();
            	for(AreaExcel area:arealist){
            		String admincode=null;
            		admincode=geocodingService.searchAdmincodeByName(area.getProvice(),area.getCity(),area.getCounty(),area.getTown(),area.getClevel());
            		/*if(area.getClevel()==1){
            			admincode=geocodingService.searchAdmincodeByName(area.getProvice(),area.getCity(),area.getCounty(),area.getTown(),area.getClevel());
            		}else if(area.getClevel()==2){
            			admincode=geocodingService.searchAdmincodeByName(area.getCity(),area.getClevel());
            		}else if(area.getClevel()==3){
            			admincode=geocodingService.searchAdmincodeByName(area.getCounty(),area.getClevel());
            		}else if(area.getClevel()==4){
            			admincode=geocodingService.searchAdmincodeByName(area.getTown(),area.getClevel());
            		}*/
            		area.setAdmincode(admincode);
            		adminlist.add(admincode);
            	}
            }
            
            InputStream areaReadis = new FileInputStream("E:\\行政区划生成模版.xlsx");
            OutputStream areaWriteout=new FileOutputStream("E:\\行政区划生成模版.xlsx");
            
            excelReader.writeExcelContent(areaReadis, areaWriteout, adminlist);
            
            areaReadis.close();
            areaWriteout.close();
            System.out.println(geocodingService.searchAdmincodeByName("四川","成都",null,null, 2));
            

        } catch (Exception e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }
}
