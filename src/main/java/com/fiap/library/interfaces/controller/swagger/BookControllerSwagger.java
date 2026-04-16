package com.fiap.library.interfaces.controller.swagger;

import java.util.List;

import com.fiap.library.application.dto.BookRequest;
import com.fiap.library.application.dto.BookResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface BookControllerSwagger {

    @Operation(summary = "Cria um novo livro", description = "Recebe dados de um livro e o salva no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    BookResponse criar(BookRequest request);

    @Operation(summary = "Busca um livro pelo ID", description = "Retorna os dados de um livro a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "404", description = "Livro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    BookResponse findById(Long id);

    @Operation(summary = "Lista todos os livros", description = "Retorna a lista de livros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    List<BookResponse> findAll();

    @Operation(summary = "Atualiza um livro", description = "Atualiza os dados de um livro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "404", description = "Livro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    BookResponse update(Long id, BookRequest request);

    @Operation(summary = "Remove um livro", description = "Remove um livro do sistema pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    void delete(Long id);
}
