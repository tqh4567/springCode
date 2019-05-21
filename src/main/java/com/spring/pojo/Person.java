package com.spring.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class Person {
	/**
	 * Value:基本类型、sel表达式
	 * 
	 */
	@Value("zhangsan")
	private String name;
	@Value("#{20-3}")
	private int age;
	@Value("${PERSON_BIRTHDAY}")
	private String birthday;
	private String phone;
	
	//测试person里面有pojo类型
	private Role role;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Person() {
		super();
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", birthday=" + birthday + ", phone=" + phone + "]";
	}
}