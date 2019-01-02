package com.st.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.st.pojo.Employee;

public class EmployeeDao {
	public List<Employee> selectAll() {
		List<Employee> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdata?useSSL=false",
					"root", "niit");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select id,name,sex,age from employees");
			while (rs.next()) {
				String string = (String) rs.getObject("sex");
				Employee e = new Employee((int) rs.getObject("id"), (String) rs.getObject("name"),
						string.toCharArray()[0], (int) rs.getObject("age"));
				list.add(e);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addEmployee(Employee emp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdata?useSSL=false",
					"root", "niit");
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into employees (name,sex,age) values('" + emp.getName() + "','"
					+ emp.getSex() + "','" + emp.getAge() + "')");
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int index) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdata?useSSL=false",
					"root", "niit");
			Statement statement = connection.createStatement();
			statement.executeUpdate("delete from employees where id=" + index);
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	public void updateEmployee(int index,Employee emp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdata?useSSL=false",
					"root", "niit");
			Statement statement = connection.createStatement();
			statement.executeUpdate("update employees set name='"+emp.getName()+"',sex='"+emp.getSex()+"',age="+emp.getAge()+" where id=" + index);
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
}
