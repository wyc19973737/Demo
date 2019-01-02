package com.st.demo;
import java.util.Scanner;

public class Class1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入姓名:");
		Scanner scanner=new Scanner(System.in);
		String str=scanner.next();
		scanner.close();
		String temp=str.substring(0,2);
		boolean flag=false;
		String[] xings= {"欧阳","太史","端木","上官","司马","东方","独孤","南宫","万俟","闻人","夏侯",
						 "诸葛","尉迟","公羊","赫连","澹台","皇甫","宗政","濮阳","公冶","太叔","申屠",
						 "公孙","慕容","仲孙","钟离","长孙","宇文","司徒","鲜于","司空","闾丘","子车",
						 "亓官","司寇","巫马","公西","颛孙","壤驷","公良","漆雕","乐正","宰父","谷梁",
						 "拓跋","夹谷","轩辕","令狐","段干","百里","呼延","东郭","南门","羊舌","微生",
						 "公户","公玉","公仪","梁丘","公仲","公上","公门","公山","公坚","左丘","公伯",
						 "西门","公祖","第五","公乘","贯丘","公皙","南荣","东里","东宫","仲长","子书",
						 "子桑","即墨","达奚","褚师","吴铭","淳于"};
		for(int i=0;i<xings.length;i++) {
			if(xings[i].equals(temp)) {
				flag=true;
				break;
			}
		}
		if(flag) {
			System.out.println("姓:"+temp);
			System.out.println("名:"+str.substring(2));
		}else {
			System.out.println("姓:"+str.substring(0,1));
			System.out.println("名:"+str.substring(1));
		}
		
		
	}

}
