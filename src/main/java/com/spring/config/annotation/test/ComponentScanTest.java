package com.spring.config.annotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.annotation.MyConfigration;

public class ComponentScanTest {
	/**
	 * 测试包扫描
	 */
	@SuppressWarnings("resource")
	@Test
	public void testComponentScan() {
		ApplicationContext context=new AnnotationConfigApplicationContext(MyConfigration.class);
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
}
