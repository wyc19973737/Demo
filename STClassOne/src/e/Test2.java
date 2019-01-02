package e;

import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		IZhanAndDuilie<String> duilie=new Duilie<>();
		while(true) {
			System.out.println("1.添加2.获取3.退出");
			int i=scanner.nextInt();
			if(i==1) {
				System.out.println("请输入");
				String string=scanner.next();
				duilie.set(string);
			}else if(i==2) {
				String str=duilie.get();
				System.out.println(str);
			}else if(i==3) {
				break;
			}
		}
	}

}
