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
		System.out.println("����:"+ship.getTheName());
		System.out.println("����:"+ship.getTheType());
		System.out.println("����:"+fleet.getTheNum());
		System.out.println("Ѫ��:"+ship.getTheHp());
		System.out.println("�ٶ�:"+ship.getTheSpeed());
		System.out.println("�Ѷ�:"+fleet.getTheHard());
		System.out.print("����:"+teXing.getTeXing().get(0));
		System.out.println(" ��"+teXing.getTeXing().get(1));
	}

}
