package com.zhangkai.base.handler;





import com.zhangkai.base.result.EorrorResult;
import com.zhangkai.base.result.ResultCode1;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的公共异常处理器
 *      1.声明异常处理器
 *      2.对异常统一处理
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public EorrorResult error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if(e.getClass() == CommonException.class) {
            //类型转型
            CommonException ce = (CommonException) e;
            EorrorResult EorrorResult = new EorrorResult(ce.getResultCode());
            return EorrorResult;
        }else{
            EorrorResult EorrorResult = new EorrorResult(ResultCode1.FAIL);
            return EorrorResult;
        }
    }

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public EorrorResult error(HttpServletRequest request, HttpServletResponse response,CommonException e) {
        return new EorrorResult(ResultCode1.FAIL);
    }
}
