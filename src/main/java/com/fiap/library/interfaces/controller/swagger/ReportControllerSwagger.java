package com.fiap.library.interfaces.controller.swagger;

import java.util.List;

import com.fiap.library.application.dto.BookAvailabilityReportResponse;
import com.fiap.library.application.dto.LoanResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ReportControllerSwagger {

    @Operation(summary = "Relatorio de emprestimos ativos", description = "Retorna emprestimos que ainda nao foram devolvidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatorio retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class)))
    })
    List<LoanResponse> activeLoans();

    @Operation(summary = "Relatorio de emprestimos atrasados", description = "Retorna emprestimos com data esperada de devolucao vencida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatorio retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoanResponse.class)))
    })
    List<LoanResponse> overdueLoans();

    @Operation(summary = "Relatorio de disponibilidade", description = "Retorna resumo de livros disponiveis e emprestados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatorio retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookAvailabilityReportResponse.class)))
    })
    BookAvailabilityReportResponse booksAvailability();
}
