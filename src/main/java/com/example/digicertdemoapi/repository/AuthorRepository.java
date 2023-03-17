package com.example.digicertdemoapi.repository;

import com.example.digicertdemoapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {

}
