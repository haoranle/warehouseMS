package com.dao;

import com.bojo.GoodsType;
import com.bojo.Shelf;
import com.bojo.WareHouse;
import com.connection.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/1/9.
 */
public class ShelfDao {
    DAO dao = new DAO();
    WareHouseDao wareHouseDao = new WareHouseDao();

    /**
     *
     * @return
     */
    public Shelf findShelf(String id){
        Shelf shelf = null;
        WareHouse wareHouse;
        String sql = "select * from shelf where id = ?";
        ResultSet resultSet = dao.select(sql, id);
        try {
            resultSet.next();
            shelf = new Shelf();
            shelf.setId(resultSet.getString("id"));
            shelf.setNumber(resultSet.getString("number"));
            shelf.setRemarks(resultSet.getString("remarks"));
            wareHouse = new WareHouse();
            String w_id = resultSet.getString("w_id");
            wareHouse = wareHouseDao.findWareHouse(w_id);
            shelf.setWareHouse(wareHouse);
            return shelf;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
        return shelf;
    }
    /**
     * 查询方法
     * @return
     */
    public List<Shelf> findAllShelf(){
        List<Shelf> list = new ArrayList<>();
        Shelf shelf;
        WareHouse wareHouse;
        String sql = "select * from shelf";
        ResultSet resultSet = dao.select(sql);
        try {
            while (resultSet.next()){
                shelf = new Shelf();
                shelf.setId(resultSet.getString("id"));
                shelf.setNumber(resultSet.getString("number"));
                shelf.setRemarks(resultSet.getString("remarks"));
                wareHouse = new WareHouse();
                String id = resultSet.getString("w_id");
                wareHouse = wareHouseDao.findWareHouse(id);
                shelf.setWareHouse(wareHouse);
                list.add(shelf);
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
    public boolean delete(Shelf shelf){
        String sql = "delete from shelf where id = ?";
        return dao.updeta(sql, shelf.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(Shelf shelf){
        String sql = "update shelf set number=?, remarks=?, w_id=? where id = ?";
        return dao.updeta(sql, shelf.getNumber(), shelf.getRemarks(), shelf.getWareHouse().getId(), shelf.getId());
    }

    /**
     * 添加方法
     * @param shelf
     * @return
     */
    public boolean save(Shelf shelf){
        String sql = "insert into shelf(id, number, remarks, w_id) values(?,?,?,?)";
        return dao.updeta(sql, shelf.getId(), shelf.getNumber(), shelf.getRemarks(), shelf.getWareHouse().getId());
    }
}
