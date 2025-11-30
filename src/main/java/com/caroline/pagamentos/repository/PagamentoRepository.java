package com.caroline.pagamentos.repository;

import com.caroline.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByCodigoDebito(Integer codigoDebito);
    List<Pagamento> findByCpfCnpj(String cpfCnpj);
    List<Pagamento> findByStatus(String status);
    
}
