package com.example.demo.constant;


import com.example.demo.util.ConfigUtil;

/**
 * Created by ZhaoYuJie on 2018/1/10.
 */
public class Constant {
    /**微信分配的小程序ID*/
    public static final String WX_XIAOCHENGXU_APPID = ConfigUtil.getValue("WX_XIAOCHENGXU_APPID");
    /**微信分配的小程序密钥*/
    public static final String WX_XIAOCHENGXU_SECRET = ConfigUtil.getValue("WX_XIAOCHENGXU_SECRET");
    /**微信分配的公众号ID*/
    public static final String WX_MP_APPID = ConfigUtil.getValue("WX_MP_APPID");
    /**微信分配的公众号密钥*/
    public static final String WX_MP_SECRET = ConfigUtil.getValue("WX_MP_SECRET");

    /**
     * 0：成功    <br/>-1：代表未登录 <br/>其他值：代表失败
     */
    public static final String RET_CODE = "retCode";
    public static final String RET_MSG = "retMsg";

    public static final int RET_CODE_OK = 0;
    public static final int RET_CODE_UNLONGIN = -1;


    /**
     * 微信公众号约定的验证服务器合法性的token
     */
    public static final String MP_TOKEN = "123456";
    /**
     * 公众号的access_token
     */
    public static final String MP_ACCESS_TOKEN = "mp_access_token";

}
