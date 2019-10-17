package com.example.demo;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * @project: demo
 * @description:
 * @author: zhaoyujie
 * @create: 2019-08-01 11:39
 */
public class HtmlUnitTest {
    public static Document getHtmlFromUrl(String url, boolean useHtmlUnit) throws Exception {
        if (!useHtmlUnit) {
            return Jsoup.connect(url)
                    //模拟火狐浏览器
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                    .get();
        } else {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setActiveXNative(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(10000);
            HtmlPage htmlPage = null;
            try {
                htmlPage = webClient.getPage(url);
                webClient.waitForBackgroundJavaScript(10000);
                String htmlString = htmlPage.asXml();
                return Jsoup.parse(htmlString);
            } finally {
                webClient.close();
            }
        }
    }

    @Test
    public void test01() throws Exception {
        String url = "https://www.toutiao.com/a6719372843949228558/";
        Document document = getHtmlFromUrl(url, true);
        System.out.println("===========================================================");
        System.out.println(document.outerHtml());
//        Element first = document.getElementsByClass("article-box").first();
//        String title = first.getElementsByClass("article-title").first().text();
//        String content = first.getElementsByClass("article-content").first().text();
//        System.out.println(title);
//        System.out.println("#########################################");
//        System.out.println(content);
    }
}
