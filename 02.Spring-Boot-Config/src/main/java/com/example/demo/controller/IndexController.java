package com.example.demo.controller;

import com.example.demo.config.BlogProperties;
import com.example.demo.config.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private BlogProperties blogProperties;
    @Autowired
    private TestProperties testProperties;

    @RequestMapping("/")
    String index() {
        return testProperties.getName();
    }
}
