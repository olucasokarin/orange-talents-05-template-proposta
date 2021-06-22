package br.com.zupedu.olucas.proposta.card.repository;

import br.com.zupedu.olucas.proposta.card.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("select t from Travel t where t.statusNotice is false")
    List<Travel> getNoticeTravelWithoutSync();

}
