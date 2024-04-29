package com.example.demo.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SysLog {
    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
