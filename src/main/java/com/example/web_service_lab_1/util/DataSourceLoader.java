package com.example.web_service_lab_1.util;

import com.example.web_service_lab_1.db.BookRepository;
import com.example.web_service_lab_1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSourceLoader implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Autowired
    public DataSourceLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void load() {
        Book book1 = new Book("Life in a bath house", "Once upon a time...", 1987);
        Book book2 = new Book("Pepsi Max secrets", "Once upon a time...", 1932);
        Book book3 = new Book("You've got nail", "Once upon a time...", 1876);

        this.bookRepository.saveAll(Arrays.asList(book1, book2, book3));
    }

    @Override
    public void run(String... args) throws Exception {
        load();
    }
}
