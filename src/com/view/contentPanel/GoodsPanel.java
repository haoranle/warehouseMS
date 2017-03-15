package com.view.contentPanel;

import com.bojo.Goods;
import com.dao.GoodsDao;
import com.view.Dialog.GoodsInfoDialog;
import com.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class GoodsPanel extends JPanel{

    private GoodsDao goodsDao;

    private JPanel selectPanel;
    private JPanel operatePanel;
    private JScrollPane tablePanel;
    private DefaultTableModel tableModel;
    private JTable goodsTable;

    private List<Goods> list;
    private Vector colNames;
    private Vector data;

    private static GoodsPanel goodsPanel;

    Font font = new Font("微软雅黑", Font.PLAIN, 11);

    private GoodsPanel(){
        init();
    }

    public static GoodsPanel getInstance(){
        if (goodsPanel == null) {
            goodsPanel = new GoodsPanel();
        }
        return goodsPanel;
    }

    private void init(){
        goodsDao = new GoodsDao();

        this.setBackground(Color.PINK);
        this.setLayout(new BorderLayout());

        selectPanel = new JPanel();
        selectPanel.setPreferredSize(new Dimension(0, 80));
        selectPanel.setLayout(new BorderLayout());
        this.add(selectPanel, BorderLayout.NORTH);

        operatePanel = new JPanel();
        ((FlowLayout) operatePanel.getLayout()).setAlignment(FlowLayout.RIGHT);
        selectPanel.add(operatePanel, BorderLayout.EAST);

        JButton btnAdd = new JButton("增加商品");
        btnAdd.setFont(font);
        btnAdd.setPreferredSize(new Dimension(100, 25));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new GoodsInfoDialog(MainFrame.getInstance());
            }
        });
        operatePanel.add(btnAdd);

        JButton btnDel = new JButton("删除商品");
        btnDel.setFont(font);
        btnDel.setPreferredSize(new Dimension(100, 25));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = goodsTable.getSelectedRow();
                Goods goods = list.get(index);
                goodsDao.delete(goods);
                refreshData();
            }
        });
        operatePanel.add(btnDel);

        JButton btnUp = new JButton("修改商品");
        btnUp.setFont(font);
        btnUp.setPreferredSize(new Dimension(100, 25));
        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = goodsTable.getSelectedRow();
                Goods goods = list.get(index);
                new GoodsInfoDialog(MainFrame.getInstance(), goods);
            }
        });
        operatePanel.add(btnUp);


        // 显示查询面板
        list = goodsDao.findAllGoods();
        initColNames();
        tableModel = new DefaultTableModel(formatData(list), colNames);
        goodsTable = new JTable(tableModel);
        tablePanel = new JScrollPane(goodsTable);
        tablePanel.setBackground(Color.red);
        this.add(tablePanel, BorderLayout.CENTER);

    }

    private void initColNames() {
        colNames = new Vector();
        colNames.add("编号");
        colNames.add("名称");
        colNames.add("商品类型");
        colNames.add("价格");
        colNames.add("库存");
        colNames.add("备注");
    }

    private Vector<Vector<Object>> formatData(List<Goods> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            for (Goods goods : list) {
                Vector<Object> rowData = new Vector<Object>();
                rowData.add(goods.getId());
                rowData.add(goods.getName());
                rowData.add(goods.getGoodsType().getName());
                rowData.add(goods.getPrice());
                rowData.add(goods.getAnount());
                rowData.add(goods.getRemarks());
                data.add(rowData);
            }
            return data;
        } else return null;
    }

    /**
     * TODO
     */
    public void refreshData() {
        list = goodsDao.findAllGoods();
        tableModel.setDataVector(formatData(list), colNames);
    }

}
