package com.st.info;

public class WanDaKing extends BigBoss{

	String name="���֩����";
	private int gongjili=5000;
	
	@Override
	public void attack() {
		System.out.println(name+"����    �����"+gongjili+"���˺�");
	}

	@Override
	public void showName() {
		System.out.println(name);
	}
	
}
