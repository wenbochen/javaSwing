package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class SwingDrag {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingDrag window = new SwingDrag();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SwingDrag() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setTitle("\u9510\u6377\u8BA4\u8BC1\u5BA2\u6237\u7AEF");
        frame.setBackground(new Color(204, 255, 204));
        frame.setBounds(100, 100, 452, 418);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 0, 0);
        panel.setBackground(new Color(255, 255, 204));
        frame.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        Panel panel_1 = new Panel();
        panel_1.setBackground(Color.GREEN);
        panel_1.setBounds(0, 0, 436, 59);
        frame.getContentPane().add(panel_1);
        
        JLabel lblNewLabel = new JLabel("Supplicant 4.44-1220");
        lblNewLabel.setBounds(10, 71, 145, 15);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("\u8BA4\u8BC1\u65B9\u5F0F\uFF1A");
        lblNewLabel_1.setBounds(53, 104, 69, 15);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("\u7528 \u6237 \u540D\uFF1A");
        lblNewLabel_2.setBounds(53, 140, 69, 15);
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("\u5BC6    \u7801\uFF1A");
        lblNewLabel_3.setBounds(53, 177, 69, 15);
        frame.getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("\u670D    \u52A1\uFF1A");
        lblNewLabel_4.setBounds(53, 216, 69, 15);
        frame.getContentPane().add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("\u8BED    \u8A00\uFF1A");
        lblNewLabel_5.setBounds(53, 257, 69, 15);
        frame.getContentPane().add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("\u7F51    \u5361\uFF1A");
        lblNewLabel_6.setBounds(53, 296, 69, 15);
        frame.getContentPane().add(lblNewLabel_6);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7528\u6237\u540D\u5BC6\u7801\u8BA4\u8BC1\uFF08\u6709\u7EBF\uFF09", "\u65E0\u7EBF\u8BA4\u8BC1"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(132, 101, 239, 21);
        frame.getContentPane().add(comboBox);
        
        textField = new JTextField();
        textField.setBounds(132, 137, 239, 21);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(132, 174, 239, 21);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"internet1", "internet2", "internet3", "internet4"}));
        comboBox_1.setBounds(132, 213, 239, 21);
        frame.getContentPane().add(comboBox_1);
        
        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u4E2D\u6587", "English"}));
        comboBox_2.setBounds(132, 254, 239, 21);
        frame.getContentPane().add(comboBox_2);
        
        JComboBox comboBox_3 = new JComboBox();
        comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Brodcam  4313GN 802.11", "Realtek RTL8102E/RTL8103E \u7CFB\u5217 PCI-E"}));
        comboBox_3.setBounds(132, 293, 239, 21);
        frame.getContentPane().add(comboBox_3);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("\u8BB0\u4F4F\u5BC6\u7801");
        rdbtnNewRadioButton.setBackground(Color.WHITE);
        rdbtnNewRadioButton.setBounds(278, 320, 91, 23);
        frame.getContentPane().add(rdbtnNewRadioButton);
        
        JButton button = new JButton("\u8FDE\u63A5");
        button.setBounds(35, 347, 83, 23);
        frame.getContentPane().add(button);
        
        JButton button_1 = new JButton("\u9000\u51FA");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_1.setBounds(135, 347, 83, 23);
        frame.getContentPane().add(button_1);
        
        JButton button_2 = new JButton("\u8BBE\u7F6E");
        button_2.setBounds(343, 347, 83, 23);
        frame.getContentPane().add(button_2);
        
        JButton btnNewButton = new JButton("\u6D88\u606F\u7BA1\u7406");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnNewButton.setBounds(234, 347, 91, 23);
        frame.getContentPane().add(btnNewButton);
    }
}