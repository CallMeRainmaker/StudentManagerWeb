package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.AdminDao;
import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.dao.TeacherDao;
import com.ischoolbar.programmer.model.Admin;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SystemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("toPersonalView".equals(method)){
            toPersonalView(request,response);
        }else if("toAdminView".equals(method)){
            toAdminView(request,response);
        }else if("EditPassword".equals(method))
            EditPassword(request,response);
    }

    private void EditPassword(HttpServletRequest request, HttpServletResponse response) {
        String password = request.getParameter("password");
        String newpassword = request.getParameter("newpassword");
        response.setCharacterEncoding("UTF-8");
        int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
        if(userType == 1){
            Admin admin = (Admin)request.getSession().getAttribute("user");
            if(!admin.getPassword().equals(password)){
                try {
                    response.getWriter().write("原密码错误！");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            AdminDao adminDao = new AdminDao();
            if(adminDao.editPassword(admin,newpassword)){
                try {
                    response.getWriter().write("success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    response.getWriter().write("数据库修改错误");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(userType == 2){
            Student student = (Student)request.getSession().getAttribute("user");
            if(!student.getPassword().equals(password)){
                try {
                    response.getWriter().write("原密码错误");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            StudentDao studentDao = new StudentDao();
            if(studentDao.editPassword(student,newpassword)){
                try {
                    response.getWriter().write("success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    response.getWriter().write("数据库修改错误");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(userType == 3){
            Teacher teacher = (Teacher)request.getSession().getAttribute("user");
            if(!teacher.getPassword().equals(password)){
                try {
                    response.getWriter().write("原密码错误");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            TeacherDao teacherDao = new TeacherDao();
            if(teacherDao.editPassword(teacher,newpassword)){
                try {
                    response.getWriter().write("success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    response.getWriter().write("数据库修改错误");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void toAdminView(HttpServletRequest request, HttpServletResponse response) {
        try{
            request.getRequestDispatcher("view/system.jsp").forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toPersonalView(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("view/personalView.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
