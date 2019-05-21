package com.spring.config.annotation.test;

import org.springframework.beans.factory.FactoryBean;

import com.spring.pojo.Color;
/**
 * 测试工厂注入模式
 * @author tqh4567
 *
 */

public class ColorFactory implements FactoryBean<Color> {

	public Color getObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("get ColorFactory Bean......getObject......");
		return new Color();
	}

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Color.class;
	}
	//是否为单例
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
