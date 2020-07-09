package com.zhangkai.base.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName R
 * @Description TODO
 * @Detail 项目数据统一响应类型
 *              实现步骤：
 *                  1、创建一个接口或枚举类，定义成功码和错误码
 *                  2、创建一个响应类
 *                      1、属性：成功码、提示信息、是否成功、响应数据
 *                      2、构造器私有
 *                      3、提供获取错误对象和成功对象的公共静态方法
 *                      4、提供公共的设置数据的方法
 *                  总结：
 *                      该类具有链式调用的特征
 * @Author MyPC
 * @Date 2020/6/20
 * @Version 1.0
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;  //对象类型
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<Object, Object> data = new HashMap<>();   //数据采用键值对形式存储
    //构造器私有
    private R(){

    }
    //使用静态方法响应成功对象
    public static R ok(){
        R r=new R();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        r.setSuccess(true);
        return r;
    }
    //使用静态方法响应失败对象
    public static R error(){
        R r=new R();
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        r.setSuccess(false);
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        //this代表当前对象，即R对象，返回this可以实现链式调用
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(Object key, Object value){
        this.data.put(key, value);
        return this;
    }



    public R data(Map<Object, Object> map){
        this.setData(map);
        return this;
    }
}
