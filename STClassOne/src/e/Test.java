package e;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
	
	public void test() {
		
	}
	
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(System.in);
			System.out.println("������");
			int a=scanner.nextInt();
			System.out.println("����");
			int b=scanner.nextInt();
			System.out.println("�����:"+a/b);
		} catch (InputMismatchException e) {
			System.out.println("����������!");
		}catch (ArithmeticException e) {
			System.out.println("��������Ϊ0!");
		}
	}

}
