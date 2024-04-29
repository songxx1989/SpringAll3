package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class IndexController {

    @GetMapping("/account")
    public String account(Model model) {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(Map.of("account", "KangKang", "name", "康康",
                "password", "e10adc3949ba59abbe56e", "accountType", "超级管理员", "tel", "17777777777"));
        list.add(Map.of("account", "Mike", "name", "麦克",
                "password", "e10adc3949ba59abbe56e", "accountType", "管理员", "tel", "13444444444"));
        list.add(Map.of("account", "Jane", "name", "简",
                "password", "e10adc3949ba59abbe56e", "accountType", "运维人员", "tel", "18666666666"));
        list.add(Map.of("account", "Maria", "name", "玛利亚",
                "password", "e10adc3949ba59abbe56e", "accountType", "清算人员", "tel", "19999999999"));
        model.addAttribute("accountList", list);
        return "account";
    }
}
