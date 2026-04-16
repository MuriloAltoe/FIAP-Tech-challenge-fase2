package com.fiap.library.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.library.application.dto.LoanRequest;
import com.fiap.library.application.dto.LoanResponse;
import com.fiap.library.application.mapper.LoanMapper;
import com.fiap.library.domain.entities.Loan;
import com.fiap.library.domain.repository.BookRepository;
import com.fiap.library.domain.repository.LoanRepository;
import com.fiap.library.domain.repository.UserRepository;
import com.fiap.library.infrastructure.exception.BusinessRuleException;
import com.fiap.library.infrastructure.exception.ResourceNotFoundException;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public LoanResponse createLoan(LoanRequest request) {
        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        var book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + request.getBookId()));

        if (!book.isAvailable()) {
            throw new BusinessRuleException("Book is not available for loan");
        }

        LocalDate expectedReturnDate = request.getExpectedReturnDate() != null
                ? request.getExpectedReturnDate()
                : LocalDate.now().plusDays(7);

        if (expectedReturnDate.isBefore(LocalDate.now())) {
            throw new BusinessRuleException("Expected return date cannot be in the past");
        }

        var loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setExpectedReturnDate(expectedReturnDate);
        loan.setReturnDate(null);

        book.setAvailable(false);
        bookRepository.save(book);

        var savedLoan = loanRepository.save(loan);
        return LoanMapper.toResponse(savedLoan);
    }

    @Transactional
    public LoanResponse returnLoan(Long loanId) {
        var loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + loanId));

        if (loan.getReturnDate() != null) {
            throw new BusinessRuleException("Loan has already been returned");
        }

        loan.setReturnDate(LocalDate.now());

        var book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        var updatedLoan = loanRepository.save(loan);
        return LoanMapper.toResponse(updatedLoan);
    }

    public LoanResponse findById(Long id) {
        var loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        return LoanMapper.toResponse(loan);
    }

    public List<LoanResponse> findAll() {
        return loanRepository.findAll().stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    public List<LoanResponse> findActiveLoans() {
        return loanRepository.findByReturnDateIsNull().stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    public List<LoanResponse> findOverdueLoans() {
        return loanRepository.findByExpectedReturnDateBeforeAndReturnDateIsNull(LocalDate.now()).stream()
                .map(LoanMapper::toResponse)
                .toList();
    }
}
