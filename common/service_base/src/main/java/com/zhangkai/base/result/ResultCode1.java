package com.zhangkai.base.result;

public enum ResultCode1 {
    SUCCESS(true,200,"成功"),
    FAIL(false,300,"失败");





    boolean success;
    Integer code;
    String message;
    ResultCode1(boolean success, Integer code, String message){
        this.success=success;
        this.code=code;
        this.message=message;
    }

    public boolean success() {
        return success;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }
}
