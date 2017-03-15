package com.view.Dialog;

import com.bojo.Goods;
import com.bojo.GoodsType;
import com.dao.GoodsDao;
import com.dao.GoodsTypeDao;
import com.view.contentPanel.GoodsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;


/**
 * Created by 李浩然 on 2017/1/9.
 */
public class GoodsInfoDialog extends JDialog{

    private GoodsDao goodsDao = new GoodsDao();
    private GoodsTypeDao goodsTypeDao = new GoodsTypeDao();

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblType;
    private DefaultComboBoxModel cbxTypeModel;
    private JComboBox cbxType;
    private List<GoodsType> goodsTypeList;

    private JLabel lblPrice;
    private JTextField txtPrice;

    private JLabel lblStock;
    private JTextField txtStock;

    private JLabel lblRemark;
    private JTextArea txtRemark;

    private JButton btnSave;

    private Goods goods;

    public GoodsInfoDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public GoodsInfoDialog(JFrame mainFrame, Goods goods){
        super(mainFrame);
        this.goods = goods;
        init();
    }

    private void init() {
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("商品编号");
        txtId = createTextField();

        lblName = createLabel("商品名称");
        txtName = createTextField();

        lblType = createLabel("商品类型");
        GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
        goodsTypeList = goodsTypeDao.findAllGoodsType();
        cbxTypeModel = new DefaultComboBoxModel(formatCbxType(goodsTypeList));
        cbxType = new JComboBox(cbxTypeModel);
        cbxType.setPreferredSize(new Dimension(220,30));
        this.add(cbxType);

        lblPrice = createLabel("商品价格");
        txtPrice = createTextField();

        lblStock = createLabel("商品数量");
        txtStock = createTextField();

        lblRemark = createLabel("商品描述");
        txtRemark = new JTextArea(4, 20);
        this.add(txtRemark);

        if (goods != null){
            txtId.setText(goods.getId());
            txtId.setEditable(false);
            txtName.setText(goods.getName());
            txtPrice.setText(goods.getPrice() + "");
            txtStock.setText(goods.getAnount() + "");
            txtRemark.setText(goods.getRemarks());

            for (int i = 0; i < goodsTypeList.size(); i++) {
                GoodsType goodsType = goodsTypeList.get(i);
                if (goodsType.getId().equals(goods.getGoodsType().getId())) {
                    cbxType.setSelectedIndex(i);
                    break;
                }
            }
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    boolean flag = false;
                    if (goods == null){
                        goods = new Goods();
                        goods.setId(txtId.getText());
                        goods.setName(txtName.getText());
                        goods.setPrice(Double.valueOf(txtPrice.getText()));
                        goods.setAnount(txtStock.getText());
                        goods.setRemarks(txtRemark.getText());

                        int index = cbxType.getSelectedIndex();
                        GoodsType goodsType = goodsTypeList.get(index);

                        goods.setGoodsType(goodsType);
                        flag =  goodsDao.save(goods);
                    }else {
                        goods = new Goods();
                        goods.setId(txtId.getText());
                        goods.setName(txtName.getText());
                        goods.setPrice(Double.valueOf(txtPrice.getText()));
                        goods.setAnount(txtStock.getText());
                        goods.setRemarks(txtRemark.getText());

                        int index = cbxType.getSelectedIndex();
                        GoodsType goodsType = goodsTypeList.get(index);
                        goods.setGoodsType(goodsType);

                        flag = goodsDao.updeta(goods);
                    }

                    if (flag) {
                        GoodsInfoDialog.this.dispose();
                        GoodsPanel.getInstance().refreshData();
                    }else {
                        GoodsInfoDialog.this.setTitle("数据库操作错误...");
                    }
                } catch (RuntimeException ex){
                    ex.printStackTrace();
                }
            }
        });
        this.add(btnSave);

        this.setVisible(true);
    }

    /**
     * 创建标签
     * @param name
     * @return
     */
    private JLabel createLabel(String name) {
        JLabel lbl = new JLabel(name);
        lbl.setPreferredSize(new Dimension(120, 30));
        this.add(lbl);
        return lbl;
    }

    /**
     * 创建文本框
     * @return
     */
    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setPreferredSize(new Dimension(220, 30));
        this.add(txt);
        return txt;
    }

    /**
     * 数据类型转换
     * @param list
     * @return
     */
    private Vector<Object> formatCbxType(List<GoodsType> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (GoodsType goodsType : list) {
                data.add(goodsType.getName());
            }
            return data;
        } else return null;
    }

}
