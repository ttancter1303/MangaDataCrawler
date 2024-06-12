package Hent;

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

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ImageCrawler2 {
    private static final String USER_AGENT = "Mozilla/5.0 (compatible; YandexAccessibilityBot/3.0; +http://yandex.com/bots)";
    HttpClient httpClient;
    HttpGet request;
    Document document;
    Elements imgElements;
    Elements imageElements;
    HttpResponse response;
    HttpEntity entity;
    String folderPath = "E:/DEV/ImageCrawler/image";
    public void crawlImages(String url) throws IOException {
        Set<String> imageUrls = new HashSet<>();
        try {
            document = Jsoup.connect(url).userAgent(USER_AGENT).get();
            imgElements = document.select("img#img");

            for (Element imgElement : imgElements) {
                String imageUrl = imgElement.absUrl("src");
                if (!imageUrl.isEmpty()) {
                    imageUrls.add(imageUrl);
                }
            }

            for (String imageUrl : imageUrls) {
                downloadImage(imageUrl);
            }
        } catch (IOException e) {

        }
    }
    private static String getImageNameFromUrl(String imageUrl) {
        String[] parts = imageUrl.split("/");
        String imageName = parts[parts.length - 1];
        return imageName.substring(0, imageName.lastIndexOf('.'));
    }
    public void downloadImage(String imageUrl) {
        try {
            httpClient = HttpClientBuilder.create().build();
            request = new HttpGet(imageUrl);
            request.setHeader("User-Agent", USER_AGENT);
            response = httpClient.execute(request);
            entity = response.getEntity();
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
                        System.out.println("Downloading "+fileName);
                    }
                }
            }

            EntityUtils.consume(entity);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
    public void imageDownloader2(String imageUrl){
        folderPath = "image/";
        try {
            String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            imageName = imageName.replaceAll("[^a-zA-Z0-9.-]", "");
            imageName = imageName.split("\\.")[0];
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openStream();
            String destinationPath = folderPath +imageName+".jpg";
            OutputStream outputStream = new FileOutputStream(destinationPath);
            byte[] buffer = new byte[2048];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            // Đóng luồng đầu vào và đầu ra.
            inputStream.close();
            outputStream.close();
            System.out.println("Tải ảnh thành công và lưu tại: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}