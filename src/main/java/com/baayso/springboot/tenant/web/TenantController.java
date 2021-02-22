package com.baayso.springboot.tenant.web;

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
import com.baayso.springboot.tenant.domain.TenantDO;
import com.baayso.springboot.tenant.service.TenantService;

/**
 * 控制器：租户。
 *
 * @author ChenFangjie (2020/08/12 18:29:51)
 * @since 4.0.0
 */
@RestController
@RequestMapping("/api/tenant")
public class TenantController extends CommonController {

    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    /**
     * 分页查询数据。
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     *
     * @return 返回给客户端的数据
     */
    @RequestMapping("/list")
    public PageVO<TenantDO> list(@RequestParam(defaultValue = DEFAULT_PAGE_NUM) Integer pageNum,
                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        return this.tenantService.page(pageNum, pageSize);
    }

    /**
     * 根据id获取数据。
     *
     * @param id 主键
     *
     * @return 返回给客户端的数据
     */
    @RequestMapping("/get/{id}")
    public TenantDO get(@PathVariable("id") Long id) {
        return this.tenantService.getById(id);
    }

    /**
     * 新增数据。
     *
     * @param tenant 数据对象
     *
     * @return 返回给客户端的数据
     */
    @RequestMapping("/create")
    public Boolean create(@RequestBody @Valid TenantDO tenant) {
        return this.tenantService.save(tenant);
    }

    /**
     * 更新数据。
     *
     * @param tenant 数据对象
     *
     * @return 返回给客户端的数据
     */
    @RequestMapping("/update")
    public Boolean update(@RequestBody @Valid TenantDO tenant) {
        return this.tenantService.updateById(tenant);
    }

    /**
     * 删除给定id的数据。
     *
     * @param ids 一个或多个主键
     *
     * @return 返回给客户端的数据
     */
    @RequestMapping("/delete")
    public Boolean delete(@RequestBody Long[] ids) {
        return this.tenantService.removeByIds(Arrays.asList(ids));
    }

}
