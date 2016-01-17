package com.wenbo.widget;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 弹出的提示框
 * @author admin
 *
 */
public class AlertDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5934726234041750721L;
	/**
	 * 
	 * @param msg 要显示的信息
	 */
	public AlertDialog(String msg){
		setTitle("温馨提示");
		setBounds(100, 100, 225, 150);
		JLabel msginfo = new JLabel(msg);
		JButton okbutton = new JButton("知道了");
		msginfo.setHorizontalAlignment(SwingConstants.CENTER);     //内容显示在窗口的中央
		okbutton.setBounds(160, 109, 117, 29);
		this.getContentPane().add(msginfo,BorderLayout.CENTER);
		this.getContentPane().add(okbutton);
		
		
	}
	
	class OnClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("OK")){
			
				
			}else{
			
			}
			
		}
		
	}
}
