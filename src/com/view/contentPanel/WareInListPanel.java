package com.view.contentPanel;

import com.bojo.WareHouse;
import com.bojo.WareInList;
import com.bojo.WareOut;
import com.dao.WareInListDao;
import com.view.Dialog.WareInListDialog;
import com.view.Dialog.WareOutDialog;
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
public class WareInListPanel extends ContentPanel<WareInList>{

    private List<WareInList> list;
    WareInListDao wareInListDao = new WareInListDao();

    private static WareInListPanel wareInListPanel;
    private WareInListPanel(){
        super();
        init();
    }

    public static WareInListPanel getInstance(){
        if (wareInListPanel == null) {
            wareInListPanel = new WareInListPanel();
        }
        return wareInListPanel;
    }

    private void init(){

        JButton btnAdd = new JButton("进货");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WareInListDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除进货明细");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareInList wareInList = list.get(index);
                wareInListDao.delete(wareInList);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改进货明细");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareInList wareInList = list.get(index);
                new WareInListDialog(MainFrame.getInstance(), wareInList);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = wareInListDao.findAllWareInList();
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
        colNames.add("商品");
        colNames.add("数量");
        colNames.add("余量");
        colNames.add("货架");
        colNames.add("供应商");
        colNames.add("进货单");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<WareInList> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                WareInList wareInList = (WareInList) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(wareInList.getId());
                rowData.add(wareInList.getGoods().getName());
                rowData.add(wareInList.getAmount());
                rowData.add(wareInList.getAmountLess());
                rowData.add(wareInList.getShelf().getNumber());
                rowData.add(wareInList.getSupplier().getName());
                rowData.add(wareInList.getWareIn().getNumber());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = wareInListDao.findAllWareInList();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
