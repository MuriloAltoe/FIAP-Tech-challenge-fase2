package com.fiap.library.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookAvailabilityReportResponse {

    private long totalBooks;
    private long availableBooks;
    private long borrowedBooks;
}
