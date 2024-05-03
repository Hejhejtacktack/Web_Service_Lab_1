package com.example.web_service_lab_1.controller;

import com.example.web_service_lab_1.exception.BookNotFoundException;
import com.example.web_service_lab_1.model.Book;
import com.example.web_service_lab_1.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(this.bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.bookService.getById(id), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/books/new")
    public ResponseEntity<Book> create(@RequestBody @Valid Book book,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.bookService.create(book), HttpStatus.CREATED);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> replace(@PathVariable Long id,
                                        @RequestBody @Valid Book book,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.replace(id, book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.bookService.delete(id), HttpStatus.OK);
    }
}