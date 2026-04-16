package com.fiap.library.application.mapper;

import java.util.List;

import com.fiap.library.application.dto.UsersRequest;
import com.fiap.library.application.dto.UsersResponse;
import com.fiap.library.domain.entities.Loan;
import com.fiap.library.domain.entities.Users;

public class UsersMapper {

    private UsersMapper() {
    }

    public static Users toEntity(UsersRequest dto) {
        Users user = new Users();
        user.setId(null);
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());

        if (dto.getLoanIds() != null && !dto.getLoanIds().isEmpty()) {
            List<Loan> loan = dto.getLoanIds().stream()
                    .map(id -> {
                        Loan loans = new Loan(id);
                        loans.setUser(user);
                        return loans;
                    })
                    .toList();
            user.setLoan(loan);
        }

        return user;
    }

    public static UsersResponse toResponse(Users user) {
        List<Long> loanIds = null;

        if (user.getLoan() != null) {
            loanIds = user.getLoan().stream()
                    .map(Loan::getId)
                    .toList();
        }

        return new UsersResponse(
                user.getNome(),
                user.getEmail(),
                loanIds);
    }
}
