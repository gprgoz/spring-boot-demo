package com.example.demo.util;

import com.example.demo.constant.Constant;
import com.example.demo.timer.MpAccessTokenRefreshTimer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公众号相关工具类
 * Created by ZhaoYuJie on 2018/7/18.
 */
public class MpUtil {
    private static Logger _log = LoggerFactory
            .getLogger(MpUtil.class);


    public static String createMenu(){
        String accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        if(StringUtils.isBlank(accessToken)){
            new MpAccessTokenRefreshTimer().refresh();
            accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
        String param = "{\n" +
                "     \"button\":[\n" +
                "     {    \n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          \"key\":\""+Constant.MENU_CLICK_FIRST+"\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {    \n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"搜索\",\n" +
                "               \"url\":\"http://www.soso.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\""+Constant.MENU_CLICK_SECOND+"\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
        String result = HttpUtil.posturl(url, param);
        _log.info("创建菜单返回结果：{}",result);
        return result;
    }

}
