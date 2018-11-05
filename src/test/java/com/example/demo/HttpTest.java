package com.example.demo;

import com.example.demo.util.HttpUtil;
import org.junit.Test;

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
}
