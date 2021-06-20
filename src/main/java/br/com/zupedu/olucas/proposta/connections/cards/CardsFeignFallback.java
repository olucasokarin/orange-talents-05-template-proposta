package br.com.zupedu.olucas.proposta.connections.cards;

import org.springframework.stereotype.Component;

@Component
public class CardsFeignFallback implements CardsFeign {
    @Override
    public CardAssociateResponse getCard(CardAssociateRequest request) {
        return null;
    }

    @Override
    public CardLockResponse lockCard(String id, CardLockRequest request) {
        return new CardLockResponse(StatusCardLock.FALHA);
    }
}
