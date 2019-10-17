package com.example.demo;

import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @project: demo
 * @description:
 * @author: zhaoyujie
 * @create: 2019-07-12 21:08
 */
public class RedirectTest {
    public static void main(String[] args) throws IOException {
        String path = "https://upos-hz-mirrorks3u.acgvideo.com/ugaxcode/i180702li38275t0yyppz34nnryapv2h-192k.m4a?deadline=1562940786&gen=uga&os=ks3u&uparams=deadline,gen,os&upsig=2f58cc15a310986384f9837c48438f76";
//        String path = "https://upos-sz-mirrorks32u.acgvideo.com/ugaxcode/i180702li38275t0yyppz34nnryapv2h-192k.m4a?deadline=1562940786&gen=uga&os=ks3u&uparams=deadline,gen,os&upsig=2f58cc15a310986384f9837c48438f76";
        HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
//        System.out.println(conn.getHeaderField("Location"));
        System.out.println(conn.getInstanceFollowRedirects());
        System.out.println(conn.getResponseMessage());
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        System.out.println("=============================");
        if(CollectionUtils.isEmpty(headerFields))
            return;
        for(String key : headerFields.keySet()){
            System.out.println("key="+key+",value="+headerFields.get(key));
        }

//        System.out.println(getFinalURL("https://upos-hz-mirrorks3u.acgvideo.com/ugaxcode/i180702li38275t0yyppz34nnryapv2h-192k.m4a?deadline=1562940786&gen=uga&os=ks3u&uparams=deadline,gen,os&upsig=2f58cc15a310986384f9837c48438f76"));
    }

    public static String getFinalURL(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setInstanceFollowRedirects(false);
        con.connect();
        con.getInputStream();

        if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirectUrl = con.getHeaderField("Location");
            return getFinalURL(redirectUrl);
        }
        return url;
    }

}
