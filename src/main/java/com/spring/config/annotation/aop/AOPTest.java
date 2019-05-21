package com.spring.config.annotation.aop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试AOP相关的功能
 * @author tqh4567
 *
 */
public class AOPTest {
	@Test
	public void myAOPTest() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyAOPConfig.class);
		//不要自己创建new一个MyDiv，要去容器中去取
		MyDiv myDiv = context.getBean(MyDiv.class);
		int i = myDiv.div(2, 0);
		
		System.out.println("=========================");
		
//		String[] names = context.getBeanDefinitionNames();
//		for (String name : names) {
//			System.out.println(name);
//			
//		}
	}
}
