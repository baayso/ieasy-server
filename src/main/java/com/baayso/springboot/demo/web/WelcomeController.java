package com.baayso.springboot.demo.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "baayso");

        return "welcome";
    }

    @RequestMapping("/fail")
    public String fail() {
        throw new IllegalStateException("Oh dear!");
    }

    @RequestMapping("/fail2")
    public String fail2() {
        throw new IllegalStateException();
    }

}
