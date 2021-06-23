package br.com.zupedu.olucas.proposta.card.controller;

import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.model.Wallet;
import br.com.zupedu.olucas.proposta.card.repository.CardRepository;
import br.com.zupedu.olucas.proposta.card.request.WalletRequest;
import br.com.zupedu.olucas.proposta.card.validations.TypeEnumValidator;
import br.com.zupedu.olucas.proposta.connections.cards.CardWalletResponse;
import br.com.zupedu.olucas.proposta.connections.cards.CardsFeign;
import br.com.zupedu.olucas.proposta.connections.cards.StatusAssociateWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    CardRepository cardRepository;
    CardsFeign cardsFeign;
    TypeEnumValidator typeEnumValidator;

    @Autowired
    public WalletController(CardRepository cardRepository,
                            CardsFeign cardsFeign,
                            TypeEnumValidator typeEnumValidator) {
        this.cardRepository = cardRepository;
        this.cardsFeign = cardsFeign;
        this.typeEnumValidator = typeEnumValidator;
    }

    @PostMapping("/{id}")
    public ResponseEntity associateWallet(@PathVariable("id") UUID id,
                                          UriComponentsBuilder uribuild,
                                          @Valid @RequestBody WalletRequest walletRequest ) {
        Optional<Card> optionalCard = cardRepository.findByExternalId(id);
        if(optionalCard.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyError("Card not found"));
        Card card = optionalCard.get();

        CardWalletResponse associateWalletResponse =
                cardsFeign.associateWallet(card.getNumberCard(), walletRequest.convertToRequestFeign());

        if(associateWalletResponse.getResultado().equals(StatusAssociateWallet.FALHA))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(getBodyError("Card already associate on this wallet"));

        Wallet wallet = walletRequest.convertRequestToEntity(card);
        card.associateWallet(wallet);
        cardRepository.save(card);

        URI uri = uribuild.path("/api/wallets/{id}")
                .buildAndExpand(wallet.getExternalId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    private HashMap<String, String> getBodyError(String message) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("message", message);
        return hash;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(typeEnumValidator);
    }
}
