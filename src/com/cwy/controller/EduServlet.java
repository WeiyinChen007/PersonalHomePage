package com.cwy.controller;

import com.cwy.bean.Edu;
import com.cwy.bean.Result;
import com.cwy.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/v1/edu/insert")
public class EduServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");

        int userId = 0;
        String userIdText = request.getParameter("userid");
        if(userIdText != null){
            try {
                userId = Integer.parseInt(userIdText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String school = request.getParameter("school");
        String study = request.getParameter("study");
        String description = request.getParameter("description");
        Edu edu = new Edu(userId,start,end,school,study,description);
        int row = DBService.insertEdu(edu);
        Result result = null;
        if(row>0){
            result = new Result(0,"学历新增成功");
        }else{
            result = new Result(-1,"学历新增失败");
        }

        String json = result.toJson();
        response.getWriter().append(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
