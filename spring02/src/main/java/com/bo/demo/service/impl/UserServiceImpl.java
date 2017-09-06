package com.bo.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.bo.demo.dao.UserDao;
import com.bo.demo.entity.User;
import com.bo.demo.service.UserService;

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
//		int i = 1 / 0;
//		testStr.toString();
		int ret2 = userDao.updateUser(u2);
		FileInputStream fis = new FileInputStream(new File("D://123.txt"));
		if(ret1 == 1 && ret2 == 1){
			System.out.println("批量更新成功!");
			return true;
		}
		System.out.println("批量更新失败!");
		return false;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
