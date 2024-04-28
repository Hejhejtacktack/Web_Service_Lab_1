package com.example.web_service_lab_1.util;

import com.example.web_service_lab_1.db.BookEntity;
import com.example.web_service_lab_1.db.BookRepository;
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
        BookEntity bookEntity1 = new BookEntity("Life in a bath house", "Once upon a time...", 1987);
        BookEntity bookEntity2 = new BookEntity("Pepsi Max secrets", "Once upon a time...", 1932);
        BookEntity bookEntity3 = new BookEntity("You've got nail", "Once upon a time...", 1876);

        this.bookRepository.saveAll(Arrays.asList(bookEntity1, bookEntity2, bookEntity3));
    }

    @Override
    public void run(String... args) throws Exception {
        load();
    }
}
