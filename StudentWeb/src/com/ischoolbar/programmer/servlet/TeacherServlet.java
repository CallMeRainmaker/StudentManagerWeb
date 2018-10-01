package com.ischoolbar.programmer.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("toTeacherListView".equals(method)){
            TeacherList(request,response);
//        }else if("AddTeacher".equals(method)){
//            addTeacher(request,response);
//        }else if("TeacherList".equals(method)){
//            getTeacherList(request,response);
//        }else if("EditTeacher".equals(method)){
//            editTeacher(request,response);
//        }else if("DeleteTeacher".equals(method)){
//            deleteTeacher(request,response);
        }
    }

    private void TeacherList(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("view/teacherList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

