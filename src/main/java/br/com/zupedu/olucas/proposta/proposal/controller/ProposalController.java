package br.com.zupedu.olucas.proposta.proposal.controller;

import br.com.zupedu.olucas.proposta.proposal.connections.solicitation.SolicitationFeign;
import br.com.zupedu.olucas.proposta.proposal.connections.solicitation.SolicitationRequest;
import br.com.zupedu.olucas.proposta.proposal.connections.solicitation.SolicitationResponse;
import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import br.com.zupedu.olucas.proposta.proposal.repository.ProposalRepository;
import br.com.zupedu.olucas.proposta.proposal.request.ProposalRequest;
import br.com.zupedu.olucas.proposta.proposal.response.ProposalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    ProposalRepository proposalRepository;
    SolicitationFeign solicitationFeign;

    @Autowired
    public ProposalController(ProposalRepository proposalRepository,
                              SolicitationFeign solicitationFeign) {
        this.proposalRepository = proposalRepository;
        this.solicitationFeign = solicitationFeign;
    }

    @PostMapping
    public ResponseEntity createProposal(@Valid @RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uribuild) {
        Proposal proposal = proposalRequest.convertRequestToEntity();
        proposalRepository.save(proposal);

        SolicitationResponse response = solicitationFeign.solicitation(new SolicitationRequest(proposal));
        proposal.setStatus(response.getResultadoSolicitacao());
        proposalRepository.save(proposal);

        URI uri = uribuild.path("/api/proposals/{id}")
                .buildAndExpand(proposal.getExternalId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProposal(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<Proposal> proposalOptional = proposalRepository.findByExternalId(uuid);

            if (proposalOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());

            Proposal proposal = proposalOptional.get();
            ProposalResponse proposalResponse = new ProposalResponse(proposal);

            return ResponseEntity.ok(proposalResponse);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBodyNotFound());
        }
    }

    private HashMap<String, String> getBodyNotFound() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("message", "Proposal not found");
        return hash;
    }
}
