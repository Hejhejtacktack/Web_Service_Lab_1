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


    // TODO ADD /{**}

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return this.bookService.getById(id);
    }

//    @GetMapping("/book/{title}")
//    public Book getBookByTitle(@PathVariable String title) {
//        return this.bookService.getByTitle(title);
//    } TODO

    @PostMapping("/book")
    public ResponseEntity<Book> postBook(@RequestBody @Valid Book book,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.add(book);
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

    @PutMapping("/book")
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.update(book);
            return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
        }
    }
}
