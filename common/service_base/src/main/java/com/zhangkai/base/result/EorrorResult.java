package com.zhangkai.base.result;





public class EorrorResult {

    private boolean success;
    private Integer code;
    private String message;
    private Object data;
    public EorrorResult(ResultCode1 reslut){
        this.success=reslut.success();
        this.code=reslut.code();
        this.message=reslut.message();
    }
    public EorrorResult(ResultCode1 reslut, Object data){
        this.success=reslut.success();
        this.code=reslut.code();
        this.message=reslut.message();
        this.data=data;
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
