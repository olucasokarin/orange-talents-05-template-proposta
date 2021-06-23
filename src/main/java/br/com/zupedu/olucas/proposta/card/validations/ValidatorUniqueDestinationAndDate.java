package br.com.zupedu.olucas.proposta.card.validations;

import br.com.zupedu.olucas.proposta.card.request.TravelRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Configuration
public class ValidatorUniqueDestinationAndDate implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return TravelRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) return;

        TravelRequest request = (TravelRequest) o;

        Query query = manager.createQuery("select 1 from Travel t where t.destination = :destination and t.dateEndTravel = :date");
        query.setParameter("destination", request.getDestination());
        query.setParameter("date", request.getDateEndTravel());

        List<?> resultList = query.getResultList();

        if(!resultList.isEmpty())
            errors.rejectValue("destination", null, "You cannot use the same destination on the same date");
    }
}
