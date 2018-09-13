package com.ischoolbar.programmer.dao;

import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClazzDao extends BaseDao {
    public List<Clazz> getClaszz(Clazz clazz, Page page){
        List<Clazz> clazzes = new ArrayList<Clazz>();
        String sql = "select * from clazz ";
        if(!StringUtil.isEmpty(clazz.getName())){
            sql += "where name like '%" + clazz.getName() + "'";
        }
        sql +="limit " + page.getStart() + "," + page.getPageSize();
        ResultSet resultSet =  query(sql);
        try{
            while(resultSet.next()){
                Clazz cl = new Clazz();
                cl.setId(resultSet.getInt("id"));
                cl.setName(resultSet.getString("name"));
                cl.setInfo(resultSet.getString("info"));
                clazzes.add(cl);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clazzes;
    }
}
