package Hent;

import org.example.ImageCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EhentImageCrawler {

    public static void main(String[] args) {
        ImageCrawler2 imageCrawler = new ImageCrawler2();
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://e-hentai.org/g/2783851/33090713e0/");
        urls.add("https://e-hentai.org/g/2822602/ead119c02a/");
        urls.add("https://e-hentai.org/g/2721842/5c6078a4ee/");
        urls.add("https://e-hentai.org/g/2721842/5c6078a4ee/?p=1");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/?p=1");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/?p=2");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/?p=3");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/?p=4");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/?p=5");
        urls.add("https://e-hentai.org/g/2566087/9c4b611535/?p=6");

//        urls.add("https://e-hentai.org/g/2769377/5ef501e768/");
//        urls.add("https://e-hentai.org/g/2755666/f56511e05e/");
//        urls.add("https://e-hentai.org/g/2719053/43ca2c4204/");
//        urls.add("https://e-hentai.org/g/2699485/30a0a46ec0/");
//        urls.add("https://e-hentai.org/g/2687618/2d0278c1d8/");
//        urls.add("https://e-hentai.org/g/2681539/dc6eb00b1a/");
//        urls.add("https://e-hentai.org/g/2662041/ff997b8878/");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");
//        urls.add("");

        for (String url : urls) {
            System.out.println(url);
            try {
                Document document = Jsoup.connect(url).get();
                Elements spanElements = document.select("div.gdtm"); // Lấy tất cả các thẻ <span>.

                for (Element spanElement : spanElements) {
                    Element anchorElement = spanElement.select("a").first();

                    if (anchorElement != null) {
                        String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
                        try {
                            Document doc = Jsoup.connect(link).get();
                            Elements imgElements = doc.select("img#img");
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