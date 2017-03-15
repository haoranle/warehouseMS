package com.view.contentPanel;

import com.bojo.WareIn;
import com.bojo.WareInList;
import com.bojo.WareOutList;
import com.dao.WareOutListDao;
import com.view.Dialog.WareInListDialog;
import com.view.Dialog.WareOutListDialog;
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
public class WareOutListPanel extends ContentPanel<WareOutList>{

    private List<WareOutList> list;
    WareOutListDao wareOutListDao = new WareOutListDao();

    private static WareOutListPanel wareOutListPanel;
    private WareOutListPanel(){
        super();
        init();
    }

    public static WareOutListPanel getInstance(){
        if (wareOutListPanel == null) {
            wareOutListPanel = new WareOutListPanel();
        }
        return wareOutListPanel;
    }

    private void init(){


        JButton btnAdd = new JButton("出货");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WareOutListDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除出货明细");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareOutList wareOutList = list.get(index);
                wareOutListDao.delete(wareOutList);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改出货明细");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareOutList wareOutList = list.get(index);
                new WareOutListDialog(MainFrame.getInstance(), wareOutList);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = wareOutListDao.findAllWareOutList();
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
        colNames.add("数量");
        colNames.add("商品");
        colNames.add("出货单");
        colNames.add("进货明细");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<WareOutList> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                WareOutList wareOutList = (WareOutList) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(wareOutList.getId());
                rowData.add(wareOutList.getAmount());
                rowData.add(wareOutList.getGoods().getName());
                rowData.add(wareOutList.getWareOut().getNumber());
                rowData.add(wareOutList.getWareInList().getId());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = wareOutListDao.findAllWareOutList();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
