package com.spring.config.xml;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.pojo.Collection;
import com.spring.pojo.CollectionPojo;
import com.spring.pojo.Role;
import com.spring.pojo.User;

public class CollectionTest {
	@Test
	public void getCollection() {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/collection.xml");
//		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/collection.xml");
		Collection collection = (Collection) context.getBean("collection");
		System.out.println(collection);
	}
	
	@Test
	public void getCollectionPojo() {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/collectionpojo.xml");
//		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/xml/collection.xml");
		CollectionPojo collection = (CollectionPojo) context.getBean("collectionpojo");
		List<Role> list = collection.getList();
		for (Role role : list) {
			System.out.println(role.getRole_name());
		}
		Map<Role, User> map = collection.getMap();
		System.out.println(map);
		
		Set<Role> set = collection.getSet();
		for (Role role : set) {
			System.out.println(role.getRole_name());
		}
		System.out.println(collection);
	}
}
