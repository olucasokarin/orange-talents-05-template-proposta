package br.com.zupedu.olucas.proposta.proposal.controller;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import br.com.zupedu.olucas.proposta.proposal.repository.ProposalRepository;
import br.com.zupedu.olucas.proposta.proposal.request.ProposalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    ProposalRepository proposalRepository;

    @Autowired
    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    public ResponseEntity createProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uribuild) {
        Proposal proposal = proposalRequest.convertRequestToEntity();
        proposalRepository.save(proposal);

        URI uri = uribuild.path("/api/proposals/{id}")
                .buildAndExpand(proposal.getExternalId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
