package com.bo.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.bo.demo.dao.BankDao;
import com.bo.demo.entity.Account;
import com.bo.demo.service.BankService;

/**
 * @Description 基于底层 API的编程式事务管理
 * @author 王博
 * @version 2017年9月4日　下午10:40:56
 */
public class BaseBankServiceImpl implements BankService {
	
	private BankDao bankDao;
	private TransactionDefinition txDefinition;
	private PlatformTransactionManager txManager;

	public BaseBankServiceImpl() {
	}

	@Override
	public boolean transfer(int fromId, int toId, double amount) {
		TransactionStatus txStatus = txManager.getTransaction(txDefinition);// 开启一个事务
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
//					int i = 1 / 0;
					FileInputStream fis = new FileInputStream(new File("D://123.txt"));
					txManager.commit(txStatus);// 提交事务(一定要在所有业务代码完成后执行,提交事务后发生异常事务不会回滚)
					result = true;
				}
			}
			
		} catch(IncorrectResultSizeDataAccessException e){ // 查询数据为空
//			System.out.println("查询为空!");
		} catch (Exception e) {// 捕获所有异常(checked、unchecked、error)并回滚事务
			e.printStackTrace();
			txManager.rollback(txStatus);// 回滚事务
			msg = "Transfer Error!";
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

	public TransactionDefinition getTxDefinition() {
		return txDefinition;
	}

	public void setTxDefinition(TransactionDefinition txDefinition) {
		this.txDefinition = txDefinition;
	}

	public PlatformTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

}
