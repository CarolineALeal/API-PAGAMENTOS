package com.caroline.pagamentos.controller;
import com.caroline.pagamentos.dto.AtualizarStatusRequest;
import com.caroline.pagamentos.dto.PagamentoRequest;
import com.caroline.pagamentos.enums.StatusPagamento;
import com.caroline.pagamentos.model.Pagamento;
import com.caroline.pagamentos.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Pagamentos", description = "Operações relacionadas a pagamentos")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Criar pagamento",
            description = "Cria um novo pagamento no sistema com base nos dados informados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@Valid @RequestBody PagamentoRequest request) {
        Pagamento pagamento = service.criarPagamento(request);
        return ResponseEntity.ok(pagamento);
    }

    @Operation(
            summary = "Atualizar status do pagamento",
            description = "Atualiza o status de um pagamento existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "422", description = "Regra de negócio inválida")
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<Pagamento> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusRequest request
    ) {
        Pagamento pagamentoAtualizado = service.atualizarStatus(id, request);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @Operation(
            summary = "Buscar pagamento por ID",
            description = "Retorna os dados de um pagamento específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        Pagamento pagamento = service.buscarPorId(id);
        return ResponseEntity.ok(pagamento);
    }

    @Operation(
            summary = "Excluir pagamento",
            description = "Remove um pagamento do sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pagamento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPagamento(@PathVariable Long id) {
        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar pagamentos com filtros",
            description = "Permite buscar pagamentos por código de débito, CPF/CNPJ e status"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Pagamento>> buscarPagamentos(
        @RequestParam(required = false) Integer codigoDebito,
        @RequestParam(required = false) String cpfCnpj,
        @RequestParam(required = false) StatusPagamento status
    ) {
        return ResponseEntity.ok(service.buscarPagamentos(codigoDebito, cpfCnpj, status));
    }
}
