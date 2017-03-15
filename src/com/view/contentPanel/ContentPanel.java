package com.view.contentPanel;

import com.bojo.GoodsType;
import com.dao.GoodsTypeDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public abstract class ContentPanel<T> extends JPanel{

    protected Font font = new Font("微软雅黑", Font.PLAIN, 11);

    protected JPanel selectPanel;
    protected JPanel operatePanel;

    protected JScrollPane tablePanel;
    protected JTable jTable;
    protected DefaultTableModel tableModel;

    protected Vector colNames;
    protected Vector data;

    public ContentPanel(){
        init();
    }


    private void init(){
      //  this.setBackground(Color.white);
        this.setBackground(Color.PINK);
        this.setLayout(new BorderLayout());

        selectPanel = new JPanel();
        selectPanel.setPreferredSize(new Dimension(0, 80));
        selectPanel.setLayout(new BorderLayout());
        this.add(selectPanel, BorderLayout.NORTH);

        operatePanel = new JPanel();
        ((FlowLayout) operatePanel.getLayout()).setAlignment(FlowLayout.RIGHT);
        selectPanel.add(operatePanel, BorderLayout.EAST);

        // 添加控制
        // 显示内容

    }

    public abstract void initColNames();

    public abstract Vector<Vector<Object>> formatData(List<T> list);
}
