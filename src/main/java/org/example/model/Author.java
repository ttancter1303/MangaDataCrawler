package org.example.model;

public class Author {
    private String name;
    private String description;
    private String information;

    public Author() {
    }

    public Author(String name, String description, String information) {
        this.name = name;
        this.description = description;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
