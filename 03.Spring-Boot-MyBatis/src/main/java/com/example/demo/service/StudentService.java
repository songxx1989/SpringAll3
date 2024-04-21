package com.example.demo.service;

import com.example.demo.bean.Student;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    Student queryBySno(String sno);
}
