package com.view.Dialog;

import com.bojo.Supplier;
import com.bojo.WareHouse;
import com.dao.SupplierDao;
import com.view.contentPanel.SupplierPanel;
import com.view.contentPanel.WareHousePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class SupplierInfoDialog extends JDialog{

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblPhone;
    private JTextField txtPhone;

    private JLabel lblAddress;
    private JTextField txtAddress;

    private SupplierDao supplierDao = new SupplierDao();
    private Supplier supplier;
    private JButton btnSave;

    public SupplierInfoDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public SupplierInfoDialog(JFrame mainFrame, Supplier supplier){
        super(mainFrame);
        this.supplier = supplier;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("供应商编号");
        txtId = createTextField();

        lblName = createLabel("供应商名称");
        txtName = createTextField();

        lblPhone = createLabel("联系方式");
        txtPhone = createTextField();

        lblAddress = createLabel("地址");
        txtAddress = createTextField();

        if (supplier != null) {
            txtId.setText(supplier.getId());
            txtId.setEditable(false);
            txtName.setText(supplier.getName());
            txtPhone.setText(supplier.getPhone());
            txtAddress.setText(supplier.getAddress());
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Supplier supplierTemp = new Supplier();
                supplierTemp.setId(txtId.getText());
                supplierTemp.setName(txtName.getText());
                supplierTemp.setPhone(txtPhone.getText());
                supplierTemp.setAddress(txtAddress.getText());

                boolean flag = false;
                if (supplier == null) {
                    flag = supplierDao.save(supplierTemp);
                } else {
                    flag = supplierDao.updete(supplierTemp);
                }

                if (flag) {
                    SupplierInfoDialog.this.dispose();
                    SupplierPanel.getInstance().refreshData();
                }else {
                    SupplierInfoDialog.this.setTitle("数据库操作错误...");
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
