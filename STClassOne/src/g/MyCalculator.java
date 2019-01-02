package g;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyCalculator {
	public void getCalculator() {
		JFrame frame = new JFrame("计算器");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();

		FlowLayout fl = new FlowLayout(FlowLayout.LEFT,5,5);
		panel.setLayout(fl);

		JTextField text1 = new JTextField();
		text1.setPreferredSize(new Dimension(90, 30));
		panel.add(text1);

		String[] strs= {"+","-","*","/","%"};
		JComboBox<String> jc=new JComboBox<>(strs);
		jc.setFont(new Font("黑体", Font.BOLD, 20));
//		jc.addItem("+");
//		jc.addItem("-");
//		jc.addItem("*");
//		jc.addItem("/");
//		jc.addItem("%");
		panel.add(jc);

		JTextField text2 = new JTextField();
		text2.setPreferredSize(new Dimension(90, 30));
		panel.add(text2);

		JButton btn = new JButton("=");
		panel.add(btn);

		JTextField text3 = new JTextField();
		text3.setPreferredSize(new Dimension(90, 30));
		text3.setEditable(false);
		panel.add(text3);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int num1 = Integer.parseInt(text1.getText());
					int num2 = Integer.parseInt(text2.getText());
					String symbol=(String)jc.getSelectedItem();
					int num3=0;
					if(symbol.equals("+")) {
						num3= num1 + num2;
					}else if(symbol.equals("-")) {
						num3= num1 - num2;
					}else if(symbol.equals("*")) {
						num3= num1 * num2;
					}else if(symbol.equals("/")) {
						num3= num1 / num2;
					}else if(symbol.equals("%")) {
						num3= num1 % num2;
					}
					text3.setText(String.valueOf(num3));
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(frame, "请输入数字", "提示信息", JOptionPane.CANCEL_OPTION);
					text1.setText(null);
					text2.setText(null);
				}
			}
		});

		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
