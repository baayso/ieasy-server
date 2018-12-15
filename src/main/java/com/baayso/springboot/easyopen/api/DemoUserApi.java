package com.baayso.springboot.easyopen.api;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.baayso.springboot.common.CommonApi;
import com.baayso.springboot.common.domain.PageVO;
import com.baayso.springboot.common.domain.ResultVO;
import com.baayso.springboot.common.easyopen.message.CommonErrors;
import com.baayso.springboot.demo.domain.DemoUserDO;
import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.baayso.springboot.demo.service.DemoUserService;
import com.baayso.springboot.easyopen.message.DemoUserErrors;
import com.baayso.springboot.easyopen.param.UserAddParam;
import com.baayso.springboot.easyopen.param.UserDeleteParam;
import com.baayso.springboot.easyopen.param.UserGetParam;
import com.baayso.springboot.easyopen.param.UserUpdateParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.easyopen.doc.DataType;
import com.gitee.easyopen.doc.annotation.ApiDocField;
import com.gitee.easyopen.doc.annotation.ApiDocMethod;

/**
 * EasyOpen业务类，标注了 @ApiService 注解。
 *
 * @author ChenFangjie (2018/11/29 14:05)
 * @since 3.0.0
 */
@ApiService(ignoreSign = true)
public class DemoUserApi extends CommonApi {

    @Inject
    private DemoUserService demoUserService;


    @Api(name = "demo.user.add", version = "1.0")
    @ApiDocMethod(description = "添加一个用户", resultClass = ResultVO.class)
    public ResultVO<Boolean> add(UserAddParam param) {

        if (!super.validator.isChineseAndEnglishAndNumber(param.getName(), 1, 20)) {
            throw DemoUserErrors.USER_NAME_LENGTH.getException(1, 20); // 这里的“1”和“20”会填充到{0}和{1}中
        }

        if (!super.validator.isInt(param.getAge(), 0, 200)) {
            throw DemoUserErrors.USER_AGE_RANGE.getException(0, 200);
        }

        if (!super.validator.isInt(param.getStatus()) || !super.validator.isEnum(OrderStatus.class, Integer.parseInt(param.getStatus()))) {
            throw DemoUserErrors.USER_STATUS_ERROR.getException();
        }

        if (super.validator.required(param.getIntro()) && !super.validator.isChineseAndEnglishAndNumber(param.getIntro(), 1, 200)) {
            throw DemoUserErrors.USER_INTRO_LENGTH.getException(1, 200);
        }

        String name = param.getName();
        Integer age = Integer.valueOf(param.getAge());
        OrderStatus status = OrderStatus.valueOf(Integer.parseInt(param.getStatus()));
        String intro = param.getIntro();

        DemoUserDO user = DemoUserDO.builder() //
                .name(name) //
                .age(age) //
                .status(status) //
                .intro(intro) //
                .build();
        user.initBeforeAdd("ha-ha");

        boolean successful = this.demoUserService.saveUser(user);

        return ResultVO.creator(successful);
    }

    @Api(name = "demo.user.delete", version = "1.0")
    @ApiDocMethod(description = "删除用户", resultClass = ResultVO.class)
    public ResultVO<Boolean> delete(UserDeleteParam param) {

        if (!super.validator.isLong(param.getId())) {
            throw CommonErrors.ID_ERROR.getException();
        }

        Long id = Long.valueOf(param.getId());

        boolean successful = this.demoUserService.deletes(id);

        return ResultVO.creator(successful);
    }

    @Api(name = "demo.user.update", version = "1.0")
    @ApiDocMethod(description = "更新用户", resultClass = ResultVO.class)
    public ResultVO<Boolean> update(UserUpdateParam param) {

        if (!super.validator.isLong(param.getId())) {
            throw CommonErrors.ID_ERROR.getException();
        }

        Long id = Long.valueOf(param.getId());

        boolean successful = this.demoUserService.update(id);

        return ResultVO.creator(successful);
    }

    @Api(name = "demo.user.get", version = "1.0")
    @ApiDocMethod(description = "获取一个用户")
    public DemoUserDO get(UserGetParam param) {

        if (!super.validator.isLong(param.getId())) {
            throw CommonErrors.ID_ERROR.getException();
        }

        Long id = Long.valueOf(param.getId());

        return this.demoUserService.getById(id);
    }

    @Api(name = "demo.user.page.list", version = "1.0")
    @ApiDocMethod(description = "分页获取用户列表",
            results = {
                    @ApiDocField(name = "list", description = "数据", dataType = DataType.ARRAY, elementClass = DemoUserDO.class),
                    @ApiDocField(name = "total", description = "总记录数", dataType = DataType.LONG),
                    @ApiDocField(name = "size", description = "每页记录数", dataType = DataType.LONG),
                    @ApiDocField(name = "current", description = "当前页", dataType = DataType.LONG),
                    @ApiDocField(name = "pages", description = "总页数", dataType = DataType.LONG),
            })
    public PageVO<DemoUserDO> pageList() {

        // int pageSize = StringUtils.isNumeric(limit) ? Integer.parseInt(limit) : 3;
        // int pageNum  = StringUtils.isNumeric(start) ? Integer.parseInt(start) : 1;

        IPage<DemoUserDO> page = this.demoUserService.page(
                new Page<>(1, 100),
                new QueryWrapper<DemoUserDO>()
                        .between("age", "18", "20"));

        return PageVO.creator(page);
    }

    @Api(name = "demo.user.list", version = "1.0")
    @ApiDocMethod(description = "获取用户列表",
            results = {
                    @ApiDocField(name = "list", description = "用户列表", dataType = DataType.ARRAY, elementClass = DemoUserDO.class),
            })
    public ResultVO<DemoUserDO> list() {

        List<DemoUserDO> users = this.demoUserService.list( //
                new QueryWrapper<>(DemoUserDO.builder() //
                        .status(OrderStatus.WAIT_PAY) //
                        .build()) //
                        // .like(StringUtils.isNotBlank(name), "name", name) //
                        .between("age", 18, 20) //
                        .orderByDesc("age") //
                        .orderByAsc("create_time"));

        users.forEach(u -> u.setDatetime(LocalDateTime.now()));

        return ResultVO.creator(users);
    }

}
