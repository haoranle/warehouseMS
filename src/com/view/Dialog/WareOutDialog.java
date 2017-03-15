package com.view.Dialog;

import com.bojo.Manager;
import com.bojo.WareIn;
import com.bojo.WareOut;
import com.dao.ManagerDao;
import com.dao.WareInDao;
import com.dao.WareOutDao;
import com.view.contentPanel.WareInPanel;
import com.view.contentPanel.WareOutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by 李浩然 on 2017/1/10.
 */
public class WareOutDialog extends JDialog{

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblNumber;
    private JTextField txtNumber;

    private JLabel lblOuttime;
    private JTextField txtOuttime;


    private JLabel lblManager;
    private DefaultComboBoxModel comboBoxModel;
    private JComboBox comboBox;
    private List<Manager> list;

    private WareOutDao wareOutDao = new WareOutDao();
    private WareOut wareOut;
    private JButton btnSave;

    public WareOutDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public WareOutDialog(JFrame mainFrame, WareOut wareOut){
        super(mainFrame);
        this.wareOut = wareOut;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("仓库编号");
        txtId = createTextField();

        lblNumber = createLabel("仓库代号");
        txtNumber = createTextField();

        lblOuttime = createLabel("时间");
        txtOuttime = createTextField();

        lblManager = createLabel("经办人");
        ManagerDao managerDao = new ManagerDao();
        list = managerDao.findAllManager();
        comboBoxModel = new DefaultComboBoxModel(formatCbxType(list));
        comboBox = new JComboBox(comboBoxModel);
        comboBox.setPreferredSize(new Dimension(220,30));
        this.add(comboBox);

        if (wareOut != null) {
            txtId.setText(wareOut.getId());
            txtId.setEditable(false);
            txtNumber.setText(wareOut.getNumber());
            txtOuttime.setText(wareOut.getOutTime().toString());

            for (int i = 0; i < list.size(); i++){
                Manager manager = list.get(i);
                if (wareOut.getManager().getId().equals(manager.getId())) {
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
        txtOuttime.setEditable(false);

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WareOut wareOutTemp = new WareOut();
                wareOutTemp.setId(txtId.getText());
                wareOutTemp.setNumber(txtNumber.getText());
                Timestamp timestamp;
                timestamp = new Timestamp(new Date().getTime());
                wareOutTemp.setOutTime(timestamp);
                wareOutTemp.setManager(list.get(comboBox.getSelectedIndex()));

                boolean flag = false;
                if (wareOut == null) {
                    flag = wareOutDao.save(wareOutTemp);
                } else {
                    flag = wareOutDao.updete(wareOutTemp);
                }

                if (flag) {
                    WareOutDialog.this.dispose();
                    WareOutPanel.getInstance().refreshData();
                }else {
                    WareOutDialog.this.setTitle("数据库操作错误...");
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
    private Vector<Object> formatCbxType(List<Manager> list) {
        if (list != null && !list.isEmpty()) {
            Vector<Object> data = new Vector<Object>();
            for (Manager manager : list) {
                data.add(manager.getName());
            }
            return data;
        } else return null;
    }
}
