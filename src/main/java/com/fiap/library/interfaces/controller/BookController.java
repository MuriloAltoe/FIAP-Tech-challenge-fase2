package com.fiap.library.interfaces.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.library.application.dto.BookRequest;
import com.fiap.library.application.dto.BookResponse;
import com.fiap.library.application.service.BookService;
import com.fiap.library.interfaces.controller.swagger.BookControllerSwagger;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController implements BookControllerSwagger {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @PostMapping
    public BookResponse criar(@RequestBody @Valid BookRequest request) {
        return bookService.save(request);
    }

    @Override
    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @Override
    @GetMapping
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @Override
    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody @Valid BookRequest request) {
        return bookService.update(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
