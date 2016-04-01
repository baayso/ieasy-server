package com.baayso.springboot.web;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.entity.TestUser;
import com.baayso.springboot.service.TestUserService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Inject
    private TestUserService testUserService;

    @RequestMapping
    public List<TestUser> now() {
        List<TestUser> users = this.testUserService.findAll();
        users.forEach(u -> u.setDatetime(new Date()));

        return users;
    }

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello";
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
