package br.com.zupedu.olucas.proposta.connections.cards;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CardNoticeResponse {
    private StatusNotice resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardNoticeResponse(StatusNotice resultado) {
        this.resultado = resultado;
    }

    public StatusNotice getResultado() {
        return resultado;
    }
}
