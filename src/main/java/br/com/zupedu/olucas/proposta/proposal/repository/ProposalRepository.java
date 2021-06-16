package br.com.zupedu.olucas.proposta.proposal.repository;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("select p from Proposal p where p.card is null and p.status = 'ELIGIBLE'")
    List<Proposal> getProposalWithoutCard();
}
