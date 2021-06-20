package br.com.zupedu.olucas.proposta.proposal.schedules;

import br.com.zupedu.olucas.proposta.connections.cards.CardAssociateRequest;
import br.com.zupedu.olucas.proposta.connections.cards.CardAssociateResponse;
import br.com.zupedu.olucas.proposta.connections.cards.CardsFeign;
import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import br.com.zupedu.olucas.proposta.proposal.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableAsync
@EnableScheduling
public class ScheduleCardsAssociateProposal {

    ProposalRepository proposalRepository;
    CardsFeign cardsFeign;

    @Autowired
    public ScheduleCardsAssociateProposal(ProposalRepository proposalRepository,
                                          CardsFeign cardsFeign) {
        this.proposalRepository = proposalRepository;
        this.cardsFeign = cardsFeign;
    }

    @Scheduled(fixedDelayString = "${schedule.time.card.fixedDelay}")
    public void schedulingCardsAndAssociateInProposal() {
        List<Proposal> proposalWithoutCard = proposalRepository.getProposalWithoutCard();
        List<Proposal> proposalWithCard = new ArrayList<>();

        proposalWithoutCard.stream()
                .forEach(proposal -> {
                    CardAssociateResponse cardResponse = cardsFeign.getCard(new CardAssociateRequest(proposal));
                    if(Objects.nonNull(cardResponse)) {
                        proposal.associateCardInProposal(cardResponse.convertResponseToEntity());
                        proposalWithCard.add(proposal);
                    }
                });
        proposalRepository.saveAll(proposalWithCard);
    }

}
