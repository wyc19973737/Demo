package com.st.demo;

public class Class2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] hong = new int[6];// 定义一个6个值的空数组
		// 生成6个1~33不重复的随机整数添加到数组中
		for (int i = 0; i < hong.length; i++) {
			hong[i] = (int) (Math.random() * 33) + 1;
			while (i != 0) {
				boolean flag = false;
				for (int j = 0; j < i; j++) {
					if (hong[i] == hong[j]) {
						hong[i] = (int) (Math.random() * 33) + 1;
						break;
					}
					if (j == i - 1) {
						flag = true;
					}
				}
				if (flag) {
					break;
				}
			}
			// 数组排序
			for (int j = i - 1; j >= 0; j--) {
				if (hong[j] < hong[j + 1]) {
					int temp = hong[j + 1];
					hong[j + 1] = hong[j];
					hong[j] = temp;
				}
			}
		}
		// 输出红球数组
		for (int i = 0; i < hong.length; i++) {
			System.out.print(hong[hong.length - 1 - i] + " ");
		}
		// 生成蓝球
		int lan = (int) (Math.random() * 16) + 1;
		System.out.print(": " + lan);
	}

}
