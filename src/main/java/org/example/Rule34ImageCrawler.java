package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class test {

    public static void main(String[] args) {
        ImageCrawler imageCrawler = new ImageCrawler();
        String url = "https://rule34.xxx/index.php?page=post&s=list&tags=dogeza"; // Thay thế bằng URL của trang web bạn muốn lấy đường dẫn ảnh.
        try {
            Document document = Jsoup.connect(url).get();
            Elements spanElements = document.select("span"); // Lấy tất cả các thẻ <span>.

            for (Element spanElement : spanElements) {
                Element anchorElement = spanElement.select("a").first(); // Lấy thẻ <a> đầu tiên bên trong thẻ <span>.

                if (anchorElement != null) {
                    String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
                    System.out.println("Link in span: " + link);
                    try {
                        Document doc = Jsoup.connect(link).get();
                        Elements imgElements = doc.select("img#image");
                        for (Element imgElement : imgElements) {
                            String imageUrl = imgElement.absUrl("src");
                            System.out.println("Image URL: " + imageUrl);
                            String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                            imageName = imageName.replaceAll("[^a-zA-Z0-9.-]", "");
                            imageName = imageName.split("\\.")[0];
                            System.out.println("Image Name: " + imageName);


//                            int questionMarkIndex = imageUrl.indexOf("?");
//                            if (questionMarkIndex != -1) {
//                                String cleanedUrl = imageUrl.substring(0, questionMarkIndex);
//                                System.out.println("Image URL: " + cleanedUrl);
//                                imageCrawler.downloadImage(cleanedUrl);
//                            } else {
//
//                            }
                            imageCrawler.imageDownloader2(imageUrl,imageName);

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
