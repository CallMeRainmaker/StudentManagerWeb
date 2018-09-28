package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.util.NumberGenerateUtil;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("toStudentListView".equals(method)){
            studentList(request,response);
        }else if("AddStudent".equals(method)){
            addStudent(request,response);
        }else if("StudentList".equals(method)){
            getStudentList(request,response);
        }else if("EditStudent".equals(method)){
            editStudent(request,response);
        }else if("DeleteStudent".equals(method)){
            deleteStudent(request,response);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        StudentDao studentDao = new StudentDao();
        if(studentDao.deleteStudent(id)){
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String qq = request.getParameter("qq");
        int clazzid = Integer.parseInt(request.getParameter("clazzid"));
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setSex(sex);
        student.setMobile(mobile);
        student.setQq(qq);
        student.setClazzId(clazzid);
        StudentDao studentDao = new StudentDao();
        if(studentDao.editStudent(student)){
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getStudentList(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Integer currentPage = request.getParameter("page") == null ? 1: Integer.parseInt(request.getParameter("page"));
        Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
        Integer clazz = request.getParameter("clazzid") == null ? 0 : Integer.parseInt(request.getParameter("clazzid"));
        Student  student = new Student();
        student.setName(name);
        student.setClazzId(clazz);
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.getStudentList(student,new Page(currentPage,pageSize));
        int total = studentDao.getStudentTotal(student);
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> ret = new HashMap<>();
        ret.put("tatal",total);
        ret.put("rows",students);
        try{
            response.getWriter().write(JSONArray.fromObject(students).toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String qq = request.getParameter("qq");
        int clazzid = Integer.parseInt(request.getParameter("clazzid"));
        Student student = new Student();
        student.setId(clazzid);
        student.setName(name);
        student.setPassword(password);
        student.setSex(sex);
        student.setMobile(mobile);
        student.setQq(qq);
        student.setNumber(NumberGenerateUtil.NumberGenerate(clazzid));
        StudentDao studentDao = new StudentDao();
        if(studentDao.addStudent(student)){
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
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
