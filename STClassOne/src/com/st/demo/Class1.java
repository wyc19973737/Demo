package com.st.demo;
import java.util.Scanner;

public class Class1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("����������:");
		Scanner scanner=new Scanner(System.in);
		String str=scanner.next();
		scanner.close();
		String temp=str.substring(0,2);
		boolean flag=false;
		String[] xings= {"ŷ��","̫ʷ","��ľ","�Ϲ�","˾��","����","����","�Ϲ�","��ٹ","����","�ĺ�",
						 "���","ξ��","����","����","�̨","�ʸ�","����","���","��ұ","̫��","����",
						 "����","Ľ��","����","����","����","����","˾ͽ","����","˾��","����","�ӳ�",
						 "����","˾��","����","����","���","����","����","���","����","�׸�","����",
						 "�ذ�","�й�","��ԯ","���","�θ�","����","����","����","����","����","΢��",
						 "����","����","����","����","����","����","����","��ɽ","����","����","����",
						 "����","����","����","����","����","���","����","����","����","�ٳ�","����",
						 "��ɣ","��ī","����","��ʦ","����","����"};
		for(int i=0;i<xings.length;i++) {
			if(xings[i].equals(temp)) {
				flag=true;
				break;
			}
		}
		if(flag) {
			System.out.println("��:"+temp);
			System.out.println("��:"+str.substring(2));
		}else {
			System.out.println("��:"+str.substring(0,1));
			System.out.println("��:"+str.substring(1));
		}
		
		
	}

}
