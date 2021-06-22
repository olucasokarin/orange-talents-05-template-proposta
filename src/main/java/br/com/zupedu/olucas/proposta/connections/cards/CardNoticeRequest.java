package br.com.zupedu.olucas.proposta.connections.cards;

import java.time.LocalDate;

public class CardNoticeRequest {
    private String destino;
    private LocalDate validoAte;

    public CardNoticeRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
