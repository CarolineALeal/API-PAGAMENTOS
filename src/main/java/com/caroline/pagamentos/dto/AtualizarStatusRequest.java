package com.caroline.pagamentos.dto;

import com.caroline.pagamentos.enums.StatusPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizarStatusRequest {
    private StatusPagamento status;
}
