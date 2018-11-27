package com.baayso.springboot.demo2.domain

import com.baayso.springboot.demo.domain.enums.OrderStatus
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

/**
 * 数据对象：测试实体。
 *
 * @author ChenFangjie (2018/3/21 22:56)
 * @since 2.0.0
 */
@TableName("demo_user")
data class Demo2UserDO(val id: Long? = null,
                       val tenantId: Long? = null,
                       val name: String? = null,
                       val age: Int? = null,
                       val status: OrderStatus? = null,
                       val intro: String? = null,
                       @TableField(exist = false) val datetime: LocalDateTime? = null) {

}
