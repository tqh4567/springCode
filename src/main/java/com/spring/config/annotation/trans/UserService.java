package com.spring.config.annotation.trans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void insertUser() {
		userDao.insert();
		System.out.println("插入成功。。。。。");
		int i=1/0;
	}

}
