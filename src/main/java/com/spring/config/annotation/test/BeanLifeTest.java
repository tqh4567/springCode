package com.spring.config.annotation.test;



import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.junit.Test;
import com.spring.config.annotation.BeanLifeConfig;
import com.spring.pojo.Dog;
//在配置类上加载包扫描，在其测试类上加无效！注解扫描加载
//@ComponentScan(value="com.spring.pojo")
public class BeanLifeTest {
	@Test
	public void getLife() {
		//ApplicationContext没有相应的关闭容器的方法
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BeanLifeConfig.class);
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
			
		}
		
		context.close();	
	}
}
