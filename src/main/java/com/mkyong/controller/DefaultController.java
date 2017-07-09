package com.mkyong.controller;

import com.mkyong.SQLBase.TableSelector;
import com.mkyong.payment.paymentSummary.MonthSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @Autowired
    TableSelector tableSelector;
    @Autowired
    MonthSummary monthSummary;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }


    @GetMapping("/instruktor")
    public String user() {
        return "/instruktor";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    //TODO: usunac przekierowanie na main.css i usunac ten fragment
    @GetMapping("/main.css")
    public String home2() {
        return "/home";
    }


}