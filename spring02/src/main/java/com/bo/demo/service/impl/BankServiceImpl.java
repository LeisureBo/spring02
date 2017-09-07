package com.bo.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.bo.demo.dao.BankDao;
import com.bo.demo.entity.Account;
import com.bo.demo.service.BankService;

/**
 * @Description 简单实现
 * @author 王博
 * @version 2017年9月5日　下午9:53:56
 */
public class BankServiceImpl implements BankService {

	private BankDao bankDao;
	
	private String testStr;
	
	/**
	 * 
	 */
	public BankServiceImpl() {
	}

	@Override
	public boolean transfer(int fromId, int toId, double amount) throws FileNotFoundException {
		String msg = "您输入的账号有误!";
		boolean result = false;
		try {
			Account from = bankDao.queryAccountById(fromId);
			Account to = bankDao.queryAccountById(toId);
			if(from != null && to != null){
				msg = "账号'"+fromId+"'余额不足!";
				if(from.getAmount() > amount){
					from.setAmount(from.getAmount() - amount);
					to.setAmount(to.getAmount() + amount);
					bankDao.updateAccount(from);
					bankDao.updateAccount(to);
					msg = "账号'"+fromId+"'成功给账号'"+toId+"'转账 "+"$"+amount;
					/*默认:unchecked异常和error会导致回滚,checked异常不会到导致回滚*/
					int i = 1 / 0;
//					testStr.toString();
//					FileInputStream fis = new FileInputStream(new File("D://123.txt"));
					result = true;
				}
			}
			
		} catch(IncorrectResultSizeDataAccessException e){ // 查询数据为空
//			System.out.println("查询为空!");
		}
		System.out.println(msg);
		return result;
	}

	@Override
	public void doComplexLogic() {
	}

	public BankDao getBankDao() {
		return bankDao;
	}

	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}

}
