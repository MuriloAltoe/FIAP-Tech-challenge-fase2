package com.fiap.library.application.mapper;

import com.fiap.library.application.dto.LoanResponse;
import com.fiap.library.domain.entities.Loan;

public class LoanMapper {

    private LoanMapper() {
    }

    public static LoanResponse toResponse(Loan loan) {
        String status = loan.getReturnDate() == null ? "OPEN" : "RETURNED";

        return new LoanResponse(
                loan.getId(),
                loan.getUser().getId(),
                loan.getBook().getId(),
                loan.getLoanDate(),
                loan.getExpectedReturnDate(),
                loan.getReturnDate(),
                status);
    }
}
