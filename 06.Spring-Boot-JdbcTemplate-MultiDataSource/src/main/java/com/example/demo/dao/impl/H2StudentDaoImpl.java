package com.example.demo.dao.impl;

import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("h2StudentDao")
public class H2StudentDaoImpl implements StudentDao {

    @Autowired
    @Qualifier("h2JdbcTemplate")
    private JdbcTemplate h2JdbcTemplate;

    @Override
    public List<Map<String, Object>> getAllStudents() {
        return h2JdbcTemplate.queryForList("select * from student");
    }
}
