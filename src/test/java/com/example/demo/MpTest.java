package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.Constant;
import com.example.demo.util.JedisUtil;
import com.example.demo.util.MpUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;


public class MpTest {

    @Test
    public void createMenuTest(){
        MpUtil.createMenu();
    }

    @Test
    public void getMenuTest(){
        MpUtil.getMenu();
    }

    @Test
    public void deleteMenuTest(){
        MpUtil.deleteMenu();
    }

    @Test
    public void getMenuConfigTest(){
        MpUtil.getMenuConfig();
    }

    @Test
    public void getWeiXinIpTest(){
        MpUtil.getWeiXinIp();
    }

    @Test
    public void refreshAccessTokenTest(){
        MpUtil.refreshAccessToken();
    }

    @Test
    public void refreshTicketTest(){
        if(StringUtils.isBlank(JedisUtil.get(Constant.MP_ACCESS_TOKEN))){
            MpUtil.refreshAccessToken();
        }
        String accessToken = JedisUtil.get(Constant.MP_ACCESS_TOKEN);
        MpUtil.refreshTicket(accessToken);
    }

    @Test
    public void arrayListTest(){
        ArrayList<String> list = new ArrayList<String>() {{
            add("aa");
        }};
        System.out.println(JSON.toJSONString(list));
    }
}
