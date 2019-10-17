package com.example.demo.service.impl;

import com.example.demo.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @project: demo
 * @description:
 * @author: zhaoyujie
 * @create: 2019-07-04 16:14
 */
@Transactional
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Override
    public void login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
    }
}
