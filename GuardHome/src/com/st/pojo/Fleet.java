package com.st.pojo;

import java.util.List;

public class Fleet {
	private int num;
	private Ship ship;
	private String hard;
	private List<String> teXing;
	
	public Fleet(int num,Ship ship,String hard,List<String> teXing) {
		this.num=num;
		this.ship=ship;
		this.hard=hard;
		this.teXing=teXing;
	}
	
	public Fleet getFleet() {
		num = getNum();
		ship=getShip();
		hard=getHard();
		teXing=getTeXing();
		Fleet fleet=new Fleet(num, ship, hard, teXing);
		return fleet;
	}
	private int getNum() {
		return (int)Math.random()*16+8;
	}
	private String getHard() {
		int a=(int)Math.floor(Math.random()*6);
		String str="";
		if(a==0) {
			str="С��һ��";
		}else if(a==1) {
			str="ƽӹ֮��";
		}else if(a==2) {
			str="�ƾ�����";
		}else if(a==3) {
			str="�о�����";
		}else if(a==4) {
			str="ǧ������";
		}else if(a==5) {
			str="�ѹ�����";
		}
		return str;
	}

	public Ship getShip() {
		return ship;
	}
	
	public List<String> getTeXing() {
		return teXing;
	}
	
	public int getTheNum() {
		return num;
	}
	public String getTheHard() {
		return hard;
	}
}
