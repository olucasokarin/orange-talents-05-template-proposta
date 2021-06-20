package br.com.zupedu.olucas.proposta.card.model;

import br.com.zupedu.olucas.proposta.proposal.model.Proposal;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    private String numberCard;
    @OneToOne(mappedBy = "card")
    Proposal proposal;
    @OneToMany(mappedBy = "card")
    List<Biometry> biometrics;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    List<Lock> locks;
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

    public String getNumberCard() {
        return numberCard;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void lockTheCard(Lock lock) {
        this.locks.add(lock);
    }
}
