package com.example.digicertdemoapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
  private String isbn;
  private String title;
  private PublisherDTO publisher;
  private List<AuthorDTO> authorList;

}
