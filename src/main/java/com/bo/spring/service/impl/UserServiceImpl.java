package com.bo.spring.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bo.spring.dao.UserDao;
import com.bo.spring.entity.User;
import com.bo.spring.service.UserService;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月6日　下午2:08:56
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	private String testStr;
	
	@Override
	public boolean batchUpdateUser(User u1, User u2) throws FileNotFoundException {
		int ret1 = userDao.updateUser(u1);
		int i = 1 / 0;
//		testStr.toString();
		int ret2 = userDao.updateUser(u2);
//		FileInputStream fis = new FileInputStream(new File("D://123.txt"));
//		testStr.toString();
		if(ret1 == 1 && ret2 == 1){
			System.out.println("批量更新成功!");
			return true;
		}
		System.out.println("批量更新失败!");
		return false;
	}

	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean txUpdateUser(User u1, User u2) throws FileNotFoundException {// 事务性
		int ret1 = userDao.updateUser(u1);// 因后面的异常而回滚
//		int i = 1 / 0;// 抛出运行时异常，事务回滚
		FileInputStream fis = new FileInputStream(new File("D:/test/a.txt"));// 抛出编译时异常,若指定@Transactional(rollbackFor=Exception.class),则事务回滚;
		int ret2 = userDao.updateUser(u2);// 未执行
		if(ret1 == 1 && ret2 == 1){
			System.out.println("txUpdateUser success!");
			return true;
		}
		System.out.println("txUpdateUser failed!");
		return false;
	}
	
	@Transactional
	@Override
	// 事务性操作，不可catch Exception或RuntimeException而不抛出，导致外围框架捕获不到异常，认为执行正确而提交。
	public boolean txUpdateUserAndCatch(User u1, User u2){  
		try {
			int ret1 = userDao.updateUser(u1);// 执行成功
			int i = 1 / 0;// 运行时异常
			int ret2 = userDao.updateUser(u2);// 未执行
			if(ret1 == 1 && ret2 == 1){
				System.out.println("txUpdateUserAndCatch success!");
				return true;
			}
		} catch (Exception e) { // 所有异常被捕获而未抛出
			// 在此手动抛出[非编译性异常]使spring接收到异常可回滚
//			throw e;
			// 或者手动回滚
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		System.out.println("txUpdateUserAndCatch failed!");
		return false;
	}
	
	// 非事务方法中调用事务方法测试:事务不会回滚
	@Override
	public boolean NonTransactionUpdate(User u1, User u2){
		// 1.txUpdate方法内发生异常,事务没有回滚.
//		return this.txUpdateUser(u1, u2);
		// 2.txUpdateUserAndCatch方法内捕获并抛出异常，事务没有回滚.
		// 3.txUpdateUserAndCatch方法catch中手动回滚事务失败:org.springframework.transaction.NoTransactionException: No transaction aspect-managed TransactionStatus in scope
		return this.txUpdateUserAndCatch(u1, u2);
	}
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
