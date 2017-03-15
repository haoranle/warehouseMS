package com.view.contentPanel;

import com.bojo.Goods;
import com.bojo.GoodsType;
import com.dao.GoodsTypeDao;
import com.view.Dialog.GoodsInfoDialog;
import com.view.Dialog.GoodsTypeInfoDialog;
import com.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class GoodsTypePanel extends JPanel {

    Font font = new Font("微软雅黑", Font.PLAIN, 11);

    private GoodsTypeDao goodsTypeDao;

    private JPanel selectPanel;
    private JPanel operatePanel;
    private DefaultTableModel tableModel;
    private JTable goodsTypeTable;
    private List<GoodsType> list;

    private Vector colNames;
    private Vector data;

    private static GoodsTypePanel goodsTypePanel;

    private JScrollPane tablePanel;

    private GoodsTypePanel(){
        goodsTypeDao = new GoodsTypeDao();
        init();
    }

    public static GoodsTypePanel getInstance(){
        if (goodsTypePanel == null) {
            goodsTypePanel = new GoodsTypePanel();
        }
        return goodsTypePanel;
    }

    private void init(){
        this.setBackground(Color.PINK);
        this.setLayout(new BorderLayout());

        selectPanel = new JPanel();
        selectPanel.setPreferredSize(new Dimension(0, 80));
        selectPanel.setLayout(new BorderLayout());
        this.add(selectPanel, BorderLayout.NORTH);

        operatePanel = new JPanel();
        ((FlowLayout) operatePanel.getLayout()).setAlignment(FlowLayout.RIGHT);
        selectPanel.add(operatePanel, BorderLayout.EAST);


        JButton btnAdd = new JButton("增加商品类型");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GoodsTypeInfoDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除商品类型");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = goodsTypeTable.getSelectedRow();
                GoodsType goodsType = list.get(index);
                goodsTypeDao.delete(goodsType);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改商品类型");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = goodsTypeTable.getSelectedRow();
                GoodsType goodsType = list.get(index);
                new GoodsTypeInfoDialog(MainFrame.getInstance(), goodsType);
            }
        });
        operatePanel.add(btnUp);

        // 显示信息
        list = goodsTypeDao.findAllGoodsType();
        initColNames();
        tableModel = new DefaultTableModel(formatData(list), colNames);
        goodsTypeTable = new JTable(tableModel);
        tablePanel = new JScrollPane(goodsTypeTable);
        tablePanel.setBackground(Color.red);
        this.add(tablePanel, BorderLayout.CENTER);


    }

    private void initColNames() {
        colNames = new Vector();
        colNames.add("编号");
        colNames.add("名称");
    }

    private Vector<Vector<Object>> formatData(List<GoodsType> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (GoodsType goodsType : list) {
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(goodsType.getId());
                rowData.add(goodsType.getName());
                data.add(rowData);
            }
            return data;
        } else return null;
    }
    /**
     * TODO
     */
    public void refreshData() {
        list = goodsTypeDao.findAllGoodsType();
        tableModel.setDataVector(formatData(list), colNames);
    }
}
