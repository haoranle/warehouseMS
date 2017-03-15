package com.view.Dialog;

import com.bojo.GoodsType;
import com.bojo.Manager;
import com.bojo.Shelf;
import com.bojo.WareHouse;
import com.dao.ShelfDao;
import com.dao.WareHouseDao;
import com.sun.media.jfxmediaimpl.HostUtils;
import com.view.contentPanel.ManagerPanel;
import com.view.contentPanel.ShelfPanel;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class ShelfInfoDialog extends JDialog{

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblNumber;
    private JTextField txtNumber;

    private JLabel lblWareHouse;
    private DefaultComboBoxModel comboBoxModel;
    private JComboBox comboBox;
    private List<WareHouse> houseList;

    private JLabel lblRemarks;
    private JTextArea txaRrmarks;

    private ShelfDao shelfDao = new ShelfDao();
    private Shelf shelf;
    private JButton btnSave;
    public ShelfInfoDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public ShelfInfoDialog(JFrame mainFrame, Shelf shelf){
        super(mainFrame);
        this.shelf = shelf;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("货架编号");
        txtId = createTextField();

        lblNumber = createLabel("货架代号");
        txtNumber = createTextField();

        lblWareHouse = createLabel("所属仓库");
        WareHouseDao wareHouseDao = new WareHouseDao();
        houseList = wareHouseDao.findAllWareHouse();
        comboBoxModel = new DefaultComboBoxModel(formatCbxType(houseList));
        comboBox = new JComboBox(comboBoxModel);
        comboBox.setPreferredSize(new Dimension(220,30));
        this.add(comboBox);

        lblRemarks = createLabel("备注");
        txaRrmarks = new JTextArea(4,20);
        this.add(txaRrmarks);

        if (shelf != null) {
            txtId.setText(shelf.getId());
            txtId.setEditable(false);
            txtNumber.setText(shelf.getNumber());
            txaRrmarks.setText(shelf.getRemarks());

            for (int i = 0; i < houseList.size(); i++) {
                WareHouse wareHouse = houseList.get(i);
                if (wareHouse.getId().equals(shelf.getWareHouse().getId())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shelf shelfTemp = new Shelf();
                shelfTemp.setId(txtId.getText());
                shelfTemp.setNumber(txtNumber.getText());
                shelfTemp.setRemarks(txaRrmarks.getText());
                int index = comboBox.getSelectedIndex();
                shelfTemp.setWareHouse(houseList.get(index));

                boolean flag = false;
                if (shelf == null) {
                    flag = shelfDao.save(shelfTemp);
                } else {
                    flag = shelfDao.updete(shelfTemp);
                }

                if (flag) {
                    ShelfInfoDialog.this.dispose();
                    ShelfPanel.getInstance().refreshData();
                }else {
                    ShelfInfoDialog.this.setTitle("数据库操作错误...");
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
    private Vector<Object> formatCbxType(List<WareHouse> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (WareHouse wareHouse : list) {
                data.add(wareHouse.getNumber());
            }
            return data;
        } else return null;
    }
}
