package com.example.homediet.common;

public enum ErrorCode implements IErrorCode{
    CART_NOT_FOUNT(201, "没有该记录"),
    REGISTER_FAILED(205, "注册失败"),
    UPDATE_FAILED(206, "超出范围"),
    SYSTEM_FAILED(209, "系统错误"),
    DATA_NOT_FOUND(210, "查询结果为空"),
    INSERT_FAILED(211, "未查询到此用户"),
    ADDCART_FAILED(212, "不存在该商品");





    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

