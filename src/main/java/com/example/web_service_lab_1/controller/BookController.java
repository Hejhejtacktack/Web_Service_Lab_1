package com.example.web_service_lab_1.controller;

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

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.bookService.getById(id), HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/book/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable("title") String title,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.bookService.getByTitle(title), HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<Book> create(@RequestBody @Valid Book book,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.add(book);
            return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
        }
    }

    // TODO
    @PutMapping("/book/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,
                                       @RequestBody @Valid Book book,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.update(id, book);
            return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/book")
    public ResponseEntity<Book> deleteBook(@RequestBody @Valid Book book,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.delete(book);
            return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
        }
    }
}
