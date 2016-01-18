package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Random;
/**
 * 双色球摇奖机
 * @author admin
 *
 */
public class DoubleBall extends JFrame implements ActionListener,Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640921399000089279L;
	private JPanel contentPane;
	private JTextField redball;
	private JTextField blueball;
	private JButton startbutton ;
	private JButton stopbutton ;
	static DoubleBall frame;
	private Thread t;
	boolean isRun = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new DoubleBall();
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
	public DoubleBall() {
		setTitle("双色球摇奖机");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		t = new Thread(this);
		JLabel lblNewLabel = new JLabel("福彩双色球摇奖机");
		lblNewLabel.setBounds(146, 25, 123, 16);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("红球:");
		label.setBounds(31, 72, 61, 16);
		contentPane.add(label);
		
		redball = new JTextField();
		redball.setBounds(66, 65, 186, 28);
		contentPane.add(redball);
		redball.setColumns(10);
		
		JLabel label_1 = new JLabel("蓝球:");
		label_1.setBounds(258, 72, 39, 16);
		contentPane.add(label_1);
		
		blueball = new JTextField();
		blueball.setBounds(293, 66, 61, 28);
		contentPane.add(blueball);
		blueball.setColumns(10);
		
		startbutton= new JButton("开  始");
		startbutton.setActionCommand("start");
		startbutton.addActionListener(this);
		startbutton.setBounds(135, 118, 117, 29);
		contentPane.add(startbutton);
		
		stopbutton = new JButton("停  止");
		stopbutton.setActionCommand("stop");
		stopbutton.addActionListener(this);
		stopbutton.setBounds(135, 155, 117, 29);
		contentPane.add(stopbutton);
		
		JButton resetbutton = new JButton("重  选");
		resetbutton.setActionCommand("reset");
		resetbutton.addActionListener(this);
		resetbutton.setBounds(135, 191, 117, 29);
		contentPane.add(resetbutton);
	}

	private void getOneTicket(){
		int redarray[] = new int[6];//new一个数组来存放红球
		Random r = new Random();
		int red_no =0;
		int blue_no = r.nextInt(16)+1;//蓝球号码
		int count =0;//生成个数
		while(count<6){
			boolean flag = true;
			red_no =r.nextInt(33)+1;
			for(int i=0;i<count;i++){
				if(redarray[i]==red_no){
					//如果已存在 跳出
					flag = false;
					break;
				}
			}
			if(flag){ //如果不存在 放入红球数组
				redarray[count]=red_no;
				count++;
			}
		}
		Arrays.sort(redarray);//从大到小排序
		StringBuilder sb = new StringBuilder();
		int temp;
		String str;
		for(int i=0;i<6;i++){
			temp = redarray[i];
			if(temp<10){//如果小于10 前面加10
				str = "0"+temp;
			}else{
				str = temp+"";
			}
			if(i==0){
				sb.append(str);
			}else{
				sb.append("-"+str);
			}
		}
		redball.setText(sb.toString());
		if(blue_no<10){//如果小于10 前面加0
			str = "0"+blue_no;
		}else{
			str = blue_no+"";
		}
		blueball.setText(str);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("start")){
			startbutton.setEnabled(false);
			stopbutton.setEnabled(true);
			if(isRun){
				t.start();
			
			}else{
				isRun = true;
			}
					
		}else if(command.equals("stop")){
				isRun = false;
				startbutton.setEnabled(true);
				stopbutton.setEnabled(false);
		}else{
			redball.setText("00-00-00-00-00-00");
			blueball.setText("00");
			isRun = false;
			startbutton.setEnabled(true);
			stopbutton.setEnabled(false);
			JOptionPane.showMessageDialog(frame, "奖池已清空,请按开始按钮重新开始~");
		}

		
	}

	@Override
	public void run() {
		//使用线程实现滚动
		while(true){
			if(isRun){
				getOneTicket();
				try {
					Thread.sleep(100);//休眠0.1s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//
			}else{
				try {
					Thread.sleep(1000);//休眠1s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//
			}
		
			
		}
		
	
		
	}
}
