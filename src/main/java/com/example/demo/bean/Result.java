package com.example.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @project: cas-mbc-cleanup
 * @description: controller方法返回结果类
 * @author: zhaoyujie
 * @create: 2019-03-28 17:33
 */
@ApiModel(value = "返回类")
public class Result<T> {
    @ApiModelProperty(value = "返回码，0成功，1失败")
    public String code;
    @ApiModelProperty(value = "描述")
    public String message;
    @ApiModelProperty(value = "数据对象")
    public T content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
