package com.cwy.controller;

import com.cwy.bean.Result;
import com.cwy.bean.User;
import com.cwy.service.DBService_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v1/user/insert")
public class UserInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收前端发送的数据
        String ageText = request.getParameter("age");
        int age = Integer.parseInt(ageText);
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String weixin = request.getParameter("weixin");
        String qq = request.getParameter("qq");
        String weibo = request.getParameter("weibo");
        String sex = request.getParameter("sex");
        String description = request.getParameter("description");
        //2. 通过前端发送的数据，组合为user
        User user = new User(name, age, city, address, email, phone, weixin, qq, weibo, sex, description);
        //3. 操作service，完成用户信息的存储
        int userId = DBService_1.insertUser(user);
        //4. 将存储的结果组装为一个JSON格式数据
        Result result = null;
        if (result == null)
            result = new Result(-1, "用户新增失败", userId);
        else
            result = new Result(1, "用户新增成功", userId);

        String json = result.toJson();
        //5. 将JSON返回给前端
        response.getWriter().append(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
