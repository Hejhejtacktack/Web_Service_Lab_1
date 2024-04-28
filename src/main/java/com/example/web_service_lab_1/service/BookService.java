package com.example.web_service_lab_1.service;

import com.example.web_service_lab_1.exception.BookNotFoundException;
import com.example.web_service_lab_1.db.BookEntity;
import com.example.web_service_lab_1.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void add(BookEntity bookEntity) {
        this.bookRepository.save(bookEntity);
    }

    public void delete(BookEntity bookEntity) {
        this.bookRepository.delete(bookEntity);
    }

    public void update(Long id, BookEntity newBookEntity) {
        BookEntity bookEntity = this.getById(id);
        bookEntity.setTitle(newBookEntity.getTitle());
        bookEntity.setContent(newBookEntity.getContent());
        bookEntity.setPublishYear(newBookEntity.getPublishYear());
        this.bookRepository.save(bookEntity);
    }

    public BookEntity getById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book with id: " + id));
    }

    public BookEntity getByTitle(String title) {
        return this.bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("No book with title: " + title));
    }
}
