package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wenbo.bean.Record;
import com.wenbo.util.DBUtils;
/**
 * 爱记账
 * @Description 
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年1月27日 下午4:20:15
 * @version V1.0
 */
public class IJizhang extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IJizhang frame = new IJizhang();
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
	public IJizhang() {
		setTitle("爱记账");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//
		setBounds(100, 100, 546, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		JLabel label = new JLabel("记一笔");
		label.setIcon(new ImageIcon(IJizhang.class.getResource("/res/plus.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(97, 10, 114, 45);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("类别:");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(46, 81, 44, 15);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"餐饮", "购物", "出行","生活"}));
		comboBox.setBounds(97, 78, 107, 21);
		contentPane.add(comboBox);
		
		JLabel label_2 = new JLabel("金额:");
		label_2.setBounds(46, 117, 44, 15);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(97, 114, 107, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_3 = new JLabel("说明:");
		label_3.setBounds(46, 153, 44, 15);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 150, 153, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("添  加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(97, 192, 127, 23);
		contentPane.add(btnNewButton);
		
		String [] tableheader = {"日期","金额","类别","说明"};
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 255, 274, 149);
		scrollPane.setViewportView(table);//一定要把使用ViewportView才能显示表头
		contentPane.add(scrollPane);
		
		JLabel label_4 = new JLabel("日期:");
		label_4.setBounds(214, 81, 54, 15);
		contentPane.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(243, 78, 107, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
	
	private void addOneRecord(Record r){
		Connection conn;
		try {
			conn = DBUtils.getConnection();
			Statement state = conn.createStatement();
			//先创建book表 如果不存在
			state.executeUpdate("create table if not exists book (name varchar(20),phone varchar(18),mobile varchar(18))");
			String sql = "insert into book values('" +r.getAmount()+"','"+r.getDate()+"','"+r.getRemark()+"')";
		boolean isOK =	state.execute(sql);
		
			JOptionPane.showMessageDialog(contentPane, "成功添加一条记账~");
		
			state.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("sql错误");
			e.printStackTrace();
		}
		
}
}
