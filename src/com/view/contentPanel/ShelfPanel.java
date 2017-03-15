package com.view.contentPanel;

import com.bojo.Manager;
import com.bojo.Shelf;
import com.bojo.WareHouse;
import com.dao.ShelfDao;
import com.view.Dialog.ShelfInfoDialog;
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
public class ShelfPanel extends ContentPanel<Shelf> {

    ShelfDao shelfDao = new ShelfDao();
    private List<Shelf> list;

    private static ShelfPanel shelfPanel;

    private ShelfPanel(){
        super();
        init();
    }

    public static ShelfPanel getInstance(){
        if (shelfPanel == null) {
            shelfPanel = new ShelfPanel();
        }
        return shelfPanel;
    }

    private void init(){

        JButton btnAdd = new JButton("增加货架");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShelfInfoDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除货架");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                Shelf shelf = list.get(index);
                shelfDao.delete(shelf);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改货架");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                Shelf shelf = list.get(index);
                new ShelfInfoDialog(MainFrame.getInstance(), shelf);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = shelfDao.findAllShelf();
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
        colNames.add("货架代号");
        colNames.add("备注");
        colNames.add("仓库编号");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<Shelf> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                Shelf shelf = (Shelf) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(shelf.getId());
                rowData.add(shelf.getNumber());
                rowData.add(shelf.getRemarks());
                rowData.add(shelf.getWareHouse().getName());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = shelfDao.findAllShelf();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
