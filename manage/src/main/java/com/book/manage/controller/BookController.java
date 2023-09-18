package com.book.manage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BookController {
    @RequestMapping("/bookPage")
    public String books(){
        return "books";
    }

}
