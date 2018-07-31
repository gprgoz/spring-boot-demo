package com.example.demo.service.impl;

import com.example.demo.dao.MpUserInfoMapper;
import com.example.demo.domain.MpUserInfo;
import com.example.demo.service.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ZhaoYuJie on 2018/1/10.
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Resource
    private MpUserInfoMapper mpUserInfoMapper;


    @Override
    public void addMpUserInfo(MpUserInfo mpUserInfo) {
        mpUserInfoMapper.insertSelective(mpUserInfo);
    }

    @Override
    public void delMpUserInfo(String openId) {
        mpUserInfoMapper.deleteByOpenId(openId);
    }

    @Override
    public MpUserInfo getMpUserInfo(String openid) {
        return mpUserInfoMapper.selectByOpenId(openid);
    }


}
