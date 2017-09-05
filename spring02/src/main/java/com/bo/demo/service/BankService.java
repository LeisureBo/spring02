package com.bo.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月4日　下午10:38:30
 */
public interface BankService {
	public boolean transfer(int fromId, int toId, double amount) throws FileNotFoundException;
	public void doComplexLogic();
}
