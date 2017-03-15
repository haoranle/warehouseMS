package com.view.login;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ${Seek} on 2016/12/18.
 */
public class LoginJFrame extends JFrame {

	private static LoginJPanel loginJPanel = null;

	static {
		loginJPanel = new LoginJPanel();
	}

	private static LoginJFrame loginJFrame;

	private LoginJFrame() throws HeadlessException {
		init();
	}

	public static LoginJFrame getInstance(){
		if (loginJFrame == null) {
			loginJFrame = new LoginJFrame();
		}
		return loginJFrame;
	}

	private void init() {
		this.add(loginJPanel);// 为窗体加入面板
		this.setTitle(" Login  ");
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);// 居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public LoginJPanel getLoginJPanel() {
		return loginJPanel;
	}
}
