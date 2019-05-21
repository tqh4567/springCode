package com.spring.config.annotation;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.spring.pojo.Color;

/**
 *@Profile注解的用法，用于多环境下Bean的切换
 *以java环境下多数据源的配置来进行演示
 *@Profile:指定组件在哪个环境下才能进行注册；不指定，在任何环境下都会注册
 *加有环境标识的组件只有满足条件才会被加载到容器中；默认情况下值default环境
 *也可以加到类上：只有是指定环境下，配置类里面的组件才会加载
 *没有标注的Bean在任何环境下都会加载
 *
 */
@Configuration
@PropertySource(value = { "classpath:/db.properties" })

public class ProfileConfig {
	
	
	@Profile("test")
	@Bean
	public Color color() {
		return new Color();
	}
	
	@Profile("test")
	@Bean("dataSourceTest")
	public DataSource dataSourceTest() throws PropertyVetoException {
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		dataSource.setUser("${jdbc.username}");
		dataSource.setPassword("${jdbc.password}");
		dataSource.setJdbcUrl("${jdbc.url.test}");
		dataSource.setDriverClass("${jdbc.driver}");
		
		return dataSource;
	}
	@Profile("dev")
	@Bean("dataSourceDev")
	public DataSource dataSourceDev() throws PropertyVetoException {
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		dataSource.setUser("${jdbc.username}");
		dataSource.setPassword("${jdbc.password}");
		dataSource.setJdbcUrl("${jdbc.url.dev}");
		dataSource.setDriverClass("${jdbc.driver}");
		
		return dataSource;
	}
	
	@Profile("pro")
	@Bean("dataSourcePro")
	public DataSource dataSourcePro() throws PropertyVetoException {
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		dataSource.setUser("${jdbc.username}");
		dataSource.setPassword("${jdbc.password}");
		dataSource.setJdbcUrl("${jdbc.url.pro}");
		dataSource.setDriverClass("${jdbc.driver}");
		
		return dataSource;
	}
}
