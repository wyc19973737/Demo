package com.st.service;

import java.util.List;

import com.st.dao.EmployeeDao;
import com.st.pojo.Employee;

public class EmployeeService {
	EmployeeDao dao=new EmployeeDao();
	
	public void add(Employee e) {
		dao.addEmployee(e);
	}

	//��ȡ����Ա����Ϣ��
	public List<Employee> load() {
		List<Employee> emps = dao.selectAll();
		return emps;
	}

	public void delete(Employee e) {
		dao.deleteEmployee(e.getId());
	}
	
	public void update(int index,Employee emp) {
		dao.updateEmployee(index, emp);
	}
	
}
