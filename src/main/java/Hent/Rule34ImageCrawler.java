package Hent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Rule34ImageCrawler {

    public static void main(String[] args) {
        ImageCrawler2 imageCrawler = new ImageCrawler2();
        ArrayList<String> urls = new ArrayList<>();
//        urls.add("https://rule34.xxx/index.php?page=post&s=list&tags=m-da_s-tarou+&pid=0");
        for (int i=20;i<27;i++){
            urls.add("https://rule34.xxx/index.php?page=post&s=list&tags=m-da_s-tarou+&pid="+i*42);
        }
        for (String url : urls) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements spanElements = document.select("span.thumb");

                for (Element spanElement : spanElements) {
                    Element anchorElement = spanElement.select("a").first(); // Lấy thẻ <a> đầu tiên bên trong thẻ <span>.

                    if (anchorElement != null) {
                        String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
                        System.out.println("Link in span: " + link);
                        try {
                            Document doc = Jsoup.connect(link).get();
                            Elements imgElements = doc.select("#image");
                            for (Element imgElement : imgElements) {
                                String imageUrl = imgElement.absUrl("src");
                                imageUrl = imageUrl.substring(0, imageUrl.indexOf(".jpg") + 4);
                                System.out.println("Image URL: " + imageUrl);
                                imageCrawler.downloadImage(imageUrl);

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
