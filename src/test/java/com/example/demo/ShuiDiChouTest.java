package com.example.demo;

import com.example.demo.domain.ShuiDiChou;
import com.example.demo.service.IShuiDiChouService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ZhaoYuJie on 2018/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShuiDiChouTest {
    @Autowired
    private IShuiDiChouService shuiDiChouService;

    @Test
    public void addTest() throws IOException {
        File input = new File("E:/shuidichou.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements elementsByClass = doc.select("div.content");

        for(Element element : elementsByClass){
            String imgHead = element.getElementsByClass("img-head").first().attr("src");
            String nickname = element.getElementsByClass("nickname").first().text();
            String money = element.getElementsByClass("money-comment").first().getElementsByTag("em").first().text();
            String comment = element.getElementsByClass("p-comment").first().text();

            ShuiDiChou shuiDiChou = new ShuiDiChou();
            shuiDiChou.setImgHead(imgHead);
            shuiDiChou.setNickname(nickname);
            shuiDiChou.setMoney(Integer.valueOf(money));
            shuiDiChou.setComment(comment);
            shuiDiChou.setCreateTime(new Date());

            shuiDiChouService.insertSelective(shuiDiChou);
        }
    }



}
