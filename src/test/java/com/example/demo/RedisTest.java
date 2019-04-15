package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.JedisUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RedisTest {
    @Test
    public void test01(){
//        String value="{\"uuid\":\"6666666666666666666\",\"title\":\"《闯关东中篇》第44集\",\"appId\":\"7\",\"protocolType\":\"vod-mp4-long\",\"url\":\"http://39.105.220.229:8018/1002973297_1010783028_56.mp4\",\"urls\":[\"http://39.105.220.229:8018/1002973297_1010783028_56.mp4\"],\"collectType\":\"2\",\"level\":\"1\",\"mediaType\":\"1\",\"playType\":\"1\",\"createdAt\":\"2018-12-20 15:39:12.041\",\"attr\":null}";
//        String value="{\"uuid\":\"f3d0bc4bb55e4b47b10560fd7ed8937f\",\"title\":\"1123第172期：你是我的宝贝\",\"appId\":\"7\",\"protocolType\":\"vod-mp4-long\",\"url\":\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542286_56.mp4\",\"urls\":[\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542285_55.mp4\",\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542286_56.mp4\",\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542287_63.mp4\",\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542291_92.mp4\",\"http://117.131.17.50/depository/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5002260351_95.mp4\",\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542289_455.mp4\",\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542284_54.mp4\",\"http://117.131.17.50/depository/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5002260352_94.mp4\",\"http://117.131.17.50/depository_sp/asset/zhengshi/5101/088/835/5101088835/media/5101088835_5003542288_91.mp4\"],\"collectType\":\"2\",\"level\":\"1\",\"mediaType\":\"1\",\"playType\":\"1\",\"createdAt\":\"2019-01-14 10:10:37.673\",\"attr\":{\"authorId\":\"5101088835\",\"authorName\":\"5500376982\",\"publicAt\":\"2019-01-14 10:10:37.673\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
//        String value="{\"uuid\":\"test18\",\"title\":\"铁警提醒：看好行李  顺利出行\",\"appId\":\"7\",\"protocolType\":\"vod-mp4\",\"url\":\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/043/884/1003043884/media/1003043884_1011285554_56.mp4\",\"urls\":[\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/043/884/1003043884/media/1003043884_1011285555_92.mp4\",\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/043/884/1003043884/media/1003043884_1011285298_455.mp4\",\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/043/884/1003043884/media/1003043884_1011285554_56.mp4\",\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/043/884/1003043884/media/1003043884_1011285556_91.mp4\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"1\",\"playType\":\"1\",\"createdAt\":\"2019-01-31 16:53:04.25\",\"attr\":{\"authorId\":\"1003038950\",\"authorName\":\"1502383477\",\"publicAt\":\"2019-01-31 16:53:04.25\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
        String value="{\"uuid\":\"test222\",\"title\":\"刘宪华翻唱经典歌曲《爱我别走》，超心动的！\",\"appId\":\"7\",\"protocolType\":\"vod-mp4\",\"url\":\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/046/890/1003046890/media/1003046890_1011304205_56.mp4\",\"urls\":[\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/046/890/1003046890/media/1003046890_1011304140_455.mp4\",\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/046/890/1003046890/media/1003046890_1011304205_56.mp4\",\"http://117.131.18.44/depository_sjq/asset/zhengshi/1003/046/890/1003046890/media/1003046890_1011304206_91.mp4\"],\"collectType\":\"2\",\"level\":\"1\",\"mediaType\":\"1\",\"playType\":\"1\",\"createdAt\":\"2019-02-01 11:50:50.985\",\"attr\":{\"authorId\":\"1003046897\",\"authorName\":\"1502391428\",\"publicAt\":\"2019-02-01 11:50:50.985\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
//        String value="{\"uuid\":\"zyj2222222222222222222222222\",\"title\":\"假借“古玩拍卖”行骗  闵行警方捣毁一诈骗团伙\",\"appId\":\"7\",\"protocolType\":\"vod-mp4\",\"url\":\"http://117.131.17.50/depository_sjq/asset/zhengshi/1002/973/630/1002973630/media/1002973630_1010785604_56.mp4\",\"urls\":[\"http://117.131.17.50/depository_sjq/asset/zhengshi/1002/973/630/1002973630/media/1002973630_1010785509_455.mp4\",\"http://117.131.17.50/depository_sjq/asset/zhengshi/1002/973/630/1002973630/media/1002973630_1010785604_56.mp4\",\"http://117.131.17.50/depository_sjq/asset/zhengshi/1002/973/630/1002973630/media/1002973630_1010785605_91.mp4\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"1\",\"playType\":\"1\",\"createdAt\":\"2019-01-10 18:31:10.656\",\"attr\":{\"authorId\":\"1002973630\",\"authorName\":\"1502318627\",\"publicAt\":\"2019-01-10 18:31:10.656\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
        JedisUtil.rpush("kafka_consumer_migu_filter",3600,value);
    }

    @Test
    public void testpic(){
//        String value="{\"uuid\":\"zyj11111111111111111\",\"title\":\"笑动剧场\",\"appId\":\"7\",\"protocolType\":\"http-pics\",\"url\":\"\",\"urls\":[\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_V34_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_TV_CONTENT.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V34_720.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_V_CONTENT.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ1080V.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V34_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_V23_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V23_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ720V.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V23_720.webp\",\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_H169_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_1080.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_720.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_720.webp\",\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_H32_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ1080H.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H32_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ720H.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H32_720.webp\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"3\",\"playType\":\"5\",\"createdAt\":\"2019-01-10 12:28:58.258\",\"attr\":{\"authorId\":\"5102193043\",\"authorName\":\"5500705300\",\"publicAt\":\"2019-01-10 12:28:58.258\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
//        String value="{\"uuid\":\"b776aab19b45445ebbf8419bb9e1f0ea\",\"title\":\"笑动剧场\",\"appId\":\"7\",\"protocolType\":\"http-pics\",\"url\":\"\",\"urls\":[\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_V34_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_TV_CONTENT.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V34_720.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_V_CONTENT.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ1080V.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V34_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_V23_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V23_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ720V.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_V23_720.webp\",\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_H169_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_1080.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_720.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H169_720.webp\",\"http://221.181.100.51:8088/image/5500/705/300/FILENAME_H32_sc.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ1080H.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H32_1080.webp\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_HSJ720H.jpg\",\"http://221.181.100.51:8088/image/5500/705/300/201901101228_FILENAME_H32_720.webp\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"3\",\"playType\":\"5\",\"createdAt\":\"2019-01-10 12:28:58.258\",\"attr\":{\"authorId\":\"5102193043\",\"authorName\":\"5500705300\",\"publicAt\":\"2019-01-10 12:28:58.258\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
//        String value="{\"uuid\":\"ce7fa352a9ad4ad89cd9577517ca5eb7\",\"title\":\"田朴珺忙上忙下为王石整理衣服 甜蜜上演“捧脸杀”\",\"appId\":\"7\",\"protocolType\":\"http-pics\",\"url\":\"\",\"urls\":[\"http://221.181.100.51:8088/image/1502/318/281/5_H32_sc.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_HSJ1080H.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_H32_1080.webp\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_HSJ720H.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_H32_720.webp\",\"http://221.181.100.51:8088/image/1502/318/281/5_V34_sc.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_TV_CONTENT.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_V34_720.webp\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_V_CONTENT.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_HSJ1080V.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/201901101639_5_V34_1080.webp\",\"http://221.181.100.51:8088/image/1502/318/281/1_TW_001_sc.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/2_TW_001_sc.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/3_TW_001_sc.jpg\",\"http://221.181.100.51:8088/image/1502/318/281/4_TW_001_sc.jpg\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"3\",\"playType\":\"5\",\"createdAt\":\"2019-01-10 17:22:08.527\",\"attr\":{\"authorId\":\"1002973278\",\"authorName\":\"1502318281\",\"publicAt\":\"2019-01-10 17:22:08.527\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
//        String value="{\"uuid\": \"c419dec0dcc1be43181796779c16334f\", \"title\": \"女足半决赛\", \"appId\": \"2\", \"protocolType\": \"aod-m4a\", \"url\": \"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477367_46.3gp\", \"urls\": [], \"level\": \"2\", \"collectType\": \"2\", \"mediaType\": \"2\", \"createdAt\": \"2019-01-15 13:52:48.369215\", \"playType\": \"1\", \"attr\": {\"upCount\": \"\", \"category\": \"\", \"playCount\": \" 19908.3w\", \"shareCount\": \"\", \"publicAt\": \"2018-10-10 00:00:00\", \"authorName\": \"糖醋马蹄酥_有声\", \"authorId\": \"37418520\"}}";
        String value="{\"uuid\":\"fe65ee5df172446b9908cde8a04dc164\",\"title\":\"女足亚洲杯中国1比3输日本\",\"appId\":\"7\",\"protocolType\":\"aod-3gp\",\"url\":\"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477367_46.3gp\",\"urls\":[\"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477367_46.3gp\",\"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477369_47.3gp\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"2\",\"playType\":\"1\",\"createdAt\":\"2019-03-26 15:13:38.421\",\"attr\":{\"authorId\":\"1002022741\",\"authorName\":\"1502373827\",\"publicAt\":\"2019-03-26 15:13:38.423\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
        JedisUtil.rpush("kafka_consumer_migu",3600,value);
    }

    @Test
    public void testAudio(){
//        String value="{\"uuid\": \"c419dec0dcc1be43181796779c16334f\", \"title\": \"女足半决赛\", \"appId\": \"2\", \"protocolType\": \"aod-m4a\", \"url\": \"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477367_46.3gp\", \"urls\": [], \"level\": \"2\", \"collectType\": \"2\", \"mediaType\": \"2\", \"createdAt\": \"2019-01-15 13:52:48.369215\", \"playType\": \"1\", \"attr\": {\"upCount\": \"\", \"category\": \"\", \"playCount\": \" 19908.3w\", \"shareCount\": \"\", \"publicAt\": \"2018-10-10 00:00:00\", \"authorName\": \"糖醋马蹄酥_有声\", \"authorId\": \"37418520\"}}";
        String value="{\"uuid\":\"zyjtest002\",\"title\":\"中国足球何日出头\",\"appId\":\"7\",\"protocolType\":\"aod-3gp\",\"url\":\"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477367_46.3gp\",\"urls\":[\"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477367_46.3gp\",\"http://117.131.18.44/depository_yf/asset/zhengshi/1002/022/741/1002022741/media/1002022741_1003477369_47.3gp\"],\"collectType\":\"2\",\"level\":\"2\",\"mediaType\":\"2\",\"playType\":\"1\",\"createdAt\":\"2019-03-26 15:13:38.421\",\"attr\":{\"authorId\":\"1002022741\",\"authorName\":\"1502373827\",\"publicAt\":\"2019-03-26 15:13:38.423\",\"category\":null,\"playCount\":null,\"shareCount\":null,\"upCount\":null}}";
        JedisUtil.lpush("kafka_consumer_migu",3600,value);
    }


    @Test
    public void test02(){
        String value = JedisUtil.lpop("kafka_consumer_migu");
        System.out.println(value);
    }

    @Test
    public void testdsdf(){
        String value = "E:/test/video/2e88bc7eae5340b9b31cf81fb528c28e/ts/2e88bc7eae5340b9b31cf81fb528c28e.m3u8";
        System.out.println(value.replaceAll("\\\\","/"));
    }

    @Test
    public void test03(){
        String data = "{\"uuid\":\"2e88bc7eae5340b9b31cf81fb528c28e\",\"title\":\"《闯关东中篇》第44集\",\"appId\":\"7\",\"protocolType\":\"vod-mp4-long\",\"url\":\"http://117.131.17.50/depository_sjq/asset/zhengshi/5101/021/531/5101021531/media/5101021531_5004286089_56.mp4\",\"urls\":[\"http://117.131.17.50/depository_sjq/asset/zhengshi/5101/021/531/5101021531/media/5101021531_5004286089_56.mp4\"],\"collectType\":\"2\",\"level\":\"1\",\"mediaType\":\"1\",\"playType\":\"1\",\"createdAt\":\"2018-12-20 15:39:12.041\",\"attr\":null}";
        System.out.println(data);
    }

    @Test
    public void test04(){
        String data = "2281.727133";
        System.out.println(Double.valueOf(data).intValue());
    }

    @Test
    public void test05(){
        String path = "D:\\test01\\video\\2e88bc7eae5340b9b31cf81fb528c28e\\frames\\tmp";
        new File(path).mkdirs();
    }

    @Test
    public void test06() throws IOException {
        FileWriter writer = new FileWriter("E:/test/test.log",true);
        writer.write("hahahahaha\n");
        writer.write("xxxxxxxxxxx\n");
        writer.close();
        writer = new FileWriter("E:/test/test.log",true);
        writer.write("kkkkkkkkkkkkkkkkkkkkk\r\n");
        writer.write("rrrrrrrrrrrrrrrrrrr\r\n");
        writer.close();
    }

    @Test
    public void test07(){
//        List<String> list = JedisUtil.lrange("kafka_consumer_migu_processing", 0, 10);
        List<String> list = JedisUtil.lrange("kafka_consumer_migu", 0, -1);
        System.out.println(list);
        System.out.println(JedisUtil.llen("kafka_consumer_migu"));
    }

    @Test
    public void test08(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token","zhaoyujie");
        jsonObject.put("userUuid","793dded47669e17f");
        jsonObject.put("currentEntityUuid","703a88061f9d4d60952276006bc3981a");
        JedisUtil.set("CWSSOTOKEN:zhaoyujie",jsonObject.toString());
    }

    @Test
    public void test09(){
        String s = JedisUtil.get("CWSSOTOKEN:Js9rb7f35QBa54Wwy23Up3N82nqNKLMhXdaTB5PEgdvHDAU5UTt9kqV6NL6ux2gm:1551051126");
        System.out.println(s);
    }

    @Test
    public void test10(){
        JedisUtil.set("cunkongjian_session_afc84c6c736144c5b5a523470b72c1ed","19#oHee01NfcJRUf2LGTdoTf7NtS_PA#18210542915",3600);
    }


}
