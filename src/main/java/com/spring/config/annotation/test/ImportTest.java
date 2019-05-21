package com.spring.config.annotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.annotation.ImportConfig;

public class ImportTest {
	@Test
	public void importTest() {
		ApplicationContext context=new AnnotationConfigApplicationContext(ImportConfig.class);
		String[] names = context.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
		//获取getFactoryBean的类型
		//获取到Bean的类型为。。。。。class com.spring.pojo.Color;
		//调用的是getObject（）方法所创建的对象类型，如果想要获取工厂Bean的类型可以在id前加上一个‘&’
		Object bean = context.getBean("getFactoryBean");
		System.out.println("获取到Bean的类型为。。。。。"+bean.getClass());
		//如果想要获取工厂Bean的类型可以在id前加上一个‘&’
		//class com.spring.config.annotation.test.ColorFactory返回的为全类名
		Object bean2 = context.getBean("&getFactoryBean");
		System.out.println(bean2.getClass());
	}
}
