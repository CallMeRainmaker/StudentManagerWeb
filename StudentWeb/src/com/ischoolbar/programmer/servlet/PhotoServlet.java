package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Student;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        if("GetPhoto".equals(method)){
            getPhoto(request,response);
        }
    }

    private void getPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = request.getParameter("uid") == null ? 0:Integer.parseInt(request.getParameter("id"));
        if(id!=0){
            int userType = Integer.parseInt(request.getSession().getAttribute("usertype").toString());
            if(userType == 2){
                StudentDao studentDao = new StudentDao();
                Student student = studentDao.getStudent(id);
                if(student!=null){
                    InputStream photo= student.getPhoto();
                    if(photo!=null){
                        byte[] bytes = new byte[photo.available()];
                        photo.read(bytes);
                        response.getOutputStream().write(bytes,0,bytes.length);
                    }
                }
            }else if(userType == 3){
                return;
            }
        }
        File file = new File("/home/huxudong/IdeaProjects/StudentManagerWeb/StudentWeb/web/File/logo.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        response.getOutputStream().write(bytes,0, bytes.length);
    }
}
