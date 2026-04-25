package com.caroline.pagamentos.model;

import com.caroline.pagamentos.enums.MetodoPagamento;
import com.caroline.pagamentos.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigoDebito;

    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private String numeroCartao;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private boolean ativo = true;

    public Pagamento() {}

}
