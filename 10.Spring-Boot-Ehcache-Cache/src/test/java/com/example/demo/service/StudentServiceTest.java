package com.example.demo.service;

import com.example.demo.bean.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void update() {
        Student student1 = studentService.queryBySno("001");
        System.out.println(student1);

        student1.setName("康康");
        studentService.update(student1);
        System.out.println("更新完毕");

        Student student2 = studentService.queryBySno("001");
        System.out.println(student2);
    }

    @Test
    void queryBySno() {
        Student student1 = studentService.queryBySno("001");
        System.out.println(student1);

        Student student2 = studentService.queryBySno("001");
        System.out.println(student2);

    }
}