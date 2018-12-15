package com.baayso.springboot.user.business;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.baayso.commons.security.password.PasswordEncoder;
import com.baayso.springboot.user.domain.UserDO;
import com.baayso.springboot.user.message.UserErrors;
import com.baayso.springboot.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gitee.easyopen.ApiContext;

/**
 * 业务逻辑：用户。
 *
 * @author ChenFangjie (2018/12/15 23:51)
 * @since 3.0.0
 */
@Service
public class UserBusiness {

    @Inject
    private UserService userService;

    @Inject
    private PasswordEncoder passwordEncoder;


    /**
     * 用户登录。
     *
     * @param username    用户名
     * @param rawPassword 明文密码
     *
     * @return jwt data.
     *
     * @since 3.0.0
     */
    public String login(String username, String rawPassword) {

        // 使用jwt
        // https://durcframework.gitee.io/easyopen/#/guide?id=%E4%BD%BF%E7%94%A8jwt

        UserDO user = this.userService.getOne( //
                new QueryWrapper<>(UserDO.builder() //
                        .username(username) //
                        .build()));

        if (user == null) {
            throw UserErrors.NOT_FOUND.getException();
        }

        if (user.getIsLock()) {
            throw UserErrors.IS_LOCK.getException();
        }

        if (user.getIsDisable()) {
            throw UserErrors.IS_DISABLE.getException();
        }

        if (user.getIsDelete()) {
            throw UserErrors.IS_DELETE.getException();
        }

        if (!this.passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw UserErrors.PASSWORD_ERROR.getException();
        }

        Map<String, String> data = new HashMap<>(2);
        data.put("id", user.getId().toString());
        data.put("username", user.getUsername());

        return ApiContext.createJwt(data);
    }

    /**
     * 新增一个用户。
     *
     * @param username    用户名
     * @param name        真实姓名
     * @param rawPassword 明文密码
     * @param phone       手机号码
     * @param email       邮箱地址
     *
     * @return 是否新增成功。
     *
     * @since 3.0.0
     */
    public boolean add(String username, //
                       String name, //
                       String rawPassword, //
                       String phone, //
                       String email) {

        UserDO user = UserDO.builder() //
                .username(username) //
                .name(name) //
                .password(this.passwordEncoder.encode(rawPassword)) //
                .phone(phone) //
                .email(email) //
                .build();
        user.initBeforeAdd();

        return this.userService.save(user);
    }

}
