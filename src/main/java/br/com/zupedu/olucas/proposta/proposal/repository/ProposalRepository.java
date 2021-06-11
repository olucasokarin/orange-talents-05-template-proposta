package br.com.zupedu.olucas.proposta.proposal.repository;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
