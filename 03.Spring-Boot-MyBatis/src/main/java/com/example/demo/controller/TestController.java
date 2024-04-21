package com.example.demo.controller;

import com.example.demo.bean.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public Student queryBySno(String sno) {
        return studentService.queryBySno(sno);
    }
}
