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
import net.sf.json.JSONArray;

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
        }else if("DeleteClazz".equals(method)){
            deleteClazz(request,response);
        }else if("EditClazz".equals(method)){
            editClazz(request,response);
        }
    }

    private void editClazz(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Clazz clazz = new Clazz();
        clazz.setName(name);
        clazz.setInfo(info);
        clazz.setId(id);
        ClazzDao clazzDao = new ClazzDao();
        if (clazzDao.editClazz(clazz)) {
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void deleteClazz(HttpServletRequest request, HttpServletResponse response) {
        Integer clazzidid = Integer.parseInt(request.getParameter("clazzid"));
        ClazzDao clazzDao = new ClazzDao();
        if(clazzDao.deleteClazz(clazzidid)){
            try{
                response.getWriter().write("success");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void clazzList(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("view/clazzList.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getClazzList(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String name = request.getParameter("clazzName");
        Integer currentPage = request.getParameter("page") == null ? 1: Integer.parseInt(request.getParameter("page"));
        Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));

        Clazz clazz = new Clazz();
        clazz.setName(name);
        ClazzDao clazzDao = new ClazzDao();
        List<Clazz> clazzes = clazzDao.getClazzList(clazz,new Page(currentPage,pageSize));
        int total = clazzDao.getClazzTotal(clazz);
        response.setCharacterEncoding("utf-8");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",total);
        map.put("rows",clazzes);
        try{
            response.getWriter().write(JSONArray.fromObject(clazzes).toString());
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
        if(clazzDao.addClazz(clazz)){
            response.getWriter().write("success");
        }
    }
}
