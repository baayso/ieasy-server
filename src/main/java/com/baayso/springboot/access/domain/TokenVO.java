package com.baayso.springboot.access.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 展示对象：API调用凭证。
 *
 * @author ChenFangjie (2016/4/13 11:22)
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 7105695537629453991L;

    private String accessToken; // 获取到的凭证
    private Long   expiresIn;   // 凭证有效时间，单位：秒

}
