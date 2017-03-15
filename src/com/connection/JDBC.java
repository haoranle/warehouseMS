package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class JDBC {

    private static String url;
    private static String name;
    private static String passWord;
    private static String driver;

    static {
        url = "jdbc:mysql://localhost:3306/goods?useUnicode=true&characterEncoding=UTF-8";
        name = "root";
        passWord = "mysql";
        driver = "com.mysql.jdbc.Driver";
    }

    /**
     * 获取数据库连接方法
     * @return
     */
    public static Connection getConnection(){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, name, passWord);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
