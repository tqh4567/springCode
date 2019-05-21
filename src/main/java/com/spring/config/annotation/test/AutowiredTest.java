package com.spring.config.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.annotation.AutowiredConfig;
import com.spring.config.annotation.service.BookService;

public class AutowiredTest {
	@SuppressWarnings("unused")
	@Test
	public void autowiredTest() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AutowiredConfig.class);
		BookService bookService = (BookService) context.getBean("bookService");
		BookService bookService2 = (BookService) context.getBean(BookService.class);
		System.out.println(bookService);
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
}
