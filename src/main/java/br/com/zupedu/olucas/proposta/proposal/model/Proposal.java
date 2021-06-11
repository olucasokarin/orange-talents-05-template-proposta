package br.com.zupedu.olucas.proposta.proposal.model;


import br.com.zupedu.olucas.proposta.proposal.validators.CPFOrCNPJ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
}
