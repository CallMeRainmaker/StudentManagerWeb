package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.TeacherDao;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.model.Teacher;
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

public class TeacherServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("toTeacherListView".equals(method)){
            TeacherList(request,response);
        }else if("AddTeacher".equals(method)){
            addTeacher(request,response);
        }else if("TeacherList".equals(method)){
            getTeacherList(request,response);
        }else if("EditTeacher".equals(method)){
            editTeacher(request,response);
        }else if("DeleteTeacher".equals(method)){
            deleteTeacher(request,response);
        }
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        TeacherDao teacherDao = new TeacherDao();
        if(teacherDao.deleteTeacher(id)){
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getTeacherList(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("teacherName");
        Integer currentPage = request.getParameter("page") == null ? 1: Integer.parseInt(request.getParameter("page"));
        Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
        Integer clazz = request.getParameter("clazz_id") == null ? 0 : Integer.parseInt(request.getParameter("clazz_id"));
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setClazz_id(clazz);
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teachers = teacherDao.getTeacherList(teacher,new Page(currentPage,pageSize));
        int total = teacherDao.getTeacherTotal(teacher);
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> ret = new HashMap<>();
        ret.put("tatal",total);
        ret.put("rows",teachers);
        try{
            response.getWriter().write(JSONArray.fromObject(teachers).toString());
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    private void editTeacher(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addTeacher(HttpServletRequest request, HttpServletResponse response) {
        int clazzid = Integer.parseInt(request.getParameter("clazzid"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String qq = request.getParameter("qq");
        Teacher teacher = new Teacher();
        teacher.setClazz_id(clazzid);
        teacher.setName(name);
        teacher.setSex(sex);
        teacher.setMobile(mobile);
        teacher.setQq(qq);
        teacher.setNumber(NumberGenerateUtil.TeacherNumberGenerate(clazzid));
        TeacherDao teacherDao = new TeacherDao();
        if(teacherDao.addTeacher(teacher)){
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
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

