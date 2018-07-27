package com.example.demo.bean;

import lombok.Data;

@Data
public class MpInMsg {
    private String ToUserName; //开发者微信号
    private String FromUserName; //发送方帐号（一个OpenID）
    private String CreateTime; //消息创建时间 （整型），为了map能自动转换，改成String类型
    private String MsgType; //text|image|event|news
    private String MsgId; //消息id，64位整型，为了map能自动转换，改成String类型

    private String Content;//  文本消息内容
    private String PicUrl;//  图片链接（由系统生成）
    private String MediaId;//  图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String Event;   //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    private String EventKey; //click事件菜单按钮的值

}
