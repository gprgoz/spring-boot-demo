package com.example.demo.bean;

import com.clouderwork.log.Interface.IUserService;
import org.springframework.stereotype.Component;

/**
 * @project: demo
 * @description: 登录用户服务
 * @author: zhaoyujie
 * @create: 2019-10-21 16:27
 */
@Component
public class LoginUserService implements IUserService {
    @Override
    public String getLoginUserName() {
        return "自定义登录用户服务类实现获取登录用户名";
    }
}
