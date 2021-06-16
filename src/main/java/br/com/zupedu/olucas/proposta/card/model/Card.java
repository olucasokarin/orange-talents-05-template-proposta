package br.com.zupedu.olucas.proposta.card.model;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    private String numberCard;
    @OneToOne
    Proposal proposal;
    @CreatedDate
    private LocalDateTime createdAt;

    @Deprecated
    public Card() {
    }

    public Card(String numberCard, LocalDateTime createdAt) {
        this.numberCard = numberCard;
        this.createdAt = createdAt;
        this.externalId = UUID.randomUUID();
    }
}
