package com.cwy.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    //操作数据库的三要素
    private static String url = "";
    private static String user = "";
    private static String password = "";

    //加载配置文件，获取文件中的三要素
    static{
        Properties ppt = new Properties();
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            ppt.load(is);
            url = ppt.getProperty("url");
            user = ppt.getProperty("user");
            password = ppt.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 用于链接数据库，得到的结果是数据库的连接对象，链接对象具备了操作数据库的很多功能。=
     * @return 链接对象
     */
    public static Connection getConn(){

        //1.    获取数据库链接
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            //3.    将链接返回给工具的使用者
            return conn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 断开数据库资源的链接，用于释放资源
     * 尽可能晚的连数据库，尽可能早的断开数据库连接
     * @param conn 要释放链接
     * @param statement 要释放的执行环境
     * @param resultSet 要释放的结果集
     */
    public static void close(Connection conn, Statement statement, ResultSet resultSet){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
