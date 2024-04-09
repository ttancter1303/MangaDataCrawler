package Hent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PinterestCrawler {
    public static void main(String[] args) {
        ImageCrawler2 imageCrawler = new ImageCrawler2();
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://www.pinterest.com/01689674504zx/%C3%BD-t%C6%B0%E1%BB%9Fng-h%C3%ACnh-x%C4%83m/");
        for (String url : urls) {
            System.out.println(url);
            try {
                Document document = Jsoup.connect(url).get();
                Elements spanElements = document.select("div.fma zI7 iyn Hsu"); // Lấy tất cả các thẻ <span>.

                for (Element spanElement : spanElements) {
                    Element anchorElement = spanElement.select("a").first();

                    if (anchorElement != null) {
                        String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
                        try {
                            Document doc = Jsoup.connect(link).get();
                            Elements imgElements = doc.select("img.hCL kVc L4E MIw");
                            for (Element imgElement : imgElements) {
                                String imageUrl = imgElement.absUrl("src");
                                imageCrawler.imageDownloader2(imageUrl);

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
}
