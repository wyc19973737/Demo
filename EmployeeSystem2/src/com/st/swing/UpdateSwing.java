package com.st.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.st.pojo.Employee;

public class UpdateSwing extends PopSwingFather {
	public void init(JFrame frame,JTable jt,EmployeeTableModel etm) {

		int index = jt.getSelectedRow();
		if (index > -1) {
			Employee employee = emps.get(index);
			JFrame updateFrame = new JFrame("修改");
			updateFrame.setSize(260, 210);
			updateFrame.setLocationRelativeTo(frame);
			JPanel jp = new JPanel();

			jp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

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
			JButton btn = new JButton("修改");

			name.setText(employee.getName());
			sex.setSelectedItem(employee.getSex()+"");
			age.setText(employee.getAge() + "");

			jp.add(addName);
			jp.add(name);
			jp.add(addSex);
			jp.add(sex);
			jp.add(addAge);
			jp.add(age);
			jp.add(btn);

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String theName = name.getText();
						if (theName.equals("")) {
							JOptionPane.showMessageDialog(updateFrame, "姓名不得为空", "提示信息", JOptionPane.CANCEL_OPTION);
						} else {
							char theSex = ((String) sex.getSelectedItem()).toCharArray()[0];
							int theAge = Integer.parseInt(age.getText());
							if (theAge < 18 || theAge > 65) {
								JOptionPane.showMessageDialog(updateFrame, "员工年龄需在18-65之间", "提示信息",
										JOptionPane.CANCEL_OPTION);
							} else {
								Employee employee = new Employee();
								employee.setName(theName);
								employee.setSex(theSex);
								employee.setAge(theAge);
								es.update(emps.get(index).getId(), employee);
								emps = es.load();
								etm.setEmps(emps);
								etm.fireTableDataChanged();
								updateFrame.setVisible(false);
							}
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(updateFrame, "员工编号为数字", "提示信息", JOptionPane.CANCEL_OPTION);
					}
				}
			});

			updateFrame.setContentPane(jp);
			updateFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(frame, "请选择员工", "提示信息", JOptionPane.CANCEL_OPTION);
		}

	}
}
