package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.AdminDao;
import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.dao.TeacherDao;
import com.ischoolbar.programmer.model.Admin;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.model.Teacher;
import com.ischoolbar.programmer.util.StringUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String method = request.getParameter("method");
        if("logout".equals(method)){
            logout(request,response);
            return;
        }

        String vcode = request.getParameter("vcode");
        String name = request.getParameter("account");
        String password = request.getParameter("password");
        int type = Integer.parseInt(request.getParameter("type"));
        String logincpacha = request.getSession().getAttribute("loginCpacha").toString();
        if (StringUtil.isEmpty(vcode)) {
            response.getWriter().write("vcodeError");
            return;
        }
        if (!vcode.toUpperCase().equals(logincpacha.toUpperCase())) {
            response.getWriter().write("vcodeError");
            return;
        }


        //用户名密码正确
        String loginStatus = "loginFaild";
        switch(type){
            case 1:{
                AdminDao adminDao = new AdminDao();
                Admin admin = adminDao.login(name, password);
                if (admin == null) {
                    response.getWriter().write("loginError");
                    return;
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", admin);
                session.setAttribute("userType", type);
                loginStatus = "admin";
                break;
            }
            case 2:{
                StudentDao studentDao = new StudentDao();
                Student student = studentDao.login(name, password);
                if (student == null) {
                    response.getWriter().write("loginError");
                    return;
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", student);
                session.setAttribute("userType", type);
                loginStatus = "student";
                break;
            }
            case 3:{
                TeacherDao teacherDao = new TeacherDao();
                Teacher teacher = teacherDao.login(name, password);
                if (teacher == null) {
                    response.getWriter().write("loginError");
                    return;
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", teacher);
                session.setAttribute("userType", type);
                loginStatus = "teacher";
            }
            default:
                break;
        }
        response.getWriter().write(loginStatus);


    }

    private void logout(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userType");
        response.sendRedirect("index.jsp");
    }
}