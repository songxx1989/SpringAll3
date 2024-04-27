package com.example.demo.dao.impl;

import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("sqliteStudentDao")
public class SqliteStudentDaoImpl implements StudentDao {

    @Autowired
    @Qualifier("sqliteJdbcTemplate")
    private JdbcTemplate sqliteJdbcTemplate;

    @Override
    public List<Map<String, Object>> getAllStudents() {
        return sqliteJdbcTemplate.queryForList("select * from student");
    }
}
