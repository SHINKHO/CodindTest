package com.book.manage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {


    @RequestMapping("/")
    public String home(){
        return  "index";
    }
    @RequestMapping("/bookPage")
    public String books(){
        return "books";
    }

    @RequestMapping("/transactionPage")
    public String transaction(){
        return "transactions";
    }
}