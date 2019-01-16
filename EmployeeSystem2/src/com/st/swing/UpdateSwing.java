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
			JFrame updateFrame = new JFrame("�޸�");
			updateFrame.setSize(260, 210);
			updateFrame.setLocationRelativeTo(frame);
			JPanel jp = new JPanel();

			jp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

			JLabel addName = new JLabel("����:");
			addName.setFont(new Font("����", Font.PLAIN, 20));
			JTextField name = new JTextField();
			name.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
			JLabel addSex = new JLabel("�Ա�:");
			addSex.setFont(new Font("����", Font.PLAIN, 20));
			JComboBox sex = new JComboBox();
			sex.addItem("��");
			sex.addItem("Ů");
			sex.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
			JLabel addAge = new JLabel("����:");
			addAge.setFont(new Font("����", Font.PLAIN, 20));
			JTextField age = new JTextField();
			age.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
			JButton btn = new JButton("�޸�");

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
							JOptionPane.showMessageDialog(updateFrame, "��������Ϊ��", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
						} else {
							char theSex = ((String) sex.getSelectedItem()).toCharArray()[0];
							int theAge = Integer.parseInt(age.getText());
							if (theAge < 18 || theAge > 65) {
								JOptionPane.showMessageDialog(updateFrame, "Ա����������18-65֮��", "��ʾ��Ϣ",
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
						JOptionPane.showMessageDialog(updateFrame, "Ա�����Ϊ����", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
					}
				}
			});

			updateFrame.setContentPane(jp);
			updateFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(frame, "��ѡ��Ա��", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
		}

	}
}
