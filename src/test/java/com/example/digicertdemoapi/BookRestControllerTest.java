package com.example.digicertdemoapi;

import com.example.digicertdemoapi.controller.BookRestController;
import com.example.digicertdemoapi.entity.Author;
import com.example.digicertdemoapi.entity.Book;
import com.example.digicertdemoapi.entity.Publisher;
import com.example.digicertdemoapi.repository.BookAuthorRepository;
import com.example.digicertdemoapi.service.AuthorBookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  AuthorBookService authorBookService;
  @MockBean
  BookAuthorRepository bookAuthorRepository;
  @Test
  public void getAllBooks_unauthorized() throws Exception {
    Book book= new Book();
    book.setTitle("test");
    book.setIsbn("234343432");
    book.setPublisher(new Publisher(UUID.randomUUID() ,"USA", "digicert"));
    book.setAuthorList(List.of(new Author(UUID.randomUUID(), "red", 1, book)));
    List<Book> records = new ArrayList<>(List.of(book));

    Mockito.when(bookAuthorRepository.findAll()).thenReturn(records);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/books"))
        .andExpect(status().isUnauthorized());
  }
}
