package com.book.manage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class TransactionController {
    @RequestMapping("/transactionPage")
    public String transaction(){
        return "transactions";
    }
}
