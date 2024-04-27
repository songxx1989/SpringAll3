package com.example.demo.dao.impl;

import com.example.demo.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class H2StudentDaoImplTest {

    @Autowired
    @Qualifier("h2StudentDao")
    private StudentDao h2StudentDao;

    @Test
    void getAllStudents() {
        List<Map<String, Object>> list = h2StudentDao.getAllStudents();
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.printf("%s:%s; ", entry.getKey(), entry.getValue());
            }
            System.out.println();
        }
    }
}