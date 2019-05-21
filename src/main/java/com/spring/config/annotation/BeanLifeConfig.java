package com.spring.config.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.spring.pojo.Car;
/**
 * 用来研究Bean的生命周期
 * 从创建-》初始化-》销毁这些都是由容器自动管理
 * 
 * 
 * 
 * 1、定义一个自定义的初始化和销毁方法。init-method和destroy-method
 * 传统xml为：
 * <bean id="collection" class="com.spring.pojo.Collection" init-method="" destroy-method="">
 * 
 * @author tqh4567
 * 构造：
 * 单例模式：ioc容器已启动就会创建Bean
 * 多例模式：用到Bean时就会创建，
 * 
 * 初始化：
 * 当所要的数据准备好了以后就会执行。。。
 * 
 * 销毁：
 * 单实例：当容器关闭的时候就会销毁
 * 多实例：容器不会管理多实例bean，需要我们手动销毁
 * 
 * 2.通过实现InitializingBean,DisposableBean接口来实现销毁的方法（参见pojo里面的Cat）
 * 
 * 3.使用JSR250规范下的注解(参见Dog)
 * 	@PostConstruct:此注解只能作用在方法上；当属性赋值完成我们就进行初始化
 * 	@PreDestroy:在容器销毁之前，进行通知清理工作
 * 4.BeanPostProcessor【接口】：
 * 
 * 原理：【
 * 遍历容器中的BeanPostProcessor，挨个执行BeforeInitialization方法，一旦方法返回null值，后边的后置处理器就不会被执行
 * 
 * populateBean(beanName, mbd, instanceWrapper);给bean进行赋值
 * InitializateBean:初始化Bean（包括以下三步）
 * {
 * 	applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);前置处理方法
 * 	遍历容器中的BeanPostProcessor，挨个执行BeforeInitialization方法，一旦方法返回null值，后边的后置处理器就不会被执行
 * 	initializeBean(beanName, exposedObject, mbd);执行自定义初始化方法
 * 	applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);后置处理方法
 * }
 * 】
 * 会遍历容器中所有的BeanPostProcessor，挨个执行BeforeInitialization方法，一旦返回null值，后边的后置处理器就会终止执行postProcessAfterInitialization
 * 初始化之前进行后置处理
 * 	postProcessBeforeInitialization(Object bean, String beanName);
 * 初始化之后进行后置处理
 * 	postProcessAfterInitialization(Object bean, String beanName)
 */	

@Configuration
@ComponentScan(value="com.spring.pojo")
public class BeanLifeConfig {
//	@Scope(("prototype"))
//	@Bean(initMethod="init",destroyMethod="destroy")
	
	public Car car() {
		return new Car();
		
	}

}
