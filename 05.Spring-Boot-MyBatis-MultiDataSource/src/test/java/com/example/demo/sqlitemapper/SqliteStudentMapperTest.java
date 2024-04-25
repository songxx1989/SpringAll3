package com.example.demo.sqlitemapper;

import com.example.demo.bean.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SqliteStudentMapperTest {

    @Autowired
    private SqliteStudentMapper studentMapper;

    @Test
    void getAllStudents() {
        List<Student> list = studentMapper.getAllStudents();
        for (Student student : list) {
            System.out.println(student);
        }
    }
}