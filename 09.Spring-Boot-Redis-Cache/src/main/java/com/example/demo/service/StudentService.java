package com.example.demo.service;

import com.example.demo.bean.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "student")
public interface StudentService {
    @CachePut(key = "#p0.sno")
    Student update(Student student);

    @Cacheable(key="#p0")
    Student queryBySno(String sno);
}
