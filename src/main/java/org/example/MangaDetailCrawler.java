package org.example;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MangaDetailCrawler {
    public static void main(String[] args) {
        mangaCrawler();
    }
    public static void mangaCrawler(){
        File mangaFolder = new File("manga");
        if (!mangaFolder.exists()) {
            mangaFolder.mkdir();
        }
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://blogtruyen.vn/33314/zankokuna-kami-ga-shihaisuru");
        urlList.add("https://blogtruyen.vn/33396/o-long-vien-linh-vat-song");
        for (String url : urlList) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements entryTitle = document.select("h1.entry-title");
                Elements content = document.select("div.content");
                Element titleElement = document.selectFirst("span.title");
                Element listWrapElement = document.selectFirst("div.list-chapters");
                System.out.println(content.text());
                if (entryTitle != null||titleElement != null) {
                    String titleText = entryTitle.text();
                    File mangaEntryFolder = new File(mangaFolder, titleText);
                    mangaEntryFolder.mkdir();


                    if (listWrapElement != null) {
                        Elements linkElements = listWrapElement.select("a");
                        for (Element linkElement : linkElements) {
                            String chapterTitleText = linkElement.text();
                            String chapterUrl = linkElement.absUrl("href");

                            File chapterEntryFolder = new File(mangaEntryFolder,chapterTitleText);

                            chapterEntryFolder.mkdirs();

                            System.out.println("Đã tạo thư mục: " + chapterEntryFolder.getAbsolutePath());
                            System.out.println("Đường dẫn chap: " + chapterUrl);
                            accessChapterUrl(chapterUrl);
                        }

                    } else {
                        System.out.println("Không tìm thấy thẻ div có class 'list-wrap'");
                    }
                    System.out.println("Đã tạo thư mục: " + mangaEntryFolder.getAbsolutePath());
                } else {
                    System.out.println("Không tìm thấy thẻ h1 có class 'entry-title'");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static void accessChapterUrl(String chapterUrl) {
        try {
            Document chapterDocument = Jsoup.connect(chapterUrl).get();
            Elements articleContent = chapterDocument.select("artical#content");
            Elements imgElements = articleContent.select("img");
            for (Element imgElement : imgElements) {
                String imgUrl = imgElement.absUrl("src");
                System.out.println("Image URL: " + imgUrl);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
