package br.com.zupedu.olucas.proposta.card.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    @NotNull
    @ManyToOne
    private Card card;
    @NotNull
    private String fingerprint;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Biometry() {
    }

    public Biometry(String fingerprint, Card card) {
        this.card = card;
        this.fingerprint = fingerprint;
        this.externalId = UUID.randomUUID();
    }

    public UUID getExternalId() {
        return externalId;
    }
}
