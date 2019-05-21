package com.spring.config.annotation.extra;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 测试一下监听器
 * 
 *
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

	public void onApplicationEvent(ApplicationEvent event) {
		
		System.out.println("收到的事件为：。。。"+event);
	}

	

}
