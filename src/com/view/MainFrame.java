package com.view;


import javax.swing.*;
import java.awt.*;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class MainFrame extends JFrame{

    /**
     * 左面板：菜单
     */
    private LeftPanel leftPanel;

    /**
     * 上面板：标题信息显示
     */
    private TopPanel topPanel;


    private static MainFrame mainFrame;
    private JPanel centerPanel;

    private MainFrame() {
        init();
    }

    public static MainFrame getInstance() {

        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    /**
     * 初始方法
     */
    private void init() {
        this.setSize(new Dimension(800,500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("美倩仓库管理系统");

        // 添加面板
        topPanel = TopPanel.getInstance();
        this.add(topPanel, BorderLayout.NORTH);

        leftPanel = LeftPanel.getInstance();
        this.add(leftPanel, BorderLayout.WEST);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.PINK);

        this.add(centerPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /**
     * 更换中心面板方法
     * @param Panel
     */
    public void changeContentPanel(JPanel Panel){
        centerPanel.removeAll();
        centerPanel.add(Panel, BorderLayout.CENTER);
        centerPanel.validate();
        centerPanel.repaint();
    }
}
