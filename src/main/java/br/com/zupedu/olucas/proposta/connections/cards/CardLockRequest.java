package br.com.zupedu.olucas.proposta.connections.cards;

import javax.validation.constraints.NotBlank;

public class CardLockRequest {
    @NotBlank
    private String sistemaResponsavel;

    public CardLockRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
