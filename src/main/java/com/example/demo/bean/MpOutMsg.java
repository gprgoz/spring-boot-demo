package com.example.demo.bean;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MpOutMsg {
    private String ToUserName; //接收方帐号（收到的OpenID）
    private String FromUserName;//  开发者微信号
    private long CreateTime;//  消息创建时间 （整型）
    private String MsgType;//  text|image
    private String Content;//  文本消息内容

    private String MediaId; //通过素材管理中的接口上传多媒体文件，得到的id。
}
