package com.bo.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bo.demo.dao.BankDao;
import com.bo.demo.entity.Account;
import com.bo.demo.service.BankService;

/**
 * @Description 基于 TransactionTemplate 的编程式事务管理
 * @author 王博
 * @version 2017年9月5日　下午1:54:17
 */
public class TemplateBankServiceImpl implements BankService {

	private BankDao bankDao;
	private TransactionTemplate transactionTemplate;
	
	@Override
	public boolean transfer(final int fromId, final int toId, final double amount) {
		return transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus txStatus) {
				/*这里可以使用默认的事务提交和回滚规则，这样在业务代码中就不需要显式调用任何事务管理的 API*/
				/**
				 * 根据默认规则，如果在执行回调方法的过程中抛出了未检查异常，
				 * 或者显式调用了TransacationStatus.setRollbackOnly()方法，则回滚事务；
				 * 如果事务执行完成或者抛出了 checked 类型的异常，则提交事务。
				 * */
				String msg = "您输入的账号有误!";
				boolean result = false;
				try {
					Account from = bankDao.queryAccountById(fromId);
					Account to = bankDao.queryAccountById(toId);
					if(from != null && to != null){
						msg = "账号'"+fromId+"'余额不足!";
						if(from.getAmount() >= amount){
							from.setAmount(from.getAmount() - amount);
							to.setAmount(to.getAmount() + amount);
							bankDao.updateAccount(from);
							bankDao.updateAccount(to);
							msg = "账号'"+fromId+"'成功给账号'"+toId+"'转账 "+"$"+amount;
//							int i = 1 / 0;
							FileInputStream fis = new FileInputStream(new File("D://123.txt"));
							result = true;
						}
					}
					
				} catch(IncorrectResultSizeDataAccessException e){ // 查询数据为空
//					System.out.println("查询为空!");
				} catch (Exception e) {// 捕获所有异常(checked、unchecked)
					e.printStackTrace();
					txStatus.setRollbackOnly();// 将事务标识为回滚的，以执行事务回滚
					msg = "Transfer Error!";
				}
				System.out.println(msg);
				return result;
			}
			
		});
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

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
}
