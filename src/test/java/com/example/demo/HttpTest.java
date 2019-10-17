package com.example.demo;

import com.example.demo.util.HttpUtil;
import com.example.demo.util.SslUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {
    @Test
    public void test01(){
//        String url = "http://10.200.66.49/poms-admin/xml/GetPlayUrlResource.html";
        String url = "http://117.131.17.18/poms-admin/xml/GetPlayUrlResource.inf";

        String param = "<Product>\n" +
                "<PrdContId>600008906</PrdContId >\n" +
                "<MediaUsageCode></MediaUsageCode> \n" +
                "<BcId>699010</BcId>\n" +
                "</Product>";

        String result = HttpUtil.posturl(url, param);

        System.out.println(result);

    }
    @Test
    public void test02() throws Exception {
        try {
            StringBuffer buffer = new StringBuffer();

            String url = "https://upos-hz-mirrorks3u.acgvideo.com/ugaxcode/i180702li38275t0yyppz34nnryapv2h-192k.m4a?deadline=1562940786&gen=uga&os=ks3u&uparams=deadline,gen,os&upsig=2f58cc15a310986384f9837c48438f76";
            System.out.println("访问地址:" + url);

            //发送get请求
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            //必须设置false，否则会自动redirect到重定向后的地址
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "http://matols.com/");
            conn.connect();

            //判定是否会进行302重定向
            if (conn.getResponseCode() == 302) {
                //如果会重定向，保存302重定向地址，以及Cookies,然后重新发送请求(模拟请求)
                String location = conn.getHeaderField("Location");
                String cookies = conn.getHeaderField("Set-Cookie");

                serverUrl = new URL(location);
                conn = (HttpURLConnection) serverUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Cookie", cookies);
                conn.addRequestProperty("Accept-Charset", "UTF-8;");
                conn.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
                conn.addRequestProperty("Referer", "http://matols.com/");
                conn.connect();
                System.out.println("跳转地址:" + location);
            }

            //将返回的输入流转换成字符串
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;

            System.out.println(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
