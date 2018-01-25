package com.bo.spring.dao;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bo.spring.entity.Account;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月4日　下午9:45:34
 */
public class BankDao implements RowMapper<Account>{

	private JdbcTemplate jdbcTemplate;
	
	public BankDao() {
	}

	public int insertAccount(Account account){
		String sql = "insert into account(amount) values(?)";
		return jdbcTemplate.update(sql, account.getAmount());
	}
	
	public int deleteAccountById(int id){
		String sql = "delete from account where id=?";
		return jdbcTemplate.update(sql, id);
	}
	
	public int updateAccount(Account account){
		String sql = "update account set amount=? where id=?";
		return jdbcTemplate.update(sql, account.getAmount(), account.getId());
	}
	
	public Account queryAccountById(int id){
		String sql = "select * from account where id=?";
		 return jdbcTemplate.queryForObject(sql, this, id);
	}

	public List<Account> queryAllAccount(){
		String sql = "select * from account";
		return jdbcTemplate.query(sql,this);
	}
	
	@Override
	public Account mapRow(ResultSet rs, int rownum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setAmount(rs.getDouble("amount"));
		return account;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
