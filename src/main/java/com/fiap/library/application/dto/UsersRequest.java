package com.fiap.library.application.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersRequest {

    @NotBlank(message = "nome is required")
    private String nome;

    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;

    private List<Long> loanIds;
}
