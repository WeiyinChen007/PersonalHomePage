package com.cwy.util;

import com.cwy.bean.User;
import com.cwy.db.BaseDao;
import com.cwy.db.UserDaoImp;

public class Demo {
    //这是main方法，程序的入口
    public static void main(String[] args) {
        BaseDao<User> dao = new UserDaoImp();
        /*User user = new User("cwy2", 25, "北京", "地址", "邮箱", "12312312312", "weixin", "qq", "weibo", "不详", "description");
        int id = dao.insert(user);
        System.out.println(id);*/

        User user = dao.findByUserId(27);
        System.out.println(user);
    }
}
