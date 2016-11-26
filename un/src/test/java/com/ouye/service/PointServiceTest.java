package com.ouye.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class PointServiceTest {

	
	@Test
	public final void testGetDailyPointCounts() {
//		fail("Not yet implemented");
	}

	@Test
	public final void testGetDailyOrderCounts() {
//		fail("Not yet implemented");
	}
	
	@BeforeClass
	public static void before(){
		System.out.println("before");
	}
	
	@AfterClass
	public static void after(){
		System.out.println("after");
	}

}
