package com.caroline.pagamentos.service;

import com.caroline.pagamentos.dto.AtualizarStatusRequest;
import com.caroline.pagamentos.dto.PagamentoRequest;
import com.caroline.pagamentos.exception.PagamentoNaoEncontradoException;
import com.caroline.pagamentos.exception.RegraDeNegocioException;
import com.caroline.pagamentos.model.Pagamento;
import com.caroline.pagamentos.repository.PagamentoRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.caroline.pagamentos.specification.PagamentoSpecification.*;


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

    public Pagamento atualizarStatus(Long id, AtualizarStatusRequest request) {

        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado"));

        String statusAtual = pagamento.getStatus();
        String novoStatus = request.getStatus();

        switch (statusAtual) {

            case "PENDENTE":
                if (!novoStatus.equals("PROCESSADO_SUCESSO") &&
                    !novoStatus.equals("PROCESSADO_FALHA")) {

                    throw new RegraDeNegocioException(
                        "PENDENTE só pode ir para PROCESSADO_SUCESSO ou PROCESSADO_FALHA."
                    );
                }
                break;

            case "PROCESSADO_SUCESSO":
                throw new RegraDeNegocioException(
                    "Pagamentos PROCESSADO_SUCESSO não podem ter o status alterado."
                );

            case "PROCESSADO_FALHA":
                if (!novoStatus.equals("PENDENTE")) {
                    throw new RegraDeNegocioException(
                        "PROCESSADO_FALHA só pode voltar para PENDENTE."
                    );
                }
                break;

            default:
                throw new RegraDeNegocioException("Status atual inválido: " + statusAtual);
        }

        pagamento.setStatus(novoStatus);
        return repository.save(pagamento);
    }


    public Pagamento buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado"));
    }

    public List<Pagamento> listarTodos() {
        return repository.findAll();
    }

    public void excluirPagamento(Long id) {
    Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado"));

    if (!pagamento.getStatus().equals("PENDENTE")) {
        throw new RegraDeNegocioException(
                "Só é possível excluir pagamentos com status PENDENTE."
            );
    }

    pagamento.setAtivo(false);
    repository.save(pagamento);
    }

    public List<Pagamento> buscarPagamentos(Integer codigoDebito, String cpfCnpj, String status) {
        Specification<Pagamento> spec = Specification
            .where(codigoDebito(codigoDebito))
            .and(cpfCnpj(cpfCnpj))
            .and(status(status));

        return repository.findAll(spec);
    }

}
