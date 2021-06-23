package br.com.zupedu.olucas.proposta.card.request;

import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.model.Wallet;
import br.com.zupedu.olucas.proposta.connections.cards.CardWalletRequest;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class WalletRequest {
    @NotBlank
    @Email
    private String email;
    private String wallet;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WalletRequest(String email, String wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public Wallet convertRequestToEntity(Card card) {
        return new Wallet(this.email, this.wallet, card);
    }

    public CardWalletRequest convertToRequestFeign() {
        return new CardWalletRequest(this.email, this.wallet);
    }

    public String getWallet() {
        return wallet;
    }
}
