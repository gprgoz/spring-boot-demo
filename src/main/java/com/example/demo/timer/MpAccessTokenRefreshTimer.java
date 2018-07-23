package com.example.demo.timer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.Constant;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.JedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ZhaoYuJie on 2018/7/18.
 */
@Component
public class MpAccessTokenRefreshTimer {
    private static Logger _log = LoggerFactory
            .getLogger(MpAccessTokenRefreshTimer.class);

    @Scheduled(fixedRate = 3000000)
    public void refresh() {
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ Constant.WX_MP_APPID+"&secret="+ Constant.WX_MP_SECRET;
            String result = HttpUtil.sendGet(url);
            _log.info("获取公众号access_token接口返回的结果：{}",result);

            JSONObject jo = JSON.parseObject(result);
            if(StringUtils.isNotBlank(jo.getString("access_token"))){
                JedisUtil.set(Constant.MP_ACCESS_TOKEN,jo.getString("access_token"),jo.getIntValue("expires_in"));
            }
        } catch (Exception e) {
            _log.warn("MpAccessTokenRefreshTimer执行失败，{}",e.getMessage());
            e.printStackTrace();
        }
    }

}
