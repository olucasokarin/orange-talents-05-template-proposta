package br.com.zupedu.olucas.proposta.card.request;

import br.com.zupedu.olucas.proposta.card.model.Biometry;
import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.validations.Base64;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class BiometryRequest {
    @NotBlank
    @Base64
    private String fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometryRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometry convertRequestToEntity(Card card) {
        return new Biometry(this.fingerprint, card);
    }
}
