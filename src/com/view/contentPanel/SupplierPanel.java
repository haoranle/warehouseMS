package com.view.contentPanel;

import com.bojo.Shelf;
import com.bojo.Supplier;
import com.bojo.WareHouse;
import com.dao.SupplierDao;
import com.view.Dialog.SupplierInfoDialog;
import com.view.Dialog.WareHouseInfoDialog;
import com.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class SupplierPanel  extends ContentPanel<Supplier>{

    private List<Supplier> list;
    SupplierDao supplierDao = new SupplierDao();

    private static SupplierPanel supplierPanel;
    private SupplierPanel(){
        super();
        init();
    }

    public static SupplierPanel getInstance(){
        if (supplierPanel == null) {
            supplierPanel = new SupplierPanel();
        }
        return supplierPanel;
    }

    private void init(){


        JButton btnAdd = new JButton("增加供应商");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SupplierInfoDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除供应商");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                Supplier supplier = list.get(index);
                supplierDao.delete(supplier);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改供应商");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                Supplier supplier = list.get(index);
                new SupplierInfoDialog(MainFrame.getInstance(), supplier);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = supplierDao.findAllSuppliser();
        initColNames();
        tableModel = new DefaultTableModel(formatData(list), colNames);
        jTable = new JTable(tableModel);
        tablePanel = new JScrollPane(jTable);
        this.add(tablePanel, BorderLayout.CENTER);
    }

    @Override
    public void initColNames() {
        colNames = new Vector();
        colNames.add("编号");
        colNames.add("名字");
        colNames.add("联系方式");
        colNames.add("地址");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<Supplier> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                Supplier supplier = (Supplier) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(supplier.getId());
                rowData.add(supplier.getName());
                rowData.add(supplier.getPhone());
                rowData.add(supplier.getAddress());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = supplierDao.findAllSuppliser();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
