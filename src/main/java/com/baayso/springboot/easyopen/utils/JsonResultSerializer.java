package com.baayso.springboot.easyopen.utils;

import com.baayso.commons.utils.JsonUtils;
import com.gitee.easyopen.ResultSerializer;

/**
 * 自定义JSON序列化。
 *
 * @author ChenFangjie (2018/11/29 17:11)
 * @since 3.0.0
 */
public class JsonResultSerializer implements ResultSerializer {

    @Override
    public String serialize(Object obj) {
        return JsonUtils.INSTANCE.toJson(obj);
    }

}
