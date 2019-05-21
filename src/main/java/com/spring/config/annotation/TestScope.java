package com.spring.config.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.pojo.Person;

public class TestScope {
	@Test
	public void getPerson() {
		ApplicationContext context=new AnnotationConfigApplicationContext(ScopeConfigration.class);
		System.out.println("ioc创建完成。。。。。。。");
//		Person person = (Person) context.getBean("person");
//		Person person1 = (Person) context.getBean("person");
//		System.out.println(person==person1);
		
	}
}
