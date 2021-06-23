package br.com.zupedu.olucas.proposta.connections.cards;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CardWalletResponse {
    private StatusAssociateWallet resultado;
    private String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardWalletResponse(StatusAssociateWallet resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public StatusAssociateWallet getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
