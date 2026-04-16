package com.fiap.library.interfaces.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.library.application.dto.LoanRequest;
import com.fiap.library.application.dto.LoanResponse;
import com.fiap.library.application.service.LoanService;
import com.fiap.library.interfaces.controller.swagger.LoanControllerSwagger;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loans")
public class LoanController implements LoanControllerSwagger {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    @PostMapping
    public LoanResponse createLoan(@RequestBody @Valid LoanRequest request) {
        return loanService.createLoan(request);
    }

    @Override
    @PostMapping("/{id}/return")
    public LoanResponse returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }

    @Override
    @GetMapping("/{id}")
    public LoanResponse findById(@PathVariable Long id) {
        return loanService.findById(id);
    }

    @Override
    @GetMapping
    public List<LoanResponse> findAll() {
        return loanService.findAll();
    }
}
