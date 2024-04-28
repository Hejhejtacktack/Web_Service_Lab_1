package com.example.web_service_lab_1.model;

public class Book {
    private final Title title;
    private final Content content;
    private final PublishYear publishYear;

    public Book(String title, String content, String publishYear) {
        this.title = new Title(title);
        this.content = new Content(content);
        this.publishYear = new PublishYear(publishYear);
    }

    public String getTitle() {
        return title.toString();
    }

    public String getContent() {
        return content.toString();
    }

    public String getPublishYear() {
        return publishYear.toString();
    }
}
