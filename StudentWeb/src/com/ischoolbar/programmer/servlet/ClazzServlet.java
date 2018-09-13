package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.ClazzDao;
import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClazzServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("toClazzListView".equals(method)){
            clazzList(request,response);
        }else if("getClazzList".equals(method)){
            getClazzList(request,response);
        }
    }

    public void clazzList(HttpServletRequest request,HttpServletResponse response) throws IOException{
        try{
            request.getRequestDispatcher("view/clazzList.jsp").forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }
    }

    private void getClazzList(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String name = request.getParameter("name");
        Integer currentPage = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("rows"));

        Clazz clazz = new Clazz();
        clazz.setName(name);
        ClazzDao clazzDao = new ClazzDao();
        List<Clazz> clazzes = clazzDao.getClaszz(clazz,new Page(currentPage,pageSize));

//        JsonConfig jsonConfig = new JsonConfig();
//        String clazzesstring = JSONArray.fromObject(clazzes,jsonConfig).toString();
//        response.setCharacterEncoding("UTF-8");
//        try{
//            response.getWriter().write(clazzesstring);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
