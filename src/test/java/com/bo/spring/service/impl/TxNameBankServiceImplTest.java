package com.bo.spring.service.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.spring.entity.Account;
import com.bo.spring.entity.User;
import com.bo.spring.service.BankService;
import com.bo.spring.service.UserService;

/**
 * @Description 基于 <tx>命名空间的声明式事务管理 测试
 * @author 王博
 * @version 2017年9月6日　上午10:08:01
 */
public class TxNameBankServiceImplTest {

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
		boolean result = bankService.transfer(2, 1, 10);// 转账成功或失败
		assertEquals("转账事务出错!", true, result);
		
		UserService userService = (UserService) ctx.getBean("userService");
		User u1 = new User(1,"bo","1111",new Account(1,100));
		User u2 = new User(2,"lin","1809", new Account(2,100));
		boolean result2 = userService.batchUpdateUser(u1, u2);
		assertEquals("批量更新用户事务出错!", true, result2);
	}

}
