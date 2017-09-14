package com.bo.spring.entity;

import java.io.Serializable;

/**
 * @Description 账号实体类
 * @author 王博
 * @version 2017年9月4日　下午9:38:55
 */
public class Account implements Serializable{

	private static final long serialVersionUID = -3541343239177392447L;
	private int id;
	private double amount;
	
	public Account() {
	}

	public Account(int id, double amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", amount=" + amount + "]";
	}
	
}
