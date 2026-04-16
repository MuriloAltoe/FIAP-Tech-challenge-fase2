package com.fiap.library.interfaces.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.library.application.dto.BookAvailabilityReportResponse;
import com.fiap.library.application.dto.LoanResponse;
import com.fiap.library.application.service.ReportService;
import com.fiap.library.interfaces.controller.swagger.ReportControllerSwagger;

@RestController
@RequestMapping("/reports")
public class ReportController implements ReportControllerSwagger {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    @GetMapping("/loans/active")
    public List<LoanResponse> activeLoans() {
        return reportService.activeLoansReport();
    }

    @Override
    @GetMapping("/loans/overdue")
    public List<LoanResponse> overdueLoans() {
        return reportService.overdueLoansReport();
    }

    @Override
    @GetMapping("/books/availability")
    public BookAvailabilityReportResponse booksAvailability() {
        return reportService.bookAvailabilityReport();
    }
}
