package com.spring.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;

import com.spring.config.annotation.test.ColorFactory;
import com.spring.config.annotation.test.MyImportBeanDefinitionRegistrar;
import com.spring.config.annotation.test.MyImportSelector;
import com.spring.pojo.Color;
import com.spring.pojo.Red;
/**
 * 注入bean的方式      1、通过包扫描可以将带有Controller、Service、@Repository的类加载到容器【只能用于自己所创建的类，对于第三方类不适用】
 * 				2、@Bean注解注入，常用于第三方包
 * 				3、@Import：可以快速的给容器中导入一个组件，有以下三种方法：
 *@Import(value = { Color.class })参数是需要注入Bean的类的类型，注入Bean的默认ID是类的全类名
 *		ImportSelector接口，可以自定义自己的选择规则，将规则传递到value={}数组
 *		ImportBeanDefinitionRegistrar接口可以自定义添加一些组件，手动注册Bean
 *		使用spring提供的FactoryBean（工厂Bean），进行注册Bean
 */

@Configuration
@Import(value = { Color.class,Red.class,MyImportSelector.class ,MyImportBeanDefinitionRegistrar.class})

public class ImportConfig {
	//使用spring提供的FactoryBean（工厂Bean），进行注册Bean
	//默认工厂BEAN创建的是getObject（）方法所创建的对象类型
	//如果想要获取工厂Bean的类型可以在id前加上一个‘&’
	@Bean
	public ColorFactory getFactoryBean() {
		return new ColorFactory();
	}
}
