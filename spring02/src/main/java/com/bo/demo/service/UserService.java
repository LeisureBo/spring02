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
}
