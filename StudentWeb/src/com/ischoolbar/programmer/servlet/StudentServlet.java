//package com.ischoolbar.programmer.servlet;
//
//import com.ischoolbar.programmer.dao.StudentDao;
//import com.ischoolbar.programmer.model.Student;
//import com.ischoolbar.programmer.util.NumberGenerateUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class StudentServlet extends HttpServlet {
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        doPost(request, response);
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        String method = request.getParameter("method");
//        if("toStudentListView".equals(method)){
//            studentList(request,response);
//        }else if("AddStudent".equals(method)){
//            addStudent(request,response);
//        }
//    }
//
//    private void addStudent(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String sex = request.getParameter("sex");
//        String mobile = request.getParameter("mobile");
//        String qq = request.getParameter("qq");
//        int clazzid = Integer.parseInt(request.getParameter("clazzid"));
//        Student student = new Student();
//        student.setId(clazzid);
//        student.setName(name);
//        student.setPassword(password);
//        student.setSex(sex);
//        student.setMobile(mobile);
//        student.setQq(qq);
//        student.setNumber(NumberGenerateUtil.NumberGenerate(clazzid));
//        StudentDao studentDao = new StudentDao();
//        if(studentDao.addStudent(student)){
//            try {
//                response.getWriter().write("success");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void studentList(HttpServletRequest request,HttpServletResponse response) throws IOException{
//        try{
//            request.getRequestDispatcher("view/studentList.jsp").forward(request,response);
//        }catch (ServletException e){
//            e.printStackTrace();
//        }
//    }
//
//}
