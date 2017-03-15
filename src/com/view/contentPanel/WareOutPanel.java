package com.view.contentPanel;

import com.bojo.WareIn;
import com.bojo.WareOut;
import com.bojo.WareOutList;
import com.dao.WareOutDao;
import com.view.Dialog.WareInDialog;
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
public class WareOutPanel extends ContentPanel<WareOut>{

    private List<WareOut> list;
    WareOutDao wareOutDao = new WareOutDao();

    private static WareOutPanel wareOutPanel;
    private WareOutPanel(){
        super();
        init();
    }

    public static WareOutPanel getInstance(){
        if (wareOutPanel == null) {
            wareOutPanel = new WareOutPanel();
        }
        return wareOutPanel;
    }

    private void init(){

        JButton btnAdd = new JButton("增加出货单");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WareOutDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除出货单");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareOut wareOut = list.get(index);
                wareOutDao.delete(wareOut);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改出货单");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                WareOut wareOut = list.get(index);
                new WareOutDialog(MainFrame.getInstance(), wareOut);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = wareOutDao.findAllWare();
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
        colNames.add("出货单代号");
        colNames.add("时间");
        colNames.add("经办人");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<WareOut> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                WareOut wareOut = (WareOut) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(wareOut.getId());
                rowData.add(wareOut.getNumber());
                rowData.add(wareOut.getOutTime());
                rowData.add(wareOut.getManager().getName());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = wareOutDao.findAllWare();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
