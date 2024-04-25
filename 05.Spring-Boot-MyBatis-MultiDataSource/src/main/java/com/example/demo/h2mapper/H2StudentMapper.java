package com.example.demo.h2mapper;

import com.example.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface H2StudentMapper {
    @Select("select sno, sname as name, ssex as sex from student")
    List<Student> getAllStudents();
}
