package com.st.info;

import java.io.Serializable;

public class Dog implements Serializable{
	private String name;
	private String size;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {                                                             
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
