package com.example.demo.web;

import com.example.demo.constant.Constant;
import com.example.demo.util.JedisUtil;
import com.example.demo.util.MpSignUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信网页授权登陆
 */
@Controller
@RequestMapping("/sdk")
public class JsSdkCtrl {
    private static Logger _log = LoggerFactory
            .getLogger(JsSdkCtrl.class);


    @RequestMapping("/share")
    public String share(HttpServletRequest request){
        String jsapi_ticket = JedisUtil.get(Constant.MP_JSAPI_TICKET);
        String url = request.getRequestURL().toString().replace("http","https");
        String queryString = request.getQueryString();
        if(StringUtils.isNotBlank(queryString)){
            url += "?"+queryString;
        }
        Map<String, String> sign = MpSignUtil.sign(jsapi_ticket, url);
        sign.put("appId",Constant.WX_MP_APPID);
        request.setAttribute("sign",sign);
        return "sdk/share";
    }



}
