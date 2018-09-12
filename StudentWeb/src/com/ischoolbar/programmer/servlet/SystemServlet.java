package com.ischoolbar.programmer.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SystemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("hello");
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{

    }
}
