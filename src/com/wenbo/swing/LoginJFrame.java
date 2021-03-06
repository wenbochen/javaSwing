package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.wenbo.util.APPConfig;
import com.wenbo.util.DBUtils;

public class LoginJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6736562971715912787L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passwordField;
	private  JLabel msg;
	static LoginJFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new LoginJFrame();
					frame.setVisible(true);
					String	dbName =APPConfig.init().getKey("dbName");
					boolean hasfind = findDBFile(dbName);
					if(hasfind){
						
					}else{
						DBUtils.init().createTables();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
/**
 * 检查数据库文件是否存在
 * @param dbname
 * @return
 */
	private static boolean findDBFile(String dbname){
		File file = new File(dbname);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Create the frame.
	 */
	public LoginJFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginJFrame.class.getResource("/res/share_to_wx_icon.png")));
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setTitle("文博系统客户端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		 panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(0, 0, 0, 0);
		contentPane.add(panel);
		 panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		 
		 JPanel topborder = new JPanel();
		 topborder.setBackground(new Color(32, 178, 170));
		 topborder.setBounds(0, 0, 450, 36);
		 contentPane.add(topborder);
		 
		 JLabel lblNewLabel = new JLabel("欢迎登陆");
		 lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		 lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		 lblNewLabel.setBounds(148, 46, 115, 24);
		 contentPane.add(lblNewLabel);
		 
		 JLabel lblNewLabel_1 = new JLabel("认证方式:");
		 lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 lblNewLabel_1.setBounds(64, 68, 61, 15);
		 contentPane.add(lblNewLabel_1);
		 
		 JComboBox comboBox = new JComboBox();
		 comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 comboBox.setModel(new DefaultComboBoxModel(new String[] {"邮箱认证", "手机认证", "扫码认证"}));
		 comboBox.setBounds(124, 65, 156, 21);
		 contentPane.add(comboBox);
		 
		 JLabel label = new JLabel("用户名:");
		 label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 label.setBounds(64, 93, 54, 15);
		 contentPane.add(label);
		 
		 username = new JTextField();
		 username.setText("wenbo");
		 username.setBounds(124, 93, 156, 21);
		 contentPane.add(username);
		 username.setColumns(10);
		 
		 JLabel label_1 = new JLabel("密  码:");
		 label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 label_1.setBounds(64, 127, 54, 15);
		 contentPane.add(label_1);
		 
		 passwordField = new JPasswordField();
		 passwordField.setText("1120");
		 passwordField.setBounds(124, 124, 156, 21);
		 contentPane.add(passwordField);
		 
		 JCheckBox checkBox = new JCheckBox("记住密码");
		 checkBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 checkBox.setSelected(true);
		 checkBox.setBounds(93, 164, 103, 23);
		 contentPane.add(checkBox);
		 
		 JButton loginbutton = new JButton("登  陆");
		 loginbutton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 loginbutton.setBounds(64, 199, 93, 23);
		 loginbutton.setActionCommand("login");
		 loginbutton.addActionListener(new OnClickListener());
		 loginbutton.registerKeyboardAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					System.out.println("用户点击登录按钮");
					String name = username.getText();
					String pwd = passwordField.getText();
					if(name.equals("wenbo")&&pwd.equals("1120")){
						frame.dispose();
						MainFrame mframe = new MainFrame();
						mframe.setVisible(true);
					}else{
						msg.setText("*用户名或密码不匹配*");
					}
					
					
			
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		 
		 msg = new JLabel("");
		 msg.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 msg.setBounds(290, 108, 122, 15);
		 msg.setForeground(Color.RED);
		 contentPane.add(msg);
		 contentPane.add(loginbutton);
		 
		 JButton resetbutton = new JButton("取  消");
		 resetbutton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		 resetbutton.setBounds(187, 199, 93, 23);
		 resetbutton.setActionCommand("reset");
		 resetbutton.addActionListener(new OnClickListener());
		 contentPane.add(resetbutton);
		 
	}
	
	/**
	 * 监控点击事件
	 * @Description 
	 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
	 * @date 2016年1月15日 下午3:18:01
	 * @version V1.0
	 */
	class OnClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("login")){
				System.out.println("用户点击登录按钮");
				String name = username.getText();
				String pwd = passwordField.getText();
				if(name.equals("wenbo")&&pwd.equals("1120")){
					frame.dispose();
					MainFrame mframe = new MainFrame();
					mframe.setVisible(true);
				}else{
					msg.setText("*用户名或密码不匹配*");
				}
				
				
			}else{
				
				int config = JOptionPane.showConfirmDialog(frame, "你确定要退出系统吗?","温馨提示",JOptionPane.YES_NO_CANCEL_OPTION);
				if(config==0){//点击是
					System.exit(0);//退出系统
				}else{
					//用户点击取消
					
				}
//				JOptionPane.showMessageDialog(frame, "你确定要退出吗?");
//				System.out.println("用户点击取消按钮");
//				AlertDialog opendialog = new AlertDialog("你确定要退出吗?");
//				opendialog.setVisible(true);
//				opendialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
//				opendialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//System.exit(0);
			}
		}
		
	}
}
