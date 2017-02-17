package com.baayso.springboot.web;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.common.entity.Page;
import com.baayso.springboot.entity.TestUser;
import com.baayso.springboot.service.TestUserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Inject
    private TestUserService testUserService;

    @RequestMapping
    public List<TestUser> now() {
        List<TestUser> users = this.testUserService.list();
        users.forEach(u -> u.setDatetime(new Date()));

        return users;
    }

    @RequestMapping("/page")
    public PageInfo<TestUser> page() {
        return this.testUserService.list(2, 3);
    }

    @RequestMapping("/page2")
    public Page<TestUser> page2() {
        int pageSize = 3;
        int pageNumber = 2;

        Page<TestUser> page = new Page<>(pageSize, pageNumber);
        page.initBeforePage();

        List<TestUser> list = this.testUserService.list();

        page.initAfterPage(new PageInfo<>(list, pageSize));

        return page;
    }

    @RequestMapping("/create")
    public boolean create() {
        return this.testUserService.saveTestUser();
    }

    @RequestMapping("/creates")
    public boolean creates() {
        return this.testUserService.saveTestUsers();
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
