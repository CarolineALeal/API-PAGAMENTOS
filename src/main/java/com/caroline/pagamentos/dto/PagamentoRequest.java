package com.caroline.pagamentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PagamentoRequest {

    @NotNull(message = "O código do débito é obrigatório")
    private Integer codigoDebito;

    @NotBlank(message = "O CPF/CNPJ é obrigatório")
    @Size(min = 11, max = 14, message = "CPF deve ter 11 dígitos e CNPJ 14")
    private String cpfCnpj;

    private String metodoPagamento;

    private String numeroCartao;

    @NotNull(message = "O valor é obrigatório")
    private Double valor;

    // GETTERS E SETTERS
    public Integer getCodigoDebito() {
        return codigoDebito;
    }

    public void setCodigoDebito(Integer codigoDebito) {
        this.codigoDebito = codigoDebito;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
