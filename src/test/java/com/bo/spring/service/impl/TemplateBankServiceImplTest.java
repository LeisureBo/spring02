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

import com.bo.spring.service.BankService;

/**
 * @Description 基于TransactionTemplate的编程式事务管理 测试
 * @author 王博
 * @version 2017年9月5日　下午2:18:29
 */
public class TemplateBankServiceImplTest {

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
	 * Test method for {@link com.bo.spring.service.impl.TemplateBankServiceImpl#transfer(int, int, double)}.
	 * @throws Exception 
	 */
	@Test
	public void testTransfer() throws Exception {
		BankService templateBankService = (BankService) ctx.getBean("templateBankService");
		boolean result = templateBankService.transfer(1, 2, 10);
		assertEquals("转账出错", true, result);
	}

}
