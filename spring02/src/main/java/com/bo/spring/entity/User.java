package com.bo.spring.entity;

import java.io.Serializable;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月6日　下午1:32:03
 */
public class User implements Serializable{

	private static final long serialVersionUID = 7768628466197824365L;
	
	private int id;
	
	private String username;
	
	private String password;
	
	private Account account;
	
	public User() {
		super();
	}

	public User(int id, String username, String password, Account account) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", account=" + account + "]";
	}
	
}
