package com.spring.config.annotation.trans;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert() {

		String sql="insert into user (name,age) values(?,?)";
		jdbcTemplate.update(sql, "tom",19);
	}
}
