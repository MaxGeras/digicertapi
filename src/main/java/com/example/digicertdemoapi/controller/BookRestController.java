package com.example.digicertdemoapi.controller;

import com.example.digicertdemoapi.dto.BookDTO;
import com.example.digicertdemoapi.entity.Book;
import com.example.digicertdemoapi.service.AuthorBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BookRestController {
  final private AuthorBookService authorBookService;

  public BookRestController(final AuthorBookService authorBookService) {
    this.authorBookService = authorBookService;
  }

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getAllBooks() {
    final List<Book> books = authorBookService.findAllBooks();
    return books.isEmpty()
        ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(books, HttpStatus.OK);
  }
  @PostMapping("/books")
  public Book saveBook(@RequestBody BookDTO book) {
    return authorBookService.save(book);
  }

  @PutMapping("/books/{bookId}")
  public ResponseEntity<Book> updateBook(@PathVariable("bookId") String bookId, @RequestBody BookDTO bookDto) {
      final Book book = authorBookService.update(bookDto, UUID.fromString(bookId));
      return new ResponseEntity<>(book, HttpStatus.OK);
  }

  @DeleteMapping("/books/{bookId}")
  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookId") String bookId) {
    authorBookService.delete(UUID.fromString(bookId));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
