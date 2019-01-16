package com.st.pojo;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private int id;
	private String name;
	private char sex;
	private int age;

	public Employee() {

	}

	public Employee(int id,String name, char sex, int age) {
		super();
		this.id=id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
