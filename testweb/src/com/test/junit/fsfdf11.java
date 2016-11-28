package com.test.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.test.test1;

public class fsfdf11 {
    
	test1 test=new test1();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMain() {
		
	}

	@Test
	public void testAdd() {
		assertEquals(4, test.add(1, 3));
	}

	@Test
	public void testMue() {
		assertEquals(8, test.mue(9, 1));
	}

}
