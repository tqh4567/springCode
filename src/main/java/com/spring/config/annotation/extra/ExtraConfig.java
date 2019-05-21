package com.spring.config.annotation.extra;

import java.util.EventListener;
import java.util.concurrent.Executor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import com.spring.pojo.Car;
/**
 * 扩展原理
 * BeanPostProcessor:Bean的后置处理器，再Bean创建对象初始化前后进行拦截工作
 * 
 * BeanFactoryPostProcessor:BeanFactory的后置处理器
 * 	执行时机：在BeanFactory标准初始化后调用（所有的Bean定义已经保存加载，但是Bean的实例还未创建）
 * 1）创建IOC容器
 * 2）invokeBeanFactoryPostProcessors(beanFactory);执行invokeBeanFactoryPostProcessors；
 * 		如何找到BeanFactoryPostProcessor的方法？
 * 		遍历容器中的所有BeanFactoryPostProcessor方法：beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);
 * 		找到后按照优先级进行排序
 * 		bean的创建方法为// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);
		而invokeBeanFactoryPostProcessors(beanFactory);在创建实例之前进行执行
 *=================================================================
 *BeanFactoryPostProcessor:BeanFactory的子接口BeanDefinitionRegistryPostProcessor
 *1、定义了一个新的方法：void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException；
 *	方法作用在应用程序上下文的标准初始化之后修改其内部bean定义注册表。所有常规bean定义都将要被加载，但还没有bean被实例化。这允许在下一个后处理阶段开始之前添加更多的bean定义。
 *	执行时机：所有Bean的实例的定义被加载，但是bean还未被初始化的时候进行加载
 *	
 *	BeanDefinitionRegistryPostProcessor优先于BeanFactoryPostProcessor的执行
 *	可以使用BeanDefinitionRegistryPostProcessor像容器中自定义添加新的组件定义，添加一个组件
 *
 *	---------------------------------Console的打印信息---------------------------------------------
 *	MyBeanDefinitionRegistryPostProcessor======>postProcessBeanDefinitionRegistry............Bean的数量10
	MyBeanDefinitionRegistryPostProcessor======>postProcessBeanFactory............Bean的数量11
	MyBeanFactoryPostProcessor的postProcessBeanFactory方法执行了。。。。。
	容器中有11Bean
	org.springframework.context.annotation.internalConfigurationAnnotationProcessor
	org.springframework.context.annotation.internalAutowiredAnnotationProcessor
	org.springframework.context.annotation.internalRequiredAnnotationProcessor
	org.springframework.context.annotation.internalCommonAnnotationProcessor
	org.springframework.context.event.internalEventListenerProcessor
	org.springframework.context.event.internalEventListenerFactory
	extraConfig
	myBeanDefinitionRegistryPostProcessor
	myBeanFactoryPostProcessor
	blue
	hello
 *	------------------------------------------------------------------------------------
 *	BeanDefinitionRegistryPostProcessor原理：
 *	1）创建IOC容器
 *	2）refresh（）===》invokeBeanFactoryPostProcessors(beanFactory)
 *		遍历容器获取所有的BeanDefinitionRegistryPostProcessor，依次触发postBeanDefinitionRegistry(),然后再触发BeanFactoryPostProcessor的postProcessBeanFactory(beanFactory);方法
 *	3）再来从容器中找到BeanPostProcessor的所有组件，依次触发postProcessorBeanFactory方法
 *
 *
 *====================================ApplicationListener=========================
 *ApplicationListener:			interface ApplicationListener<E extends ApplicationEvent> extends EventListener();
 *1、作用：监听容器中所发生的事件，事件驱动模型的开发
 *2、interface ApplicationListener<E extends ApplicationEvent> extends EventListener();
 *		监听ApplicationEvent及其以下的子类的事件
 *	步骤：
 *		1）写一个监听器取监听某个事件（ApplicationEvent及其子类参见UserService）
 *			@EventListener(classes= {需要监听的事件类})
 *			@EventListener原理参见EventListenerMethodProcessor
 *			EventListenerMethodProcessor：实现SmartInitializingSingleton接口，主要说一下SmartInitializingSingleton原理：
 *			【SmartInitializingSingleton原理】：
 *				1）创建IOC容器
 *				2）refresh（）===》finishBeanFactoryInitialization(beanFactory);初始化剩下的所有单实例Bean
 *					1、先创建Bean：getBean（），获Bean
 *					2、获取所有的单实例Bean，判断是否为SmartInitializingSingleton类型的，如果是就调用afterSingletonInstantiated（）方法
 *				publishEvent(new ContextRefreshedEvent(this));
 *		2）把监听器加入到容器中
 *		3）只要容器中有相关的事件发布我们就能监听到此事件
 *			ContextRefreshedEvent容器刷新完成
 *			ContextClosedEvent容器关闭事件
 *		4）如何发布一个事件
 *			context.publishEvent(new ApplicationEvent(new String("我发布的事件。。。")) {

			
			private static final long serialVersionUID = 1L;
		});
		
	发布监听机制的原理：
	事件：ContextRefreshedEvent、MyBeanFactoryPostProcessorTest（自定义事件）、ContextClosedEvent上下文关闭事件
		ContextRefreshedEvent：上下文刷新事件：工作原理：
		【事件发布原理】：（无论式自己创建还是系统事件，都是按照此流程）
			1）创建IOC容器
 *			2）refresh（）===》finishRefresh();
 *				publishEvent(new ContextRefreshedEvent(this));
 *			3）publishEvent(new ContextRefreshedEvent(this));
 *				发布流程：
 *				1.getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);（获得容器上下文的派发器）
 *				2.multicastEvent(applicationEvent, eventType);派发事件，如果有Executor，可以支持异步派发，若果没有直接执行
 *					遍历容器，拿到所有的ApplicationListener，
 *					for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
						Executor executor = getTaskExecutor();
						if (executor != null) {
							executor.execute(new Runnable() {
								@Override
								public void run() {
									invokeListener(listener, event);
								}
							});
						}
						else {
							invokeListener(listener, event);
						}
					}
					
				3、拿到listern执行onApplicationEvent(event);
	【事件的派发器ApplicationEventMulticaster】获取过程:
			1）创建IOC容器
 *			2）refresh（）会调用很多的方法其中就包括initApplicationEventMulticaster();
 *				首先去找容器中有没有id为applicationEventMulticaster的组件，如果有就获取，如果没有，就可以new SimpleApplicationEventMulticaster(beanFactory);
 *				然后注册到容器中去，就可以再其他组件在需要派发器时就会自动注入这个new的派发器
 *	【如何知道容器中有哪些监听器】getApplicationListeners(event, type)
 *		1）创建IOC容器
 *		2）refresh（）会调用很多的方法其中就包括registerListeners();
 *		3）如果找到，就将Listener注册到派发器中，没有就会执行
 *			String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
			for (String listenerBeanName : listenerBeanNames) {
				getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
			}
		添加Listener到派发器中
 */
@ComponentScan("com.spring.config.annotation.extra")
@Configuration
public class ExtraConfig {
	@Bean
	public Car blue() {
		return new Car();
	}
	
}
