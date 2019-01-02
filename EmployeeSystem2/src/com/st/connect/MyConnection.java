package com.st.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdata?useSSL=false", "root", "niit");
			Statement statement=connection.createStatement();
			ResultSet rs=statement.executeQuery("select id,name,sex,age from employees");
			while(rs.next()) {
				System.out.println(rs.getObject("id"));
				System.out.println(rs.getObject("name"));
				System.out.println(rs.getObject("sex"));
				System.out.println(rs.getObject("age"));
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
