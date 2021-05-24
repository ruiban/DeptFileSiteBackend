package com.dept.filesite.entity;

/**
 * @className: ResultCodeEnum
 * @description: 响应码
 * @author: 201998
 * @create: 2020-09-28 14:59:00
 */
public enum ResultCodeEnum {
    SUCCESS(1000,"操作成功"),

    FAILED(1001,"操作失败"),

    VALIDATE_FAILED(1002,"参数校验失败"),

    ERROR(5000,"未知错误");

    private int code;

    private String msg;

    ResultCodeEnum(int code, String msg){
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
