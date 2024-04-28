package com.example.web_service_lab_1.controller;

import com.example.web_service_lab_1.db.BookEntity;
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

    // TODO

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable("id") Long id,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.bookService.getById(id), HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/book/{title}")
    public ResponseEntity<BookEntity> getBookByTitle(@PathVariable("title") String title,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(this.bookService.getByTitle(title), HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<BookEntity> create(@RequestBody @Valid BookEntity bookEntity,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.add(bookEntity);
            return new ResponseEntity<>(bookEntity, HttpStatus.ACCEPTED);
        }
    }

    // TODO
    @PutMapping("/book/{id}")
    public ResponseEntity<BookEntity> update(@PathVariable Long id,
                                             @RequestBody @Valid BookEntity bookEntity,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bookEntity, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.update(id, bookEntity);
            return new ResponseEntity<>(bookEntity, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/book")
    public ResponseEntity<BookEntity> deleteBook(@RequestBody @Valid BookEntity bookEntity,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            this.bookService.delete(bookEntity);
            return new ResponseEntity<>(bookEntity, HttpStatus.ACCEPTED);
        }
    }
}
