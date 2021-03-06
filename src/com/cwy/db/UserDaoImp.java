package com.cwy.db;

import com.cwy.bean.User;
import com.cwy.util.DBUtil;

import java.sql.*;


public class UserDaoImp implements BaseDao<User> {

    //用于操作数据库存储的sql
    private static final String SQL_INSERT = "insert into cwy_user(name, age, city, address, email, phone, weixin, qq, weibo, sex, description) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //用于获取数据库的用户基本信息
    private static final String SQL_FIND_BY_USERID = "select * from cwy_user where id=?";

    @Override
    public int insert(User user) {
        //1. 获取数据库的连接：conn
        Connection conn = DBUtil.getConn();
        //2. 通过连接 conn 创建执行sql语句的环境：statement，并预编译SQL
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //3. 向执行环境中，填充 ? 所表示的参数
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getCity());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getWeixin());
            statement.setString(8, user.getQq());
            statement.setString(9, user.getWeibo());
            statement.setString(10, user.getSex());
            statement.setString(11, user.getDescription());
            //4. 执行SQL
            statement.executeUpdate();
            //5. 获取新增的用户的id
            result = statement.getGeneratedKeys();
            if (result.next()) {
                //6. 将新增的用户的（id），作为此段代码的执行结果 返回
                return result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 把连接释放
            DBUtil.close(conn, statement, result);
        }

        return -1;
    }

    @Override
    public User findByUserId(int userId) {
        //1. 获取数据库的连接：conn
        Connection conn = DBUtil.getConn();
        //2. 通过连接 conn 创建执行sql语句的环境：statement，并预编译SQL
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement(SQL_FIND_BY_USERID);
            //3. 向执行环境中，填充 ? 所表示的参数
            statement.setInt(1, userId);
            //4. 执行SQL，并获取执行的结果集（结果的集合）
            result = statement.executeQuery();
            //5. 取出结果集中的用户的各个信息，组装成一个user
            if (result.next()) {
                int age = result.getInt("age");
                String name = result.getString("name");
                String city = result.getString("city");
                String address = result.getString("address");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String weixin = result.getString("weixin");
                String qq = result.getString("qq");
                String weibo = result.getString("weibo");
                String sex = result.getString("sex");
                String description = result.getString("description");
                User user = new User(userId, name, age, city, address, email, phone, weixin, qq, weibo, sex, description);
                //6. 将组装完毕的user，作为此段代码的执行结果，返回给调用者
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 释放资源
            DBUtil.close(conn, statement, result);
        }

        return null;
    }
}
