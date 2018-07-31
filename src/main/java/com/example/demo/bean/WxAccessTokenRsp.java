package com.example.demo.bean;

import lombok.Data;

@Data
public class WxAccessTokenRsp {

	String access_token;

	Long  expires_in;

	String refresh_token;

	String openid;

	String scope;

	String unionid;


}