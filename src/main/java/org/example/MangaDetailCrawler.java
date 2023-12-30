package org.example;

import org.example.action.FolderProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MangaDetailCrawler implements FolderProvider {
    DownloadChapter downloadChapter;
    String titleText;
    String chapterTitleText;
    ImageCrawler imageCrawler;
    public void getUrlChapter(String url){
            try {
                Document document = Jsoup.connect(url).get();
                Elements spanElements = document.select("artical");

                for (Element spanElement : spanElements) {
                    Element anchorElement = spanElement.select("a").first(); // Lấy thẻ <a> đầu tiên bên trong thẻ <span>.

                    if (anchorElement != null) {
                        String link = anchorElement.absUrl("href"); // Lấy đường dẫn từ thẻ <a>.
                        System.out.println("Link in span: " + link);
                        try {
                            Document doc = Jsoup.connect(link).get();
                            Elements imgElements = doc.select("img");
                            for (Element imgElement : imgElements) {
                                String imageUrl = imgElement.absUrl("src");
                                if (!imageUrl.endsWith("0.jpg") && !imageUrl.endsWith("1.jpg")) {
                                    System.out.println("Url img: " + imageUrl);
                                    imageCrawler = new ImageCrawler(this);
                                    imageCrawler.imageDownloader2(imageUrl);
                                }
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
    public void getUrlChapterV2(String url){
            try {
                Document document = Jsoup.connect(url).get();
                Elements articalElements = document.select("article#content"); // Select the article with id 'content'

                for (Element articalElement : articalElements) {
                    Elements imgElements = articalElement.select("img");

                    for (Element imgElement : imgElements) {
                        String imageUrl = imgElement.absUrl("src");
                        System.out.println("Image URL: " + imageUrl);
                        imageCrawler = new ImageCrawler(this);
                        imageCrawler.imageDownloader2(imageUrl);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void mangaCrawler(){
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
                    titleText = entryTitle.text();
                    File mangaEntryFolder = new File(mangaFolder, titleText);
                    mangaEntryFolder.mkdir();


                    if (listWrapElement != null) {
                        Elements linkElements = listWrapElement.select("a");
                        for (Element linkElement : linkElements) {
                            chapterTitleText = linkElement.text();
                            String chapterUrl = linkElement.absUrl("href");

                            File chapterEntryFolder = new File(mangaEntryFolder,chapterTitleText);

                            chapterEntryFolder.mkdirs();
                            System.out.println("Đã tạo thư mục: " + chapterEntryFolder.getAbsolutePath());
                            System.out.println("Đường dẫn chap: " + chapterUrl);
                            getFolderPath(titleText,chapterTitleText);
                            getUrlChapterV2(chapterUrl);
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

    @Override
    public String getFolderPath() {
        return null;
    }

    @Override
    public String getFolderPath(String mangaTitle, String chapterNumber) {
        return "/manga/"+mangaTitle+"/" + chapterNumber + "/";
    }
}
