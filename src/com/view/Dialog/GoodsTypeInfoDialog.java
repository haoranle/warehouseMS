package com.view.Dialog;

import com.bojo.GoodsType;
import com.dao.GoodsTypeDao;
import com.view.contentPanel.GoodsPanel;
import com.view.contentPanel.GoodsTypePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class GoodsTypeInfoDialog extends JDialog{

    private GoodsTypeDao goodsTypeDao = new GoodsTypeDao();

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblName;
    private JTextField txtName;

    private GoodsType goodsType;

    private JButton btnSave;
    public GoodsTypeInfoDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public GoodsTypeInfoDialog(JFrame mainFrame, GoodsType goodsType){
        super(mainFrame);
        this.goodsType = goodsType;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("商品类型编号");
        txtId = createTextField();

        lblName = createLabel("类型名称");
        txtName = createTextField();

        if (goodsType != null) {
            txtId.setText(goodsType.getId());
            txtId.setEditable(false);
            txtName.setText(goodsType.getName());
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsType goodsTypeTemp = new GoodsType();
                goodsTypeTemp.setId(txtId.getText());
                goodsTypeTemp.setName(txtName.getText());

                boolean flag = false;
                if (goodsType == null) {
                    flag = goodsTypeDao.save(goodsTypeTemp);
                } else {
                    flag = goodsTypeDao.updete(goodsTypeTemp);
                }

                if (flag) {
                    GoodsTypeInfoDialog.this.dispose();
                    GoodsTypePanel.getInstance().refreshData();
                }else {
                    GoodsTypeInfoDialog.this.setTitle("数据库操作错误...");
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
