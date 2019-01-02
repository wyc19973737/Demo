package com.st.demo;

import java.util.ArrayList;
import java.util.List;

public class Class3 {

	public static void main(String[] args) {
		// 输出100~999的水仙花数
//		for(int i=100;i<999;i++) {
//			int bai=i/100;
//			int shi=i/10%10;
//			int ge=i%10;
//			if(bai*bai*bai+shi*shi*shi+ge*ge*ge==i) {
//				System.out.println(i);
//			}
//		}

		// 分解因式
//		int a=210;
//		List<Integer> nums = new ArrayList<Integer>();
//		while(a!=1){
//			for(int i=2;i<=a;i++) {
//				if(a%i==0) {
//					nums.add(i);
//					a /= i;
//					break;
//				}
//			}
//		}
//		System.out.print(a+"可分解因式为:");
//		for(int i=0;i<nums.size();i++) {
//			if(i<nums.size()-1) {
//				System.out.print(nums.get(i)+"*");
//			}else {
//				System.out.print(nums.get(i));
//			}
//		}

		// 求两个数的最大公约数和最小公倍数
//		int m=3,n=2;
//		if(n>m) {
//			int temp=n;
//			n=m;
//			m=temp;
//		}
//		if(m%n==0) {
//			System.out.println("最大公约数为"+n);
//			System.out.println("最小公倍数为"+m);
//		}else {
//			boolean flag=true;
//			for(int i=2;i<=Math.sqrt(n);i++) {
//				System.out.println(i);
//				if(n%i==0&&m%(n/i)==0) {
//					System.out.println("最大公约数为"+(n/i));
//					flag=false;
//					break;
//				}
//			}
//			if(flag) {
//				System.out.println("最大公约数为"+1);
//			}
//			for(int i=2;i<=m;i++) {
//				if(n*i%m==0) {
//					System.out.println("最小公倍数为"+(n*i));
//					break;
//				}
//			}
//		}

		// 求一个字符串中 英文字母、空格、数字和其他字符的个数
//		String str = "abcde123  ##*";
//		int eng=0;
//		int num=0;
//		int space=0;
//		int others=0;
//		for (int i = 0; i < str.length(); i++) {
//			char ch = str.charAt(i);
//			int a = ch;
//			if (a >= 65 && a <= 90 || a >= 97 && a <= 122) {
//				eng++;
//			}else if(a>=48&&a<=57) {
//				num++;
//			}else if(a==32) {
//				space++;
//			}else {
//				others++;
//			}
//		}
//		System.out.println("英文字母的个数有"+eng+"个，数字个数有"+num+"个，空格个数有"+space+"个，其他字符有"+others+"个");
//		

		//测厚0.08mm的纸对折多少次可以超过珠穆朗玛峰
//		int cishu=0;
//		double houdu=0.08;
//		while(true) {
//			if(houdu>8848130) {
//				break;
//			}else {
//				cishu++;
//				houdu *= 2;
//			}
//		}
//		System.out.println(cishu);

		
		//年月日输出该天是该年的第几天
//		int year = 2018;
//		int month = 3;
//		int day = 1;
//		int days = 0;
//		for (int i = 1; i <= month; i++) {
//			switch (i) {
//			case 1:
//				days += day;
//				break;
//			case 2:
//				days += 31;
//				break;
//			case 3:
//				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
//					days += 29;
//				} else {
//					days += 28;
//				}
//				break;
//			case 4:
//				days += 31;
//				break;
//			case 5:
//				days += 30;
//				break;
//			case 6:
//				days += 31;
//				break;
//			case 7:
//				days += 30;
//				break;
//			case 8:
//				days += 31;
//				break;
//			case 9:
//				days += 31;
//				break;
//			case 10:
//				days += 30;
//				break;
//			case 11:
//				days += 31;
//				break;
//			case 12:
//				days += 30;
//				break;
//			}
//		}
//		System.out.println(days);
		
		
		//反向输出一个数字
//		int a=2018;
//		String string=""+a;
//		String str="";
//		for(int i=0;i<string.length();i++) {
//			char c=string.charAt(string.length()-i-1);
//			str += c;
//		}
//		a=Integer.parseInt(str);
//		System.out.println(a);
		
		
		//验证鬼谷猜想任何一个数如果是偶数就除以二，是奇数就乘三加一,最后得到的数肯定是1
//		int a=7;
//		while(a!=1) {
//			if(a%2==0) {
//				a/=2;
//			}else {
//				a=a*3+1;
//			}
//		}
//		System.out.println(a);
		
		
		//求1~10000之间所有的完全数
//		for(int i=2;i<10000;i++) {
//			int num=i;
//			int yinzihe=1;
//			for(int j=2;j<i;j++) {
//				if(num%j==0) {
//					yinzihe += j;
//				}
//			}
//			if(yinzihe==i) {
//				System.out.println(i);
//			}
//		}
		
		
		//猴子吃桃10天剩一个，每天吃一半加一个
//		int tao=1;
//		for(int i=0;i<9;i++) {
//			tao=tao*2+2;
//		}
//		System.out.println(tao);
		
		
		
	}

}
