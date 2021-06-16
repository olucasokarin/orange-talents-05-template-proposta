package br.com.zupedu.olucas.proposta.proposal.connections.cards;

import br.com.zupedu.olucas.proposta.card.model.Card;

import java.time.LocalDateTime;

public class CardAssociateResponse {
    private String id;
    private LocalDateTime emitidoEm;

    public CardAssociateResponse(String id, LocalDateTime emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Card convertResponseToEntity() {
        return new Card(this.id, this.emitidoEm);
    }
}

