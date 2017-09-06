package com.bo.demo.service.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.demo.entity.Account;
import com.bo.demo.entity.User;
import com.bo.demo.service.BankService;
import com.bo.demo.service.UserService;

/**
 * @Description 基于TransactionInterceptor的声明式事务管理 测试
 * @author 王博
 * @version 2017年9月5日　下午11:07:39
 */
public class InterceptorBankServiceImplTest {

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

	@Test
	public void test() throws Exception {
		BankService bankService = (BankService) ctx.getBean("bankService");
		boolean result = bankService.transfer(1, 2, 10);// 转账成功或失败
		assertEquals("转账事务出错!", true, result);
		
		/** spring-tx-3.2  测试专用 */
		UserService userService = (UserService) ctx.getBean("userService");
		User u1 = new User(1,"bo","2013455",new Account(1,100));
		User u2 = new User(2,"lin","lin", new Account(2,100));
		boolean result2 = userService.batchUpdateUser(u1, u2);
		assertEquals("批量更新用户事务出错!", true, result2);
	}

}
