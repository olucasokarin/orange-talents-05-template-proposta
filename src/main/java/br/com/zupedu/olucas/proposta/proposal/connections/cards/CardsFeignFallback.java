package br.com.zupedu.olucas.proposta.proposal.connections.cards;

import org.springframework.stereotype.Component;

@Component
public class CardsFeignFallback implements CardsFeign{
    @Override
    public CardAssociateResponse getCard(CardAssociateRequest request) {
        return null;
    }
}
