package com.dao;

import com.bojo.Manager;
import com.bojo.WareIn;
import com.bojo.WareOut;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class WareOutDao {
    DAO dao = new DAO();
    ManagerDao managerDao = new ManagerDao();

    /**
     * 查询方法
     * @return
     */
    public WareOut findWare(String id){
        WareOut wareOut = null;
        Manager manager;
        String sql = "select * from wareout where id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
            resultSet.next();
            wareOut = new WareOut();
            wareOut.setId(resultSet.getString("id"));
            wareOut.setNumber(resultSet.getString("number"));
            wareOut.setOutTime(resultSet.getTimestamp("outtime"));
            String m_id = resultSet.getString("m_id");
            manager = managerDao.findManager(m_id);
            wareOut.setManager(manager);
            return wareOut;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return wareOut;
    }
    /**
     * 查询方法
     * @return
     */
    public List<WareOut> findAllWare(){
        List<WareOut> list = new ArrayList<>();
        WareOut wareOut;
        Manager manager;
        String sql = "select * from wareout";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()) {
                wareOut = new WareOut();
                wareOut.setId(resultSet.getString("id"));
                wareOut.setNumber(resultSet.getString("number"));
                wareOut.setOutTime(resultSet.getTimestamp("outtime"));
                String id = resultSet.getString("m_id");
                manager = managerDao.findManager(id);
                wareOut.setManager(manager);
                list.add(wareOut);
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
    public boolean delete(WareOut wareOut){
        String sql = "delete from wareout where id = ?";
        return dao.updeta(sql, wareOut.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(WareOut wareOut){
        String sql = "update wareout set number=?, outtime=?, m_id=? where id = ?";
        return dao.updeta(sql, wareOut.getNumber(), wareOut.getOutTime(),
                wareOut.getManager().getId(), wareOut.getId());
    }

    /**
     * 添加方
     *
     * @return
     */
    public boolean save(WareOut wareOut){
        String sql = "insert into wareout values(?,?,?,?)";
        return dao.updeta(sql, wareOut.getId(), wareOut.getNumber(), wareOut.getOutTime(),
                wareOut.getManager().getId());
    }
}
