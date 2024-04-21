package com.example.demo.bean;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 4125732006499364944L;
    private String sno;
    private String name;
    private String sex;
}
