package com.spring.config.annotation.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

import com.spring.pojo.Car;

/**
 * 测试反射
 * @author tqh4567
 *
 */
public class ReflectTest {
	@Test
	public void getPerson() {
		try {
			//获取字节码文件
			Class<?> name = Class.forName("com.spring.pojo.Car");
			//获取构造器来创建对象==》public Car(String name, String color, Integer price)
			//推荐使用包装类型，如果为int，而name.getConstructor(String.class,String.class,Integer.class);里面为Integer会报没有相对应的方法
			Constructor<?> constructor = name.getConstructor(String.class,String.class,Integer.class);
			//通过构造器来创建对象
			Car instance = (Car) constructor.newInstance("奔驰","黑色",1222);
			System.out.println(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getPersonMethod() {
		try {
			//获取字节码文件
			 Car instance = (Car) Class.forName("com.spring.pojo.Car").newInstance();
			Method method=instance.getClass().getMethod("destroy", null);
			method.invoke(instance, null);
			System.out.println(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
