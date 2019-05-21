package com.spring.config.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.spring.config.annotation.ValueConfig;
import com.spring.pojo.Person;

public class ValueTest {
	
	@Test
	public void valueTest() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ValueConfig.class);
		Person person = (Person) context.getBean("person");
		System.out.println(person);
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		//可以通过context获取运行环境，来获取properties中的属性值
		ConfigurableEnvironment environment = context.getEnvironment();
		
		String string = environment.getProperty("PERSON_BIRTHDAY");
		System.out.println(string);
	}
}
