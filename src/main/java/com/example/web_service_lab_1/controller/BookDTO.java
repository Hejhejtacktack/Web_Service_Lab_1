package com.example.web_service_lab_1.controller;

import com.example.web_service_lab_1.model.Book;

public class BookDTO {
    public String title;
    public String content;
    public String publishYear;

    public BookDTO(String title, String content, String publishYear) {
        this.title = title;
        this.content = content;
        this.publishYear = publishYear;
    }

    public static BookDTO from(Book book) {
        return new BookDTO(book.getTitle(),
                book.getContent(),
                book.getPublishYear());
    }
}
