package br.com.zupedu.olucas.proposta.proposal.controller;

import br.com.zupedu.olucas.proposta.proposal.connections.SolicitationFeign;
import br.com.zupedu.olucas.proposta.proposal.connections.SolicitationRequest;
import br.com.zupedu.olucas.proposta.proposal.connections.SolicitationResponse;
import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import br.com.zupedu.olucas.proposta.proposal.repository.ProposalRepository;
import br.com.zupedu.olucas.proposta.proposal.request.ProposalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    ProposalRepository proposalRepository;
    SolicitationFeign cardsFeign;

    @Autowired
    public ProposalController(ProposalRepository proposalRepository, SolicitationFeign cardsFeign) {
        this.proposalRepository = proposalRepository;
        this.cardsFeign = cardsFeign;
    }

    @PostMapping
    public ResponseEntity createProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uribuild) {
        Proposal proposal = proposalRequest.convertRequestToEntity();
        proposalRepository.save(proposal);

        SolicitationResponse response = cardsFeign.solicitation(new SolicitationRequest(proposal));
        proposal.setStatus(response.getResultadoSolicitacao());
        proposalRepository.save(proposal);

        URI uri = uribuild.path("/api/proposals/{id}")
                .buildAndExpand(proposal.getExternalId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
