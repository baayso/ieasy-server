package com.baayso.springboot.demo.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.commons.tool.CommonResponseStatus;
import com.baayso.springboot.common.controller.CommonController;
import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.common.exception.ApiViolationException;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baayso.springboot.demo.service.DemoUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
@RequestMapping("/demo/api")
public class DemoApiController extends CommonController {

    @Inject
    private DemoUserService demoUserService;

    @RequestMapping
    public List<DemoUserDO> now(String name) {
        List<DemoUserDO> users = this.demoUserService.list( //
                new QueryWrapper<>(DemoUserDO.builder() //
                        .status(OrderStatus.WAIT_PAY) //
                        .build()) //
                        .like(StringUtils.isNotBlank(name), "name", name) //
                        .between("age", 18, 20) //
                        .orderByDesc("age") //
                        .orderByAsc("create_time"));

        users.forEach(u -> u.setDatetime(LocalDateTime.now()));

        return users;
    }

    @RequestMapping("/page")
    public IPage<DemoUserDO> page(String pageSize, String pageNum) {

        int ps = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 3;
        int pn = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 1;

        IPage<DemoUserDO> page = this.demoUserService.page(
                new Page<>(pn, ps),
                new QueryWrapper<DemoUserDO>()
                        .between("age", "18", "20"));

        return page;
    }

    @RequestMapping("/page2")
    public PageVO<DemoUserDO> page2(String pageSize, String pageNum) {

        int ps = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 3;
        int pn = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 1;

        // PageVO<DemoUserDO> page = new PageVO<>(ps, pn);
        // page.initBeforePage();

        // List<DemoUserDO> list = this.demoUserService.list();

        // page.initAfterPage(new PageInfo<>(list, ps));

        return new PageVO<>();
    }

    @RequestMapping("/deletes")
    public Boolean deletes(String id) {

        if (!super.validator.isLong(id)) {
            throw new ApiViolationException(CommonResponseStatus.ILLEGAL_DATA);
        }

        return this.demoUserService.deletes(Long.valueOf(id));
    }

    @RequestMapping("/create")
    public boolean create() {
        return this.demoUserService.saveUser();
    }

    @RequestMapping("/creates")
    public boolean creates() {
        return this.demoUserService.saveUsers();
    }

    @RequestMapping("/creates/async")
    public Future<Boolean> asyncCreates() throws InterruptedException {
        return this.demoUserService.asyncSaveUsers();
    }

    @RequestMapping("/update")
    public boolean update(String id) {

        if (!super.validator.isLong(id)) {
            throw new ApiViolationException(CommonResponseStatus.ILLEGAL_DATA);
        }

        return this.demoUserService.update(Long.valueOf(id));
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

    @RequestMapping("/test")
    public String test(@RequestBody String json) {
        System.out.println(json);

        return json;
    }

}
