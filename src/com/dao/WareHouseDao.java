package com.dao;

import com.bojo.WareHouse;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class WareHouseDao {
    DAO dao = new DAO();

    /**
     *
     * @return
     */
    public WareHouse findWareHouse(String id){
        WareHouse wareHouse = null;
        String sql = "select * from warehouse where id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
            if (resultSet != null) {
                resultSet.next();
                wareHouse = new WareHouse();
                wareHouse.setId(resultSet.getString("id"));
                wareHouse.setNumber(resultSet.getString("number"));
                wareHouse.setName(resultSet.getString("name"));
                wareHouse.setRemarks(resultSet.getString("remarks"));
                return wareHouse;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }

        return wareHouse;
    }


    /**
     * 查询方法
     * @return
     */
    public List<WareHouse> findAllWareHouse(){
        List<WareHouse> list = new ArrayList<>();
        WareHouse wareHouse;
        String sql = "select * from warehouse";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()){
                wareHouse = new WareHouse();
                wareHouse.setId(resultSet.getString("id"));
                wareHouse.setNumber(resultSet.getString("number"));
                wareHouse.setName(resultSet.getString("name"));
                wareHouse.setRemarks(resultSet.getString("remarks"));
                list.add(wareHouse);
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
     * @return
     */
    public boolean delete(WareHouse wareHouse){
        String sql = "delete from warehouse where id = ?";
        return dao.updeta(sql, wareHouse.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(WareHouse wareHouse){
        String sql = "update warehouse set number=?, name=?, remarks=? where id=?";
        return dao.updeta(sql, wareHouse.getNumber(), wareHouse.getName(),
                wareHouse.getRemarks(), wareHouse.getId());
    }

    /**
     * 添加方法
     * @param wareHouse
     * @return
     */
    public boolean save(WareHouse wareHouse){
        String sql = "insert into warehouse values(?,?,?,?)";
        return dao.updeta(sql, wareHouse.getId(), wareHouse.getNumber(),
                wareHouse.getName(), wareHouse.getRemarks());
    }
}
