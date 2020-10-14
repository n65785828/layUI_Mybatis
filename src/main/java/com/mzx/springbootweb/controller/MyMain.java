package com.mzx.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//入口控制器
@Controller
public class MyMain {


    @GetMapping(value = {"old"})
    public String loginOld(){
        return "login.html";
    }

    @GetMapping(value = {""})
    public String login(){
        return "page/login-3.html";
    }

    @GetMapping(value = {"/index"})
    public String index(){
        return "index.html";
    }
}
