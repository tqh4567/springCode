package com.spring.config.annotation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring.config.annotation.service.BookService;
/**自动装配时AutowiredAnnotationBeanPostProcessor来解析注解，这个类来进行自动装配的
 * 
 * @Autowired:自动注入（属于spring规范）
 * 1.默认在容器中按类型去找：如果找到就注入.相当于context.getBean(BookService.class)
 * 2.如果存在多个对象，就会取按照名称取找。 context.getBean("bookService")
 * 3.@Qualifier("bookService"),当存在多个对象时可以制定加载对象的ID，而不是属性名
 * 4.自动装配一定要将属性赋值好，即容器中存在需要注入的Bean如果不存在就会报错；如果找不到也可以让其继续执行，可以在@Autowired(required=false)
 * 5.@Primary来设置首选加载对象，当多个Bean时，默认加载首选Bean
 *
 *@Resource:自动注入（jsr250）规范里面属于Java规范
 *	@Resource默认按照属性名称进行注入；可以用@Resource(name="需要装配的属性名")进行更改默认的装配；
 *	没有首选装配的功能；也没有@Autowired(required=false)的功能
 *
 *@Inject:自动注入（jsr330）里面属于Java规范.需要另外的javax.inject的包的支持
 *@Inject可以进行自动装配，并且支持@Autowired所包含的功能（@Qualifier、@Primary）
 *没有@Autowired(required=false)的功能
 *
 *
 *@Autowired：作用范围：构造器、参数、属性、方法、
 *方法上：@Bean+方法参数：参数从容其中获取，默认不加Autowired：都能自动装配
 *构造器：如果一个类只有一个有参构造器，Autowired默认不加，可以从容器中自动获取
 *参数：
 *
 *
 *4自定义组件想要使用Spring容器底层的一些组件（applicationcontext、BeanFactory、xxx）
	自定义组件需要实现xxxAware接口：在创建对象的时候，会调用接口规定的方法注入组件
	将spring的底层组件注入到自定义的Bean中（参见Dog）
	xxxAware功能使用xxxProcessor进行实现：例如：
	ApplicationContextAware===》ApplicationContextAwareProcessor
 */

@ComponentScan(value= {"com.spring.config.annotation.dao","com.spring.config.annotation.controller","com.spring.config.annotation.service"})
@Configuration

public class AutowiredConfig {

}
