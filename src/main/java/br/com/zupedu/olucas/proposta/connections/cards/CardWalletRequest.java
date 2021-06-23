package br.com.zupedu.olucas.proposta.connections.cards;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CardWalletRequest {
    @Email
    @NotBlank
    private String email;
    private String carteira;

    public CardWalletRequest(String email, String walletStatusName) {
        this.email = email;
        this.carteira = walletStatusName;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
