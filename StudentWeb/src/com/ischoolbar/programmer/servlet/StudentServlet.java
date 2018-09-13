package com.ischoolbar.programmer.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("toStudentListView".equals(method)){
            studentList(request,response);
        }
    }

    public void studentList(HttpServletRequest request,HttpServletResponse response) throws IOException{
        try{
            request.getRequestDispatcher("view/studentList.jsp").forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }
    }

}
