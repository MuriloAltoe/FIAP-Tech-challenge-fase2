package com.fiap.library.interfaces.controller.swagger;

import java.util.List;

import com.fiap.library.application.dto.UsersRequest;
import com.fiap.library.application.dto.UsersResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UsersControllerSwagger {

    @Operation(summary = "Cria um novo usuario", description = "Recebe dados de um usuario e o salva no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    UsersResponse criar(UsersRequest request);

    @Operation(summary = "Busca um usuario pelo ID", description = "Retorna os dados de um usuario a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    UsersResponse findById(Long id);

    @Operation(summary = "Lista todos os usuarios", description = "Retorna a lista de usuarios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    List<UsersResponse> findAll();

    @Operation(summary = "Remove um usuario", description = "Remove um usuario do sistema pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    void delete(Long id);

    @Operation(summary = "Atualiza um usuario", description = "Atualiza os dados de um usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    UsersResponse update(Long id, UsersRequest request);
}
