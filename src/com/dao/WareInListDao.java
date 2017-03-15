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
public class WareInListDao {
    DAO dao = new DAO();
    GoodsDao goodsDao = new GoodsDao();
    WareInDao wareInDao = new WareInDao();
    ShelfDao shelfDao = new ShelfDao();
    SupplierDao supplierDao = new SupplierDao();

    /**
     *
     * @return
     */
    public WareInList findWareInList(String id){
        WareInList wareInList = null;
        String sql = "select * from wareinlist where id= ?";
        ResultSet resultSet = dao.select(sql, id);

        try {
            resultSet.next();
            wareInList = new WareInList();
            wareInList.setId(resultSet.getString("id"));
            wareInList.setAmount(resultSet.getString("amount"));
            wareInList.setAmountLess(resultSet.getString("amountless"));

            String g_id = resultSet.getString("g_id");
            Goods goods = new Goods();
            goods = goodsDao.findGoods(g_id);
            wareInList.setGoods(goods);

            String s_id = resultSet.getString("s_id");
            Shelf shelf = new Shelf();
            shelf = shelfDao.findShelf(s_id);
            wareInList.setShelf(shelf);

            String su_id = resultSet.getString("su_id");
            Supplier supplier = new Supplier();
            supplier = supplierDao.findSuppliser(su_id);
            wareInList.setSupplier(supplier);

            String wi_id = resultSet.getString("wi_id");
            WareIn wareIn = new WareIn();
            wareIn = wareInDao.findWareIn(wi_id);
            wareInList.setWareIn(wareIn);
            return wareInList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wareInList;
    }

    /**
     * 查询方法
     * @return
     */
    public List<WareInList> findAllWareInList(){
        List<WareInList> list = new ArrayList<>();
        WareInList wareInList;
        String sql = "select * from wareinlist";
        ResultSet resultSet = dao.select(sql);

        try {
            while (resultSet.next()) {
                wareInList = new WareInList();
                wareInList.setId(resultSet.getString("id"));
                wareInList.setAmount(resultSet.getString("amount"));
                wareInList.setAmountLess(resultSet.getString("amountless"));

                String g_id = resultSet.getString("g_id");
                Goods goods = new Goods();
                goods = goodsDao.findGoods(g_id);
                wareInList.setGoods(goods);

                String s_id = resultSet.getString("s_id");
                Shelf shelf = new Shelf();
                shelf = shelfDao.findShelf(s_id);
                wareInList.setShelf(shelf);

                String su_id = resultSet.getString("su_id");
                Supplier supplier = new Supplier();
                supplier = supplierDao.findSuppliser(su_id);
                wareInList.setSupplier(supplier);

                String wi_id = resultSet.getString("wi_id");
                WareIn wareIn = new WareIn();
                wareIn = wareInDao.findWareIn(wi_id);
                wareInList.setWareIn(wareIn);

                list.add(wareInList);
            }
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除方法
     * @return
     */
    public boolean delete(WareInList wareInList){
        String sql = "delete from wareinlist where id = ?";
        return dao.updeta(sql, wareInList.getId());
    }

    /**
     * 修改方法
     * @return
     */
    public boolean updete(WareInList wareInList){
        String sql = "update wareinlist set amount=?, amountless=?, g_id=?, s_id=?, su_id=?, wi_id=? where id =?";
        return dao.updeta(sql, wareInList.getAmount(), wareInList.getAmountLess(), wareInList.getGoods().getId(),
                wareInList.getShelf().getId(), wareInList.getSupplier().getId(), wareInList.getWareIn().getId(), wareInList.getId());
    }

    public boolean save(WareInList wareInList){
        String sql = "insert into wareinlist values(?,?,?,?,?,?,?)";
        return dao.updeta(sql, wareInList.getId(), wareInList.getAmount(), wareInList.getAmountLess(),
                wareInList.getGoods().getId(), wareInList.getShelf().getId(), wareInList.getSupplier().getId(),
                wareInList.getWareIn().getId());
    }
}
