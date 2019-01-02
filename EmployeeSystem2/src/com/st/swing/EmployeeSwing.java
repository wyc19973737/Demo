package com.st.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.st.pojo.Employee;
import com.st.service.EmployeeService;

public class EmployeeSwing {
	EmployeeService es = new EmployeeService();

	private JScrollPane jsp;

	private List<Employee> emps = new ArrayList<>();
	List<Employee> list = new ArrayList<>();

	private EmployeeTableModel etm = new EmployeeTableModel(emps);

	public void createSwing() {
		JFrame frame = new JFrame("员工管理系统");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);
		panel.setLayout(fl);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		final int TEXT_WIDTH = 100;
		final int TEXT_HEIGHT = 30;
		JLabel label1 = new JLabel("姓名:");
		label1.setFont(new Font("宋体", Font.PLAIN, 20));
		JTextField text1 = new JTextField();
		text1.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JLabel label2 = new JLabel("性别:");
		label2.setFont(new Font("宋体", Font.PLAIN, 20));
		JComboBox text2 = new JComboBox();
		text2.addItem(" ");
		text2.addItem("男");
		text2.addItem("女");
		text2.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JLabel label3 = new JLabel("年龄:");
		label3.setFont(new Font("宋体", Font.PLAIN, 20));
		JTextField text3 = new JTextField();
		text3.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		text3.setText("0");
		JButton select = new JButton("查询");

		panel1.add(label1);
		panel1.add(text1);
		panel1.add(label2);
		panel1.add(text2);
		panel1.add(label3);
		panel1.add(text3);
		panel1.add(select);

		emps = es.load();
		etm.setEmps(emps);
		JTable jt = new JTable(etm);
		jsp = new JScrollPane(jt);
		panel2.add(jsp);

		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = text1.getText();
					char sex = ((String) text2.getSelectedItem()).toCharArray()[0];
					int age = Integer.parseInt(text3.getText());
					list.removeAll(list);
					if (name.equals("") && sex == ' ' && age == 0) {
						list = es.load();
					} else if (sex == ' ' && age == 0) {
						for (Employee employee : emps) {
							if (employee.getName().equals(name)) {
								list.add(employee);
							}
						}
					} else if (name.equals("") && age == 0) {
						for (Employee employee : emps) {
							if (employee.getSex() == sex) {
								list.add(employee);
							}
						}
					} else if (name.equals("") && sex == ' ') {
						for (Employee employee : emps) {
							if (employee.getAge() == age) {
								list.add(employee);
							}
						}
					} else if (age == 0) {
						for (Employee employee : emps) {
							if (employee.getName().equals(name) && employee.getSex() == sex) {
								list.add(employee);
							}
						}
					} else if (sex == ' ') {
						for (Employee employee : emps) {
							if (employee.getName().equals(name) && employee.getAge() == age) {
								list.add(employee);
							}
						}
					} else if (name.equals("")) {
						for (Employee employee : emps) {
							if (employee.getSex() == sex && employee.getAge() == age) {
								list.add(employee);
							}
						}
					} else {
						for (Employee employee : emps) {
							if (employee.getName().equals(name) && employee.getSex() == sex
									&& employee.getAge() == age) {
								list.add(employee);
							}
						}
					}
					etm.setEmps(list);
					etm.fireTableDataChanged();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(frame, "年龄为数字,不输入年龄为0", "提示信息", JOptionPane.CANCEL_OPTION);
				}
			}
		});

		JButton addBtn = new JButton("新增");
		panel3.add(addBtn);
		JButton updateBtn = new JButton("修改");
		panel3.add(updateBtn);
		JButton deleteBtn = new JButton("删除");
		panel3.add(deleteBtn);

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddSwing as=new AddSwing();
				as.init(frame,etm);
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateSwing us=new UpdateSwing();
				us.init(frame, jt,etm);
			}
		});

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = jt.getSelectedRow();
				if (index > -1) {
					es.delete(emps.get(index));
					emps = es.load();
					etm.setEmps(emps);
					etm.fireTableDataChanged();
				} else {
					JOptionPane.showMessageDialog(frame, "请选择员工", "提示信息", JOptionPane.CANCEL_OPTION);
				}
			}
		});

		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
