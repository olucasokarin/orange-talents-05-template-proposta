package br.com.zupedu.olucas.proposta.proposal.connections.cards;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;

import javax.validation.constraints.NotBlank;

public class CardAssociateRequest {
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    public CardAssociateRequest(Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = String.valueOf(proposal.getId());
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
