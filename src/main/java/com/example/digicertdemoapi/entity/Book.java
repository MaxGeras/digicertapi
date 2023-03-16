package com.example.digicertdemoapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "book_author_table")
public class Book {
  @Id
  @Column(name = "book_author_table_id", columnDefinition = "uuid", updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "isbn", nullable = false)
  private String isbn;
  @Column(name = "title", nullable = false)
  private String title;
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_table_id")
  private Publisher publisher;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "book")
  private List<Author> authorList;

}
