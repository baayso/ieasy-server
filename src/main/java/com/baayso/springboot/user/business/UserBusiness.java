package com.baayso.springboot.user.business;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baayso.commons.security.password.PasswordEncoder;
import com.baayso.springboot.common.easyopen.message.CommonErrors;
import com.baayso.springboot.common.easyopen.utils.JwtUtils;
import com.baayso.springboot.user.domain.UserDO;
import com.baayso.springboot.user.message.UserErrors;
import com.baayso.springboot.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

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

        UserDO user = this.userService.getOne( //
                new QueryWrapper<>(UserDO.builder() //
                        .username(username) //
                        .build()));

        if (user == null) {
            throw UserErrors.NOT_FOUND.getException();
        }

        if (user.getLocked()) {
            throw UserErrors.IS_LOCKED.getException();
        }

        if (user.getDisabled()) {
            throw UserErrors.IS_DISABLED.getException();
        }

        if (user.getDeleted()) {
            throw UserErrors.IS_DELETED.getException();
        }

        if (!this.passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw UserErrors.PASSWORD_ERROR.getException();
        }

        return JwtUtils.createToken(user.getId().toString(), username);
    }

    /**
     * 判断用户名是否已被使用。
     *
     * @param username 用户名
     *
     * @return 用户名是否已被使用
     *
     * @since 3.0.0
     */
    public boolean hasUsername(String username) {

        int count = this.userService.count( //
                new QueryWrapper<>(UserDO.builder() //
                        .username(username) //
                        .build()));

        return count > 0;
    }

    /**
     * 判断手机号码是否已被使用。
     *
     * @param phone 手机号码
     *
     * @return 手机号码是否已被使用
     *
     * @since 3.0.0
     */
    public boolean hasPhone(String phone) {

        int count = this.userService.count( //
                new QueryWrapper<>(UserDO.builder() //
                        .phone(phone) //
                        .build()));

        return count > 0;
    }

    /**
     * 判断邮箱地址是否已被使用。
     *
     * @param email 邮箱地址
     *
     * @return 邮箱地址是否已被使用
     *
     * @since 3.0.0
     */
    public boolean hasEmail(String email) {

        int count = this.userService.count( //
                new QueryWrapper<>(UserDO.builder() //
                        .email(email) //
                        .build()));

        return count > 0;
    }

    /**
     * 新增一个用户。
     *
     * @param username    用户名
     * @param name        姓名
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

        String currentLoginUsername = JwtUtils.getUsername();

        if (this.hasUsername(username)) {
            throw UserErrors.USER_NAME_USED.getException();
        }

        if (StringUtils.isNotBlank(phone) && this.hasPhone(phone)) {
            throw UserErrors.PHONE_USED.getException();
        }

        if (StringUtils.isNotBlank(email) && this.hasEmail(email)) {
            throw UserErrors.EMAIL_USED.getException();
        }

        UserDO user = (UserDO) UserDO.builder() //
                .username(username) //
                .name(name) //
                .password(this.passwordEncoder.encode(rawPassword)) //
                .phone(StringUtils.isNotBlank(phone) ? phone : null) //
                .email(StringUtils.isNotBlank(email) ? email : null) //
                .build() //
                .initBeforeAdd(currentLoginUsername);

        return this.userService.save(user);
    }

    /**
     * 根据 用户ID 删除用户。
     *
     * @param id 用户ID
     *
     * @return 是否删除成功。
     *
     * @since 3.0.0
     */
    public boolean removeById(Long id) {

        String currentLoginUsername = JwtUtils.getUsername();

        UserDO user = this.userService.getById(id);

        if (user == null || user.getDeleted()) {
            throw UserErrors.NOT_FOUND.getException();
        }

        UserDO updateUser = (UserDO) UserDO.builder() //
                .id(id) //
                .deleted(Boolean.TRUE) //
                .build() //
                .initBeforeUpdate(currentLoginUsername);

        return this.userService.updateById(updateUser);
    }

    /**
     * 根据用户ID更新用户信息。
     *
     * @param userId 用户ID
     * @param name   姓名
     * @param phone  手机号码
     * @param email  邮箱地址
     *
     * @return 是否更新成功。
     *
     * @since 3.0.0
     */
    public boolean updateById(Long userId, //
                              String name, //
                              String phone, //
                              String email) {

        String currentLoginUsername = JwtUtils.getUsername();

        if (StringUtils.isBlank(name) && StringUtils.isBlank(phone) && StringUtils.isBlank(email)) {
            throw CommonErrors.NOTHING_UPDATE.getException();
        }

        if (StringUtils.isNotBlank(phone) && this.hasPhone(phone)) {
            throw UserErrors.PHONE_USED.getException();
        }

        if (StringUtils.isNotBlank(email) && this.hasEmail(email)) {
            throw UserErrors.EMAIL_USED.getException();
        }

        UserDO user = this.userService.getById(userId);

        if (user == null || user.getDeleted()) {
            throw UserErrors.NOT_FOUND.getException();
        }

        UserDO updateUser = (UserDO) UserDO.builder() //
                .id(userId) //
                .name(StringUtils.isNotBlank(name) ? name : null) //
                .phone(StringUtils.isNotBlank(phone) ? phone : null) //
                .email(StringUtils.isNotBlank(email) ? email : null) //
                .build() //
                .initBeforeUpdate(currentLoginUsername);

        return this.userService.updateById(updateUser);
    }

    /**
     * 根据用户ID获取用户信息。
     *
     * @param id 用户ID
     *
     * @return 用户信息
     *
     * @since 3.0.0
     */
    public UserDO getById(Long id) {

        // 校验JWT
        JwtUtils.getUsername();

        UserDO user = this.userService.getById(id);

        if (user == null || user.getDeleted()) {
            throw UserErrors.NOT_FOUND.getException();
        }

        return this.userService.getById(id);
    }

}
