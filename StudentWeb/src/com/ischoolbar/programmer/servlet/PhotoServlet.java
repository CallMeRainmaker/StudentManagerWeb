package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.StudentDao;
import com.ischoolbar.programmer.model.Student;
import com.lizhou.exception.FileFormatException;
import com.lizhou.exception.NullFileException;
import com.lizhou.exception.ProtocolException;
import com.lizhou.exception.SizeException;
import com.lizhou.fileload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

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
        }else if("SetPhoto".equals(method)){
            setPhoto(request,response);
        }
    }

    private void setPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException{
        FileUpload fileUpload = new FileUpload(request);
        int sid = request.getParameter("sid") == null ? 0 : Integer.parseInt(request.getParameter("sid"));
        int tid = request.getParameter("tid") == null ? 0 : Integer.parseInt(request.getParameter("tid"));
        fileUpload.setFileFormat("jpg");
        fileUpload.setFileFormat("png");
        fileUpload.setFileFormat("jpeg");
        fileUpload.setFileFormat("gif");
        fileUpload.setFileSize(2048*1000);
        response.setCharacterEncoding("UTF-8");
        try {
            InputStream inputStream = fileUpload.getUploadInputStream();
            if(sid!=0){
                Student student = new Student();
                student.setId(sid);
                student.setPhoto(inputStream);
                StudentDao studentDao = new StudentDao();
                if(studentDao.setStudentPhoto(student)){
                    response.getWriter().write("<div id='message'>上传成功</div>");
                }else {
                    response.getWriter().write("<div id='message'>上传失败</div>");
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (NullFileException e) {
            e.printStackTrace();
        } catch (SizeException e) {
            e.printStackTrace();
        } catch (FileFormatException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }

    private void getPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int sid = request.getParameter("sid") == null ? 0:Integer.parseInt(request.getParameter("sid"));
        if(sid!=0){
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.getStudent(sid);
            if(student!=null){
                InputStream photo= student.getPhoto();
                if(photo!=null){
                    byte[] bytes = new byte[photo.available()];
                    photo.read(bytes);
                    response.getOutputStream().write(bytes,0,bytes.length);
                }
            }
        }
        File file = new File("/home/huxudong/IdeaProjects/StudentManagerWeb/StudentWeb/web/File/logo.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        response.getOutputStream().write(bytes,0, bytes.length);
    }
}
