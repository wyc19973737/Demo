package com.st.main;

import java.util.ArrayList;
import java.util.List;

import com.st.pojo.Fleet;
import com.st.pojo.Ship;
import com.st.pojo.TeXing;

public class MainClass {

	public static void main(String[] args) {
		TeXing teXing=new TeXing(new ArrayList<>());
		Ship ship=new Ship("","",0,0);
		Ship theShip=ship.getShip();
		Fleet fleet=new Fleet(0,theShip,"",teXing.getTeXing());
		fleet.getFleet();
		System.out.println("船名:"+ship.getTheName());
		System.out.println("类型:"+ship.getTheType());
		System.out.println("数量:"+fleet.getTheNum());
		System.out.println("血量:"+ship.getTheHp());
		System.out.println("速度:"+ship.getTheSpeed());
		System.out.println("难度:"+fleet.getTheHard());
		System.out.print("技能:"+teXing.getTeXing().get(0));
		System.out.println(" 忌"+teXing.getTeXing().get(1));
	}

}
