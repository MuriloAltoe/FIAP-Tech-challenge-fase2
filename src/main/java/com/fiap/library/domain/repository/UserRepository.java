package com.fiap.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.library.domain.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
