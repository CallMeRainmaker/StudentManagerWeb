package com.ischoolbar.programmer.dao;

import com.ischoolbar.programmer.model.Clazz;
import com.ischoolbar.programmer.model.Page;
import com.ischoolbar.programmer.model.Student;
import com.ischoolbar.programmer.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends BaseDao {
    public boolean deleteStudent(int id){
        String sql = "delete from student where id = "+id;
        return update(sql);
    }

    public boolean editStudent(Student student){
        String sql = "update student set name = '"+student.getName()+"'";
        sql += ",sex = '"+student.getSex()+"',mobile = '"+student.getMobile()+"'";
        sql += ",qq = '"+student.getQq()+"',clazz_id = '"+student.getClazzId()+"'";
        sql += "where id = '"+student.getId()+"'";
        return update(sql);
    }

    public boolean addStudent(Student student){
        String sql = "insert into student values(null,'"+student.getNumber()+"','"+student.getName()+"'";
        sql += ",'" + student.getPassword() + "'," + student.getClazzId();
        sql += ",'" + student.getSex() + "','" + student.getMobile() + "'";
        sql += ",'" + student.getQq() + "',null)";
        return update(sql);
    }

    public List<Student> getStudentList(Student student, Page page){
        List<Student> ret = new ArrayList<Student>();
        String sql = "select * from student ";
        if(!StringUtil.isEmpty(student.getName())){
            sql += "where name like '%" + student.getName() + "%'";
        }
        if(student.getClazzId() != 0){
            sql += " and clazz_id = " + student.getClazzId();
        }
        sql += " limit " + page.getStart() + "," + page.getPageSize();
        ResultSet resultSet = query(sql.replaceFirst("and","where"));
        try {
            while(resultSet.next()){
                Student st = new Student();
                st .setId(resultSet.getInt("id"));
                st.setClazzId(resultSet.getInt("clazz_id"));
                st .setMobile(resultSet.getString("mobile"));
                st .setName(resultSet.getString("name"));
                st.setPassword(resultSet.getString("password"));
                st.setQq(resultSet.getString("qq"));
                st.setSex(resultSet.getString("sex"));
                st.setNumber(resultSet.getString("number"));
                st.setPhoto(resultSet.getBinaryStream("photo"));
                ret.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int getStudentTotal(Student student){
        int total = 0;
        String sql = "select count(*)as total from student ";
        if(!StringUtil.isEmpty(student.getName())){
            sql += "where name like '%" + student.getName() + "%'";
        }
        if(student.getClazzId() != 0){
            sql += " and clazz_id = " + student.getClazzId();
        }
        ResultSet resultSet = query(sql.replaceFirst("and","where"));
        try{
            while(resultSet.next()){
                total = resultSet.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
}
