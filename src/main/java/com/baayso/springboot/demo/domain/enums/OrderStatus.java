package com.baayso.springboot.demo.domain.enums;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.enums.IEnum;

/**
 * 订单状态枚举。
 *
 * @author ChenFangjie (2016/4/27 8:59)
 * @since 1.0.0
 */
public enum OrderStatus implements IEnum {

    /* 注意：如果数据库表字段默认值0，则枚举中需要增加一个为0的枚举项 */

    /** 待付款 */
    WAIT_PAY(128, "待付款"),

    /** 付款中 */
    PAYING(256, "付款中"),

    /** 部分付款 */
    PART_PAID(384, "部分付款"),

    /** 待发货 */
    WAIT_SEND(512, "待发货"),

    /** 待收货 */
    WAIT_RECEIVE(640, "待收货"),

    /** 待评价 */
    WAIT_COMMENT(768, "待评价"),

    /** 交易成功 */
    SUCCESS(896, "交易成功"),

    /** 交易关闭 */
    CLOSE(1024, "交易关闭"),

    /** 退款处理中 */
    REFUND_PROCESSING(1152, "退款处理中"),

    /** 退款中 */
    REFUNDING(1280, "退款中"),

    /** 退款成功 */
    REFUND_SUCCESS(1408, "退款成功"),

    /** 退款关闭 */
    REFUND_CLOSE(1536, "退款关闭"),

    /** 退货处理中 */
    RETURN_PROCESSING(1664, "退货处理中"),

    /** 请退货 */
    RETURN_PLEASE(1792, "请退货"),

    /** 退货中 */
    RETURNING(1920, "退货中"),

    /** 退货成功 */
    RETURN_SUCCESS(2048, "退货成功"),

    /** 退货关闭 */
    RETURN_CLOSE(2176, "退货关闭");


    private final int    value;
    private final String desc;

    private OrderStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * Return the enum constant of this type with the specified numeric value.
     *
     * @param value the numeric value of the enum to be returned
     *
     * @return the enum constant with the specified numeric value
     *
     * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
     * @since 1.0.0
     */
    public static OrderStatus valueOf(int value) {
        for (OrderStatus status : values()) {
            if (status.value == value) {
                return status;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }

    /**
     * 是否包含指定名称的枚举项。
     *
     * @param name 指定名称
     *
     * @return 当传入的名称包含在枚举项中时返回true，否则返回false
     *
     * @since 1.0.0
     */
    public static boolean contains(String name) {
        if (StringUtils.isNotBlank(name)) {
            // 所有的枚举值
            OrderStatus[] values = OrderStatus.values();
            // 遍历查找
            for (OrderStatus status : values) {
                if (status.name().equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Return the integer value of this code.
     */
    @Override
    public Serializable getValue() {
        return this.value;
    }

    /**
     * Return the reason phrase of this code.
     */
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
