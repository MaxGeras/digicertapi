package com.example.digicertdemoapi.service;

import com.example.digicertdemoapi.dto.BookDTO;
import com.example.digicertdemoapi.entity.*;
import com.example.digicertdemoapi.enums.GenderAuthor;
import com.example.digicertdemoapi.exception.ResourceNotFoundException;
import com.example.digicertdemoapi.repository.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorBookService {
  private final BookAuthorRepository bookAuthorRepository;

  public AuthorBookService(final BookAuthorRepository bookAuthorRepository) {
    this.bookAuthorRepository = bookAuthorRepository;
  }

  /**
   * Save the request to the db
   * @param bookDTO incoming request
   * @return book entity
   */
  public Book save(final BookDTO bookDTO) {
      // validation needs to be done
      // based on the business needs
      final Publisher publisher = new Publisher();
      publisher.setCountry(bookDTO.getPublisher().getCountry());
      publisher.setName(bookDTO.getPublisher().getName());

      final List<Author> authorList = new ArrayList<>();
      bookDTO.getAuthorList().forEach(authorDTO -> {
          final Author author = new Author();
          author.setGender(authorDTO.getGender()
              .equalsIgnoreCase(GenderAuthor.MALE.toString())
              ? 1
              : 2
          );
          author.setName(authorDTO.getName());
          authorList.add(author);
      });

      final Book book = new Book();
      book.setAuthorList(authorList);
      book.setPublisher(publisher);
      book.setTitle(bookDTO.getTitle());
      book.setIsbn(bookDTO.getIsbn());

      return bookAuthorRepository.save(book);
  }

  /**
   * Update existing records
   * @param bookDTO fields to update
   * @param bookId book id
   * @return updated entity Book
   */
  public Book update(final BookDTO bookDTO, final UUID bookId) {
    final Book book = bookAuthorRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found book with id = " + bookId));

    if (bookDTO.getTitle() != null) {
      book.setTitle(bookDTO.getTitle());
    }

    if(bookDTO.getIsbn() != null) {
      book.setIsbn(bookDTO.getIsbn());
    }

    if(bookDTO.getPublisher() != null) {
      book.getPublisher().setName(bookDTO.getPublisher().getName());
      book.getPublisher().setCountry(bookDTO.getPublisher().getCountry());
    }

    if(bookDTO.getAuthorList() != null && !bookDTO.getAuthorList().isEmpty()) {
      book.getAuthorList().clear();
      bookDTO.getAuthorList().forEach(authorDTO -> {
        final Author author = new Author();
        author.setGender(authorDTO.getGender()
            .equalsIgnoreCase(GenderAuthor.MALE.toString())
            ? 1
            : 2
        );
        author.setName(authorDTO.getName());
        book.getAuthorList().add(author);
      });
    }

    return bookAuthorRepository.save(book);
  }

  /**
   * Delete book from db
   * @param bookId book id
   */
  public void delete(final UUID bookId) {
    final Book book = bookAuthorRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found book with id = " + bookId));
    bookAuthorRepository.delete(book);
  }

  /**
   * Return all books
   * @return list of books
   */
  public List<Book> findAllBooks() {
    return new ArrayList<>(bookAuthorRepository.findAll());
  }

}
