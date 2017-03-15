package com.view.contentPanel;

import com.bojo.WareHouse;
import com.bojo.WareIn;
import com.dao.WareInDao;
import com.view.Dialog.WareHouseInfoDialog;
import com.view.Dialog.WareInDialog;
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
public class WareInPanel extends ContentPanel<WareIn>{

    private List<WareIn> list;
    WareInDao wareInDao = new WareInDao();

    private static WareInPanel wareInPanel;
    private WareInPanel(){
        super();
        init();
    }

    public static WareInPanel getInstance(){
        if (wareInPanel == null) {
            wareInPanel = new WareInPanel();
        }
        return wareInPanel;
    }

    private void init(){

        JButton btnAdd = new JButton("增加进货单");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WareInDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除进货单");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareIn wareIn = list.get(index);
                wareInDao.delete(wareIn);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改进货单");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareIn wareIn = list.get(index);
                new WareInDialog(MainFrame.getInstance(), wareIn);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = wareInDao.findAllWareIn();
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
        colNames.add("进货单代号");
        colNames.add("时间");
        colNames.add("经办人");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<WareIn> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                WareIn wareIn = (WareIn) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(wareIn.getId());
                rowData.add(wareIn.getNumber());
                rowData.add(wareIn.getInTime());
                rowData.add(wareIn.getManager().getName());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = wareInDao.findAllWareIn();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
