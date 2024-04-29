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

@RestController
@RequestMapping("/rs")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.bookService.getById(id), HttpStatus.OK);
        } catch (BookNotFoundException bNFE) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/book/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable("title") String title,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            try {
                return new ResponseEntity<>(this.bookService.getByTitle(title), HttpStatus.OK);
            } catch (BookNotFoundException bNFE) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/book/new")
    public ResponseEntity<Book> create(@RequestBody @Valid Book book,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.create(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
    }

    @PutMapping("/book/{id}")
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

    @DeleteMapping("/book/delete")
    public ResponseEntity<Book> delete(@RequestBody @Valid Book book,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.delete(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }
}