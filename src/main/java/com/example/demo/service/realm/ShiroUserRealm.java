package com.example.demo.service.realm;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.domain.SysUser;
import com.example.demo.domain.SysUserExample;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @project: demo
 * @description: 通过Realm这个领域对象对认证领域和授权领域信息进行检测
 * @author: zhaoyujie
 * @create: 2019-07-02 12:04
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 授权检测
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1、获取用户权限（用户-->角色-->资源）
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        //通过用户Id关联角色表和权限表，查出权限列表
        List<String> permissionList = new ArrayList<>();

        Set<String> set = new HashSet<>(permissionList);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    /**
     * 认证检测
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //2根据用户名查询用户信息
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andAccountEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(CollectionUtils.isEmpty(sysUsers) || sysUsers.size()>1){
            return null;
        }
        SysUser sysUser = sysUsers.get(0);
        ByteSource saltSource = null;
        try {
            saltSource = ByteSource.Util.bytes(sysUser.getSalt().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser.getAccount(),sysUser.getPassword(),saltSource,getName());

        //存储用户信息
        SecurityUtils.getSubject().getSession().setAttribute("user",sysUser);
        return info;
    }
}
