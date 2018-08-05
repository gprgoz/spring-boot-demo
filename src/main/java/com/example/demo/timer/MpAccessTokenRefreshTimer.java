package com.example.demo.timer;

import com.example.demo.constant.Constant;
import com.example.demo.util.JedisUtil;
import com.example.demo.util.MpUtil;
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
            MpUtil.refreshAccessToken();
            MpUtil.refreshTicket(JedisUtil.get(Constant.MP_ACCESS_TOKEN));
        } catch (Exception e) {
            _log.warn("MpAccessTokenRefreshTimer执行失败，{}",e.getMessage());
            e.printStackTrace();
        }
    }

}
