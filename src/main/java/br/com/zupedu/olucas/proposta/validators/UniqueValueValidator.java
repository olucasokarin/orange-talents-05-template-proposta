package br.com.zupedu.olucas.proposta.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager manager;

    private Class<?> entity;
    private String attribute;

    @Override
    public void initialize(UniqueValue annotation) {
        this.entity = annotation.entity();
        this.attribute = annotation.attribute();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + entity.getName()
                                            + " where " + attribute + " = :comparator");
        query.setParameter("comparator", value);

        List<?> result = query.getResultList();
        return result.isEmpty();
    }
}
