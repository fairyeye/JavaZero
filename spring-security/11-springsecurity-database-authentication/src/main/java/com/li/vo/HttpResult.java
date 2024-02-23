package com.li.vo;

import org.springframework.security.core.Authentication;

/**
 * @program: spring-boot-security
 * @description: 返回结果
 * @create: 2024-01-31 20:51:42
 **/
public class HttpResult {
    private Integer code;
    private String msg;
    private Object data;

    public HttpResult() {
    }

    public HttpResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
