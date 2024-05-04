package com.example.demo.service.impl;

import com.example.demo.bean.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper mapper;

    @Override
    public Student update(Student student) {
        mapper.update(student);
        return mapper.queryBySno(student.getSno());
    }

    @Override
    public void deleteBySno(String sno) {
        mapper.deleteBySno(sno);
    }

    @Override
    public Student queryBySno(String sno) {
        return mapper.queryBySno(sno);
    }
}
