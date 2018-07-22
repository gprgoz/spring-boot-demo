package com.example.demo.service;


import com.example.demo.domain.MpUserInfo;

/**
 * Created by ZhaoYuJie on 2018/1/10.
 */
public interface IUserInfoService {

    void addMpUserInfo(MpUserInfo mpUserInfo);

    void delMpUserInfo(String openId);

}
