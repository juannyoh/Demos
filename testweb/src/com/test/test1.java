package com.test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class test1 {

	/**
	 * @param args
	 * @Author Juannyoh
	 * 2015-6-25
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("1111111");
		
		
		//String xx="510001";
		//System.out.println(xx.indexOf("310"));
		
		//System.out.println(xx.substring(0, 4));
		
		/*List<Beans> test=new ArrayList<Beans>();
		for(int x=0;x<1;x++){
			Beans record=new Beans();
			record.setCol1(x+"1");
			record.setCol2("");
			record.setCol3("3");
			test.add(record);
		}
		
		try {
			testGetOrSet(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Beans b=new Beans();
		try {
			Field field=b.getClass().getDeclaredField("col4");
			field.set(b, Double.parseDouble("44"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int add(int x,int y){
		return x+y;
	}
	public int mue(int x,int y){
		return x-y;
	}
	
	
	
	public static <T> void testGetOrSet(List<T> list) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
        Object obj=list.get(0);
		Class tClass = list.get(0).getClass();
        //获得该类的所有属性
        Field[] fields = tClass.getDeclaredFields();
        for(Field field:fields){
        	//field.set(list.get(0), "222");//给字段赋值
            //PropertyDescriptor pd = new PropertyDescriptor(field.getName(), tClass);
            //获得set方法
            //Method set = pd.getWriteMethod();
            //set.invoke(list.get(0), new Object[]{"123"});
            //获得get方法
            //Method get = pd.getReadMethod();
            //Object getValue = get.invoke(list.get(0), new Object[]{});
            //System.out.println("field:"+field.getName()+"---getValue:"+getValue);
        	if(field.get(obj)==null||field.get(obj).equals("")){
        		field.set(obj, "dgdfyhfji");
        	}
        	
        	System.out.println("field:"+field.getName()+"---getValue:"+field.get(obj));
        }
    }
	

}
