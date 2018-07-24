package com.example.demo.util;

import com.example.demo.bean.Item;
import com.example.demo.bean.MpOutMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MessageUtil {
    public static HashMap parseXML(HttpServletRequest request) throws Exception {
        HashMap map = new HashMap();
        // 通过IO获得Document   
        SAXReader reader = new SAXReader();
        Document doc = reader.read(request.getInputStream());
        //得到xml的根节点   
        Element root = doc.getRootElement();
        recursiveParseXML(root, map);
        return map;
    }

    private static void recursiveParseXML(Element root, HashMap map) {
        //得到根节点的子节点列表   
        List<Element> elementList = root.elements();
        //判断有没有子元素列表   
        if (elementList.size() == 0) {
            map.put(root.getName(), root.getTextTrim());
        } else {
            //遍历   
            for (Element e : elementList) {
                recursiveParseXML(e, map);
            }
        }
    }

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点都增加CDATA标记   
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static <T> String messageToXML(T t) {
        xstream.alias("xml", t.getClass());
        //设置使用注解方式来处理别名信息
        xstream.processAnnotations(t.getClass());
        return xstream.toXML(t);
    }

    public static void main(String[] args) {
        MpOutMsg mpOutMsg = new MpOutMsg();
        mpOutMsg.setCreateTime(new Date().getTime());
        mpOutMsg.setMsgType("news");
        mpOutMsg.setArticleCount(1);
        List<Item> articleList = new ArrayList<>();
        Item item = new Item();
        item.setTitle("图文测试");
        item.setDescription("发送消息“图文”两个字，就会收到这条图片消息，点击查看详情。");
        item.setPicUrl("https://local.woxinshangdi.com/static/img/01.jpg");
        item.setUrl("https://local.woxinshangdi.com");
        articleList.add(item);
        mpOutMsg.setArticles(articleList);
        String outMsg = MessageUtil.messageToXML(mpOutMsg);
        System.out.println(outMsg);
    }
}