package Hent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;

public class ImageCrawler2 {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";

    private final String folderPath = "E:/DEV/ImageCrawler/image";

    private static String getImageNameFromUrl(String imageUrl) {
        String[] parts = imageUrl.split("/");
        String imageName = parts[parts.length - 1];

        int dotIndex = imageName.lastIndexOf('.');
        return (dotIndex != -1) ? imageName.substring(0, dotIndex) : imageName;
    }

    public void downloadImage(String imageUrl) {
        try {
            String fileName = Paths.get(new URL(imageUrl).getPath()).getFileName().toString();
            String destinationFile = Paths.get(folderPath, fileName).toString();

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);

            try (InputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream out = new FileOutputStream(destinationFile)) {

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                System.out.println("Image downloaded to " + destinationFile);
            }
        } catch (IOException e) {
            System.err.println("Error downloading image: " + e.getMessage());
        }
    }

    public void imageDownloader2(String imageUrl) {
        try {
            String imageName = getImageNameFromUrl(imageUrl).replaceAll("[^a-zA-Z0-9.-]", "");
            String destinationPath = Paths.get(folderPath, imageName + ".jpg").toString();

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);

            try (InputStream inputStream = connection.getInputStream();
                 OutputStream outputStream = new FileOutputStream(destinationPath)) {

                byte[] buffer = new byte[8192];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }

                System.out.println("Tải ảnh thành công và lưu tại: " + destinationPath);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi tải ảnh: " + e.getMessage());
        }
    }
}
