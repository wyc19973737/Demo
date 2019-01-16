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

	private EmployeeTableModel etm = new EmployeeTableModel(emps);

	public void createSwing() {
		JFrame frame = new JFrame("Ա������ϵͳ");
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
		JLabel label1 = new JLabel("����:");
		label1.setFont(new Font("����", Font.PLAIN, 20));
		JTextField text1 = new JTextField();
		text1.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JLabel label2 = new JLabel("�Ա�:");
		label2.setFont(new Font("����", Font.PLAIN, 20));
		JComboBox text2 = new JComboBox();
		text2.addItem(" ");
		text2.addItem("��");
		text2.addItem("Ů");
		text2.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		JLabel label3 = new JLabel("����:");
		label3.setFont(new Font("����", Font.PLAIN, 20));
		JTextField text3 = new JTextField();
		text3.setPreferredSize(new Dimension(TEXT_WIDTH, TEXT_HEIGHT));
		text3.setText("0");
		JButton select = new JButton("��ѯ");

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
					List<Employee> list = new ArrayList<>();
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
					JOptionPane.showMessageDialog(frame, "����Ϊ����,����������Ϊ0", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
				}
			}
		});

		JButton addBtn = new JButton("����");
		panel3.add(addBtn);
		JButton updateBtn = new JButton("�޸�");
		panel3.add(updateBtn);
		JButton deleteBtn = new JButton("ɾ��");
		panel3.add(deleteBtn);

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addFrame = new JFrame("���");
				addFrame.setSize(300, 200);
				addFrame.setLocationRelativeTo(frame);
				JPanel addPanel = new JPanel();
				addPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
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
				JButton btn = new JButton("���");

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String theName = name.getText();
							if (theName.equals("")) {
								JOptionPane.showMessageDialog(addFrame, "��������Ϊ��", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
							} else {
								char theSex = ((String) sex.getSelectedItem()).toCharArray()[0];
								int theAge = Integer.parseInt(age.getText());
								if (theAge < 18 || theAge > 65) {
									JOptionPane.showMessageDialog(addFrame, "Ա����������18-65֮��", "��ʾ��Ϣ",
											JOptionPane.CANCEL_OPTION);
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
							JOptionPane.showMessageDialog(addFrame, "��������������", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
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
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = jt.getSelectedRow();
				if (index > -1) {
					Employee employee=emps.get(index);
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
					sex.setSelectedItem(employee.getSex());
					age.setText(employee.getAge()+"");
					
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
									JOptionPane.showMessageDialog(updateFrame, "��������Ϊ��", "��ʾ��Ϣ",
											JOptionPane.CANCEL_OPTION);
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
								JOptionPane.showMessageDialog(updateFrame, "Ա�����Ϊ����", "��ʾ��Ϣ",
										JOptionPane.CANCEL_OPTION);
							}
						}
					});

					updateFrame.setContentPane(jp);
					updateFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "��ѡ��Ա��", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
				}
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
					JOptionPane.showMessageDialog(frame, "��ѡ��Ա��", "��ʾ��Ϣ", JOptionPane.CANCEL_OPTION);
				}
			}
		});

		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
