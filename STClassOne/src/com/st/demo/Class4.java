package com.st.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.st.info.Dog;

public class Class4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Dog> dogList = new ArrayList();
		dogList=Class4.load();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("0:录入 1:显示 2:退出系统 3:删除");
			int a = scanner.nextInt();
			if (a == 0) {
				Dog dog = new Dog();
				System.out.println("请输入名字:");
				String name = scanner.next();
				dog.setName(name);
				System.out.println("请输入大小:");
				String size = scanner.next();
				dog.setSize(size);
				System.out.println("请输入年龄:");
				int age = scanner.nextInt();
				dog.setAge(age);
				dogList.add(dog);
				Class4.save(dogList);
			} else if (a == 1) {
				int i=0;
				for (Dog dog : dogList) {
					System.out.println(i+" "+dog.getName() + " " + dog.getSize() + " " + dog.getAge());
					i++;
				}
			} else if (a == 2) {
				break;
			}else if(a==3){
				System.out.println("请输入要删除的序号:");
				int num=scanner.nextInt();
				dogList.remove(num);
				Class4.save(dogList);
			} else {
				continue;
			}
		}

	}

	public static void save(List<Dog> list) {
		try {
			FileOutputStream fos=new FileOutputStream("c:/HTML/dog.txt");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List load() {
		try {
			FileInputStream fis=new FileInputStream("c:/HTML/dog.txt");
			ObjectInputStream ois=new ObjectInputStream(fis);
			List list=(List)ois.readObject();
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
