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
    /**微信网页授权回调地址*/
    public static final String CALL_BACK_URL = ConfigUtil.getValue("CALL_BACK_URL");
    /**微信网页授权失败重定向到错误页面*/
    public static final String OAUTH_FAIL_URL = ConfigUtil.getValue("OAUTH_FAIL_URL");
    /**微信网页授权成功重定向到成功页面*/
    public static final String OAUTH_SUCCESS_URL = ConfigUtil.getValue("OAUTH_SUCCESS_URL");

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
    /**
     * 公众号的jsapi_ticket
     */
    public static final String MP_JSAPI_TICKET = "mp_jsapi_ticket";

    /**
     * 公众号菜单点击按钮值
     */
    public static final String MENU_CLICK_FIRST = "V1001_TODAY_MUSIC";
    public static final String MENU_CLICK_SECOND = "V1001_GOOD";

}
