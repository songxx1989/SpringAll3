package com.example.demo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private String sno;
    private String name;
    private String sex;
}
