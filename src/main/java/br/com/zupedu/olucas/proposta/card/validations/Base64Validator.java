package br.com.zupedu.olucas.proposta.card.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            byte[] decode = java.util.Base64.getDecoder().decode(value.getBytes());
            new String(decode);
            return true;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }
}
