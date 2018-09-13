package com.ischoolbar.programmer.dao;
import com.ischoolbar.programmer.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//封装基本操作

public class BaseDao {
    private DBUtil dbUtil = new DBUtil();

    public void closeCon(){
        dbUtil.closeCon();
    }

    public ResultSet query(String sql){
        try{
            PreparedStatement preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            return preparedStatement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
