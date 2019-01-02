package com.st.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.st.pojo.Employee;
import com.st.service.EmployeeService;

public class AddSwing extends PopSwingFather {
	
	EmployeeService es=new EmployeeService();
	
	public void init(JFrame frame,EmployeeTableModel etm) {
		JFrame addFrame = new JFrame("添加");
		addFrame.setSize(300, 200);
		addFrame.setLocationRelativeTo(frame);
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		JLabel addName = new JLabel("姓名:");
		addName.setFont(new Font("宋体", Font.PLAIN, 20));
		JTextField name = new JTextField();
		name.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JLabel addSex = new JLabel("性别:");
		addSex.setFont(new Font("宋体", Font.PLAIN, 20));
		JComboBox sex = new JComboBox();
		sex.addItem("男");
		sex.addItem("女");
		sex.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JLabel addAge = new JLabel("年龄:");
		addAge.setFont(new Font("宋体", Font.PLAIN, 20));
		JTextField age = new JTextField();
		age.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JButton btn = new JButton("添加");

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String theName = name.getText();
					if (theName.equals("")) {
						JOptionPane.showMessageDialog(addFrame, "姓名不得为空", "提示信息", JOptionPane.CANCEL_OPTION);
					} else {
						char theSex = ((String) sex.getSelectedItem()).toCharArray()[0];
						int theAge = Integer.parseInt(age.getText());
						if (theAge < 18 || theAge > 65) {
							JOptionPane.showMessageDialog(addFrame, "员工年龄需在18-65之间", "提示信息", JOptionPane.CANCEL_OPTION);
						} else {
							Employee employee = new Employee();
							employee.setName(theName);
							employee.setSex(theSex);
							employee.setAge(theAge);
							es.add(employee);
							emps = es.load();
							etm.setEmps(emps);
							etm.fireTableDataChanged();
							addFrame.setVisible(false);
						}
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(addFrame, "年龄请输入数字", "提示信息", JOptionPane.CANCEL_OPTION);
				}
			}
		});

		addPanel.add(addName);
		addPanel.add(name);
		addPanel.add(addSex);
		addPanel.add(sex);
		addPanel.add(addAge);
		addPanel.add(age);
		addPanel.add(btn);
		addFrame.setContentPane(addPanel);
		addFrame.setVisible(true);
	}
}
