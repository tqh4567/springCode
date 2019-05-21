package com.spring.config.annotation.extra;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@EventListener(classes= {ApplicationEvent.class})
	public void listerner(ApplicationEvent event) {
		System.out.println("UserService====监听到的事件..........");
		
	}
}
