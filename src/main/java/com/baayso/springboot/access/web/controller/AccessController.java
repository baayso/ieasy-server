package com.baayso.springboot.access.web.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baayso.springboot.access.business.AccessBusiness;
import com.baayso.springboot.access.domain.TokenVO;
import com.baayso.springboot.common.controller.CommonController;
import com.baayso.springboot.common.tool.OperationResult;

/**
 * 接口调用凭证控制器。
 *
 * @author ChenFangjie (2016/4/11 15:34)
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/token")
public class AccessController extends CommonController {

    @Inject
    private AccessBusiness accessBusiness;

    /**
     * 获取access token
     *
     * @since 1.0.0
     */
    @RequestMapping
    public OperationResult<TokenVO> getToken(String accessKey, String accessSecret) {
        TokenVO token = this.accessBusiness.getToken(accessKey, accessSecret);

        return new OperationResult<>(token);
    }

}
