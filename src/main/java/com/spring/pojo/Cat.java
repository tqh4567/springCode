package com.spring.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
public class Cat  implements InitializingBean,DisposableBean{
	public  Cat() {
		System.out.println("cat ......constructor.........");
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("cat ......destroy.........");
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("cat ......afterPropertiesSet.........init");
	}
}
