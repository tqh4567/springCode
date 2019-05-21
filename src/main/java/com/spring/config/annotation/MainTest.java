package com.spring.config.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.pojo.Person;

public class MainTest {
	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(MyConfigration.class);
		Person person = context.getBean(Person.class);
		//获得容器中类的名称
		String[] names = context.getBeanDefinitionNames();
		//根据类名获得Bean在Spring容器中的名称
		//context.getBeanNamesForType(Person.class);
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println(person);
	}
}
