package com.ouye.service;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ouye.bean.LoginUser;
import com.ouye.bean.UserEntity;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {

	@Resource
	private UserService userService;
	
	
/*	private static  SessionFactory sessionFactory;
	private static  ServiceRegistry serviceRegistry;
*/	
	@BeforeClass
	public static void before(){
		/*Configuration configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
	}
	
	@Ignore
	@Test
	public void testGetDailyUserCounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
//		Session session = null;
//		Transaction tx = null;
		LoginUser.setTenantId("egisp_dev2");
//		session = sessionFactory.openSession();
//		tx = session.beginTransaction();
		UserEntity user=new UserEntity();
		user.setUsername("testmuti");
		user.setCreateTime(new Date());
		this.userService.addUser(user);
		
//		session.saveOrUpdate(user);
//		tx.commit();
//		session.close();
	}

}
