package com.example.demo.dao;

import com.example.demo.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int add(Student student);

    int update(Student student);

    int deleteBySno(String sno);

    List<Map<String, Object>> queryListMap();

    Student queryBySno(String sno);
}
