package com.example.digicertdemoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
  @Id
  @Column(name = "author_table_id", columnDefinition = "uuid", updatable = false )
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "gender", nullable = false)
  private int gender;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_id")
  @JsonIgnore
  private Book book;
}
