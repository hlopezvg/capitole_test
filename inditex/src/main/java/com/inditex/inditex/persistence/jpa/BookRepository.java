package com.inditex.inditex.persistence.jpa;


import com.inditex.inditex.persistence.entitites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
