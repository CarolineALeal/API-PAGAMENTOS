package com.caroline.pagamentos.enums;

public enum StatusPagamento {

    PENDENTE {
        @Override
        public boolean podeIrPara(StatusPagamento novo) {
            return novo == PROCESSADO_SUCESSO || novo == PROCESSADO_FALHA;
        }
    },

    PROCESSADO_SUCESSO {
        @Override
        public boolean podeIrPara(StatusPagamento novo) {
            return false;
        }
    },

    PROCESSADO_FALHA {
        @Override
        public boolean podeIrPara(StatusPagamento novo) {
            return novo == PENDENTE;
        }
    };

    public abstract boolean podeIrPara(StatusPagamento novo);
}
