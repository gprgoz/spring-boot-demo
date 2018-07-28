package com.example.demo;

import com.example.demo.util.MpUtil;
import org.junit.Test;


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

}
