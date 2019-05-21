package com.spring.config.annotation.extra;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyBeanFactoryPostProcessorTest {
	@Test
	public void testBeanFactory() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ExtraConfig.class);
		//发布事件
		context.publishEvent(new ApplicationEvent(new String("我发布的事件。。。")) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		});
		context.close();
	}
}
