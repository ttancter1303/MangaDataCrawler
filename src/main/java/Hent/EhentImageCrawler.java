package Hent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EhentImageCrawler {

    private static final String DIV_SELECTOR = "div#gdt";
    private static final String IMG_SELECTOR = "img#img";
    private static final int TOTAL_PAGES = 7;

    public static void main(String[] args) {
        ImageCrawler2 imageCrawler = new ImageCrawler2();
        List<String> urls = new ArrayList<>();


        urls.add("https://e-hentai.org/g/3224002/f64aa7b311/");
        for (int i=1;i<56;i++){
            urls.add("https://e-hentai.org/g/3224002/f64aa7b311/"+i);
        }



        for (String url : urls) {
            System.out.println("Crawling URL: " + url);
            try {
                Document document = Jsoup.connect(url).get();
                Elements spanElements = document.select(DIV_SELECTOR);
                for (Element spanElement : spanElements) {
                    Elements anchorElements = spanElement.select("a"); // Lấy tất cả thẻ <a> trong phần tử <span>

                    for (Element anchor : anchorElements) {
                        String link = anchor.absUrl("href"); // Lấy URL tuyệt đối của thẻ <a>
                        System.out.println("Found link: " + link);
                        crawlImagePage(link, imageCrawler);
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to connect to URL: " + url);
                e.printStackTrace();
            }
        }
    }

    private static void crawlImagePage(String link, ImageCrawler2 imageCrawler) {
        try {
            Document doc = Jsoup.connect(link).get();
            Elements imgElements = doc.select(IMG_SELECTOR);
            for (Element imgElement : imgElements) {
                String imageUrl = imgElement.absUrl("src");
                System.out.println("Downloading image from URL: " + imageUrl);
            }
        } catch (IOException e) {
            System.err.println("Failed to download image from link: " + link);
            e.printStackTrace();
        }
    }
}
