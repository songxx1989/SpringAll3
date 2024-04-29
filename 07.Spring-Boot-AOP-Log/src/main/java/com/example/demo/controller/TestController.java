package com.example.demo.controller;

import com.example.demo.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    @Log("执行方法1")
    @GetMapping("/one")
    public void methodOne(String name) {
    }

    @Log("执行方法2")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    @Log("执行方法3")
    @GetMapping("/three")
    public void methodThree(String name, Integer age) {
    }
}
