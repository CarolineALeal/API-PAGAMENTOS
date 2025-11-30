package com.caroline.pagamentos.service;

import com.caroline.pagamentos.dto.PagamentoRequest;
import com.caroline.pagamentos.model.Pagamento;
import com.caroline.pagamentos.repository.PagamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public Pagamento criarPagamento(PagamentoRequest request) {
        Pagamento pagamento = new Pagamento();
        pagamento.setCodigoDebito(request.getCodigoDebito());
        pagamento.setCpfCnpj(request.getCpfCnpj());
        pagamento.setMetodoPagamento(request.getMetodoPagamento());
        pagamento.setNumeroCartao(request.getNumeroCartao());
        pagamento.setValor(request.getValor());
        pagamento.setStatus("PENDENTE");
        pagamento.setAtivo(true);

        return repository.save(pagamento);
    }

    public Pagamento atualizarStatus(Long id, String novoStatus) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        String statusAtual = pagamento.getStatus();

        if (statusAtual.equals("PENDENTE")) {
            if (!novoStatus.equals("PROCESSADO_SUCESSO") &&
                    !novoStatus.equals("PROCESSADO_FALHA")) {
                throw new RuntimeException("Status inválido para PAGAMENTO PENDENTE.");
            }
        } else if (statusAtual.equals("PROCESSADO_SUCESSO")) {
            throw new RuntimeException("Pagamento já processado com sucesso não pode ser alterado.");
        } else if (statusAtual.equals("PROCESSADO_FALHA")) {
            if (!novoStatus.equals("PENDENTE")) {
                throw new RuntimeException("Pagamento com falha só pode voltar para PENDENTE.");
            }
        }

        pagamento.setStatus(novoStatus);
        return repository.save(pagamento);
    }

    public Pagamento buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public List<Pagamento> listarTodos() {
        return repository.findAll();
    }

    public void excluirPagamento(Long id) {
    Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

    if (!pagamento.getStatus().equals("PENDENTE")) {
        throw new RuntimeException("Só é possível excluir pagamentos com status PENDENTE.");
    }

    pagamento.setAtivo(false);
    repository.save(pagamento);
    }

    public List<Pagamento> buscarPagamentos(Integer codigoDebito, String cpfCnpj, String status) {
    if (codigoDebito != null) {
        return repository.findByCodigoDebito(codigoDebito);
    } else if (cpfCnpj != null) {
        return repository.findByCpfCnpj(cpfCnpj);
    } else if (status != null) {
        return repository.findByStatus(status);
    } else {
        return repository.findAll();
    }
}



}
