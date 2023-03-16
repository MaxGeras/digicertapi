package com.example.digicertdemoapi.repository;

import com.example.digicertdemoapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookAuthorRepository extends JpaRepository<Book, UUID> {

}
