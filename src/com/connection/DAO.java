package com.connection;

import java.sql.*;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class DAO {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DAO(){
    }


    /**
     * 通用更新方法
     * @param sql
     * @param args
     */
    public boolean updeta(String sql, Object ... args){
        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {


                }
            }
        }
        return false;
    }

    /**
     * 通用查询方法
     * @param sql
     * @param args
     * @return
     */
    public ResultSet select(String sql, Object ... args){

        try {
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return resultSet;
    }

    /**
     * 关闭数据库连接
     */
    public void close(){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
