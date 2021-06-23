package br.com.zupedu.olucas.proposta.card.model;

import br.com.zupedu.olucas.proposta.card.model.enums.WalletStatusName;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    private String cardNumber;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private WalletStatusName nameWallet;
    @ManyToOne
    private Card card;

    @Deprecated
    public Wallet() {
    }

    public Wallet(String email, String nameWallet, Card card) {
        this.cardNumber = card.getNumberCard();
        this.email = email;
        this.nameWallet = WalletStatusName.valueOf(nameWallet);
        this.card = card;
        this.externalId = UUID.randomUUID();
    }

    public UUID getExternalId() {
        return externalId;
    }
}
