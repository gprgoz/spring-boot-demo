package com.example.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.WxAccessTokenRsp;
import com.example.demo.bean.WxUserInfoRsp;
import com.example.demo.constant.Constant;
import com.example.demo.domain.MpUserInfo;
import com.example.demo.service.IUserInfoService;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.JedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by ZhaoYuJie on 2018/7/2.
 */
@Controller
@RequestMapping(("/login"))
public class LoginCtrl {
    private static Logger _log = LoggerFactory
            .getLogger(LoginCtrl.class);

    @Autowired
    private IUserInfoService userInfoService;



    @RequestMapping("/oauth")
    public String oauth(HttpServletRequest request){
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

        String state = UUID.randomUUID().toString().replace("-", "");

        JedisUtil.set(request.getSession().getId(),state);
        try {
            url = url.replace("APPID", Constant.WX_MP_APPID).replace("REDIRECT_URI", URLEncoder.encode(Constant.CALL_BACK_URL, "utf-8")).replace("SCOPE", "snsapi_userinfo").replace("STATE", state);
            return "redirect:" +url;
        } catch (UnsupportedEncodingException e) {
            _log.warn("oauth error：{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/callback")
    public String callback(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(state) || !state.equals(JedisUtil.get(request.getSession().getId()))) {
//            return "redirect:"+Constant.OAUTH_FAIL_URL;
            return "login/fail";
        }

        String accessUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String accessParam = "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

        accessParam = accessParam.replace("APPID", Constant.WX_MP_APPID).replace("SECRET",Constant.WX_MP_SECRET).replace("CODE",code);

        String accessResult = HttpUtil.posturl(accessUrl, accessParam);

        WxAccessTokenRsp wxAccessTokenRsp = JSONObject.parseObject(accessResult, WxAccessTokenRsp.class);

        String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        userUrl = userUrl.replace("ACCESS_TOKEN", wxAccessTokenRsp.getAccess_token()).replace("OPENID", wxAccessTokenRsp.getOpenid());

        String userResult = HttpUtil.sendGet(userUrl);

        WxUserInfoRsp wxUserInfoRsp = JSONObject.parseObject(userResult, WxUserInfoRsp.class);

        MpUserInfo mpUserInfo = userInfoService.getMpUserInfo(wxUserInfoRsp.getOpenid());

        if(mpUserInfo == null){ //没有则创建
            mpUserInfo = new MpUserInfo();
            mpUserInfo.setOpenId(wxUserInfoRsp.getOpenid());
            mpUserInfo.setNickName(wxUserInfoRsp.getNickname());
            mpUserInfo.setUnionId(wxUserInfoRsp.getUnionid());
            userInfoService.addMpUserInfo(mpUserInfo);
        }

        request.setAttribute("mpUserInfo",mpUserInfo);

//        return "redirect:" +Constant.OAUTH_SUCCESS_URL;
        return "login/success";
    }



}
