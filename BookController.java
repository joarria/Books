package com.example.demo;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

class BookController {
    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/book")
    List<Book> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Single item
    @GetMapping("/book/{id}")
    Book one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    @PutMapping("/book/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(book -> {
                    book.setName(newBook.getName());
                    book.setAuthor(newBook.getAuthor());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/book/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
