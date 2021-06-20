package br.com.zupedu.olucas.proposta.card.controller;

import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.model.Lock;
import br.com.zupedu.olucas.proposta.card.repository.CardRepository;
import br.com.zupedu.olucas.proposta.connections.cards.CardLockRequest;
import br.com.zupedu.olucas.proposta.connections.cards.CardLockResponse;
import br.com.zupedu.olucas.proposta.connections.cards.CardsFeign;
import br.com.zupedu.olucas.proposta.connections.cards.StatusCardLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/lock")
public class LockController {

    private CardRepository cardRepository;
    private CardsFeign cardsFeign;

    @Autowired
    public LockController(CardRepository cardRepository, CardsFeign cardsFeign) {
        this.cardRepository = cardRepository;
        this.cardsFeign = cardsFeign;
    }

    @PostMapping("/{cardId}")
    public ResponseEntity lockCard(@PathVariable("cardId") String cardId, HttpServletRequest request) {
        try {
            UUID uuid = UUID.fromString(cardId);
            String userAgent = request.getHeader("User-Agent");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if(ipAddress == null)
                ipAddress = request.getRemoteHost();

            Optional<Card> optionalCard = cardRepository.findByExternalId(uuid);
            if (optionalCard.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());
            Card card = optionalCard.get();

            CardLockResponse cardLockResponse = cardsFeign.lockCard(card.getNumberCard(),
                    new CardLockRequest("card proposal system"));

            if (cardLockResponse.getResultado().equals(StatusCardLock.FALHA))
                return ResponseEntity.unprocessableEntity().build();

            card.lockTheCard(new Lock(ipAddress, userAgent, card));
            cardRepository.save(card);

            return ResponseEntity.ok().build();

        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());
        }
    }

    private HashMap<String, String> getBodyNotFound() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("message", "Card not found");
        return hash;
    }
}
