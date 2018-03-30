package com.baayso.springboot.demo.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baayso.springboot.demo.service.DemoUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

@RestController
@RequestMapping("/demo/api")
public class DemoApiController {

    @Inject
    private DemoUserService demoUserService;

    @RequestMapping
    public List<DemoUserDO> now(String name) {
        List<DemoUserDO> users = this.demoUserService.selectList( //
                new EntityWrapper<>(DemoUserDO.builder() //
                        .status(OrderStatus.WAIT_PAY) //
                        .build()) //
                        .like(StringUtils.isNotBlank(name), "name", name) //
                        .between("age", 18, 20) //
                        .orderBy("age", false) //
                        .orderBy("id"));

        users.forEach(u -> u.setDatetime(LocalDateTime.now()));

        return users;
    }

    @RequestMapping("/page")
    public Page<DemoUserDO> page(String pageSize, String pageNum) {

        int ps = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 3;
        int pn = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 1;

        Page<DemoUserDO> page = this.demoUserService.selectPage(
                new Page<>(pn, ps),
                new EntityWrapper<DemoUserDO>()
                        .between("age", "18", "20"));

        return page;
    }

    @RequestMapping("/page2")
    public PageVO<DemoUserDO> page2(String pageSize, String pageNum) {

        int ps = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 3;
        int pn = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 1;

        PageVO<DemoUserDO> page = new PageVO<>(ps, pn);
        page.initBeforePage();

        // List<DemoUserDO> list = this.demoUserService.list();

        // page.initAfterPage(new PageInfo<>(list, ps));

        return page;
    }

    @RequestMapping("/deletes")
    public Boolean deletes() {
        return this.demoUserService.deletes();
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
