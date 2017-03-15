package com.dao;

import com.bojo.GoodsType;
import com.bojo.Supplier;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class SupplierDao {
    DAO dao = new DAO();

    /**
     *
     * @return
     */
    public Supplier findSuppliser(String id){
        Supplier supplier = null;
        String sql = "select * from supplier where id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
            resultSet.next();
            supplier = new Supplier();
            supplier.setId(resultSet.getString("id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setPhone(resultSet.getString("phone"));
            supplier.setAddress(resultSet.getString("address"));
            return supplier;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return supplier;
    }
    /**
     * 查询方法
     * @return
     */
    public List<Supplier> findAllSuppliser(){
        List<Supplier> list = new ArrayList<>();
        Supplier supplier;
        String sql = "select * from supplier";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()) {
                supplier = new Supplier();
                supplier.setId(resultSet.getString("id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setPhone(resultSet.getString("phone"));
                supplier.setAddress(resultSet.getString("address"));
                list.add(supplier);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return list;
    }

    /**
     * 删除方法
     *
     * @return
     */
    public boolean delete(Supplier supplier){
        String sql = "delete from supplier where id = ?";
        return dao.updeta(sql, supplier.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(Supplier supplier){
        String sql = "update supplier set name=?, phone=?, address=? where id =?";
        return dao.updeta(sql, supplier.getName(), supplier.getPhone(), supplier.getAddress(),
                supplier.getId());
    }

    /**
     * 添加方法
     * @param supplier
     * @return
     */
    public boolean save(Supplier supplier){
        String sql = "insert into supplier values(?,?,?,?)";
        return dao.updeta(sql, supplier.getId(), supplier.getName(), supplier.getPhone(),
                supplier.getAddress());
    }
}
