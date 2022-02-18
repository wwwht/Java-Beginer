package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.io.Serializable;

//这三个注解是lombok的，除了导入依赖，idea还需要安装插件（具体操作问度娘）
@Data   //set/get方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor  //无参构造器
public class CommonResult<T>
{
    // 404 not fount
    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}