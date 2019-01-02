package e;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
	
	public void test() {
		
	}
	
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(System.in);
			System.out.println("被除数");
			int a=scanner.nextInt();
			System.out.println("除数");
			int b=scanner.nextInt();
			System.out.println("结果是:"+a/b);
		} catch (InputMismatchException e) {
			System.out.println("请输入数字!");
		}catch (ArithmeticException e) {
			System.out.println("除数不能为0!");
		}
	}

}
