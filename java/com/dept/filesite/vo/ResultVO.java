package com.dept.filesite.vo;

import com.dept.filesite.entity.ResultCodeEnum;

/**
 * @className: ResultVO
 * @description: 响应结果类
 * @author: 201998
 * @create: 2020-09-28 14:52:00
 */
public class ResultVO<T> {

    private int code;

    private String msg;

    private T data;

    public ResultVO(T data){
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultVO(ResultCodeEnum resultCode, T data){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
