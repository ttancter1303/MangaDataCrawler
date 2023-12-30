package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EhentImageCrawler {

    public static void main(String[] args) {
//        ImageCrawler imageCrawler = new ImageCrawler();
//        ArrayList<String> urls = new ArrayList<>();
//        urls.add("https://e-hentai.org/g/2611733/6ecc9a9f17/");
//        urls.add("https://e-hentai.org/g/2611733/6ecc9a9f17/?p=1");
//        urls.add("https://e-hentai.org/g/2611733/6ecc9a9f17/?p=2");
//        for (String url : urls) {
//            System.out.println(url);
//            try {
//                Document document = Jsoup.connect(url).get();
//                Elements spanElements = document.select("div.gdtm"); // Lấy tất cả các thẻ <span>.
//
//                for (Element spanElement : spanElements) {
//                    Element anchorElement = spanElement.select("a").first();
//
//                    if (anchorElement != null) {
//                        String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
//                        try {
//                            Document doc = Jsoup.connect(link).get();
//                            Elements imgElements = doc.select("img#img");
//                            for (Element imgElement : imgElements) {
//                                String imageUrl = imgElement.absUrl("src");
//                                imageCrawler.imageDownloader2(imageUrl);
//
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}