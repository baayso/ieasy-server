package com.baayso.springboot.common;

import java.text.SimpleDateFormat;

import org.springside.modules.utils.mapper.JsonMapper;
import org.springside.modules.utils.time.DateFormatUtil;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Json工具类。
 *
 * @author ChenFangjie (2015/12/9 20:22)
 * @since 1.0.0
 */
public class JsonUtils extends JsonMapper {

    public static final JsonUtils INSTANCE;

    static {
        INSTANCE = new JsonUtils();
        JsonUtils.INSTANCE.getMapper().setDateFormat(new SimpleDateFormat(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND));
    }

    public JsonUtils() {
        super(null);
    }

    public JsonUtils(JsonInclude.Include include) {
        super(include);
    }

}
