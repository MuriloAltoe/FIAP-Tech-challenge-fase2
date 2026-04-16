package com.fiap.library.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersResponse {

    private String nome;
    private String email;
    private List<Long> loanIds;
}
