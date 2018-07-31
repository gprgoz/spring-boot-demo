package com.example.demo.bean;

import lombok.Data;

@Data
public class WxUserInfoRsp {
    String openid;
    String nickname;
    String sex;
    String province;
    String city;
    String country;
    String headimgurl;
    String unionid;

}
