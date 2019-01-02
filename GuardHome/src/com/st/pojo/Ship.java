package com.st.pojo;

public class Ship {
	
	private String name;
	private String type;
	private int hp;
	private int speed;
	
	public Ship(String name, String type, int hp, int speed) {
		super();
		this.name = name;
		this.type = type;
		this.hp = hp;
		this.speed = speed;
	}

	public Ship getShip(){
		name = getName();
		type = getType();
		hp = getHp();
		speed = getSpeed();
		Ship ship=new Ship(name, type, hp, speed);
		return ship;
	}
	
	private String getName() {
		int a=(int)Math.floor(Math.random()*3);
		if(a==0) {
			return "小船";
		}else if(a==1) {
			return "中船";
		}else{
			return "大船";
		}
	}
	private String getType() {
		int a=(int)Math.floor(Math.random()*3);
		if(a==0) {
			return "轻型船";
		}else if(a==1) {
			return "中型船";
		}else{
			return "重型船";
		}
	}
	private int getHp() {
		return (int)(Math.random()*20000)+50000;
	}
	private int getSpeed() {
		return (int)(Math.random()*40)+50;
	}

	public String getTheName() {
		return name;
	}
	public String getTheType() {
		return type;
	}
	public int getTheHp() {
		return hp;
	}
	public int getTheSpeed() {
		return speed;
	}
	
}
