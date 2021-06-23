package br.com.zupedu.olucas.proposta.card.validations;

import br.com.zupedu.olucas.proposta.card.model.enums.WalletStatusName;
import br.com.zupedu.olucas.proposta.card.request.WalletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class TypeEnumValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return WalletRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) return;

        WalletRequest walletRequest = (WalletRequest) o;

        try {
            WalletStatusName.valueOf(walletRequest.getWallet());
        }catch (IllegalArgumentException e) {
            errors.rejectValue("wallet", null, "The wallet '" + walletRequest.getWallet() + "' not exists in: " +
                    Arrays.stream(WalletStatusName.values()).map(WalletStatusName::name).collect(Collectors.toList()));
        }
    }
}
