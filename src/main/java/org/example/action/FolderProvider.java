package org.example.action;

public interface FolderProvider {
    String getFolderPath();

    String getFolderPath(String mangaTitle, String chapterNumber);
}
