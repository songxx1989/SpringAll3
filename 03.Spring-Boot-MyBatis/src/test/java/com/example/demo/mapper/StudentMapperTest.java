package com.example.demo.mapper;

import com.example.demo.bean.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void add() {
        Student student = new Student();
        student.setSno("100");
        student.setName("tony");
        student.setSex("F");
        int i = studentMapper.add(student);
        assertEquals(1, i);
    }

    @Test
    void update() {
        Student student = new Student();
        student.setSno("101");
        student.setName("pony");
        student.setSex("M");
        studentMapper.add(student);

        student.setSex("F");
        studentMapper.update(student);
        student = studentMapper.queryBySno(student.getSno());
        assertEquals("F", student.getSex());
    }

    @Test
    void deleteBySno() {
        Student student = new Student();
        student.setSno("102");
        student.setName("john");
        student.setSex("M");
        studentMapper.add(student);

        int i = studentMapper.deleteBySno(student.getSno());
        assertEquals(1, i);
    }

    @Test
    void queryBySno() {
        Student student = new Student();
        student.setSno("104");
        student.setName("tom");
        student.setSex("F");
        studentMapper.add(student);

        student = studentMapper.queryBySno(student.getSno());
        assertNotNull(student);
        assertEquals("tom", student.getName());
        assertEquals("F", student.getSex());
    }
}