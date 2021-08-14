package com.cwy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharSetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1. 调整请求编码为UTF-8
        //2. 设置响应编码为UTF-8
        req.setCharacterEncoding("utf-8");
        //resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        //3. 放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
