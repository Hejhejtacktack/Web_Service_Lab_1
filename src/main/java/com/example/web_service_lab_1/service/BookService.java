package com.example.web_service_lab_1.service;

import com.example.web_service_lab_1.exception.BookNotFoundException;
import com.example.web_service_lab_1.db.BookRepository;
import com.example.web_service_lab_1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    public void replace(Long id, Book newBook) {
        try {
            Book book = getById(id);
            book.setTitle(newBook.getTitle());
            book.setContent(newBook.getContent());
            book.setPublishYear(newBook.getPublishYear());
            this.bookRepository.save(book);
        } catch (BookNotFoundException bNFE) {
            this.bookRepository.save(new Book(
                    newBook.getTitle(),
                    newBook.getContent(),
                    newBook.getPublishYear()
            ));
        }

    }

    public Book getById(Long id) throws BookNotFoundException {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book with id: " + id));
    }

    public Book getByTitle(String title) {
        return this.bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("No book with title: " + title));
    }
}
