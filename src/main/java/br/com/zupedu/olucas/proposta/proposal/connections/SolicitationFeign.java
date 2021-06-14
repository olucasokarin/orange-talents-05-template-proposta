package br.com.zupedu.olucas.proposta.proposal.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${solicitation.host}", name = "solicitation", fallback = SolicitationFeignFallback.class)
public interface SolicitationFeign {

    @PostMapping
    SolicitationResponse solicitation(@RequestBody SolicitationRequest solicitationRequest);
}

