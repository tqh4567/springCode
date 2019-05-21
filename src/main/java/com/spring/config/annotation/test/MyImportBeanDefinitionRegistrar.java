package com.spring.config.annotation.test;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.spring.pojo.Rainbow;
import com.spring.pojo.Red;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
	//添加自定义的bean
	/**
	 * AnnotationMetadata:当前类的注册信息
	 * BeanDefinitionRegistry：BeanDefinition的注册类，把所需要添加到容器中的Bean：BeanDefinitionRegistry.registryBeanDefinition()
	 * 方法进行手工注册
	 */
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean definition = registry.containsBeanDefinition("com.spring.pojo.Red");
		boolean definition1 = registry.containsBeanDefinition("com.spring.pojo.Blue");
		if(definition1&&definition) {
			//制定Bean的定义信息（类型、scop等）
			RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
			//制定Bean的名称，并且注册Bean
			registry.registerBeanDefinition("rainbow", beanDefinition);
		}
		
	}

}
