package com.bo.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bo.demo.entity.User;

/**
 * @Description 
 * @author 王博
 * @version 2017年9月6日　下午1:34:42
 */
public class UserDao implements RowMapper<User>{
	
	private BankDao bankDao;
	private JdbcTemplate jdbcTemplate;

	public int insertUser(User user){
		String sql = "insert into user(username,password,account_id) values(?)";
		return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getAccount().getId());
	}
	
	public int deleteUserById(int id){
		String sql = "delete from user where id=?";
		return jdbcTemplate.update(sql, id);
	}
	
	public int updateUser(User user){
		StringBuffer sql = new StringBuffer("update user set ");
		if(user.getUsername() != null){
			sql.append("username='").append(user.getUsername()+"',");
		}
		if(user.getPassword() != null){
			sql.append("password='").append(user.getPassword()+"',");
		}
		if(user.getAccount() != null && user.getAccount().getId() > 0){
			sql.append("account_id='").append(user.getAccount().getId()+"',");
		}
		sql.deleteCharAt(sql.length() - 1);//删除最后一个逗号
		sql.append(" where id='").append(user.getId()+"'");
		return jdbcTemplate.update(sql.toString());
	}
	
	public User queryUserById(int id){
		String sql = "select * from user where id=?";
		 return jdbcTemplate.queryForObject(sql, this, id);
	}

	public List<User> queryAllUser(){
		String sql = "select * from user";
		return jdbcTemplate.query(sql,this);
	}
	
	@Override
	public User mapRow(ResultSet rs, int rownum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setAccount(bankDao.queryAccountById(rs.getInt("account_id")));
		return user;
	}

	public BankDao getBankDao() {
		return bankDao;
	}

	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
