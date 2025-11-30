package com.caroline.pagamentos.dto;

public class AtualizarStatusRequest {
    private String status;

    public AtualizarStatusRequest() {}

    public AtualizarStatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
