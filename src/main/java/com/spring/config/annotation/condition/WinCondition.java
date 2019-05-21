package com.spring.config.annotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WinCondition implements Condition {
	/**
	 * 判断当前系统的操作环境是否是Windows
	 */

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//获取BeanFactory工厂
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		//获取类的加载器
		ClassLoader classLoader = context.getClassLoader();
		//获取当前的运行环境
		Environment environment = context.getEnvironment();
		String name = environment.getProperty("os.name");
		//获取注册的信息,可以获取到Bean也可以向容器中注入Bean
		BeanDefinitionRegistry registry = context.getRegistry();
		return name.contains("Windows");
	}

}
