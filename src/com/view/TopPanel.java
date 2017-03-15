package com.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class TopPanel extends JPanel {

    /**
     * 标题标签
     */
    private JLabel lbSysName;

    private static TopPanel topPanel;

    private TopPanel() {
        init();
    }

    public static TopPanel getInstance() {
        if (topPanel == null) {
            topPanel = new TopPanel();
        }
        return topPanel;
    }

    /**
     * 初始方法
     */
    private void init() {

       // this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(0, 80));
        this.setBackground(Color.PINK);

        Font font = new Font("微软雅黑", Font.BOLD, 32);

        lbSysName = new JLabel();
        lbSysName.setFont(font);
        lbSysName.setForeground(Color.black);
        lbSysName.setText("美倩仓库管理系统");
        this.add(lbSysName,BorderLayout.CENTER);
    }

}
