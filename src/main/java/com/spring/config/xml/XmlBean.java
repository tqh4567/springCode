package com.spring.config.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.pojo.Collection;
import com.spring.pojo.Person;

public class XmlBean {
	//测试通过xml构造器的方法进行注入bean
	@Test
	public void constructorTest() {
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/person.xml");
		Person person1 = (Person) context.getBean("person1");
		System.out.println(person1.getAge()+"========="+person1.getName());
	}
	
	//测试通过set方法进行注入bean
		@Test
		public void setTest() {
			@SuppressWarnings("resource")
			ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/person.xml");
			Person person2 = (Person) context.getBean("person2");
			System.out.println(person2);
		}
		//测试通过set方法进行注入bean
		@Test
		public void setRoleTest() {
			@SuppressWarnings("resource")
			ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/person.xml");
			Person person3 = (Person) context.getBean("person3");
			System.out.println(person3+person3.getRole().getRole_name());
		}
		@Test
		public void collectionTest() {
			@SuppressWarnings("resource")
			ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/collection.xml");
			Collection collection = (Collection) context.getBean("collection");
			System.out.println(collection);
		}
}
