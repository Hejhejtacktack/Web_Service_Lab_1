package com.example.web_service_lab_1.service;

import com.example.web_service_lab_1.exception.BookNotFoundException;
import com.example.web_service_lab_1.model.Book;
import com.example.web_service_lab_1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void add(Book book) {
        this.bookRepository.save(book);
    }

    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    public void update(Book book) {

    }

    public Book getById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book with id: " + id));
    }

    public Book getByTitle(String title) {
        return this.bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("No book with title: " + title));
    }
}
