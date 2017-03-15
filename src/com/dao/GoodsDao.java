package com.dao;

import com.bojo.Goods;
import com.bojo.GoodsType;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class GoodsDao {

    DAO dao = new DAO();

    /**
     *
     * @return
     */
    public Goods findGoods(String id){
        Goods goods = null;
        GoodsType goodsType;

        String sql = "select g.id g_id,g.name g_name, price, anount, remarks, t.id t_id, t.name t_name from goods g inner join goodstype t on g.t_id = t.id where g.id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
                resultSet.next();
                goods = new Goods();
                goods.setId(resultSet.getString("g_id"));
                goods.setName(resultSet.getString("g_name"));
                goods.setPrice(resultSet.getDouble("price"));
                goods.setAnount(resultSet.getString("anount"));
                goods.setRemarks(resultSet.getString("remarks"));
                goodsType = new GoodsType();
                goodsType.setId(resultSet.getString("t_id"));
                goodsType.setName(resultSet.getString("t_name"));
                goods.setGoodsType(goodsType);
                return goods;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return goods;
    }

    /**
     *
     * @return
     */
    public List<Goods> findAllGoods(){

        List<Goods> list = new ArrayList();
        Goods goods;
        GoodsType goodsType;

        String sql = "select g.id g_id, g.name g_name, price, anount, remarks, t.id t_id, t.name t_name from goods g inner join goodstype t on g.t_id = t.id";
        ResultSet resultSet = dao.select(sql);
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    goods = new Goods();
                    goods.setId(resultSet.getString("g_id"));
                    goods.setName(resultSet.getString("g_name"));
                    goods.setPrice(resultSet.getDouble("price"));
                    goods.setAnount(resultSet.getString("anount"));
                    goods.setRemarks(resultSet.getString("remarks"));
                    goodsType = new GoodsType();
                    goodsType.setId(resultSet.getString("t_id"));
                    goodsType.setName(resultSet.getString("t_name"));
                    goods.setGoodsType(goodsType);
                    list.add(goods);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return list;
    }

    /**
     * 删除方法
     * @param g
     */
    public boolean delete(Goods g){
        String sql = "delete from goods where id = ?";
        return dao.updeta(sql,g.getId());
    }

    /**
     * 更新方法
     * @param g
     * @return
     */
    public boolean updeta(Goods g){
        String sql = "update goods set name=?, price=?, anount=?, remarks=?, t_id=? where id=?";
        return dao.updeta(sql, g.getName(), g.getPrice(), g.getAnount(), g.getRemarks(), g.getGoodsType().getId(), g.getId());
    }

    /**
     * 添加方法
     * @param g
     * @return
     */
    public boolean save(Goods g){
        String sql = "insert into goods(id ,name, price, anount, remarks, t_id) values(?,?,?,?,?,?)";
        return dao.updeta(sql, g.getId(), g.getName(), g.getPrice(), g.getAnount(), g.getRemarks(), g.getGoodsType().getId());
    }

    public static void main(String[] args) {
        GoodsDao goodsDao = new GoodsDao();

    }

}