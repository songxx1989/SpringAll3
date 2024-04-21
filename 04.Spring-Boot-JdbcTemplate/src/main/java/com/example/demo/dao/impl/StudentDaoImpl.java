package com.example.demo.dao.impl;

import com.example.demo.bean.Student;
import com.example.demo.dao.StudentDao;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate tpl;

    @Override
    public int add(Student student) {
        String sql = "insert into student(sno, sname, ssex) values(:sno, :name, :sex)";
        return tpl.update(sql, new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int update(Student student) {
        String sql = "update student set sname=:name,ssex=:sex where sno=:sno";
        return tpl.update(sql, new BeanPropertySqlParameterSource(student));
    }

    @Override
    public int deleteBySno(String sno) {
        String sql = "delete from student where sno=:sno";
        return tpl.update(sql, Map.of("sno", sno));
    }

    @Override
    public List<Map<String, Object>> queryListMap() {
        String sql = "select * from student";
        return tpl.queryForList(sql, new HashMap<>());
    }

    @Override
    public Student queryBySno(String sno) {
        String sql = "select * from student where sno=:sno";
        List<Student> studentList = tpl.query(sql, Map.of("sno", sno), new StudentMapper());
        if (studentList != null && studentList.size() > 0) return studentList.get(0);
        return null;
    }
}
