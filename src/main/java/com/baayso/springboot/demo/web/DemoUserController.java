package com.baayso.springboot.demo.web;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.commons.tool.CommonResponseStatus;
import com.baayso.springboot.common.controller.CommonController;
import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.common.domain.ResultVO;
import com.baayso.springboot.common.exception.ApiViolationException;
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
    public ResultVO<PageVO<DemoUserDO>> list(String pageNum, String pageSize) {

        pageNum = StringUtils.isEmpty(pageNum) ? "1" : pageNum;
        pageSize = StringUtils.isEmpty(pageNum) ? "10" : pageSize;

        if (!this.validator.isInt(pageNum)) {
            throw new ApiViolationException(CommonResponseStatus.ILLEGAL_DATA);
        }

        if (!this.validator.isInt(pageSize)) {
            throw new ApiViolationException(CommonResponseStatus.ILLEGAL_DATA);
        }

        return ResultVO.ok(this.demoUserService.page(Integer.parseInt(pageNum), Integer.parseInt(pageSize)));
    }

    /**
     * 根据ID获取数据。
     *
     * @param id ID
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/get/{id}")
    public ResultVO<DemoUserDO> get(@PathVariable("id") Long id) {
        DemoUserDO demoUser = this.demoUserService.getById(id);

        return ResultVO.ok(demoUser);
    }

    /**
     * 新增数据。
     *
     * @param demoUser 数据对象
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/create")
    public ResultVO<Boolean> create(@RequestBody DemoUserDO demoUser) {
        boolean result = this.demoUserService.save(demoUser);

        return ResultVO.ok(result);
    }

    /**
     * 更新数据。
     *
     * @param demoUser 数据对象
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/update")
    public ResultVO<Boolean> update(@RequestBody DemoUserDO demoUser) {
        boolean result = this.demoUserService.updateById(demoUser);

        return ResultVO.ok(result);
    }

    /**
     * 删除给定id的数据。
     *
     * @param ids IDs
     *
     * @return 返回给客户端的操作结果
     */
    @RequestMapping("/delete")
    public ResultVO<Boolean> delete(@RequestBody Long[] ids) {
        boolean result = this.demoUserService.removeByIds(Arrays.asList(ids));

        return ResultVO.ok(result);
    }

}
