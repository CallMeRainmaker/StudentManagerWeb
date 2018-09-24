package com.ischoolbar.programmer.dao;

import com.ischoolbar.programmer.model.Student;

public class StudentDao extends BaseDao {
    public boolean addStudent(Student student){
            String sql = "insert into s_student values(null,'" + student.getNumber() + "','" + student.getName() + "'";
            sql += ",'" + student.getPassword() + "'," + student.getSex();
            sql += ",'" + student.getClazzId() + "','" + student.getMobile() + "'";
            sql += ",'" + student.getQq() + "',null)";
            return update(sql);
    }
}
