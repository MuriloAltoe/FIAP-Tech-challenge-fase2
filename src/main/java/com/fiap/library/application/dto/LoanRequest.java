package com.fiap.library.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "bookId is required")
    private Long bookId;

    private LocalDate expectedReturnDate;
}
