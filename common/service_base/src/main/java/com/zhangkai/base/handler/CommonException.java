package com.zhangkai.base.handler;




import com.zhangkai.base.result.ResultCode1;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception  {

    private ResultCode1 resultCode;

    public CommonException(ResultCode1 resultCode) {
        this.resultCode = resultCode;
    }
}
