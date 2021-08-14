package com.cwy.service;


import com.cwy.bean.User;
import com.cwy.db.BaseDao;
import com.cwy.db.UserDaoImp;

public class DBService_1 {
    private static BaseDao<User> userDao = new UserDaoImp();

    public static int insertUser(User user) {
        return userDao.insert(user);
    }

    public static User findUserByUserId(int userId) {
        return userDao.findByUserId(userId);
    }

}
