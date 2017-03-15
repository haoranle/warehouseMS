package com.view.contentPanel;

import com.bojo.GoodsType;
import com.bojo.Manager;
import com.dao.ManagerDao;
import com.view.Dialog.GoodsTypeInfoDialog;
import com.view.Dialog.ManagerInfoDialog;
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
public class ManagerPanel extends ContentPanel<Manager>{

    ManagerDao managerDao = new ManagerDao();

    private static ManagerPanel managerPanel;
    private List<Manager> list;

    private ManagerPanel(){
        super();
        init();
    }
    public static ManagerPanel getInstance(){
        if (managerPanel == null) {
            managerPanel = new ManagerPanel();
        }
        return managerPanel;
    }

    private void init() {

        JButton btnAdd = new JButton("增加管理员");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerInfoDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除管理员");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                Manager manager = list.get(index);
                managerDao.delete(manager);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改管理员信息");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jTable.getSelectedRow();
                Manager manager = list.get(index);
                new ManagerInfoDialog(MainFrame.getInstance(), manager);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = managerDao.findAllManager();
        initColNames();
        tableModel = new DefaultTableModel(formatData(list), colNames);
        jTable = new JTable(tableModel);
        tablePanel = new JScrollPane(jTable);
        tablePanel.setBackground(Color.red);
        this.add(tablePanel, BorderLayout.CENTER);

    }

    @Override
    public void initColNames() {
        colNames = new Vector();
        colNames.add("编号");
        colNames.add("名称");
        colNames.add("账号");
        colNames.add("密码");
        colNames.add("电话");
        colNames.add("工作");
    }

    @Override
    public Vector<Vector<Object>> formatData(List<Manager> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Object object : list) {
                Manager manager = (Manager) object;
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(manager.getId());
                rowData.add(manager.getName());
                rowData.add(manager.getAccount());
                rowData.add(manager.getPassWord());
                rowData.add(manager.getPhone());
                rowData.add(manager.getJob());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = managerDao.findAllManager();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
