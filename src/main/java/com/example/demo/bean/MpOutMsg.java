package com.example.demo.bean;

import lombok.Data;

import java.util.List;

@Data
public class MpOutMsg {
    private String ToUserName; //接收方帐号（收到的OpenID）
    private String FromUserName;//  开发者微信号
    private long CreateTime;//  消息创建时间 （整型）
    private String MsgType;//  text|image
    private String Content;//  文本消息内容

    private String MediaId; //通过素材管理中的接口上传多媒体文件，得到的id。

    private int ArticleCount;

    private List<Item> Articles;

}
