package com.ischoolbar.programmer.util;

import java.sql.*;

public class DBUtil {

    private String DBUrl = "jdbc:mysql://localhost:3306/db_student_manager_web?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String DBUser = "root";
    private String DBPassword = "19971008";
    private String jdbcName = "com.mysql.jdbc.Driver";
    private Connection connection = null;
    public Connection getConnection(){

        try{
            Class.forName(jdbcName);
            connection = DriverManager.getConnection(DBUrl,DBUser,DBPassword);
            System.out.println("数据库连接成功");
        }catch(Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeCon() {
        if(connection!=null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        DBUtil dbUtil = new DBUtil();
        dbUtil.getConnection();
    }
}
