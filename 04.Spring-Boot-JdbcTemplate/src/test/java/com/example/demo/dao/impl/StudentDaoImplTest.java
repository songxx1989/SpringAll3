package com.example.demo.dao.impl;

import com.example.demo.bean.Student;
import com.example.demo.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudentDaoImplTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    void add() {
        Student student = new Student();
        student.setSno("100");
        student.setName("tony");
        student.setSex("F");
        int i = studentDao.add(student);
        assertEquals(1, i);
    }

    @Test
    void update() {
        Student student = new Student();
        student.setSno("101");
        student.setName("pony");
        student.setSex("M");
        studentDao.add(student);

        student.setSex("F");
        studentDao.update(student);
        student = studentDao.queryBySno(student.getSno());
        assertEquals("F", student.getSex());
    }


    @Test
    void deleteBySno() {
        Student student = new Student();
        student.setSno("102");
        student.setName("john");
        student.setSex("M");
        studentDao.add(student);

        int i = studentDao.deleteBySno(student.getSno());
        assertEquals(1, i);
    }

    @Test
    void queryListMap() {
        List<Map<String, Object>> list = studentDao.queryListMap();
        assertTrue(list.size() >= 3);
    }

    @Test
    void queryBySno() {
        Student student = new Student();
        student.setSno("104");
        student.setName("tom");
        student.setSex("F");
        studentDao.add(student);

        student = studentDao.queryBySno(student.getSno());
        assertNotNull(student);
        assertEquals("tom", student.getName());
        assertEquals("F", student.getSex());
    }

}