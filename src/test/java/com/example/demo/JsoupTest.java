package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.SslUtils;
import com.example.demo.util.UnicodeUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: demo
 * @description:
 * @author: zhaoyujie
 * @create: 2019-06-26 10:12
 */
public class JsoupTest {
    @Test
    public void test01() throws Exception {
        SslUtils.ignoreSsl();
//        String articleUrl = "https://www.toutiao.com/a6706283511256449548";
        String articleUrl = "https://www.toutiao.com/a6714455450831553027/";
//        Map<String,String> map = new HashMap<>();
//        map.put("accept","text/javascript, text/html, application/xml, text/xml, */*");
//        map.put("content-type","application/x-www-form-urlencoded");
//        Document document = Jsoup.connect(articleUrl).ignoreContentType(true).get();
        Document document = Jsoup.connect(articleUrl).get();
//        Element first = document.getElementsByClass("article-content").first();
//        System.out.println(first.outerHtml());

        System.out.println(document.outerHtml());
//        Elements body = document.getElementsByTag("body");
//        JSONObject jsonObject = JSON.parseObject(body.text());
//        JSONArray comments = jsonObject.getJSONObject("data").getJSONArray("comments");
//
//        System.out.println(document.text());
//        System.out.println("=====================================");
//        for(Object object : comments){
//            JSONObject comment = (JSONObject) object;
//            System.out.println(comment.getString("text"));
//        }

    }

    @Test
    public void test03(){
//        String s = "\\u89d2\\u8272\\u4fe1\\u606f\\u4e0d\\u5b58\\u5728";
        String s = "\\u6807\\u7b7e\\u654f\\u611f\\u8bcd";
        System.out.println(UnicodeUtil.decodeUnicode(s));
    }

}
