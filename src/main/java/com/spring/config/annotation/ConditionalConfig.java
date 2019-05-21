package com.spring.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.spring.config.annotation.condition.LinuxCondition;
import com.spring.config.annotation.condition.WinCondition;
import com.spring.pojo.Person;

@Configuration
//放在类上，类满足条件时返回类里面定义Bean
@Conditional(value = { WinCondition.class })
public class ConditionalConfig {
	/**
	 * @Conditional:按照条件向容器中注入Bean
	 * 放在方法上按照condition注入方法返会的Bean
	 * 放在类上，类满足条件时返回类里面定义Bean
	 * @Conditional(value = { 条件列表 })
	 *如果是windo系统就注入bill，如果是Linux系统就注入linus(可以修改运行参数-Dos.name=linux设置系统参数为linux系统)
	 */
	
//	@Conditional(value = { WinCondition.class })
	@Bean("bill")
	public Person person1() {
		return new Person("bill Gates",62);
	}
//	@Conditional(value = { LinuxCondition.class })
	@Bean("linus")
	public Person person2() {
		return new Person("linus",48);
	}
}
