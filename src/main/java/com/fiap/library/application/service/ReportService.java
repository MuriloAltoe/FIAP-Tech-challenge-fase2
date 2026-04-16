package com.fiap.library.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.library.application.dto.BookAvailabilityReportResponse;
import com.fiap.library.application.dto.LoanResponse;
import com.fiap.library.domain.repository.BookRepository;

@Service
public class ReportService {

    private final LoanService loanService;
    private final BookRepository bookRepository;

    public ReportService(LoanService loanService, BookRepository bookRepository) {
        this.loanService = loanService;
        this.bookRepository = bookRepository;
    }

    public List<LoanResponse> activeLoansReport() {
        return loanService.findActiveLoans();
    }

    public List<LoanResponse> overdueLoansReport() {
        return loanService.findOverdueLoans();
    }

    public BookAvailabilityReportResponse bookAvailabilityReport() {
        long availableBooks = bookRepository.countByAvailableTrue();
        long borrowedBooks = bookRepository.countByAvailableFalse();

        return new BookAvailabilityReportResponse(
                availableBooks + borrowedBooks,
                availableBooks,
                borrowedBooks);
    }
}
