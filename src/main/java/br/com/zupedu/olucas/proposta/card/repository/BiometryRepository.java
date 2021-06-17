package br.com.zupedu.olucas.proposta.card.repository;

import br.com.zupedu.olucas.proposta.card.model.Biometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometryRepository extends JpaRepository<Biometry, Long> {
}
