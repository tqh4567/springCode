package com.spring.pojo;

public class Car {
	private String name;
	private String color;
	private Integer price;
	//有参构造器
	public Car(String name, String color, Integer price) {
		super();
		this.name = name;
		this.color = color;
		this.price = price;
	}
	
	//构造器
	public Car() {
		System.out.println("car.....constror....");
	}
	public void init() {
		System.out.println("init a car........");
		
	}
	public void destroy() {
		System.out.println("destroy a car........");
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", color=" + color + ", price=" + price + "]";
	}
	
}
