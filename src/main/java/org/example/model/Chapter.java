package org.example.model;

import java.awt.*;
import java.util.LinkedList;

public class Chapter {
    private String name;
    private String date;
    private LinkedList<Image> imageLinkedList;

    public Chapter() {

    }

    public Chapter(String name, String date, LinkedList<Image> imageLinkedList) {
        this.name = name;
        this.date = date;
        this.imageLinkedList = imageLinkedList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedList<Image> getImageLinkedList() {
        return imageLinkedList;
    }

    public void setImageLinkedList(LinkedList<Image> imageLinkedList) {
        this.imageLinkedList = imageLinkedList;
    }
}
