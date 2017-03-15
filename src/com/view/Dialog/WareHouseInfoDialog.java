package com.view.Dialog;

import com.bojo.Manager;
import com.bojo.WareHouse;
import com.dao.WareHouseDao;
import com.view.contentPanel.ManagerPanel;
import com.view.contentPanel.WareHousePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class WareHouseInfoDialog extends JDialog{

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblNumber;
    private JTextField txtNumber;

    private JLabel lblRemarks;
    private JTextArea txaRrmarks;

    private WareHouseDao wareHouseDao = new WareHouseDao();
    private WareHouse wareHouse;
    private JButton btnSave;

    public WareHouseInfoDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public WareHouseInfoDialog(JFrame mainFrame, WareHouse wareHouse){
        super(mainFrame);
        this.wareHouse = wareHouse;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("仓库编号");
        txtId = createTextField();

        lblName = createLabel("仓库名称");
        txtName = createTextField();

        lblNumber = createLabel("仓库代号");
        txtNumber = createTextField();

        lblRemarks = createLabel("备注");
        txaRrmarks = new JTextArea(4,20);
        this.add(txaRrmarks);

        if (wareHouse != null) {
            txtId.setText(wareHouse.getId());
            txtId.setEditable(false);
            txtName.setText(wareHouse.getName());
            txtNumber.setText(wareHouse.getNumber());
            txaRrmarks.setText(wareHouse.getRemarks());
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WareHouse wareHouseTemp = new WareHouse();
                wareHouseTemp.setId(txtId.getText());
                wareHouseTemp.setName(txtName.getText());
                wareHouseTemp.setNumber(txtNumber.getText());
                wareHouseTemp.setRemarks(txaRrmarks.getText());

                boolean flag = false;
                if (wareHouse == null) {
                    flag = wareHouseDao.save(wareHouseTemp);
                } else {
                    flag = wareHouseDao.updete(wareHouseTemp);
                }

                if (flag) {
                    WareHouseInfoDialog.this.dispose();
                    WareHousePanel.getInstance().refreshData();
                }else {
                    WareHouseInfoDialog.this.setTitle("数据库操作错误...");
                }
            }
        });
        this.add(btnSave);

        this.setVisible(true);
    }

    /**
     * 创建标签
     * @param name
     * @return
     */
    private JLabel createLabel(String name) {
        JLabel lbl = new JLabel(name);
        lbl.setPreferredSize(new Dimension(120, 30));
        this.add(lbl);
        return lbl;
    }

    /**
     * 创建文本框
     * @return
     */
    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setPreferredSize(new Dimension(220, 30));
        this.add(txt);
        return txt;
    }
}
