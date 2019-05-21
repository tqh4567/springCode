package com.spring.pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;
@Component
public class Dog implements ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware,EnvironmentAware{
	
	private ApplicationContext applicationContext;
//	Aware
	public  Dog() {
		System.out.println("Dog ......constructor.........");
	}
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct a Dog........");
		
	}
	@PreDestroy
	public void destroy() {
		System.out.println("@PreDestroy a Dog........");
		
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
		System.out.println("传入的application"+applicationContext);
		
	}
	public void setBeanName(String name) {
		System.out.println("BeanAware的name是==》"+name);
		
	}
	//EmbeddedValueResolverAware是解析表达式
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		// TODO Auto-generated method stub
		String string = resolver.resolveStringValue("这个是传入的${os.name},#{20*3}");
		System.out.println(string);
	}
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		String string = environment.getProperty("os.name");
		System.out.println(string);
		
		
	}
}
