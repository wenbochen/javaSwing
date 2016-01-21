package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wenbo.bean.Person;
import com.wenbo.util.DBUtils;
import com.wenbo.util.TextUtils;
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
	private JLabel warnMsg;
	private JTable table;
	private JTextField input;

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
	/**
	 * 根据输入关键词查询
	 * @param keyword
	 */
private void getTable(String keyword){
	try {
		Connection conn =DBUtils.getConnection();
		Statement state = conn.createStatement();
		//先创建book表 如果不存在
		state.executeUpdate("create table if not exists book (name varchar(20),phone varchar(18),mobile varchar(18))");
		String sql = "select * from book where name like '%"+keyword+"%' or phone like '%"+keyword+"%' or mobile like '%"+keyword
				+"%'";
		ResultSet rs = state.executeQuery(sql);
//		String [] tableheader = {"姓名","电话","手机"};
		Vector<String> tableheader = new Vector<String>();
		tableheader.addElement("姓名");
		tableheader.addElement("电话");
		tableheader.addElement("手机");
		Vector<Vector<String>>  vdata = new Vector<Vector<String>> ();
		int count =0;
		while(rs.next()){
			Vector<String>  vrow = new Vector<String> ();
			String name = rs.getString(1);
			String phone = rs.getString(2);
			String mobile = rs.getString(3);
			vrow.addElement(name);
			vrow.addElement(phone);
			vrow.addElement(mobile);
			vdata.addElement(vrow);
//			System.out.println(name+" ==="+phone+"==="+mobile);
//			result.setText(name+" ==="+phone+"==="+mobile);
			count++;
		}
		result.setText("共查询到 "+count+" 条记录");
		DefaultTableModel model = new DefaultTableModel(vdata,tableheader){
			 public boolean isCellEditable(int row, int column) { 
				    return false; 
				    //不可编辑表格
				  } 
		};
		table.setModel(model);
		table.repaint();
		table.updateUI();
		rs.close();
		conn.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * 增加一条记录
 * @param P
 */
private void addOnePerson(Person p){
	Connection conn;
	try {
		conn = DBUtils.getConnection();
		Statement state = conn.createStatement();
		//先创建book表 如果不存在
		state.executeUpdate("create table if not exists book (name varchar(20),phone varchar(18),mobile varchar(18))");
		String sql = "insert into book values('" +p.getName()+"','"+p.getPhone()+"','"+p.getMobile()+"')";
	boolean isOK =	state.execute(sql);
	
		result.setText("成功添加一条记录~");
	
		state.close();
		conn.close();
	} catch (SQLException e) {
		System.out.println("sql错误");
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
		
	    warnMsg = new JLabel("");
		warnMsg.setForeground(Color.RED);
		warnMsg.setBounds(54, 19, 199, 15);
		contentPane.add(warnMsg);
		
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
		
		result = new JLabel("");
		result.setBounds(234, 97, 172, 15);
		contentPane.add(result);
		
		JButton addbutton = new JButton("添  加");
		addbutton.setActionCommand("add");
		addbutton.addActionListener(this);
		addbutton.setBounds(211, 65, 93, 23);
		contentPane.add(addbutton);
		
		JButton querybutton = new JButton("查  询");
		querybutton.setActionCommand("query");
		querybutton.addActionListener(this);
		querybutton.setBounds(211, 124, 93, 23);
		contentPane.add(querybutton);
		String [] tableheader = {"姓名","电话","手机"};
		Object[][] data =null;
		DefaultTableModel model = new DefaultTableModel(data,tableheader){
			 public boolean isCellEditable(int row, int column) { 
				    return false; 
				    //不可编辑表格
				  } 
		};
		
		table = new JTable();
		table.setModel(model);
		table.setSize(180, 131);
		//contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 168, 278, 131);
		scrollPane.setViewportView(table);//一定要把table添加到JScrollPane面板上才能显示表头
		contentPane.add(scrollPane);
		
		input = new JTextField();
		input.setToolTipText("输入关键字:如姓名 手机号");
		input.setBounds(54, 125, 147, 21);
		contentPane.add(input);
		input.setColumns(10);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("add")){
			String namevalue = name.getText();
			String phonevalue = phone.getText();
			String mobilevalue =mobile.getText();
			if(TextUtils.isEmpty(namevalue)){
				warnMsg.setText("请输入姓名");
				return;
			}
			if(TextUtils.isEmpty(phonevalue)&&TextUtils.isEmpty(mobilevalue)){
				warnMsg.setText("电话号码和手机号至少输入一个");
				return;
			}
		
			warnMsg.setText("");
			Person p = new Person();
			p.setName(namevalue);
			p.setPhone(phonevalue);
			p.setMobile(mobilevalue);
			addOnePerson(p);
			
		}else if(command.equals("query")){
			String keyword = input.getText();
			if(TextUtils.isEmpty(keyword)){
				warnMsg.setText("请输入关键词后查询");
				return;
			}else{
				warnMsg.setText("");
			}
			
			getTable(keyword);
		}
		
	}
}
