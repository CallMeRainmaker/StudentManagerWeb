package com.ischoolbar.programmer.dao;

import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Teacher;
import com.ischoolbar.programmer.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends BaseDao {
    public boolean deleteTeacher(int id){
        String sql = "delete from teacher where id = "+id;
        return  update(sql);
    }

    public boolean addTeacher(Teacher teacher){
        String sql = "insert into teacher values(null,'"+teacher.getNumber()+"','"+teacher.getName()+"'";
        sql += ",'" + teacher.getPassword() + "'," + teacher.getClazz_id();
        sql += ",'" + teacher.getSex() + "','" + teacher.getMobile() + "'";
        sql += ",'" + teacher.getQq() + "',null)";
        return update(sql);
    }

    public List<Teacher> getTeacherList(Teacher teacher, Page page) {
        List<Teacher> teachers = new ArrayList<Teacher>();
        String sql = "select * from teacher ";
        if(!StringUtil.isEmpty(teacher.getName())){
            sql += "where name like '%" + teacher.getName() + "%'";
        }
        if(teacher.getClazz_id() != 0){
            sql += " and clazz_id = " + teacher.getClazz_id();
        }
        sql += " limit " + page.getStart() + "," + page.getPageSize();
        ResultSet resultSet = query(sql.replaceFirst("and","where"));
        try {
            while(resultSet.next()){
                Teacher teacher1 = new Teacher();
                teacher1.setId(resultSet.getInt("id"));
                teacher1.setNumber(resultSet.getString("number"));
                teacher1.setName(resultSet.getString("name"));
                teacher1.setSex(resultSet.getString("sex"));
                teacher1.setMobile(resultSet.getString("mobile"));
                teacher1.setQq(resultSet.getString("qq"));
                teacher1.setClazz_id(resultSet.getInt("clazz_id"));
                teachers.add(teacher1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public int getTeacherTotal(Teacher teacher) {
        int total = 0;
        String sql = "select count(*)as total from teacher ";
        if (!StringUtil.isEmpty(teacher.getName())) {
            sql += "where name like '%" + teacher.getName() + "%'";
        }
        if (teacher.getClazz_id() != 0) {
            sql += " and clazz_id = " + teacher.getClazz_id();
        }
        ResultSet resultSet = query(sql.replaceFirst("and", "where"));
        try {
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
