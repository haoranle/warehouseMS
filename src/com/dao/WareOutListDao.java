package com.dao;

import com.bojo.*;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class WareOutListDao {
    DAO dao = new DAO();
    GoodsDao goodsDao = new GoodsDao();
    WareOutDao wareOutDao = new WareOutDao();
    WareInListDao wareInListDao = new WareInListDao();

    /**
     * 查询方法
     * @return
     */
    public List<WareOutList> findAllWareOutList(){
        List<WareOutList> list = new ArrayList<>();
        WareOutList wareOutList;
        String sql = "select * from wareoutlist";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()) {
                wareOutList = new WareOutList();
                wareOutList.setId(resultSet.getString("id"));
                wareOutList.setAmount(resultSet.getString("amount"));

                String g_id = resultSet.getString("g_id");
                Goods goods = new Goods();
                goods = goodsDao.findGoods(g_id);
                wareOutList.setGoods(goods);

                String wil_id = resultSet.getString("wil_id");
                WareInList wareInList = new WareInList();
                wareInList = wareInListDao.findWareInList(wil_id);
                wareOutList.setWareInList(wareInList);

                String wo_id = resultSet.getString("wo_id");
                WareOut wareOut = new WareOut();
                wareOut = wareOutDao.findWare(wo_id);
                wareOutList.setWareOut(wareOut);
                list.add(wareOutList);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 删除方法
     * @return
     */
    public boolean delete(WareOutList wareOutList){
        String sql = "delete from wareoutlist where id =?";
        return dao.updeta(sql, wareOutList.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(WareOutList wareOutList){

        String sql = "update wareoutlist set amount=?, g_id=?, wil_id=?, wo_id=? where id=?";
        return dao.updeta(sql, wareOutList.getAmount(), wareOutList.getGoods().getId(),
                wareOutList.getWareInList().getId(), wareOutList.getWareOut().getId(), wareOutList.getId());
    }

    /**
     * 添加方法
     * @return
     */
    public boolean save(WareOutList wareOutList){

        String sql = "insert into wareoutlist values(?,?,?,?,?)";
        return dao.updeta(sql, wareOutList.getId(), wareOutList.getAmount(),wareOutList.getGoods().getId(),
                wareOutList.getWareInList().getId(), wareOutList.getWareOut().getId());
    }
}
