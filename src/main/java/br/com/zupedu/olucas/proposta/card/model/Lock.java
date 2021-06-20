package br.com.zupedu.olucas.proposta.card.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Lock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    private String numberCard;
    private String ipClient;
    private String userAgent;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    private Card card;

    @Deprecated
    public Lock() {
    }

    public Lock(String ipClient, String userAgent, Card card) {
        this.numberCard = card.getNumberCard();
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.card = card;
        this.externalId = UUID.randomUUID();
    }
}
