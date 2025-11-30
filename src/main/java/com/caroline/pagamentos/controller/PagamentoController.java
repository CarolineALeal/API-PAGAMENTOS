package com.caroline.pagamentos.controller;

import com.caroline.pagamentos.dto.PagamentoRequest;
import com.caroline.pagamentos.model.Pagamento;
import com.caroline.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@Valid @RequestBody PagamentoRequest request) {
        Pagamento pagamento = service.criarPagamento(request);
        return ResponseEntity.ok(pagamento);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pagamento> atualizarStatus(
        @PathVariable Long id,
        @RequestParam String status
    ) {
        Pagamento pagamentoAtualizado = service.atualizarStatus(id, status);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        Pagamento pagamento = service.buscarPorId(id);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPagamento(@PathVariable Long id) {
        service.excluirPagamento(id);
        return ResponseEntity.ok("Pagamento excluído logicamente com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> buscarPagamentos(
        @RequestParam(required = false) Integer codigoDebito,
        @RequestParam(required = false) String cpfCnpj,
        @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(service.buscarPagamentos(codigoDebito, cpfCnpj, status));
    }



}
