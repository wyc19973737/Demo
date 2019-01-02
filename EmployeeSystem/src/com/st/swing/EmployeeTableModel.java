package com.st.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.st.pojo.Employee;

public class EmployeeTableModel extends AbstractTableModel {

	private int index = 1;

	private List<Employee> emps = new ArrayList<>();

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}

	public EmployeeTableModel(List<Employee> emps) {
		this.emps = emps;
	}

	public String getColumnName(int column) {
		if (column == 0) {
			return "编号";
		} else if (column == 1) {
			return "姓名";
		} else if (column == 2) {
			return "性别";
		} else if (column == 3) {
			return "年龄";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return emps.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return emps.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return emps.get(rowIndex).getName();
		} else if (columnIndex == 2) {
			return emps.get(rowIndex).getSex();
		} else if (columnIndex == 3) {
			return emps.get(rowIndex).getAge();
		}
		return null;
	}

}
