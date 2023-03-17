package com.example.digicertdemoapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Book {
  @Id
  @Column(columnDefinition = "uuid", updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "isbn", nullable = false)
  private String isbn;
  @Column(name = "title", nullable = false)
  private String title;
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SELECT)
  @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_table_id")
  private Publisher publisher;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.MERGE)
  @Fetch(FetchMode.SELECT)
  private Set<Author> authorList;
}
