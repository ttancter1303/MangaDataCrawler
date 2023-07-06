package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ImageCrawler {
    private static final String USER_AGENT = "Mozilla/5.0 (compatible; YandexAccessibilityBot/3.0; +http://yandex.com/bots)";

    public void crawlImages(String url) throws IOException {
        Set<String> imageUrls = new HashSet<>();
        String folderPath = "E:/DEV/ImageCrawler/image";
        long delay = 1000;
        try {
            Document document = Jsoup.connect(url).userAgent(USER_AGENT).get();
            Elements imgElements = document.select("img");

            for (Element imgElement : imgElements) {
                String imageUrl = imgElement.absUrl("src");
                if (!imageUrl.isEmpty()) {
                    imageUrls.add(imageUrl);
                }
            }

            for (String imageUrl : imageUrls) {
                downloadImage(imageUrl, folderPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.connect(url).get();
        Elements imageElements = document.select("img[src$=.png]");

        for (Element imageElement : imageElements) {
            String imageUrl = imageElement.absUrl("src");
            String imageName = getImageNameFromUrl(imageUrl);
            String jpgFilePath = folderPath + imageName + ".jpg";

            // Tải ảnh PNG từ URL
            BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));

            // Chuyển đổi từ PNG sang JPEG
            BufferedImage jpgImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            jpgImage.createGraphics().drawImage(bufferedImage, 0, 0, java.awt.Color.WHITE, null);

            // Lưu ảnh dưới dạng định dạng JPEG
            ImageIO.write(jpgImage, "jpg", new File(jpgFilePath));

        }
    }
    private static String getImageNameFromUrl(String imageUrl) {
        String[] parts = imageUrl.split("/");
        String imageName = parts[parts.length - 1];
        return imageName.substring(0, imageName.lastIndexOf('.'));
    }
    public void downloadImage(String imageUrl,String folderPath) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(imageUrl);
            request.setHeader("User-Agent", USER_AGENT);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
                    String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                    String filePath = folderPath + File.separator + fileName;
                    try (OutputStream outputStream = new FileOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }

            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}