package com.wenbo.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * 通讯录 使用sqlite记录
 * @Description 
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年1月18日 下午3:06:00
 * @version V1.0
 */
public class AddressBook extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1393989441117864376L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField mobile;
	private JLabel result;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressBook frame = new AddressBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
private void getAmsg(){
	try {
		Class.forName("org.sqlite.JDBC");
		//加载驱动
		//创建链接 数据库不存在则自动创建
		Connection conn =DriverManager.getConnection("jdbc:sqlite:books.db"); 
		Statement state = conn.createStatement();
	//	state.executeUpdate("create table if not exists book (name varchar(20),phone varchar(18),mobile varchar(18))");
	//	state.execute("insert into book values('admin','010-68351588','13810261258')");
		ResultSet rs = state.executeQuery("select * from book");
		if(rs.next()){
			String name = rs.getString(1);
			String phone = rs.getString(2);
			String mobile = rs.getString(3);
			System.out.println(name+" ==="+phone+"==="+mobile);
			result.setText(name+" ==="+phone+"==="+mobile);
		}
		rs.close();
		conn.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	/**
	 * Create the frame.
	 */
	public AddressBook() {
		setTitle("通讯录");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//
		setBounds(100, 100, 512, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		JLabel label = new JLabel("姓名:");
		label.setBounds(54, 44, 37, 15);
		contentPane.add(label);
		
		name = new JTextField();
		name.setBounds(102, 41, 99, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel label_1 = new JLabel("电话:");
		label_1.setBounds(54, 69, 37, 15);
		contentPane.add(label_1);
		
		phone = new JTextField();
		phone.setBounds(102, 66, 99, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel label_2 = new JLabel("手机:");
		label_2.setBounds(54, 94, 37, 15);
		contentPane.add(label_2);
		
		mobile = new JTextField();
		mobile.setBounds(101, 91, 100, 21);
		contentPane.add(mobile);
		mobile.setColumns(10);
		
		result = new JLabel("xx");
		result.setBounds(295, 69, 83, 15);
		contentPane.add(result);
		
		JButton addbutton = new JButton("添  加");
		addbutton.setActionCommand("add");
		addbutton.addActionListener(this);
		addbutton.setBounds(42, 132, 93, 23);
		contentPane.add(addbutton);
		
		JButton querybutton = new JButton("查  询");
		querybutton.setActionCommand("query");
		querybutton.addActionListener(this);
		querybutton.setBounds(145, 132, 93, 23);
		contentPane.add(querybutton);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{123, 145, 125},
				{778, 767, 777},
			},
			new String[] {
				"\u59D3\u540D", "\u7535\u8BDD", "\u624B\u673A"
			}
		));
		table.setBounds(71, 233, 154, -23);
		contentPane.add(table);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("query")){
			getAmsg();
		}
		
	}
}
