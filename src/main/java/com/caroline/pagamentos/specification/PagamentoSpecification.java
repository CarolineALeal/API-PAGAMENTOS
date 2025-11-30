package com.caroline.pagamentos.specification;

import com.caroline.pagamentos.model.Pagamento;
import org.springframework.data.jpa.domain.Specification;

public class PagamentoSpecification {

    public static Specification<Pagamento> codigoDebito(Integer codigoDebito) {
        return (root, query, builder) -> 
            codigoDebito == null ? null : builder.equal(root.get("codigoDebito"), codigoDebito);
    }

    public static Specification<Pagamento> cpfCnpj(String cpfCnpj) {
        return (root, query, builder) -> 
            cpfCnpj == null ? null : builder.equal(root.get("cpfCnpj"), cpfCnpj);
    }

    public static Specification<Pagamento> status(String status) {
        return (root, query, builder) -> 
            status == null ? null : builder.equal(root.get("status"), status);
    }
}
