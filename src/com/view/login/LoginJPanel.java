package com.view.login;

import com.bojo.Manager;
import com.dao.ManagerDao;
import com.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ${Seek} on 2016/12/18.
 */
public class LoginJPanel extends JPanel {

	// 定义组件
	private JButton buttonOK = null;
	private JButton buttonCANEL = null;//按钮

	// 文本
	private JTextField nameField = null;

	// 密码
	private JPasswordField jPasswordField = null;

	// 标签
	private JLabel nameLabel = null;
	private JLabel passwdLabel = null;
	private JLabel welcomeLabel = null;

	//三个面板
	private JPanel jPaneUP = null;
	private JPanel jPaneCENTER = null;
	private JPanel jPaneDOWN = null;
	private JPanel jpaneTop = null;

	public LoginJPanel() {
		this.creatPan();
		loginListener();
	}

	//创建面板
	public void creatPan(){

		//创建
		jpaneTop = new JPanel();
		jPaneUP = new JPanel();
		jPaneCENTER = new JPanel();
		jPaneDOWN = new JPanel();

		buttonOK = new JButton("Sign in");
		buttonCANEL = new JButton("Reset");

		nameField = new JTextField(10);
		jPasswordField = new JPasswordField(10);

		Font f = new Font("微软雅黑",Font.BOLD ,20);

		nameLabel = new JLabel("      StuId   ");
		passwdLabel = new JLabel("password");
		welcomeLabel = new JLabel(" 美倩仓库管理 ");

		welcomeLabel.setFont(f);

		//面板上贴面板
		this.add(jpaneTop);
		this.add(jPaneUP);
		this.add(jPaneCENTER);
		this.add(jPaneDOWN);

		//设置布局管理器
		this.setLayout(new GridLayout(4,1,5,5));

		jpaneTop.add(welcomeLabel);

		//加组件
		//上面板
		jPaneUP.add(nameLabel);
		jPaneUP.add(nameField);

		//中面板
		jPaneCENTER.add(passwdLabel);
		jPaneCENTER.add(jPasswordField);

		//下面板
		jPaneDOWN.add(buttonOK);
		jPaneDOWN.add(buttonCANEL);
	}

	private void loginListener(){
		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account = nameField.getText();
				String password = String.copyValueOf(jPasswordField.getPassword());
				// account = "1";
				// password = "1";
				System.out.println(account);
				System.out.println(password);
				if (account.trim().equals("") || password.trim().equals("")) {
					// TODO
					return;
				}

				ManagerDao managerDao = new ManagerDao();
				System.out.println(managerDao.seleteAccountOfPassword(account, password));
				if (managerDao.seleteAccountOfPassword(account, password)) {
					LoginJFrame.getInstance().setVisible(false);
					MainFrame.getInstance();
				}
			}
		});
	}

	public JButton getButtonOK() {
		return buttonOK;
	}

	public JButton getButtonCANEL() {
		return buttonCANEL;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JPasswordField getjPasswordField() {
		return jPasswordField;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getPasswdLabel() {
		return passwdLabel;
	}

	public JPanel getjPaneUP() {
		return jPaneUP;
	}

	public JPanel getjPaneCENTER() {
		return jPaneCENTER;
	}

	public JPanel getjPaneDOWN() {
		return jPaneDOWN;
	}
}