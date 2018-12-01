package com.baayso.springboot.easyopen.api;

import com.baayso.springboot.common.domain.ResultVO;
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
public class HelloWorldApi {

    @Api(name = "hello.world")
    @ApiDocMethod(description = "Hello World",
            results = {
                    @ApiDocField(name = "ret", description = "数据", dataType = DataType.STRING),
            })
    public ResultVO<String> helloWorld() {
        return ResultVO.creator("hello world");
    }

    @Api(name = "hello.world.2")
    @ApiDocMethod(description = "测试无返回数据")
    public void helloWorld2() {
    }

}
