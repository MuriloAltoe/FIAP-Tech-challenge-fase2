package com.fiap.library.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.library.domain.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByReturnDateIsNull();

    List<Loan> findByExpectedReturnDateBeforeAndReturnDateIsNull(LocalDate date);

    boolean existsByBookIdAndReturnDateIsNull(Long bookId);

    boolean existsByUserIdAndReturnDateIsNull(Long userId);
}
