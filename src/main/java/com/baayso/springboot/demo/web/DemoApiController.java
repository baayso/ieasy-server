package com.baayso.springboot.demo.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.common.controller.CommonController;
import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baayso.springboot.demo.service.DemoUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/demo")
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
    public IPage<DemoUserDO> page(@RequestParam(defaultValue = DEFAULT_PAGE_NUM) Integer pageNum,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {

        IPage<DemoUserDO> page = this.demoUserService.page(
                new Page<>(pageNum, pageSize),
                new QueryWrapper<DemoUserDO>()
                        .between("age", "18", "20"));

        return page;
    }

    @RequestMapping("/page2")
    public PageVO<DemoUserDO> page2(@RequestParam(defaultValue = DEFAULT_PAGE_NUM) Integer pageNum,
                                    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        return this.demoUserService.page(pageNum, pageSize);
    }

    @RequestMapping("/test")
    public List<DemoUserDO> test() {
        return this.demoUserService.test();
    }

    @RequestMapping("/deletes")
    public Boolean deletes(@RequestParam Long id) {
        return this.demoUserService.deletes(id);
    }

    @RequestMapping("/create")
    public boolean create() {
        return this.demoUserService.save();
    }

    @RequestMapping("/creates")
    public boolean creates() {
        return this.demoUserService.saves();
    }

    @RequestMapping("/creates/async")
    public Map<String, Long> asyncCreates() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        Future<Long> firstTask = this.demoUserService.asyncSaves();
        Future<Long> secondTask = this.demoUserService.asyncSaves2();

        Long firstTaskResult = firstTask.get();
        Long secondTaskResult = secondTask.get();

        long endTime = System.currentTimeMillis();

        log.info("firstTaskResult: {}", firstTaskResult);
        log.info("secondTaskResult: {}", secondTaskResult);

        Map<String, Long> result = new HashMap<>(3);
        result.put("firstTask", firstTaskResult);
        result.put("secondTask", secondTaskResult);
        result.put("total", endTime - startTime);

        return result;
    }

    @RequestMapping("/update")
    public boolean update(@RequestParam Long id) {
        return this.demoUserService.update(id);
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

    @PostMapping("/json")
    public String json(@RequestBody String json) {
        System.out.println(json);

        return json;
    }

}
