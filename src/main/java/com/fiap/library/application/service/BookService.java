package com.fiap.library.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.library.application.dto.BookRequest;
import com.fiap.library.application.dto.BookResponse;
import com.fiap.library.application.mapper.BookMapper;
import com.fiap.library.domain.repository.BookRepository;
import com.fiap.library.domain.repository.LoanRepository;
import com.fiap.library.infrastructure.exception.BusinessRuleException;
import com.fiap.library.infrastructure.exception.ResourceNotFoundException;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public BookResponse save(BookRequest bookRequest) {
        var book = BookMapper.toEntity(bookRequest);
        var savedBook = bookRepository.save(book);
        return BookMapper.toResponse(savedBook);
    }

    public BookResponse findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return BookMapper.toResponse(book);
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public BookResponse update(Long id, BookRequest bookRequest) {
        var existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        var updatedBook = BookMapper.toEntity(bookRequest);
        updatedBook.setId(existingBook.getId());
        var savedBook = bookRepository.save(updatedBook);
        return BookMapper.toResponse(savedBook);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }

        if (loanRepository.existsByBookIdAndReturnDateIsNull(id)) {
            throw new BusinessRuleException("Book cannot be deleted while there is an active loan");
        }

        bookRepository.deleteById(id);
    }
}
