package com.spring.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
/**
 * 后置处理器
 * @author tqh4567
 *
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor{
	//bean容器刚创建的实例
	//beanName实例的名字
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("postProcessBeforeInitialization........."+beanName+"=>"+bean);
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization........."+beanName+"=>"+bean);
		// TODO Auto-generated method stub
		return bean;
	}

}
