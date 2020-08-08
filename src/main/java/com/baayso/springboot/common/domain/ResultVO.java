package com.baayso.springboot.common.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.baayso.commons.tool.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回给客户端的操作结果（转换成json格式后返回给客户端）。
 *
 * @author ChenFangjie (2016/4/11 16:17)
 * @since 1.0.0
 */
@Getter
@Setter
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 4L;

    private boolean success;    // 请求状态
    private Integer code;       // 返回编码
    private String  message;    // 提示消息
    private T       data;       // 返回数据

    public ResultVO() {
    }

    public ResultVO(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVO(boolean success, ResponseStatus status, T data) {
        this.success = success;
        this.code = status.value();
        this.message = status.getReason();
        this.data = data;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("success", this.success);
        map.put("code", this.code);
        map.put("message", this.message);
        map.put("data", this.data);

        return map;
    }

    public static final class ResultVOBuilder<T> {
        private boolean success;    // 请求状态
        private Integer code;       // 返回编码
        private String  message;    // 提示消息
        private T       data;       // 返回数据

        private ResultVOBuilder() {
        }

        public static <T> ResultVOBuilder<T> builder() {
            return new ResultVOBuilder<>();
        }

        public ResultVOBuilder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public ResultVOBuilder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public ResultVOBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ResultVOBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResultVO<T> build() {
            ResultVO<T> resultVO = new ResultVO<>();
            resultVO.setSuccess(success);
            resultVO.setCode(code);
            resultVO.setMessage(message);
            resultVO.setData(data);
            return resultVO;
        }
    }

}
