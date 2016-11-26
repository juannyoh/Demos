package com.ouye;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ouye.action.DailyActionTest;
import com.ouye.service.PointServiceTest;

/**
 * /**
 * 测试套件 同时运行多个测试类
 * 
 * @author Juannyoh
 * 
 */
@RunWith(Suite.class)
@SuiteClasses(value = { 
		PointServiceTest.class,
		DailyActionTest.class })
public class TestSuiteCase {

}
