package br.com.zupedu.olucas.proposta.card.controller;

import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.model.Travel;
import br.com.zupedu.olucas.proposta.card.repository.CardRepository;
import br.com.zupedu.olucas.proposta.card.request.TravelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    CardRepository cardRepository;

    @Autowired
    public TravelController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping("/{id}")
    public ResponseEntity registerTravel(@PathVariable("id") UUID id,
                                         HttpServletRequest request,
                                         @Valid @RequestBody TravelRequest travelRequest) {
        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if(ipAddress == null)
            ipAddress = request.getRemoteHost();

        Optional<Card> optionalCard = cardRepository.findByExternalId(id);
        if(optionalCard.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());
        Card card = optionalCard.get();
        Travel travel = travelRequest.convertRequestToEntity(card, userAgent, ipAddress);
        card.registerTravel(travel);
        cardRepository.save(card);

        return ResponseEntity.ok().build();
    }

    private HashMap<String, String> getBodyNotFound() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("message", "Card not found");
        return hash;
    }
}
