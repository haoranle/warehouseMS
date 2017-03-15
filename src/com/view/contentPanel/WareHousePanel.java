package com.view.contentPanel;

import com.bojo.Manager;
import com.bojo.Supplier;
import com.bojo.WareHouse;
import com.dao.WareHouseDao;
import com.view.Dialog.ManagerInfoDialog;
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
public class WareHousePanel extends ContentPanel<WareHouse>{

    private List<WareHouse> list;
    WareHouseDao wareHouseDao = new WareHouseDao();

    private static WareHousePanel wareHousePanel;
    private WareHousePanel(){
        super();
        init();
    }

    public static WareHousePanel getInstance(){
        if (wareHousePanel == null) {
            wareHousePanel = new WareHousePanel();
        }
        return wareHousePanel;
    }

    private void init(){

        JButton btnAdd = new JButton("增加商品类型");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WareHouseInfoDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除仓库");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareHouse wareHouse = list.get(index);
                wareHouseDao.delete(wareHouse);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改仓库");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareHouse wareHouse = list.get(index);
                new WareHouseInfoDialog(MainFrame.getInstance(), wareHouse);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = wareHouseDao.findAllWareHouse();
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
        colNames.add("仓库代号");
        colNames.add("名字");
        colNames.add("备注");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<WareHouse> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                WareHouse wareHouse = (WareHouse) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(wareHouse.getId());
                rowData.add(wareHouse.getNumber());
                rowData.add(wareHouse.getName());
                rowData.add(wareHouse.getRemarks());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = wareHouseDao.findAllWareHouse();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
