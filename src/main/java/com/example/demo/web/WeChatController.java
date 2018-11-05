package com.example.demo.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Item;
import com.example.demo.bean.MpInMsg;
import com.example.demo.bean.MpOutMsg;
import com.example.demo.constant.Constant;
import com.example.demo.domain.MpUserInfo;
import com.example.demo.service.IUserInfoService;
import com.example.demo.util.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 接收微信公众号服务器发送过来的消息
 */
@Controller
public class WeChatController {
    private static Logger _log = LoggerFactory
            .getLogger(WeChatController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/weChat",method = RequestMethod.GET)
    @ResponseBody
    public String validate(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr){
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {Constant.MP_TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String join = StringUtils.join(arr);
        String mySignature = EncryptUtil.string2SHA1(join);
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(mySignature.equals(signature)){
            _log.info("接入成功");
            return echostr;
        }
        _log.info("接入失败");
        return null;
    }

    @RequestMapping(value = "/weChat",method = RequestMethod.POST)
    @ResponseBody
    public Object handleMessage(HttpServletRequest request){
        _log.info("request.getRemoteAddr()={}",IpUtility.getRemoteAddr(request));
        MpInMsg mpInMsg = null;
        try {
            HashMap map = MessageUtil.parseXML(request);
            _log.info("接收到公众号消息：{}",map.toString());
            mpInMsg = MapUtil.mapToObject(map,MpInMsg.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        if("event".equals(mpInMsg.getMsgType())){ //事件类型
            //关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
            if(JedisUtil.get(mpInMsg.getFromUserName()+mpInMsg.getCreateTime()) != null){
                return "";
            }
            JedisUtil.set(mpInMsg.getFromUserName()+mpInMsg.getCreateTime(),"1",24*3600);

            if("subscribe".equals(mpInMsg.getEvent())){ //订阅（关注）
                String openId = mpInMsg.getFromUserName();

                String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
                        + JedisUtil.get(Constant.MP_ACCESS_TOKEN)+"&openid="+openId+"&lang=zh_CN";
                String result = HttpUtil.sendGet(url);
                _log.info("获取公众号关注用户的信息：{}",result);

                JSONObject jo = JSON.parseObject(result);

                MpUserInfo mpUserInfo = new MpUserInfo();
                mpUserInfo.setOpenId(openId);
                mpUserInfo.setNickName(jo.getString("nickname"));
                mpUserInfo.setUnionId(jo.getString("unionid"));

                userInfoService.addMpUserInfo(mpUserInfo);

                MpOutMsg mpOutMsg = new MpOutMsg();
                mpOutMsg.setToUserName( mpInMsg.getFromUserName());
                mpOutMsg.setFromUserName(mpInMsg.getToUserName());
                mpOutMsg.setCreateTime(new Date().getTime());
                mpOutMsg.setMsgType("text");
                mpOutMsg.setContent("感谢您关注我们的公众号！");
                return MessageUtil.beanToXML(mpOutMsg);
            }else if("unsubscribe".equals(mpInMsg.getEvent())){ //取消订阅（取消关注）
                String openId = mpInMsg.getFromUserName();
                userInfoService.delMpUserInfo(openId);
                return "";
            }else if("CLICK".equals(mpInMsg.getEvent())){ //点击事件
                MpOutMsg mpOutMsg = new MpOutMsg();
                mpOutMsg.setToUserName( mpInMsg.getFromUserName());
                mpOutMsg.setFromUserName(mpInMsg.getToUserName());
                mpOutMsg.setCreateTime(new Date().getTime());
                mpOutMsg.setMsgType("text");
                if(Constant.MENU_CLICK_FIRST.equals(mpInMsg.getEventKey())){
                    mpOutMsg.setContent("今日没有歌！");
                    return MessageUtil.beanToXML(mpOutMsg);
                }else if(Constant.MENU_CLICK_SECOND.equals(mpInMsg.getEventKey())){
                    mpOutMsg.setContent("感谢点赞！");
                    return MessageUtil.beanToXML(mpOutMsg);
                }
                return "";
            }
        }else if("text".equals(mpInMsg.getMsgType())){ //接收普通文本消息
            if(mpInMsg.getContent().contains("图文")){
                MpOutMsg mpOutMsg = new MpOutMsg();
                mpOutMsg.setToUserName( mpInMsg.getFromUserName());
                mpOutMsg.setFromUserName(mpInMsg.getToUserName());
                mpOutMsg.setCreateTime(new Date().getTime());
                mpOutMsg.setMsgType("news");
                mpOutMsg.setArticleCount(1);
                List<Item> articleList = new ArrayList<>();
                Item item = new Item();
                item.setTitle("图文测试");
                item.setDescription("发送消息“图文”两个字，就会收到这条图片消息，点击查看详情。");
                item.setPicUrl("https://local.woxinshangdi.com/img/01.jpg");
                item.setUrl("https://local.woxinshangdi.com");
                articleList.add(item);
                mpOutMsg.setArticles(articleList);
                return MessageUtil.beanToXML(mpOutMsg);
            }else{
                MpOutMsg mpOutMsg = new MpOutMsg();
                mpOutMsg.setToUserName( mpInMsg.getFromUserName());
                mpOutMsg.setFromUserName(mpInMsg.getToUserName());
                mpOutMsg.setCreateTime(new Date().getTime());
                mpOutMsg.setMsgType("text");
                mpOutMsg.setContent("已收到你的消息："+mpInMsg.getContent());
                return MessageUtil.beanToXML(mpOutMsg);
            }

        }
        return "";
    }

}
