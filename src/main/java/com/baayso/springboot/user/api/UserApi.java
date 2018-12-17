package com.baayso.springboot.user.api;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.baayso.springboot.common.CommonApi;
import com.baayso.springboot.common.domain.ResultVO;
import com.baayso.springboot.common.easyopen.message.CommonErrors;
import com.baayso.springboot.user.business.UserBusiness;
import com.baayso.springboot.user.domain.UserDO;
import com.baayso.springboot.user.message.UserErrors;
import com.baayso.springboot.user.param.UserAddParam;
import com.baayso.springboot.user.param.UserLoginParam;
import com.baayso.springboot.user.param.UserUpdateParam;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
import com.gitee.easyopen.doc.DataType;
import com.gitee.easyopen.doc.annotation.ApiDocField;
import com.gitee.easyopen.doc.annotation.ApiDocMethod;

/**
 * 用户接口。
 *
 * @author ChenFangjie (2018/12/14 23:07)
 * @since 3.0.0
 */
@ApiService(ignoreSign = true)
public class UserApi extends CommonApi {

    @Inject
    private UserBusiness userBusiness;


    @Api(name = "user.login", ignoreJWT = true, version = "1.0")
    @ApiDocMethod(description = "用户登录",
            results = {
                    @ApiDocField(name = "ret", description = "jwt data"),
            })
    public ResultVO<String> login(UserLoginParam param) {

        String username = param.getUsername();
        String password = param.getPassword();

        if (!super.validator.isEnglishAndNumberAndUnderscore(username, 1, 20)) {
            throw UserErrors.USER_NAME_ERROR.getException(1, 20);
        }

        if (!super.validator.isAllowCharacter(password, 6, 20)) {
            throw UserErrors.PASSWORD_RAW_ERROR.getException(1, 20);
        }

        String jwt = this.userBusiness.login(username, password);

        return ResultVO.creator(jwt);
    }

    @Api(name = "user.has.username", ignoreJWT = true, version = "1.0")
    @ApiDocMethod(description = "判断用户名是否已被使用",
            params = {
                    @ApiDocField(name = "username", description = "用户名", required = true, example = "admin")
            },
            results = {
                    @ApiDocField(name = "ret", description = "用户名是否已被使用", dataType = DataType.BOOLEAN, example = "false"),
            })
    public ResultVO<Boolean> hasUsername(String username) {

        if (!super.validator.isEnglishAndNumberAndUnderscore(username, 1, 20)) {
            throw UserErrors.USER_NAME_ERROR.getException(1, 20);
        }

        boolean has = this.userBusiness.hasUsername(username);

        return new ResultVO<>(has);
    }

    @Api(name = "user.has.phone", ignoreJWT = true, version = "1.0")
    @ApiDocMethod(description = "判断手机号码是否已被使用",
            params = {
                    @ApiDocField(name = "phone", description = "手机号码", required = true, example = "13712345678")
            },
            results = {
                    @ApiDocField(name = "ret", description = "手机号码是否已被使用", dataType = DataType.BOOLEAN, example = "false"),
            })
    public ResultVO<Boolean> hasPhone(String phone) {

        if (!super.validator.isLong(phone)) {
            throw UserErrors.PHONE_ERROR.getException();
        }

        boolean has = this.userBusiness.hasPhone(phone);

        return new ResultVO<>(has);
    }

    @Api(name = "user.has.email", ignoreJWT = true, version = "1.0")
    @ApiDocMethod(description = "判断邮箱地址是否已被使用",
            params = {
                    @ApiDocField(name = "email", description = "邮箱地址", required = true, example = "i@baayso.com")
            },
            results = {
                    @ApiDocField(name = "ret", description = "邮箱地址是否已被使用", dataType = DataType.BOOLEAN, example = "false"),
            })
    public ResultVO<Boolean> hasEmail(String email) {

        if (!super.validator.isEmail(email)) {
            throw UserErrors.EMAIL_ERROR.getException();
        }

        boolean has = this.userBusiness.hasEmail(email);

        return new ResultVO<>(has);
    }

    @Api(name = "user.add", version = "1.0")
    @ApiDocMethod(description = "新增一个用户", resultClass = ResultVO.class)
    public ResultVO<Boolean> add(UserAddParam param) {

        String username = param.getUsername();
        String name = param.getName();
        String password = param.getPassword();
        String phone = param.getPhone();
        String email = param.getEmail();

        if (!super.validator.isEnglishAndNumberAndUnderscore(username, 1, 20)) {
            throw UserErrors.USER_NAME_ERROR.getException(1, 20);
        }

        if (!super.validator.isChineseAndEnglishAndNumber(name, 1, 20)) {
            throw UserErrors.NAME_ERROR.getException(1, 20);
        }

        if (!super.validator.isAllowCharacter(password, 6, 20)) {
            throw UserErrors.PASSWORD_RAW_ERROR.getException(1, 20);
        }

        if (super.validator.required(phone) && !super.validator.isLong(phone)) {
            throw UserErrors.PHONE_ERROR.getException();
        }

        if (super.validator.required(email) && !super.validator.isEmail(email)) {
            throw UserErrors.EMAIL_ERROR.getException();
        }

        boolean successful = this.userBusiness.add( //
                username, //
                name, //
                password, //
                phone, //
                email);

        return ResultVO.creator(successful);
    }

    @Api(name = "user.delete", version = "1.0")
    @ApiDocMethod(description = "删除用户",
            params = {
                    @ApiDocField(name = "id", description = "用户ID", dataType = DataType.LONG, required = true, example = "123")
            },
            resultClass = ResultVO.class)
    public ResultVO<Boolean> delete(String id) {

        if (!super.validator.isLong(id)) {
            throw CommonErrors.ID_ERROR.getException();
        }

        Long userId = Long.valueOf(id);

        boolean successful = this.userBusiness.removeById(userId);

        return ResultVO.creator(successful);
    }

    @Api(name = "user.update", version = "1.0")
    @ApiDocMethod(description = "更新用户信息", resultClass = ResultVO.class)
    public ResultVO<Boolean> update(UserUpdateParam param) {

        String id = param.getId();
        String name = param.getName();
        String phone = param.getPhone();
        String email = param.getEmail();

        if (!super.validator.isLong(id)) {
            throw CommonErrors.ID_ERROR.getException();
        }

        if (StringUtils.isBlank(name) && StringUtils.isBlank(phone) && StringUtils.isBlank(email)) {
            throw CommonErrors.NOTHING_UPDATE.getException();
        }

        if (super.validator.required(name) && !super.validator.isChineseAndEnglishAndNumber(name, 1, 20)) {
            throw UserErrors.NAME_ERROR.getException(1, 20);
        }

        if (super.validator.required(phone) && !super.validator.isLong(phone)) {
            throw UserErrors.PHONE_ERROR.getException();
        }

        if (super.validator.required(email) && !super.validator.isEmail(email)) {
            throw UserErrors.EMAIL_ERROR.getException();
        }

        Long userId = Long.valueOf(id);

        boolean successful = this.userBusiness.updateById( //
                userId, //
                name, //
                phone, //
                email);

        return ResultVO.creator(successful);
    }

    @Api(name = "user.get", version = "1.0")
    @ApiDocMethod(description = "获取用户详细信息",
            params = {
                    @ApiDocField(name = "id", description = "用户ID", dataType = DataType.LONG, required = true, example = "123")
            })
    public UserDO get(String id) {

        if (!super.validator.isLong(id)) {
            throw CommonErrors.ID_ERROR.getException();
        }

        Long userId = Long.valueOf(id);

        return this.userBusiness.getById(userId);
    }

}
