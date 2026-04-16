package com.fiap.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.library.domain.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    long countByAvailableTrue();

    long countByAvailableFalse();
}
