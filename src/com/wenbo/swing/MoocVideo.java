package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wenbo.util.FileUtils;

/**
 * 
 * @Description 慕课网下载视频重命名
 * @author <a href="http://my.oschina.net/chenbo">chenbo</a>
 * @date 2016年11月18日 下午2:16:06
 * @version V1.0
 */
public class MoocVideo extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8136138468225463593L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel label ;
	private JFileChooser jfc;
	private String path = "";
	private String savepath = "";
	int count = 1;
	private JTextField targetField;
	private JButton btnsave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoocVideo frame = new MoocVideo();
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
	public MoocVideo() {
		setTitle("慕课网视频导出工具");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//
		setBounds(100, 100, 586, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setBounds(63, 42, 281, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton choiceFile = new JButton("选择文件夹");
		choiceFile.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		choiceFile.setBounds(354, 41, 133, 23);
		choiceFile.addActionListener(this);
		choiceFile.setActionCommand("sourcefile");
		contentPane.add(choiceFile);
		
		 label = new JLabel("正在处理");
		 label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(63, 208, 424, 15);
		contentPane.add(label);
		
		JButton importbutton = new JButton("导出");
		importbutton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		importbutton.setBounds(354, 163, 133, 23);
		importbutton.addActionListener(this);
		importbutton.setActionCommand("import");
		contentPane.add(importbutton);
		
		targetField = new JTextField();
		targetField.setBounds(63, 97, 281, 21);
		contentPane.add(targetField);
		targetField.setColumns(10);
		
		btnsave = new JButton("保存在");
		btnsave.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnsave.setBounds(355, 96, 132, 23);
		btnsave.addActionListener(this);
		btnsave.setActionCommand("savepath");
		contentPane.add(btnsave);
		
		jfc=new JFileChooser();  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		 if(command.equals("sourcefile")){
				//打开文件选择器
				jfc=new JFileChooser();  
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int state =jfc.showDialog(new JLabel(), "选择视频源文件夹");  //打开文件选择器后的操作 如果用户取消则返回1 否则返回0
				if(state==1){
					return;
				}
				File file=jfc.getSelectedFile();  
			
				if(file.isDirectory()){
					textField.setText(file.getAbsolutePath());
					path = file.getAbsolutePath();
					
				
				}
			}else if(command.equals("savepath")){

				//打开文件选择器
				jfc=new JFileChooser();  
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int state =jfc.showDialog(new JLabel(), "选择保存文件夹");  //打开文件选择器后的操作 如果用户取消则返回1 否则返回0
				if(state==1){
					return;
				}
				File file=jfc.getSelectedFile();  
			
				if(file.isDirectory()){
					
					savepath = file.getAbsolutePath();
					targetField.setText(file.getAbsolutePath());
				}
				
			}else if(command.equals("import")){
				File smulu = new File(path);
				String filelist[] = smulu.list();
				for(int i=0;i<filelist.length;i++){
					File f = new File(path+"\\"+filelist[i]);
					if(f.isDirectory()){
						listFile(f);
					}
				}
			}
		
	}
	
	public void listFile(File dir){
		String filelist[] = dir.list();
		String mpath = dir.getAbsolutePath();
		File mp4file = null;
		for(int i=0;i<filelist.length;i++){
			File f = new File(mpath+"\\"+filelist[i]);
			if(f.isFile()){
				
				String ext = FileUtils.getFileName(f.getAbsolutePath());
				if(ext.contains("mp4")){
					mp4file =f;
				}else if(ext.contains("json")){
					String content = FileUtils.read(f.getAbsolutePath());
					JSONObject json = JSONObject.parseObject(content);
					String name =json.getString("sectionName");
					System.out.println(count +"_"+name+" "+f.getAbsolutePath());
					String filename = count +"_"+name+".mp4";
					File topath = new File(savepath+"\\"+filename);
					label.setText("正在处理"+count +"_"+name+" "+f.getAbsolutePath());
					FileUtils.CopyFile(mp4file, topath);
					count++;
				
				}
				
				
			}
		}
	}
}
