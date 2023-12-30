package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UrlCrawler {
//    private static int maxUrlLength;
//    public static Set<String> crawlUrls(String url) throws IOException {
//        Set<String> urls = new HashSet<>();
//        Document document = Jsoup.connect(url).get();
//        Elements links = document.select("a[href]");
//
//        for (Element link : links) {
//            String href = link.attr("abs:href");
//            urls.add(href);
//        }
//
//        return urls;
//    }
//
//    public static void main(String[] args) {
//        List<String> listUrl = new ArrayList<>();
//        listUrl.add("https://e-hentai.org/g/2743835/f63692ecce/");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        listUrl.add("");
//        for (String urlToCrawl : listUrl) {
//            try {
//                Set<String> allUrls = crawlUrls(urlToCrawl);
//                for (String url : allUrls) {
//                    System.out.println(url);
//                    ImageCrawler imageCrawler = new ImageCrawler();
//                    imageCrawler.crawlImages(url);
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}







