package com.view.Dialog;

import com.bojo.GoodsType;
import com.bojo.Manager;
import com.dao.ManagerDao;
import com.view.contentPanel.GoodsTypePanel;
import com.view.contentPanel.ManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class ManagerInfoDialog extends JDialog{

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblAccount;
    private JTextField txtAccount;

    private JLabel lblPassword;
    private JTextField txtPassword;

    private JLabel lblPhone;
    private JTextField txtPhone;

    private JLabel lblJob;
    private JTextField txtJob;

    private ManagerDao managerDao = new ManagerDao();
    private Manager manager;

    private JButton btnSave;
    public ManagerInfoDialog(JFrame mainFrame){
        super(mainFrame);
        init();
    }

    public ManagerInfoDialog(JFrame mainFrame, Manager manager){
        super(mainFrame);
        this.manager = manager;
        init();
    }

    private void init(){
        this.setModal(true);
        this.setSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));

        lblId = createLabel("管理员编号");
        txtId = createTextField();

        lblName = createLabel("管理员名称");
        txtName = createTextField();

        lblAccount = createLabel("账号");
        txtAccount = createTextField();

        lblPassword = createLabel("密码");
        txtPassword = createTextField();

        lblPhone = createLabel("联系方式");
        txtPhone = createTextField();

        lblJob = createLabel("工作");
        txtJob = createTextField();

        if (manager != null) {
            txtId.setText(manager.getId());
            txtId.setEditable(false);
            txtName.setText(manager.getName());
            txtAccount.setText(manager.getAccount());
            txtPassword.setText(manager.getPassWord());
            txtPhone.setText(manager.getPhone());
            txtJob.setText(manager.getJob());
        }

        btnSave = new JButton("保存");
        btnSave.setPreferredSize(new Dimension(120,30));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager managerTemp = new Manager();
                managerTemp.setId(txtId.getText());
                managerTemp.setName(txtName.getText());
                managerTemp.setAccount(txtAccount.getText());
                managerTemp.setPassWord(txtPassword.getText());
                managerTemp.setPhone(txtPhone.getText());
                managerTemp.setJob(txtJob.getText());

                boolean flag = false;
                if (manager == null) {
                    flag = managerDao.save(managerTemp);
                } else {
                    flag = managerDao.updete(managerTemp);
                }

                if (flag) {
                    ManagerInfoDialog.this.dispose();
                    ManagerPanel.getInstance().refreshData();
                }else {
                    ManagerInfoDialog.this.setTitle("数据库操作错误...");
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
}
