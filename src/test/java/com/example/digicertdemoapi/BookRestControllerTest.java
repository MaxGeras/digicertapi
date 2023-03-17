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

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  AuthorBookService authorBookService;
  @MockBean
  BookAuthorRepository bookAuthorRepository;

  String token = "eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY4NDI3MTA5OCwiaWF0IjoxNjc5MDAwNjk4fQ.MKxhTFzKQknOJnkeI_Wv23Del-phmKoB-d7rLRGq1PEV7XJs33fKW2AiM5cgJJlf5-R1WCXSTBoo0qpuO7YNGg";
  @Test
  public void getAllBooks_unauthorized() throws Exception {
    Book book= new Book();
    book.setTitle("test");
    book.setIsbn("234343432");
    book.setPublisher(new Publisher(UUID.randomUUID() ,"USA", "digicert"));
    book.setAuthorList(new HashSet<>(List.of(new Author("Test Test", 1, book))));
    List<Book> records = new ArrayList<>(List.of(book));

    Mockito.when(bookAuthorRepository.findAll()).thenReturn(records);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/books"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void getAllBooks_success() throws Exception {
    Book book= new Book();
    book.setTitle("test");
    book.setIsbn("234343432");
    book.setPublisher(new Publisher(UUID.randomUUID() ,"USA", "digicert"));
    book.setAuthorList(new HashSet<>(List.of(new Author("Test Test", 1, book))));
    List<Book> records = new ArrayList<>(List.of(book));

    Mockito.when(bookAuthorRepository.findAll()).thenReturn(records);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/books")
            .header("Authorization", token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }
}
