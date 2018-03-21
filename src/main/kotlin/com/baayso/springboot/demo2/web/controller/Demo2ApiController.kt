package com.baayso.springboot.demo2.web.controller

import com.baayso.springboot.demo.domain.enums.OrderStatus
import com.baayso.springboot.demo2.domain.Demo2UserDO
import com.baayso.springboot.demo2.service.Demo2UserService
import com.baomidou.mybatisplus.mapper.EntityWrapper
import org.apache.commons.lang3.StringUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

/**
 * 测试2。
 *
 * @author ChenFangjie (2018/3/21 23:21)
 * @since 2.0.0
 */
@RestController
@RequestMapping("/demo2/api")
class Demo2ApiController {

    @Inject
    lateinit var demo2UserService: Demo2UserService

    @RequestMapping
    fun now(name: String?): List<Demo2UserDO> {
        val list = this.demo2UserService.selectList(
                EntityWrapper(Demo2UserDO(status = OrderStatus.WAIT_PAY))
                        .like(StringUtils.isNotBlank(name), "name", name)
                        .between("age", 18, 20))

        return list
    }

}