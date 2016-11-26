package com.ouye.action;

import java.util.List;
import java.util.Map;

//静态引用方法
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static java.lang.System.out;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml",
		"classpath*:/spring-servlet.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class DailyActionTest {

	@Autowired
	private DailyAction dailyAction;

	@Autowired
	private WebApplicationContext wecContext;

	private MockMvc mockMvc;

	// 模拟request,response
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void before() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
		response.setCharacterEncoding("utf-8");
		this.mockMvc = webAppContextSetup(this.wecContext).build();
	}

	@Ignore
	@Test
	public void testGetDailyUserCounts() {
		List<Map<String, Object>> list = this.dailyAction.getDailyUserCounts();
		out.println("######1:" + list);
	}

	@Test
	public void testGetDailyPointCounts() {
		List<Map<String, Object>> list = this.dailyAction
				.getDailyPointCounts("40288e9f48625c010148625c07160000");
		out.println("######2:" + list);
		String s1[]={"1","2"};
		String s2[]={"1","2"};
		assertArrayEquals("相等",s1, s2);//true
		assertNotEquals(2, 1);//true
//		assertSame(s1, s2);//false
		assertThat(1, allOf(greaterThan(0), lessThan(16)));
		assertThat("eee.df",allOf(Matchers.startsWith("e"),Matchers.endsWith(".df")));
		assertThat("eee.df",anyOf(Matchers.startsWith("s"),Matchers.endsWith(".df")));
	}

	@Ignore
	@Test
	public void testGetDailyOrderCounts() throws Exception {
		mockMvc.perform(
				(post("/daily/dailyordercounts").param("eid",
						"40288e9f48625c010148625c07160000")))
				.andExpect(status().isOk()).andDo(print());
	}

	@Ignore
	@Test
	public void testGetDailyFendanApiCounts() {
		try {
			ResultActions actions = mockMvc
					.perform((post("/daily/dailyfendanapicounts").param("eid",
							"8a04a77b4cbc865c014cc0b8df9c0019")));

			MvcResult mvcResult = actions.andReturn();

			actions.andDo(MockMvcResultHandlers.print());

			actions.andExpect(MockMvcResultMatchers.status().isOk());

			MockHttpServletResponse response = mvcResult.getResponse();
			if (response.getStatus() == 200) {
				String contents=response.getContentAsString();
				JSONArray json=JSON.parseArray(contents);
				out.println("######4:" + json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
