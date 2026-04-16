package com.fiap.library.application.mapper;

import com.fiap.library.application.dto.BookRequest;
import com.fiap.library.application.dto.BookResponse;
import com.fiap.library.domain.entities.Book;

public class BookMapper {

    private BookMapper() {
    }

    public static Book toEntity(BookRequest dto) {
        Book book = new Book();
        book.setId(null);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailable(dto.isAvailable());
        return book;
    }

    public static BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.isAvailable());
    }
}
