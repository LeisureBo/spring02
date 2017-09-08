package com.bo.demo.service;

import java.io.FileNotFoundException;

import com.bo.demo.entity.User;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月6日　下午2:06:48
 */
public interface UserService {
	
	public boolean batchUpdateUser (User u1, User u2) throws FileNotFoundException;
	
	public boolean txUpdateUser(User u1, User u2) throws FileNotFoundException;// 注解事务测试1
	
	public boolean txUpdateUserAndCatch(User u1, User u2);// 注解事务测试2

	public boolean NonTransactionUpdate(User u1, User u2);// 注解非事务测试
}
