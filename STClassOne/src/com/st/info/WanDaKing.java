package com.st.info;

public class WanDaKing extends BigBoss{

	String name="万达蜘蛛王";
	private int gongjili=5000;
	
	@Override
	public void attack() {
		System.out.println(name+"攻击    造成了"+gongjili+"点伤害");
	}

	@Override
	public void showName() {
		System.out.println(name);
	}
	
}
