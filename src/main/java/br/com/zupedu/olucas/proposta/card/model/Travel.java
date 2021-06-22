package br.com.zupedu.olucas.proposta.card.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    @NotNull
    private String numberCard;
    @NotNull
    @NotEmpty
    private String destination;
    @Future
    @NotNull
    private LocalDate dateEndTravel;
    private String userAgent;
    private String ipClient;
    private boolean statusNotice;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    private Card card;

    @Deprecated
    public Travel() {
    }

    public Travel(Card card, String destination, LocalDate dateEndTravel, String userAgent, String ipClient) {
        this.numberCard = card.getNumberCard();
        this.destination = destination;
        this.dateEndTravel = dateEndTravel;
        this.userAgent = userAgent;
        this.ipClient = ipClient;
        this.card = card;
        this.statusNotice = false;
        this.externalId = UUID.randomUUID();
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDateEndTravel() {
        return dateEndTravel;
    }

    public void syncNoticeWithAPI() {
        this.statusNotice = true;
    }
}
