package com.baayso.springboot.common.domain;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 展示对象：普通API（非分页API）返回的数据。
 *
 * @author ChenFangjie (2018/12/1 12:14)
 * @since 3.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 7579387903427921919L;

    private List<T> list;        // 列表数据
    private T       ret;         // 基本类型数据
    private Boolean successful;  // 是否成功


    public ResultVO(T ret) {
        this.ret = ret;
    }

    public static <T> ResultVO<T> creator(List<T> list) {
        return ResultVO.<T>builder().list(list).build();
    }

    public static <T> ResultVO<T> creator(T ret) {
        return ResultVO.<T>builder().ret(ret).build();
    }

    public static <T> ResultVO<T> creator(Boolean successful) {
        return ResultVO.<T>builder().successful(successful).build();
    }

}
