package com.st.demo;

import java.util.ArrayList;
import java.util.List;

public class Class3 {

	public static void main(String[] args) {
		// ���100~999��ˮ�ɻ���
//		for(int i=100;i<999;i++) {
//			int bai=i/100;
//			int shi=i/10%10;
//			int ge=i%10;
//			if(bai*bai*bai+shi*shi*shi+ge*ge*ge==i) {
//				System.out.println(i);
//			}
//		}

		// �ֽ���ʽ
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
//		System.out.print(a+"�ɷֽ���ʽΪ:");
//		for(int i=0;i<nums.size();i++) {
//			if(i<nums.size()-1) {
//				System.out.print(nums.get(i)+"*");
//			}else {
//				System.out.print(nums.get(i));
//			}
//		}

		// �������������Լ������С������
//		int m=3,n=2;
//		if(n>m) {
//			int temp=n;
//			n=m;
//			m=temp;
//		}
//		if(m%n==0) {
//			System.out.println("���Լ��Ϊ"+n);
//			System.out.println("��С������Ϊ"+m);
//		}else {
//			boolean flag=true;
//			for(int i=2;i<=Math.sqrt(n);i++) {
//				System.out.println(i);
//				if(n%i==0&&m%(n/i)==0) {
//					System.out.println("���Լ��Ϊ"+(n/i));
//					flag=false;
//					break;
//				}
//			}
//			if(flag) {
//				System.out.println("���Լ��Ϊ"+1);
//			}
//			for(int i=2;i<=m;i++) {
//				if(n*i%m==0) {
//					System.out.println("��С������Ϊ"+(n*i));
//					break;
//				}
//			}
//		}

		// ��һ���ַ����� Ӣ����ĸ���ո����ֺ������ַ��ĸ���
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
//		System.out.println("Ӣ����ĸ�ĸ�����"+eng+"�������ָ�����"+num+"�����ո������"+space+"���������ַ���"+others+"��");
//		

		//���0.08mm��ֽ���۶��ٴο��Գ������������
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

		
		//��������������Ǹ���ĵڼ���
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
		
		
		//�������һ������
//		int a=2018;
//		String string=""+a;
//		String str="";
//		for(int i=0;i<string.length();i++) {
//			char c=string.charAt(string.length()-i-1);
//			str += c;
//		}
//		a=Integer.parseInt(str);
//		System.out.println(a);
		
		
		//��֤��Ȳ����κ�һ���������ż���ͳ��Զ����������ͳ�����һ,���õ������϶���1
//		int a=7;
//		while(a!=1) {
//			if(a%2==0) {
//				a/=2;
//			}else {
//				a=a*3+1;
//			}
//		}
//		System.out.println(a);
		
		
		//��1~10000֮�����е���ȫ��
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
		
		
		//���ӳ���10��ʣһ����ÿ���һ���һ��
//		int tao=1;
//		for(int i=0;i<9;i++) {
//			tao=tao*2+2;
//		}
//		System.out.println(tao);
		
		
		
	}

}
