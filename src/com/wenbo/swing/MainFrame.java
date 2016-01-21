package com.wenbo.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3527800456463557059L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("欢迎你-文博");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 442);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);//不设置布局方式
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(6, 0, 152, 24);
		contentPane.add(menuBar);
		
		JMenu filemenu = new JMenu("文件");
		menuBar.add(filemenu);
		
		JMenuItem newfile = new JMenuItem("新建");
		filemenu.add(newfile);
		
		JMenuItem openfile = new JMenuItem("打开");
		filemenu.add(openfile);
		
		JMenuItem savefile = new JMenuItem("保存");
		filemenu.add(savefile);
		
		JMenuItem menuItem = new JMenuItem("退出");
		filemenu.add(menuItem);
		
		JMenu editmenu = new JMenu("编辑");
		menuBar.add(editmenu);
		
		JMenuItem menuItem_3 = new JMenuItem("剪切");
		editmenu.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("复制");
		editmenu.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("粘贴");
		editmenu.add(menuItem_5);
		
		JMenu menu_2 = new JMenu("搜索");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_6 = new JMenuItem("关键词");
		menu_2.add(menuItem_6);
		
		JMenu menu_3 = new JMenu("关于");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_7 = new JMenuItem("关于我们");
		menu_3.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("帮助");
		menu_3.add(menuItem_8);
		
		final JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("主菜单") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("入门小程序");
						node_1.add(new DefaultMutableTreeNode("简易计算器"));
						node_1.add(new DefaultMutableTreeNode("双色球生成器"));
						node_1.add(new DefaultMutableTreeNode("通讯录"));
						node_1.add(new DefaultMutableTreeNode("二维码生成器"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("中级");
						node_1.add(new DefaultMutableTreeNode("basketball"));
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
						node_1.add(new DefaultMutableTreeNode("hockey"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("高级功能");
						node_1.add(new DefaultMutableTreeNode("hot dogs"));
						node_1.add(new DefaultMutableTreeNode("pizza"));
						node_1.add(new DefaultMutableTreeNode("ravioli"));
						node_1.add(new DefaultMutableTreeNode("bananas"));
					add(node_1);
				}
			}
		));
		tree.setBounds(16, 36, 142, 334);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node ==null)
					return;
				Object bean = node.getUserObject();
				if(node.isLeaf()){ //是叶子节点
					String name = bean.toString();
					System.out.println(name);
					if(name.equals("简易计算器")){
						SimpleCalculator jsq = new SimpleCalculator();
						jsq.setVisible(true);
					}else if(name.equals("双色球生成器")){
						DoubleBall db = new DoubleBall();
						db.setVisible(true);
					}else if(name.equals("通讯录")){
						AddressBook book = new AddressBook();
						book.setVisible(true);
					}
					
				}
				
			}
		});
		contentPane.add(tree);
	}
}
