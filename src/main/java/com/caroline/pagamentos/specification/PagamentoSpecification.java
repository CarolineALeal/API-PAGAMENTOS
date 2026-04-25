package com.caroline.pagamentos.specification;

import com.caroline.pagamentos.enums.StatusPagamento;
import com.caroline.pagamentos.model.Pagamento;
import org.springframework.data.jpa.domain.Specification;

public class PagamentoSpecification {

    public static Specification<Pagamento> codigoDebito(Integer codigoDebito) {
        return (root, query, builder) ->
                codigoDebito != null
                        ? builder.equal(root.get("codigoDebito"), codigoDebito)
                        : builder.conjunction();
    }

    public static Specification<Pagamento> cpfCnpj(String cpfCnpj) {
        return (root, query, builder) ->
                cpfCnpj != null
                        ? builder.equal(root.get("cpfCnpj"), cpfCnpj)
                        : builder.conjunction();
    }

    public static Specification<Pagamento> status(StatusPagamento status) {
        return (root, query, builder) ->
                status != null
                        ? builder.equal(root.get("status"), status)
                        : builder.conjunction();
    }
}
