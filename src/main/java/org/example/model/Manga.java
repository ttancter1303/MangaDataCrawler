package org.example.model;

import java.util.List;

public class Manga {
    public Manga(String name, String content, String author, List<Tag> listTag, List<Chapter> listChapter) {
        this.name = name;
        this.content = content;
        this.author = author;
        this.listTag = listTag;
        this.listChapter = listChapter;
    }

    public Manga() {
    }

    private String name;
    private String content;
    private String author;
    private List<Tag> listTag;
    private List<Chapter> listChapter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Tag> getListTag() {
        return listTag;
    }

    public void setListTag(List<Tag> listTag) {
        this.listTag = listTag;
    }

    public List<Chapter> getListChapter() {
        return listChapter;
    }

    public void setListChapter(List<Chapter> listChapter) {
        this.listChapter = listChapter;
    }
}
