package com.spring.config.annotation.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.annotation.ProfileConfig;
/**
 * 切换工作空间
 * 1.修改运行时参数：-Dspring.profiles.active=test/dev/pro
 * 2.使用代码的方式进行切换（参见testProfile01）
 * @author tqh4567
 *
 */
public class ProfileTest {
	@Test
	public void testProfile() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ProfileConfig.class);
		String[] names = context.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
			
		}
	}
	//有参构造默认执行register(annotatedClasses);
	//refresh();无法在容器启动前进行自动义设置，所以使用无参构造器进行设置
	@Test
	public void testProfile01() {
		//1.创建AnnotationConfigApplicationContext的无参构造器
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		//2.获取context的运行环境信息，设置激活的Profiles（数组，可以设置多个）
		context.getEnvironment().setActiveProfiles("test");
		//3.获取注册信息
		context.register(ProfileConfig.class);
		//4.刷新容器
		context.refresh();
		String[] names = context.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
			
		}
	}
}
