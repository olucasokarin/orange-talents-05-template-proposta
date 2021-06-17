package br.com.zupedu.olucas.proposta.card.controller;

import br.com.zupedu.olucas.proposta.card.model.Biometry;
import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.repository.BiometryRepository;
import br.com.zupedu.olucas.proposta.card.repository.CardRepository;
import br.com.zupedu.olucas.proposta.card.request.BiometryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
public class BiometryController {

    BiometryRepository biometryRepository;
    CardRepository cardRepository;

    @Autowired
    public BiometryController(BiometryRepository biometryRepository,
                              CardRepository cardRepository) {
        this.biometryRepository = biometryRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/{cardId}/biometry")
    public ResponseEntity createBiometry(@PathVariable("cardId") String cardId,
                                         @RequestBody @Valid BiometryRequest biometryRequest,
                                         UriComponentsBuilder uribuild) {
        try {
            UUID uuid = UUID.fromString(cardId);
            Optional<Card> optionalCard = cardRepository.findByExternalId(uuid);

            if (optionalCard.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());

            Biometry biometry = biometryRequest.convertRequestToEntity(optionalCard.get());
            biometryRepository.save(biometry);

            URI uri = uribuild.path("/api/biometrics/{id}")
                    .buildAndExpand(biometry.getExternalId()).toUri();
            return ResponseEntity.created(uri).build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());
        }
    }

    private HashMap<String, String> getBodyNotFound() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("message", "Card not found");
        return hash;
    }
}
