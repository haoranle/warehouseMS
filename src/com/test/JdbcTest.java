package com.test;

import java.sql.*;

/**
 * Created by 李浩然 on 2017/1/7.
 * 修改這個的是好孩子 that is good boy who edit this source
 */
public class JdbcTest {
    public JdbcTest() {
    }

    public static void main(String[] args) {

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // 连接数据库
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/goods_lee?useUnicode=true&characterEncoding=UTF-8",
                            "root", "mysql");

            // 执行查询
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM goodsType");

            // 打印返回结果
            while (resultSet.next()) {
                System.out.print("编号 ： " + resultSet.getString(1));
                System.out.print("   ");
                System.out.println("类型 ： " + resultSet.getString(2));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
