package com.example.digicertdemoapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "publisher_table")
public class Publisher {
  @Id
  @Column(name = "publisher_table_id", columnDefinition = "uuid", updatable = false )
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  String name;
  String country;
}

