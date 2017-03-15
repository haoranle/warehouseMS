package com.view.Dialog;

import com.bojo.*;
import com.dao.*;
import com.view.contentPanel.WareInListPanel;
import com.view.contentPanel.WareInPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/10.
 */
public class WareInListDialog extends JDialog{

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblAmount;
    private JTextField txtAmount;

    private JLabel lblAmountLess;
    private JTextField txtAmountLess;

    private JLabel lblGoods;
    private DefaultComboBoxModel comboBoxModelGoods;
    private JComboBox comboBoxGoods;
    private java.util.List<Goods> listGoods;

    private JLabel lblShelf;
    private DefaultComboBoxModel comboBoxModelShelf;
    private JComboBox comboBoxShelf;
    private java.util.List<Shelf> listShelf;

    private JLabel lblSupplier;
    private DefaultComboBoxModel comboBoxModelSupplier;
    private JComboBox comboBoxSupplier;
    private java.util.List<Supplier> listSupplier;

    private JLabel lblWareIn;
    private DefaultComboBoxModel comboBoxModelWareIn;
    private JComboBox comboBoxWareIn;
    private java.util.List<WareIn> listWareIn;

    WareInListDao wareInListDao = new WareInListDao();
    private WareInList wareInList;
    private JButton btnSave;

    public WareInListDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public WareInListDialog(JFrame mainFrame, WareInList wareInList){
        super(mainFrame);
        this.wareInList = wareInList;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("进货编号");
        txtId = createTextField();

        lblAmount = createLabel("存量");
        txtAmount = createTextField();

//        lblAmountLess = createLabel("余量");
//        txtAmountLess = createTextField();

        lblGoods = createLabel("商品");
        GoodsDao goodsDao = new GoodsDao();
        listGoods = goodsDao.findAllGoods();
        comboBoxModelGoods = new DefaultComboBoxModel(formatCbxTypeGoods(listGoods));
        comboBoxGoods = new JComboBox(comboBoxModelGoods);
        comboBoxGoods.setPreferredSize(new Dimension(220,30));
        this.add(comboBoxGoods);

        lblShelf = createLabel("货架");
        ShelfDao shelfDao = new ShelfDao();
        listShelf = shelfDao.findAllShelf();
        comboBoxModelShelf = new DefaultComboBoxModel(formatCbxTypeShelf(listShelf));
        comboBoxShelf= new JComboBox(comboBoxModelShelf);
        comboBoxShelf.setPreferredSize(new Dimension(220,30));
        this.add(comboBoxShelf);

        lblSupplier = createLabel("供应商");
        SupplierDao supplierDao = new SupplierDao();
        listSupplier = supplierDao.findAllSuppliser();
        comboBoxModelSupplier = new DefaultComboBoxModel(formatCbxTypeSupplier(listSupplier));
        comboBoxSupplier = new JComboBox(comboBoxModelSupplier);
        comboBoxSupplier.setPreferredSize(new Dimension(220,30));
        this.add(comboBoxSupplier);

        lblWareIn = createLabel("进货单");
        WareInDao wareInDao = new WareInDao();
        listWareIn = wareInDao.findAllWareIn();
        comboBoxModelWareIn = new DefaultComboBoxModel(formatCbxTypeWareIn(listWareIn));
        comboBoxWareIn = new JComboBox(comboBoxModelWareIn);
        comboBoxWareIn.setPreferredSize(new Dimension(220,30));
        this.add(comboBoxWareIn);


        if (wareInList != null) {
            txtId.setText(wareInList.getId());
            txtId.setEditable(false);
            txtAmount.setText(wareInList.getAmount());
            txtAmountLess.setText(wareInList.getAmountLess());

            for (int i = 0; i < listGoods.size(); i++){
                Goods goods = listGoods.get(i);
                if (wareInList.getGoods().getId().equals(goods.getId())) {
                    comboBoxGoods.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < listShelf.size(); i++){
                Shelf shelf = listShelf.get(i);
                if (wareInList.getShelf().getId().equals(shelf.getId())) {
                    comboBoxShelf.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < listSupplier.size(); i++){
                Supplier supplier = listSupplier.get(i);
                if (wareInList.getSupplier().getId().equals(supplier.getId())) {
                }
            }

            for (int i = 0; i < listWareIn.size(); i++){
                WareIn wareIn = listWareIn.get(i);
                if (wareInList.getWareIn().getId().equals(wareIn.getId())) {
                }
            }
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WareInList wareInListTemp = new WareInList();
                wareInListTemp.setId(txtId.getText());
                wareInListTemp.setAmount(txtAmount.getText());
                wareInListTemp.setAmountLess(txtAmountLess.getText());
                wareInListTemp.setGoods(listGoods.get(comboBoxGoods.getSelectedIndex()));
                wareInListTemp.setShelf(listShelf.get(comboBoxShelf.getSelectedIndex()));
                wareInListTemp.setSupplier(listSupplier.get(comboBoxSupplier.getSelectedIndex()));
                wareInListTemp.setWareIn(listWareIn.get(comboBoxWareIn.getSelectedIndex()));

                boolean flag = false;
                if (wareInList == null) {
                    flag = wareInListDao.save(wareInListTemp);
                } else {
                    flag = wareInListDao.updete(wareInListTemp);
                }

                if (flag) {
                    WareInListDialog.this.dispose();
                    WareInListPanel.getInstance().refreshData();
                }else {
                    WareInListDialog.this.setTitle("数据库操作错误...");
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
    private Vector<Object> formatCbxTypeGoods(List<Goods> list) {
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
    private Vector<Object> formatCbxTypeShelf(List<Shelf> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (Shelf shelf : list) {
                data.add(shelf.getNumber());
            }
            return data;
        } else return null;
    }

    /**
     * 数据类型转换
     * @param list
     * @return
     */
    private Vector<Object> formatCbxTypeSupplier(List<Supplier> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (Supplier supplier : list) {
                data.add(supplier.getName());
            }
            return data;
        } else return null;
    }

    /**
     * 数据类型转换
     * @param list
     * @return
     */
    private Vector<Object> formatCbxTypeWareIn(List<WareIn> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (WareIn wareIn : list) {
                data.add(wareIn.getNumber());
            }
            return data;
        } else return null;
    }
}
