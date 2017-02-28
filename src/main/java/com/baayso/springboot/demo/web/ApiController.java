package com.baayso.springboot.demo.web;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.common.domain.Page;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.service.DemoUserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Inject
    private DemoUserService demoUserService;

    @RequestMapping
    public List<DemoUserDO> now() {
        List<DemoUserDO> users = this.demoUserService.list();
        users.forEach(u -> u.setDatetime(new Date()));

        return users;
    }

    @RequestMapping("/page")
    public PageInfo<DemoUserDO> page(String pageSize, String pageNum) {

        int ps = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 3;
        int pn = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 2;

        return this.demoUserService.list(pn, ps);
    }

    @RequestMapping("/page2")
    public Page<DemoUserDO> page2(String pageSize, String pageNum) {

        int ps = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 3;
        int pn = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 2;

        Page<DemoUserDO> page = new Page<>(ps, pn);
        page.initBeforePage();

        List<DemoUserDO> list = this.demoUserService.list();

        page.initAfterPage(new PageInfo<>(list, ps));

        return page;
    }

    @RequestMapping("/create")
    public boolean create() {
        return this.demoUserService.saveTestUser();
    }

    @RequestMapping("/creates")
    public boolean creates() {
        return this.demoUserService.saveTestUsers();
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
