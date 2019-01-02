package com.st.swing;

import java.util.List;

import com.st.pojo.Employee;
import com.st.service.EmployeeService;

public class PopSwingFather {
	final int TEXT_WIDTH = 100;
	final int TEXT_HEIGHT = 30;
	EmployeeService es=new EmployeeService();
	List<Employee> emps = es.load();
}
