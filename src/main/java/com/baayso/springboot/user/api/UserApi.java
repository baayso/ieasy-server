package com.baayso.springboot.user.api;

import java.util.Map;

import javax.inject.Inject;

import com.auth0.jwt.interfaces.Claim;
import com.baayso.springboot.common.CommonApi;
import com.baayso.springboot.common.domain.ResultVO;
import com.baayso.springboot.user.business.UserBusiness;
import com.baayso.springboot.user.message.UserErrors;
import com.baayso.springboot.user.param.UserAddParam;
import com.baayso.springboot.user.param.UserLoginParam;
import com.gitee.easyopen.ApiContext;
import com.gitee.easyopen.annotation.Api;
import com.gitee.easyopen.annotation.ApiService;
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


    @Api(name = "user.login", version = "1.0")
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

    @Api(name = "user.add", version = "1.0")
    @ApiDocMethod(description = "新增一个用户", resultClass = ResultVO.class)
    public ResultVO<Boolean> add(UserAddParam param) {

        Map<String, Claim> jwtData = ApiContext.getJwtData();

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


}
