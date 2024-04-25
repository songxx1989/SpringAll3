package com.example.demo.sqlitemapper;

import com.example.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SqliteStudentMapper {
    @Select("select sno, sname as name, ssex as sex from student")
    List<Student> getAllStudents();
}
