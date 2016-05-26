package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import com.wenbo.util.DateUtils;
import com.wenbo.util.QRCodeUtil;
import com.wenbo.util.TextUtils;
/**
 * 二维码生成器
 * @Description 
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年1月20日 下午5:16:53
 * @version V1.0
 */
public class QRCodeBuilder extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2995703556410990463L;
	private JPanel contentPane;
	private JLabel codeimage;
	private JTextArea textArea;
	private JLabel warnmsg;
	private JLabel logo_prew;
	private JLabel lbllogol;
	private JButton save_button,barbutton;
	private JFileChooser jfc;
	private String path = "";
	private BufferedImage bufferimage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QRCodeBuilder frame = new QRCodeBuilder();
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
	public QRCodeBuilder() {
		setTitle("二维码/条码生成器");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//
		setBounds(100, 100, 586, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		JLabel label = new JLabel("二维码内容:");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(28, 26, 73, 15);
		contentPane.add(label);
		
		JButton button = new JButton("生成二维码");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBounds(139, 96, 121, 23);
		button.addActionListener(this);
		button.setActionCommand("build");
		contentPane.add(button);
		
	     textArea = new JTextArea();
	     textArea.setWrapStyleWord(true);//设置断行不断字
	     textArea.setLineWrap(true);//设置自动换行
	     textArea.setRows(6);
		textArea.setToolTipText("输入网址,数字,或者文字");
		textArea.setBounds(111, 10, 419, 64);
		contentPane.add(textArea);
		
		 codeimage = new JLabel("");
		codeimage.setBounds(111, 144, 300, 300);
	
		contentPane.add(codeimage);
		
		warnmsg = new JLabel("");
		warnmsg.setForeground(Color.RED);
		warnmsg.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		warnmsg.setBounds(28, 119, 502, 15);
		contentPane.add(warnmsg);
		
		logo_prew = new JLabel("");
		logo_prew.setIcon(new ImageIcon(QRCodeBuilder.class.getResource("/res/intel_logo.png")));
		logo_prew.setBounds(10, 179, 59, 54);
		contentPane.add(logo_prew);
		
		lbllogol = new JLabel("默认logo");
		lbllogol.setBounds(10, 243, 59, 15);
		contentPane.add(lbllogol);
		
		JButton choicelogo = new JButton("选择logo");
		choicelogo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		choicelogo.setBounds(10, 133, 93, 23);
		choicelogo.addActionListener(this);
		choicelogo.setActionCommand("file");
		contentPane.add(choicelogo);
		
		save_button= new JButton("保存图片");
		save_button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		save_button.setBounds(447, 273, 113, 23);
		save_button.setVisible(false);
		save_button.setActionCommand("save");
		save_button.addActionListener(this);
		contentPane.add(save_button);
		
	    barbutton = new JButton("生成条码");
	    barbutton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		barbutton.setBounds(318, 96, 121, 23);
		barbutton.setActionCommand("buildbar");
		barbutton.addActionListener(this);
		contentPane.add(barbutton);
		
		jfc=new JFileChooser();  
	}
	/**
	 * 生成二维码
	 * @param content 二维码内容
	 * @param path 文件路径
	 */
	private void showQRCode(String content,String path){
	
		try {
			bufferimage = QRCodeUtil.createQRCode(content,path,true);
			ImageIcon icon = new ImageIcon(bufferimage);
			codeimage.setIcon(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 生成条码
	 * @param content 条码内容
	 * @param path 文件路径
	 */
	private void showBarCode(String content,String path){
		
		try {
			bufferimage = QRCodeUtil.getBarcode(content,240,60,20);
			ImageIcon icon = new ImageIcon(bufferimage);
			codeimage.setIcon(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		//生成二维码
		if(command.equals("build")){
			String content = textArea.getText();
			if(TextUtils.isEmpty(content)){
				warnmsg.setText("请输入内容~");
				return;
			}else{
				warnmsg.setText("");
				showQRCode(content,path);
				save_button.setVisible(true);
			}
			//生成条码
		}else if(command.equals("buildbar")){
			String content = textArea.getText();
			if(TextUtils.isEmpty(content)){
				warnmsg.setText("请输入内容,内容只能为数字,字母");
				return;
			}else{
				if(!content.matches("[A-Za-z0-9]+")){
					warnmsg.setText("条码内容只能为数字,字母或者数字+字母组合");
					return;
				}
				warnmsg.setText("");
				showBarCode(content,path);
				save_button.setVisible(true);
			}
		}
		else if(command.equals("file")){
			//打开文件选择器
			jfc=new JFileChooser();  
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setFileFilter(new ImageFileFilter());//设置文件过滤器
			int state =jfc.showDialog(new JLabel(), "选择文件");  //打开文件选择器后的操作 如果用户取消则返回1 否则返回0
			if(state==1){
				return;
			}
			File file=jfc.getSelectedFile();  
			if(file==null)
				return;
			if(file.isFile()){
				warnmsg.setText("选择文件: "+file.getAbsolutePath());
				path = file.getAbsolutePath();
				ImageIcon icon = new ImageIcon(path);
				logo_prew.setIcon(icon);
			}
		}else if(command.equals("save")){
			
			String filename = DateUtils.getNowTime(DateUtils.DATE_KEY_STR)+".jpg";
			File file = new File(filename);
			jfc=new JFileChooser();  
			jfc.setFileFilter(new ImageFileFilter());//设置文件过滤器
			jfc.setDialogType(JFileChooser.SAVE_DIALOG);
			jfc.setSelectedFile(file);
			int savestate = jfc.showSaveDialog(this);
			if(savestate==JFileChooser.APPROVE_OPTION){//选择的文件名
				file = jfc.getSelectedFile();
				String savepath = file.getAbsolutePath();
				boolean isok = QRCodeUtil.saveQRCode(bufferimage, savepath, false);
				if(isok){
					warnmsg.setText("保存文件在: "+file.getAbsolutePath());
				}else{
					warnmsg.setText("保存失败! ");
				}
				
			}
			
		}
		
	}
	/**
	 * 
	 * @Description 
	 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
	 * @date 2016年1月21日 下午4:38:37
	 * @version V1.0
	 */
	class ImageFileFilter extends FileFilter{

		@Override
		public boolean accept(File file) {
			if(file.isDirectory()){
				return true;
			}
			if(file.getName().endsWith("jpg")||file.getName().endsWith("jpeg")||file.getName().endsWith("gif")||file.getName().endsWith("png")){
				return true;
			}
			return false;
		}

		@Override
		public String getDescription() {
			
			return "图片文件(*.jpg, *.jpeg, *.gif, *.png)";
		}
		
	}
}
