package com.spring.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.spring.pojo.Person;
//@PropertySource加载外部的配置文件
@Configuration
@PropertySource(value = { "classpath:/person.properties" })
public class ValueConfig {

	@Bean
	public Person person() {
		return new Person();
	}
}
