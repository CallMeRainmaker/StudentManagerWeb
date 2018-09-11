package com.ischoolbar.programmer.servlet;
import com.ischoolbar.programmer.util.CpachaUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 *验证码servlet
 */
public class CpachaServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
    public void picture(HttpServletRequest requset,HttpServletResponse response) throws IOException{
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        CpachaUtil cpachaUtil = new CpachaUtil();
        String generateVcode = cpachaUtil.getCode();
        HttpSession session = requset.getSession();
        session.setAttribute("loginCpacha",generateVcode);
        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
        cpachaUtil.write(response.getOutputStream());
    }
    public void doPost(HttpServletRequest requset,HttpServletResponse response) throws IOException{
        String method = requset.getParameter("method");
        if("loginCpacha".equals(method)){
            picture(requset,response);
            return;
        }
    }

}

