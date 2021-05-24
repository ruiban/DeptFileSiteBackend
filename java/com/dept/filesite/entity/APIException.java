package com.dept.filesite.entity;

/**
 * @className: APIException
 * @description: 自定义异常
 * @author: 201998
 * @create: 2020-09-28 15:52:00
 */
public class APIException extends RuntimeException {
    private int code;

    private String msg;

    public APIException(String msg){
        this.code = 1001;
        this.msg = msg;
    }

    public APIException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
