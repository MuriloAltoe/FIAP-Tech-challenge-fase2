package com.fiap.library.interfaces.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.library.application.dto.UsersRequest;
import com.fiap.library.application.dto.UsersResponse;
import com.fiap.library.application.service.UsersService;
import com.fiap.library.interfaces.controller.swagger.UsersControllerSwagger;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController implements UsersControllerSwagger {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    @PostMapping
    public UsersResponse criar(@RequestBody @Valid UsersRequest request) {
        return usersService.save(request);
    }

    @Override
    @GetMapping("/{id}")
    public UsersResponse findById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    @Override
    @GetMapping
    public List<UsersResponse> findAll() {
        return usersService.findAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public UsersResponse update(@PathVariable Long id, @RequestBody @Valid UsersRequest request) {
        return usersService.update(id, request);
    }
}
