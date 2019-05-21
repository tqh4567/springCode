package com.spring.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.spring.config.annotation.service.BookService;
import com.spring.pojo.Person;

@Configuration
public class ScopeConfigration {
	/**
	 * 注入的类id默认是方法名，如果想要修改，可以在Bean（value=""）进行修改
	 * @return
	 * @Bean:的注入类型默认是单实例的
	 * 即容器中只会注入一个person对象，如果想要改变就需要@Scope指定范围：
	 * singleton（默认）：单例存在于spring容器。在此默认情况下IOC容器一旦启动就会调用方法创建实例放到ioc容器，以后获取就从ioc容器中获取
	 * prototype：多例存在。IOC容器启动时不会调用方法创建实例，放到ioc容器，只有获取对象的时候就会创建，且每次获取都会创建一个不同的实例
	 * SCOPE_REQUEST：同一次请求创建一个实例；
	 * SCOPE_SESSION：同一个session创建一个实例
	 * 类似于Bean标签中含有的scope属性可以取到以上4种值
	 * 
	 * 懒加载（延迟加载）：
	 * 懒加载时针对单例情况下的，指Ioc容器创建时不会立即创建实例，只有到首次使用的时候才会创建，ioc容器种只有一个实例
	 */
//	@Scope("prototype")
	@Lazy
	@Bean(value="person")
	public Person person() {
		System.out.println("给容器中添加person。。。。。。");
		Person person=new Person("李四",28);
		return person;
	}
	
}
