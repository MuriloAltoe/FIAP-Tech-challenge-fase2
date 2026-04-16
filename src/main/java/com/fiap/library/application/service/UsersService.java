package com.fiap.library.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.library.application.dto.UsersRequest;
import com.fiap.library.application.dto.UsersResponse;
import com.fiap.library.application.mapper.UsersMapper;
import com.fiap.library.domain.repository.LoanRepository;
import com.fiap.library.domain.repository.UserRepository;
import com.fiap.library.infrastructure.exception.BusinessRuleException;
import com.fiap.library.infrastructure.exception.ResourceNotFoundException;

@Service
public class UsersService {

    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public UsersService(UserRepository userRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public UsersResponse save(UsersRequest usersRequest) {
        var user = UsersMapper.toEntity(usersRequest);
        var savedUser = userRepository.save(user);
        return UsersMapper.toResponse(savedUser);
    }

    public UsersResponse findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UsersMapper.toResponse(user);
    }

    public List<UsersResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UsersMapper::toResponse)
                .toList();
    }

    public UsersResponse update(Long id, UsersRequest usersRequest) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        var updatedUser = UsersMapper.toEntity(usersRequest);
        updatedUser.setId(existingUser.getId());
        var savedUser = userRepository.save(updatedUser);
        return UsersMapper.toResponse(savedUser);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        if (loanRepository.existsByUserIdAndReturnDateIsNull(id)) {
            throw new BusinessRuleException("User cannot be deleted while there is an active loan");
        }

        userRepository.deleteById(id);
    }
}
