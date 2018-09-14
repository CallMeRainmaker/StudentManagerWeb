package com.ischoolbar.programmer.servlet;

import com.ischoolbar.programmer.dao.ClazzDao;
import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        }else if("AddClazz".equals(method)){
            addClass(request,response);
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
        String name = request.getParameter("clazzName");
        Integer currentPage = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("rows"));

        Clazz clazz = new Clazz();
        clazz.setName(name);
        ClazzDao clazzDao = new ClazzDao();
        List<Clazz> clazzes = clazzDao.getClazzList(clazz,new Page(currentPage,pageSize));
        int total = clazzDao.getClaszzTatal(clazz);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("rows",clazzes);
        response.setCharacterEncoding("UTF-8");
        try{
            response.getWriter().write(JsonArray.fromObject(map).toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addClass(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Clazz clazz = new Clazz();
        clazz.setName(name);
        clazz.setInfo(info);
        ClazzDao clazzDao = new ClazzDao();

    }
}
