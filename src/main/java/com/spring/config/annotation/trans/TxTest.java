package com.spring.config.annotation.trans;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class TxTest {
	@Test
	public void insertTest() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TxConfig.class);
		UserService userService =  context.getBean(UserService.class);
		userService.insertUser();
		context.close();
	}
	@Test
	public void beansTest() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TxConfig.class);
		String[] names = context.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
		context.close();
	}
}
