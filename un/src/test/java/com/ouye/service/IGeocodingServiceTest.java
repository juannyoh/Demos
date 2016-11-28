package com.ouye.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class IGeocodingServiceTest {
	
	@Resource
	IGeocodingService geocodingService;

	@Ignore
	@Test
	public final void testSearchForCounty() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public final void testSearchCountyByAdmincode() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public final void testGetAdminElement() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public final void testGetAdminGeoByCode() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public final void testGetCountyByAdmincode() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public final void testSearchAdmincodeForCounty() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSearchAdmincodeByName() {
		System.out.println(this.geocodingService.searchAdmincodeByName("四川省","成都市",null,null, 2));
	}

}
