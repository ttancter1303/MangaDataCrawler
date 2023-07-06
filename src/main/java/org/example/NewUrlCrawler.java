package org.example;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class NewUrlCrawler {

    private static Set<String> visitedUrls = new HashSet<>();
    private static int maxUrlLength;

    public static void crawlUrls(String url, int maxDepth, int maxLength) throws IOException {
        visitedUrls.clear();
        maxUrlLength = maxLength;
        crawlUrlsRecursively(url, maxDepth, 0);
    }

    private static void crawlUrlsRecursively(String url, int maxDepth, int currentDepth) throws IOException {
        if (currentDepth > maxDepth || url.length() > maxUrlLength || visitedUrls.contains(url)) {
            return;
        }

        visitedUrls.add(url);
        System.out.println(url);
        ImageCrawler imageCrawler = new ImageCrawler();
        imageCrawler.crawlImages(url);

        // Thực hiện các hoạt động crawler tại đây, ví dụ: lấy các liên kết trong trang web

        // Tiếp tục crawler với các URL liên kết
        // crawlUrlsRecursively(newUrl, maxDepth, currentDepth + 1);
    }

    public static void main(String[] args) {
        String startUrl = "https://e-hentai.org/g/2600969/72ab3db900/";
        int maxDepth = 2;
        int maxUrlLength = 100;

        try {
            crawlUrls(startUrl, maxDepth, maxUrlLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}