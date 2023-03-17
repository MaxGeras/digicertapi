package com.example.digicertdemoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "author_table")
public class Author {
  @Id
  @Column(name = "full_name", updatable = false)
  private String fullName;

  @Column(name = "gender", nullable = false)
  private int gender;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author_full_name")
  @JsonIgnore
  private Book book;
}
