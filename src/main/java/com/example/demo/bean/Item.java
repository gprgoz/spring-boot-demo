package com.example.demo.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * Created by ZhaoYuJie on 2018/7/24.
 */
@Data
@XStreamAlias("item")
public class Item {
    private String Title; //图文消息标题
    private String Description; //图文消息描述
    private String PicUrl; //图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    private String Url; //点击图文消息跳转链接

}
