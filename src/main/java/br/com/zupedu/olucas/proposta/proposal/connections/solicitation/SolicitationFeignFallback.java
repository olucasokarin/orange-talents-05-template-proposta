package br.com.zupedu.olucas.proposta.proposal.connections.solicitation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SolicitationFeignFallback implements SolicitationFeign{
    @Override
    public SolicitationResponse solicitation(SolicitationRequest request) {
        return new SolicitationResponse(request.getDocumento(), request.getNome(),
                StatusProposalResponse.COM_RESTRICAO, String.valueOf(request.getIdProposta()));
    }
}
