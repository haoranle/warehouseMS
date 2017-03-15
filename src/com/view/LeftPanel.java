package com.view;

import com.bojo.Manager;
import com.bojo.Shelf;
import com.bojo.Supplier;
import com.view.contentPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class LeftPanel extends JPanel{

    /**
     * 商品管理面板
     */
    private GoodsPanel goodsPanel;

    /**
     * 商品类型管理面板
     */
    private GoodsTypePanel goodsTypePanel;

    /**
     * 管理员管理面板
     */
    private ManagerPanel managerPanel;

    /**
     * 货架管理
     */
    private ShelfPanel shelfPanel;

    /**
     * 供应商管理
     */
    private SupplierPanel supplierPanel;

    /**
     * 仓库管理
     */
    private WareHousePanel wareHousePanel;

    /**
     * 进货单管理
     */
    private WareInPanel wareInPanel;

    /**
     * 进货明细管理
     */
    private WareInListPanel wareInListPanel;

    /**
     * 出货单管理
     */
    private WareOutPanel wareOutPanel;

    /**
     * 出货明细管理
     */
    private WareOutListPanel wareOutListPanel;




    private static LeftPanel leftPanel;

    private LeftPanel() {
        init();

    }

    public static LeftPanel getInstance() {
        if (leftPanel == null) {
            leftPanel = new LeftPanel();
        }
        return leftPanel;
    }

    /**
     * 初始方法
     */
    private void init() {
        this.setBackground(Color.PINK);
        goodsPanel = GoodsPanel.getInstance();
        goodsTypePanel = GoodsTypePanel.getInstance();
        managerPanel = ManagerPanel.getInstance();
        shelfPanel = ShelfPanel.getInstance();
        supplierPanel = SupplierPanel.getInstance();
        wareHousePanel = WareHousePanel.getInstance();
        wareInPanel = WareInPanel.getInstance();
        wareOutPanel = WareOutPanel.getInstance();
        wareInListPanel = WareInListPanel.getInstance();
        wareOutListPanel = WareOutListPanel.getInstance();

        this.setPreferredSize(new Dimension(120,0));

        // 设置水平垂直间距
        ((FlowLayout)this.getLayout()).setHgap(5);
        ((FlowLayout)this.getLayout()).setVgap(10);

        // 商品管理按钮
        this.add(createMenuButton("商品管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(goodsPanel);
            }
        }));

        // 商品类型管理按钮
        this.add(createMenuButton("商品类型管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(goodsTypePanel);
            }
        }));

        // 管理员管理按钮
        this.add(createMenuButton("管理员管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(managerPanel);
            }
        }));
        // 货架管理按钮
        this.add(createMenuButton("货架管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(shelfPanel);
            }
        }));
        // 供应商管理按钮
        this.add(createMenuButton("供应商管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(supplierPanel);
            }
        }));
        // 仓库管理按钮
        this.add(createMenuButton("仓库管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(wareHousePanel);
            }
        }));
        // 进货单管理按钮
        this.add(createMenuButton("进货单管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(wareInPanel);
            }
        }));
        // 进货明细管理按钮
        this.add(createMenuButton("进货明细管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(wareInListPanel);
            }
        }));
        // 出货单管理按钮
        this.add(createMenuButton("出货单管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(wareOutPanel);
            }
        }));
        // 出货明细管理按钮
        this.add(createMenuButton("出货明细管理", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().changeContentPanel(wareOutListPanel);
            }
        }));

    }

    /**
     * 创建菜单按钮并添加监听事件
     * @param name
     * @param listener
     * @return
     */
    private JButton createMenuButton(String name, ActionListener listener){
        JButton bu = new JButton(name);
        bu.setPreferredSize(new Dimension(100,25));
        Font font = new Font("微软雅黑", Font.PLAIN, 11);
        bu.setForeground(Color.black);
        bu.setFont(font);
        bu.addActionListener(listener);
        return bu;
    }

}
