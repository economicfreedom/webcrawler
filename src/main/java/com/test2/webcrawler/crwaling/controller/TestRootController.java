package com.test2.webcrawler.crwaling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestRootController {
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "<H1>Hello</H1>";
    }
}
