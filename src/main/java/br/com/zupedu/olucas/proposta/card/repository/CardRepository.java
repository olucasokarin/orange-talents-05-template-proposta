package br.com.zupedu.olucas.proposta.card.repository;

import br.com.zupedu.olucas.proposta.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByExternalId(UUID id);
}
