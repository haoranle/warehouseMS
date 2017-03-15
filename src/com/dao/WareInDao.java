package com.dao;

import com.bojo.GoodsType;
import com.bojo.Manager;
import com.bojo.WareIn;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class WareInDao {
    DAO dao = new DAO();
    ManagerDao managerDao = new ManagerDao();

    /**
     *
     * @return
     */
    public WareIn findWareIn(String id){
        WareIn wareIn = null;
        Manager manager;
        String sql = "select * from warein where id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
            resultSet.next();
                wareIn = new WareIn();
                wareIn.setId(resultSet.getString("id"));
                wareIn.setNumber(resultSet.getString("number"));
                wareIn.setInTime(resultSet.getTimestamp("intime"));
                String m_id = resultSet.getString("m_id");
                manager = managerDao.findManager(m_id);
                wareIn.setManager(manager);
            return wareIn;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return wareIn;
    }
    /**
     * 查询方法
     * @return
     */
    public List<WareIn> findAllWareIn(){
        List<WareIn> list = new ArrayList<>();
        WareIn wareIn;
        Manager manager;
        String sql = "select * from warein";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()) {
                wareIn = new WareIn();
                wareIn.setId(resultSet.getString("id"));
                wareIn.setNumber(resultSet.getString("number"));
                wareIn.setInTime(resultSet.getTimestamp("intime"));
                String id = resultSet.getString("m_id");
                manager = managerDao.findManager(id);
                wareIn.setManager(manager);
                list.add(wareIn);
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
    public boolean delete(WareIn wareIn){
        String sql = "delete from warein where id = ?";
        return dao.updeta(sql, wareIn.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(WareIn wareIn){
        String sql = "update warein set number=?, intime=?, m_id=? where id = ?";
        return dao.updeta(sql, wareIn.getNumber(), wareIn.getInTime(),
                wareIn.getManager().getId(), wareIn.getId());
    }

    /**
     * 添加方法
     * @param wareIn
     * @return
     */
    public boolean save(WareIn wareIn){
        String sql = "insert into warein values(?,?,?,?)";
        return dao.updeta(sql, wareIn.getId(), wareIn.getNumber(), wareIn.getInTime(),
                wareIn.getManager().getId());
    }
}
