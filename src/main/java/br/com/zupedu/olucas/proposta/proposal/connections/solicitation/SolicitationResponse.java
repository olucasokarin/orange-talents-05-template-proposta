package br.com.zupedu.olucas.proposta.proposal.connections.solicitation;

import br.com.zupedu.olucas.proposta.proposal.enums.Status;

public class SolicitationResponse {
    private String documento;
    private String nome;
    private StatusProposalResponse resultadoSolicitacao;
    private String idProposta;

    public SolicitationResponse(String documento, String nome, StatusProposalResponse resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Status getResultadoSolicitacao() {
        return resultadoSolicitacao.statusReturn();
    }

    public String getIdProposta() {
        return idProposta;
    }
}

