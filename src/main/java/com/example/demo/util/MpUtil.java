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


    /**
     * 创建自定义菜单
     * @return
     */
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
                "           {    \n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"网页授权\",\n" +
                "               \"url\":\"https://local.woxinshangdi.com/pages/oauth.html\"\n" +
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

    /**
     * 查询自定义菜单
     * @return
     */
    public static String getMenu(){
        String accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        if(StringUtils.isBlank(accessToken)){
            new MpAccessTokenRefreshTimer().refresh();
            accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accessToken;

        String result = HttpUtil.sendGet(url);
        _log.info("查询菜单返回结果：{}",result);
        return result;
    }

    /**
     * 删除自定义菜单
     * @return
     */
    public static String deleteMenu(){
        String accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        if(StringUtils.isBlank(accessToken)){
            new MpAccessTokenRefreshTimer().refresh();
            accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;

        String result = HttpUtil.sendGet(url);
        _log.info("删除菜单返回结果：{}",result);
        return result;
    }

    /**
     * 获取自定义菜单配置接口
     * @return
     */
    public static String getMenuConfig(){
        String accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        if(StringUtils.isBlank(accessToken)){
            new MpAccessTokenRefreshTimer().refresh();
            accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token="+accessToken;

        String result = HttpUtil.sendGet(url);
        _log.info("获取自定义菜单配置接口返回结果：{}",result);
        return result;
    }

    /**
     * 获取微信服务器IP地址
     * @return
     */
    public static String getWeiXinIp(){
        String accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        if(StringUtils.isBlank(accessToken)){
            new MpAccessTokenRefreshTimer().refresh();
            accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+accessToken;

        String result = HttpUtil.sendGet(url);
        _log.info("获取微信服务器IP地址返回结果：{}",result);
        return result;
    }

}
