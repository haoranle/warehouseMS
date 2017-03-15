package com.view.Dialog;

import com.bojo.*;
import com.dao.*;
import com.view.contentPanel.WareOutListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by 李浩然 on 2017/1/10.
 */
public class WareOutListDialog extends JDialog{


    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblAmount;
    private JTextField txtAmount;

    private JLabel lblGoods;
    private DefaultComboBoxModel comboBoxModelGoods;
    private JComboBox comboBoxGoods;
    private java.util.List<Goods> listGoods;

    private JLabel lblWareOut;
    private DefaultComboBoxModel comboBoxModelWareOut;
    private JComboBox comboBoxWareOut;
    private java.util.List<WareOut> listWareOut;

    private JLabel lblWareInList;
    private DefaultComboBoxModel comboBoxModelWareInList;
    private JComboBox comboBoxWareInList;
    private java.util.List<WareInList> listWareInList;

    WareOutListDao wareOutListDao = new WareOutListDao();
    private WareOutList wareOutList;
    private JButton btnSave;

    public WareOutListDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public WareOutListDialog(JFrame mainFrame, WareOutList wareOutList){
        super(mainFrame);
        this.wareOutList = wareOutList;
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

        lblAmount = createLabel("存量");
        txtAmount = createTextField();

        lblGoods = createLabel("商品");
        GoodsDao goodsDao = new GoodsDao();
        listGoods = goodsDao.findAllGoods();
        comboBoxModelGoods = new DefaultComboBoxModel(formatCbxTypeGoods(listGoods));
        comboBoxGoods = new JComboBox(comboBoxModelGoods);
        comboBoxGoods.setPreferredSize(new Dimension(220,30));
        this.add(comboBoxGoods);

        lblWareOut = createLabel("出货单");
        WareOutDao wareOutDao = new WareOutDao();
        listWareOut = wareOutDao.findAllWare();
        comboBoxModelWareOut = new DefaultComboBoxModel(formatCbxTypeWareOut(listWareOut));
        comboBoxWareOut  = new JComboBox(comboBoxModelWareOut);
        comboBoxWareOut .setPreferredSize(new Dimension(220,30));
        this.add(comboBoxWareOut );

        lblWareInList = createLabel("对应进货");
        WareInListDao wareInListDao = new WareInListDao();
        listWareInList = wareInListDao.findAllWareInList();
        comboBoxModelWareInList = new DefaultComboBoxModel(formatCbxTypeWareInList(listWareInList));
        comboBoxWareInList  = new JComboBox(comboBoxModelWareInList );
        comboBoxWareInList .setPreferredSize(new Dimension(220,30));
        this.add(comboBoxWareInList );

        if (wareOutList != null) {
            txtId.setText(wareOutList.getId());
            txtId.setEditable(false);
            txtAmount.setText(wareOutList.getAmount());

            for (int i = 0; i < listGoods.size(); i++){
                Goods goods = listGoods.get(i);
                if (wareOutList.getGoods().getId().equals(goods.getId())) {
                    comboBoxGoods.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < listWareOut.size(); i++){
                WareOut wareOut = listWareOut.get(i);
                if (wareOutList.getWareOut().getId().equals(wareOut.getId())) {
                    comboBoxWareOut.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < listWareInList.size(); i++){
                WareInList wareInList = listWareInList.get(i);
                if (wareOutList.getWareInList().getId().equals(wareInList.getId())) {
                    comboBoxWareInList.setSelectedIndex(i);
                }
            }
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WareOutList wareOutListTemp = new WareOutList();
                wareOutListTemp.setId(txtId.getText());
                wareOutListTemp.setAmount(txtAmount.getText());
                wareOutListTemp.setGoods(listGoods.get(comboBoxGoods.getSelectedIndex()));
                wareOutListTemp.setWareOut(listWareOut.get(comboBoxWareOut.getSelectedIndex()));
                wareOutListTemp.setWareInList(listWareInList.get(comboBoxWareInList.getSelectedIndex()));

                boolean flag = false;
                if (wareOutList == null) {
                    flag = wareOutListDao.save(wareOutListTemp);

                } else {
                    flag = wareOutListDao.updete(wareOutListTemp);
                }

                if (flag) {
                    WareOutListDialog.this.dispose();
                    WareOutListPanel.getInstance().refreshData();
                }else {
                    WareOutListDialog.this.setTitle("数据库操作错误...");
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

    /**
     * 数据类型转换
     * @param list
     * @return
     */
    private Vector<Object> formatCbxTypeGoods(java.util.List<Goods> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (Goods goods : list) {
                data.add(goods.getName());
            }
            return data;
        } else return null;
    }

    /**
     * 数据类型转换
     * @param list
     * @return
     */
    private Vector<Object> formatCbxTypeWareOut(java.util.List<WareOut> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (WareOut wareOut : list) {
                data.add(wareOut.getNumber());
            }
            return data;
        } else return null;
    }

    /**
     * 数据类型转换
     * @param list
     * @return
     */
    private Vector<Object> formatCbxTypeWareInList(java.util.List<WareInList> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (WareInList wareInList : list) {
                data.add(wareInList.getId());
            }
            return data;
        } else return null;
    }
}
