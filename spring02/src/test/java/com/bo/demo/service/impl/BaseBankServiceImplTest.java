package com.bo.demo.service.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.demo.service.BankService;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月4日　下午10:58:29
 */
public class BaseBankServiceImplTest {

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

	/**
	 * Test method for {@link com.bo.demo.service.impl.BaseBankServiceImpl#transfer(int, int, double)}.
	 */
	@Test
	public void testTransfer() {
		BankService bankService = (BankService) ctx.getBean("baseBankService");
		boolean result = bankService.transfer(2, 1, 50);// 转账成功或失败
		assertEquals("转账事务出错!", true, result);
	}

}
