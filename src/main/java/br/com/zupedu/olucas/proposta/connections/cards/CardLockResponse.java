package br.com.zupedu.olucas.proposta.connections.cards;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CardLockResponse {
    private StatusCardLock resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardLockResponse(StatusCardLock resultado) {
        this.resultado = resultado;
    }

    public StatusCardLock getResultado() {
        return resultado;
    }
}
