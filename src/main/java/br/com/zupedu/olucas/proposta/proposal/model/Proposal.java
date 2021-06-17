package br.com.zupedu.olucas.proposta.proposal.model;


import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.proposal.enums.Status;
import br.com.zupedu.olucas.proposta.proposal.validators.CPFOrCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    @NotNull
    @CPFOrCNPJ
    private String document; //cpf or cnpj
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String address;
    @NotNull
    @Positive
    private Double salary;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    @Deprecated
    public Proposal() {
    }

    public Proposal(String document, String email, String name, String address, Double salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.externalId = UUID.randomUUID();
    }

    public UUID getExternalId() {
        return externalId;
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public String getCardNumber() {
        if (Objects.nonNull(this.card))
            return this.card.getNumberCard();
        return null;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void associateCardInProposal(Card card) {
        this.card = card;
    }
}
