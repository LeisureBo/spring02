package com.bo.spring.service.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.spring.entity.Account;
import com.bo.spring.entity.User;
import com.bo.spring.service.UserService;

/**
 * @Description 基于 @Transactional的声明式事务管理 测试
 * @author 王博
 * @version 2017年9月8日　下午10:40:11
 */
public class TxAnnoBankServiceImplTest {

	private static ApplicationContext ctx;
	
	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @Description 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void test() throws Exception {
		UserService userService = (UserService) ctx.getBean("userService");
		User u1 = new User(1,"bo","1111",new Account(1,100));
		User u2 = new User(2,"lin","1809", new Account(2,100));
		boolean result = userService.NonTransactionUpdate(u1, u2);
		assertEquals("tx更新用户出错!", true, result);
	}

	/**
	 * 模拟controller调service层业务方法事务处理:
	 * 
	 * 在controller层中使用try..catch..处理捕获异常不会影响service层的事务处理
	 */
	@Test
	public void test02(){
		try {
			UserService userService = (UserService) ctx.getBean("userService");
			User u1 = new User(1,"bo","1111",new Account(1,100));
			User u2 = new User(2,"lin","1809", new Account(2,100));
			boolean result = userService.txUpdateUser(u1, u2);
			assertEquals("tx更新用户出错!", true, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
