package com.example.demo.service;

import com.example.demo.bean.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "student")
public interface StudentService {
    @CachePut(key = "#student.sno")
    Student update(Student student);

    @CacheEvict(key = "#sno")
    void deleteBySno(String sno);

    @Cacheable(key="#sno")
    Student queryBySno(String sno);
}
