package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Rule34ImageCrawler {

    private static ImageCrawler imageCrawler;

    public static void main(String[] args) {
        ImageCrawler imageCrawler = new ImageCrawler();
        ArrayList<String> urls = new ArrayList<>();
//        for (int i=0;i<5;i++){
//            urls.add("https://rule34.xxx/index.php?page=post&s=list&tags=to_love-ru++cum+="+i*42);
//        }
        urls.add("https://blogtruyen.vn/c844185/trung-hoa-nhat-phien-chuong-29-muc-tieu-xa-voi");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");

    }
    public static void getUrlV1(ArrayList<String> urls){
        for (String url : urls) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements spanElements = document.select("artical");

                for (Element spanElement : spanElements) {
                    Element anchorElement = spanElement.select("a").first(); // Lấy thẻ <a> đầu tiên bên trong thẻ <span>.

                    if (anchorElement != null) {
                        String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
                        System.out.println("Link in span: " + link);
                        try {
                            Document doc = Jsoup.connect(link).get();
                            Elements imgElements = doc.select("img");
                            for (Element imgElement : imgElements) {
                                String imageUrl = imgElement.absUrl("src");
                                if (!imageUrl.endsWith("0.jpg") && !imageUrl.endsWith("1.jpg")) {
                                    System.out.println("Url img: " + imageUrl);
                                    imageCrawler.imageDownloader2(imageUrl);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void getUrlV2(ArrayList<String> urls){
        for (String url : urls) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements articalElements = document.select("article#content"); // Select the article with id 'content'

                for (Element articalElement : articalElements) {
                    Elements imgElements = articalElement.select("img");

                    for (Element imgElement : imgElements) {
                        String imageUrl = imgElement.absUrl("src");
                        System.out.println("Image URL: " + imageUrl);
                        imageCrawler.imageDownloader2(imageUrl);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
