package com.dao;

import com.bojo.Manager;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class ManagerDao {

    DAO dao = new DAO();

    /**
     *
     * @return
     */
    public Manager findManager(String id){
        Manager manager = null;
        String sql = "select * from manager where id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
            resultSet.next();
            manager = new Manager();
            manager.setId(resultSet.getString("id"));
            manager.setName(resultSet.getString("name"));
            manager.setAccount(resultSet.getString("account"));
            manager.setPassWord(resultSet.getString("password"));
            manager.setPhone(resultSet.getString("phone"));
            manager.setJob(resultSet.getString("job"));
            return manager;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return manager;
    }

    /**
     * 查询方法
     * @return
     */
    public List<Manager> findAllManager(){
        List<Manager> list = new ArrayList<>();
        Manager manager;
        String sql = "select * from manager";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()){
                manager = new Manager();
                manager.setId(resultSet.getString("id"));
                manager.setName(resultSet.getString("name"));
                manager.setAccount(resultSet.getString("account"));
                manager.setPassWord(resultSet.getString("password"));
                manager.setPhone(resultSet.getString("phone"));
                manager.setJob(resultSet.getString("job"));
                list.add(manager);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return list;
    }

    public boolean seleteAccountOfPassword(String name, String password){
        boolean flag = false;

        String sql = "select * from manager where account=? and password =?";
        ResultSet resultSet = dao.select(sql, name, password);
        try {
            if (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除方法
     *
     * @return
     */
    public boolean delete(Manager manager){
        String sql = "delete from manager where id = ?";
        return dao.updeta(sql, manager.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(Manager manager){
        String sql = "update manager set name=?, account=?, password=?, phone=?, job=? where id = ?";
        return dao.updeta(sql, manager.getName(), manager.getAccount(), manager.getPassWord(),
                manager.getPassWord(), manager.getJob(), manager.getId());
    }

    public boolean save(Manager manager){
        String sql = "insert into manager values(?,?,?,?,?,?)";
        return dao.updeta(sql, manager.getId(), manager.getName(), manager.getAccount(), manager.getPassWord(),
                manager.getPhone(), manager.getJob());
    }
}
