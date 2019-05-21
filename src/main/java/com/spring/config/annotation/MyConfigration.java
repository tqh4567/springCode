package com.spring.config.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.spring.config.annotation.service.BookService;
import com.spring.pojo.Person;

@Configuration
//@ComponentScan(value="com.spring.config.annotation")//使用注解方式来实现包扫描ComponentScan为重复注解，在jdk8的环境下同一个类上可以多次使用
//如果不是Java8，可以使用ComponentScans注解value={}在数组内添加多个ComponentScan
/**
 * 实现过滤，进行排除
 * @author tqh4567
 *excludeFilters:的参数为Filter[]即为Filter数组；
 *Filter：里面的排除规则照类来排除；排除的规则可以
 *ANNOTATION：注解排除
 *ASSIGNABLE_TYPE：类型排除
 *ASPECTJ和ASPECTJ表达式进行排除；也可以自定义排除：CUSTOM
 *classes：可以写单个classes=xxx.class，如果要排除多个可以写成数组的形式classes={}
 */
//@ComponentScan(value="com.spring.config.annotation",excludeFilters= {
//		@Filter(type=FilterType.ANNOTATION,classes= {Controller.class})
//})

/**
 * 实现过滤，进行包含
 * @author tqh4567
 *includeFilters:的参数为Filter[]即为Filter数组；
 *Filter：里面的包含规则照类来包含；
 *ANNOTATION：注解排除
 *ASSIGNABLE_TYPE：类型排除
 *ASPECTJ和ASPECTJ表达式进行排除；也可以自定义排除：CUSTOM
 *classes：可以写单个classes=xxx.class，如果要排除多个可以写成数组的形式classes={}
 *注意：如果在@ComponentScan中没有添加useDefaultFilters=false则包含规则不会生效，因为ComponentScan的默认包含为true
 */
@ComponentScan(value="com.spring.config.annotation",includeFilters= {
		/*@Filter(type=FilterType.ANNOTATION,classes= {Controller.class}),
		@Filter(type=FilterType.ASSIGNABLE_TYPE,classes= {BookService.class}),*/
		@Filter(type=FilterType.CUSTOM,classes= {MyTypeFilter.class})
},useDefaultFilters=false)
public class MyConfigration {
	/**
	 * 注入的类id默认是方法名，如果想要修改，可以在Bean（value=""）进行修改
	 * @return
	 */
	@Bean(value="person")
	public Person person() {
		Person person=new Person("张三",18);
		return person;
	}
	
}
