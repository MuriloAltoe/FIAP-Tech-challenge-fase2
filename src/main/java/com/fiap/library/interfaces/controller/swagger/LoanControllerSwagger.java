package com.fiap.library.interfaces.controller.swagger;

import java.util.List;

import com.fiap.library.application.dto.LoanRequest;
import com.fiap.library.application.dto.LoanResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface LoanControllerSwagger {

    @Operation(summary = "Registra um emprestimo", description = "Cria um novo emprestimo para um usuario e livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emprestimo registrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "404", description = "Livro ou usuario nao encontrado")
    })
    LoanResponse createLoan(LoanRequest request);

    @Operation(summary = "Registra devolucao", description = "Finaliza um emprestimo a partir do id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolucao registrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "404", description = "Emprestimo nao encontrado")
    })
    LoanResponse returnLoan(Long id);

    @Operation(summary = "Busca emprestimo por id", description = "Retorna os dados de um emprestimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emprestimo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "404", description = "Emprestimo nao encontrado")
    })
    LoanResponse findById(Long id);

    @Operation(summary = "Lista emprestimos", description = "Retorna todos os emprestimos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class)))
    })
    List<LoanResponse> findAll();
}
