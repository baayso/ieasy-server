package com.baayso.springboot.demo.web;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.common.controller.CommonController;
import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.service.DemoUserService;

/**
 * 控制器：测试。
 *
 * @author ChenFangjie (2020/8/1 23:39)
 * @since 1.0.0
 */
@RestController
@RequestMapping("/demo/user/api")
public class DemoUserController extends CommonController {

    private final DemoUserService demoUserService;

    @Autowired
    public DemoUserController(DemoUserService demoUserService) {
        this.demoUserService = demoUserService;
    }

    /**
     * 分页查询数据。
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/list")
    public PageVO<DemoUserDO> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        return this.demoUserService.page(pageNum, pageSize);
    }

    /**
     * 根据ID获取数据。
     *
     * @param id ID
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/get/{id}")
    public DemoUserDO get(@PathVariable("id") Long id) {
        return this.demoUserService.getById(id);
    }

    /**
     * 新增数据。
     *
     * @param demoUser 数据对象
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/create")
    public Boolean create(@RequestBody @Valid DemoUserDO demoUser) {
        return this.demoUserService.save(demoUser);
    }

    /**
     * 更新数据。
     *
     * @param demoUser 数据对象
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/update")
    public Boolean update(@RequestBody @Valid DemoUserDO demoUser) {
        return this.demoUserService.updateById(demoUser);
    }

    /**
     * 删除给定id的数据。
     *
     * @param ids IDs
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/delete")
    public Boolean delete(@RequestBody Long[] ids) {
        return this.demoUserService.removeByIds(Arrays.asList(ids));
    }

}
