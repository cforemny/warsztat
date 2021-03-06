package com.mkyong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {


    @GetMapping("/")
    public String home1() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/404")
    public String error404() {
        return "home";
    }

    @GetMapping("/403")
    public String error403() {
        return "home";
    }

    @GetMapping("/500")
    public String error500() {
        return "home";
    }

    //TODO: usunac przekierowanie na main.css i usunac ten fragment
    @GetMapping("/main.css")
    public String home2() {
        return "home";
    }


}