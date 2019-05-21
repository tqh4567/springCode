package com.spring.config.annotation.extra;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor的postProcessBeanFactory方法执行了。。。。。");
		int count = beanFactory.getBeanDefinitionCount();
		System.out.println("容器中有"+count+"Bean");
		
		String[] strings = beanFactory.getBeanDefinitionNames();
		for (String string : strings) {
			System.out.println(string);
			
		}
		
	}

}
