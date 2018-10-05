package com.ischoolbar.programmer.servlet;

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
        }
        try{
            request.getRequestDispatcher("view/system.jsp").forward(request,response);
        }catch (ServletException e){
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
