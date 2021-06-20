package br.com.zupedu.olucas.proposta.proposal.response;

import br.com.zupedu.olucas.proposta.card.model.Card;

public class ProposalCardResponse {
    private String id;
    private String cardNumber;

    public ProposalCardResponse(Card card) {
        this.cardNumber = card.getNumberCard();
        this.id = String.valueOf(card.getExternalId());
    }

    public String getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
