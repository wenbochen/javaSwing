package com.wenbo.swing;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 简易计算器
 * @author admin
 *
 */
public class SimpleCalculator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6907470445587513774L;
	private JPanel contentPane;
	private JTextField num1;
	private JTextField num2;
	private JTextField sumnum;
	private Choice choice;
	private int opr = 1;
	static SimpleCalculator frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new SimpleCalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimpleCalculator() {
		setTitle("简易计算器");
		int releaseAndHideFrame = JFrame.DISPOSE_ON_CLOSE;//释放该窗口所占的资源并隐藏该窗口,但是不退出程序
		int exitApp = JFrame.EXIT_ON_CLOSE;//直接退出程序
		int hideFrame = JFrame.HIDE_ON_CLOSE;//隐藏该窗口 但是不释放资源
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		num1 = new JTextField();
		num1.setBounds(16, 45, 62, 28);
		contentPane.add(num1);
		num1.setColumns(10);
		
		num2 = new JTextField();
		num2.setBounds(128, 45, 62, 28);
		contentPane.add(num2);
		num2.setColumns(10);
		
		JLabel label_1 = new JLabel("=");
		label_1.setBounds(200, 50, 21, 16);
		contentPane.add(label_1);
		
		sumnum = new JTextField();
		sumnum.setEditable(false);
		sumnum.setBounds(210, 45, 117, 28);
		contentPane.add(sumnum);
		sumnum.setColumns(10);
		
		JButton countbutton = new JButton("计  算");
		countbutton.setBounds(16, 109, 117, 29);
		countbutton.setActionCommand("count");
		countbutton.addActionListener(new OnClickListener());
		contentPane.add(countbutton);
		
		JButton resetbutton = new JButton("AC");
		resetbutton.setBounds(149, 109, 117, 29);
		resetbutton.setActionCommand("ac");
		resetbutton.addActionListener(new OnClickListener());
		contentPane.add(resetbutton);
		
		choice = new Choice();
		choice.add("＋");
		choice.add("－");
		choice.add("×");
		choice.add("÷");
		choice.setBounds(84, 45, 38, 28);
		choice.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(choice);
	}
	/**
	 * 
	 * @author admin
	 *
	 */
	class OnClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("count")){
				try {
					String value1 = num1.getText();
					String value2 = num2.getText();
					if(value1.equals("")||value2.equals("")){
						JOptionPane.showMessageDialog(frame, "请先输入数字!");
						return;
					}
					double no1 = Double.parseDouble(num1.getText());
					double no2 =Double.parseDouble(num2.getText());
					double sum = 0.0;
					int c = choice.getSelectedIndex();
					switch (c) {
					// + - x /
					case 0:
						sum = no1 + no2;
						break;
					case 1:
						sum = no1 - no2;
						break;
					case 2:
						sum = no1 * no2;
						break;
					case 3:
						sum = no1 / no2;
						break;

					default:
						break;
					}
					
					sumnum.setText(sum+"");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "请输入数字!");
					e1.printStackTrace();
					
				}
				
			}else{
				num1.setText("");
				num2.setText("");
				sumnum.setText("");
			}
			
		}
		
	}
}
