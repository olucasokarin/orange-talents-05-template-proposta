package br.com.zupedu.olucas.proposta.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueValueValidator.class })
public @interface UniqueValue {

    String message() default "Data already exists";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default  { };

    Class<?> entity(); // the entity to be searched in the database
    String attribute() default "id"; // comparison parameter
}
