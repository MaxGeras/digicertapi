package com.example.digicertdemoapi.response;

import com.example.digicertdemoapi.dto.BookDTO;
import lombok.Data;

import java.util.List;

@Data
public class BookAuthorResponse {
  private List<BookDTO> data;
}
