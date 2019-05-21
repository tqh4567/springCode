package com.spring.config.annotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.spring.config.annotation.ConditionalConfig;



public class ConditionalTest {
	/**
	 * 测试Conditional注解
	 */
	@Test
	public void conditionalTest() {
		ApplicationContext context=new AnnotationConfigApplicationContext(ConditionalConfig.class);
		//获取当前运行环境的定义
		Environment environment = context.getEnvironment();
		//获取运行的操作系统信息
		String osname = environment.getProperty("os.name");
		System.out.println(osname);
		String[] names = context.getBeanDefinitionNames();
		for (String  name : names) {
			System.out.println(name);
		}
	}
}
