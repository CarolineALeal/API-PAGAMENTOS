package com.caroline.pagamentos.dto;

import com.caroline.pagamentos.enums.MetodoPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Dados necessários para criação de um pagamento")
public class PagamentoRequest {

    @Schema(
            description = "Código do débito associado ao pagamento",
            example = "12345",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O código do débito é obrigatório")
    private Integer codigoDebito;

    @Schema(
            description = "CPF ou CNPJ do pagador",
            example = "12345678900",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "O CPF/CNPJ é obrigatório")
    @Size(min = 11, max = 14, message = "CPF deve ter 11 dígitos e CNPJ 14")
    private String cpfCnpj;

    @Schema(
            description = "Método de pagamento utilizado",
            example = "CARTAO_CREDITO",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O método de pagamento é obrigatório")
    private MetodoPagamento metodoPagamento;

    @Schema(
            description = "Número do cartão (obrigatório apenas para cartão)",
            example = "4111111111111111",
            nullable = true
    )
    private String numeroCartao;

    @Schema(
            description = "Valor do pagamento",
            example = "150.75",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O valor é obrigatório")
    @Min(value = 0, message = "O valor não deve ser negativo")
    private Double valor;

}
