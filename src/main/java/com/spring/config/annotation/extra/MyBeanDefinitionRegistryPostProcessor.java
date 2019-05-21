package com.spring.config.annotation.extra;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import com.spring.pojo.Car;
/**
 * 用来测试postProcessBeanDefinitionRegistry的执行时机
 * @author tqh4567
 *
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("MyBeanDefinitionRegistryPostProcessor======>postProcessBeanFactory............Bean的数量"+beanFactory.getBeanDefinitionCount());
	}
	//bean的注册信息保存在BeanDefinitionRegistry
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("MyBeanDefinitionRegistryPostProcessor======>postProcessBeanDefinitionRegistry............Bean的数量"+registry.getBeanDefinitionCount());
		//自定义给容器中添加Bean的注册信息
		RootBeanDefinition definition = new RootBeanDefinition(Car.class);
		//或者使用
		//BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Car.class);
		registry.registerBeanDefinition("hello", definition);
	}

}
