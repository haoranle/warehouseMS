package com.dao;

import com.bojo.GoodsType;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class GoodsTypeDao {

    DAO dao = new DAO();

    /**
     * 查询方法
     * @return
     */
    public List<GoodsType> findAllGoodsType(){
        List<GoodsType> list = new ArrayList<>();
        String sql = "select id, name from goodstype";
        ResultSet resultSet = dao.select(sql);
        GoodsType goodsType;
        try {
            while (resultSet.next()){
                goodsType = new GoodsType();
                goodsType.setId(resultSet.getString("id"));
                goodsType.setName(resultSet.getString("name"));
                list.add(goodsType);
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
    public boolean delete(GoodsType goodsType){
        String sql = "delete from goodstype where id = ?";
        return dao.updeta(sql, goodsType.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(GoodsType goodsType){
        String sql = "update goodstype set name=? where id=?";
        return dao.updeta(sql, goodsType.getName(), goodsType.getId());
    }

    /**
     * 添加方法
     *
     * @return
     */
    public boolean save(GoodsType goodsType){
        String sql = "insert into goodsType(id,name) values(?,?)";
        return dao.updeta(sql, goodsType.getId(), goodsType.getName());
    }

}
