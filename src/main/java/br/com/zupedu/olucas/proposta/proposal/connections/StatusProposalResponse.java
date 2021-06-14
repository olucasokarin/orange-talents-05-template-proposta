package br.com.zupedu.olucas.proposta.proposal.connections;

import br.com.zupedu.olucas.proposta.proposal.enums.Status;

public enum StatusProposalResponse {
    SEM_RESTRICAO {
        Status statusReturn() {
            return Status.ELIGIBLE;
        }
    },
    COM_RESTRICAO {
        Status statusReturn() {
            return Status.NOT_ELIGIBLE;
        }
    };

    abstract Status statusReturn();
}
