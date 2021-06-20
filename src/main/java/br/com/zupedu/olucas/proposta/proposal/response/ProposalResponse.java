package br.com.zupedu.olucas.proposta.proposal.response;

import br.com.zupedu.olucas.proposta.proposal.enums.Status;
import br.com.zupedu.olucas.proposta.proposal.model.Proposal;

import java.util.UUID;

public class ProposalResponse {
    private UUID id;
    private String document;
    private String email;
    private String name;
    private Status status;
    private ProposalCardResponse card;

    public ProposalResponse(Proposal proposal) {
        this.id = proposal.getExternalId();
        this.document = proposal.getDocument();
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.status = proposal.getStatus();
        this.card = proposal.getCard() != null ? new ProposalCardResponse(proposal.getCard()) : null;
    }

    public UUID getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public ProposalCardResponse getCard() {
        return card;
    }
}
