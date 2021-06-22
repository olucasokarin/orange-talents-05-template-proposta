package br.com.zupedu.olucas.proposta.card.request;

import br.com.zupedu.olucas.proposta.card.model.Card;
import br.com.zupedu.olucas.proposta.card.model.Travel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelRequest {
    @NotBlank
    private String destination;
    @Future
    @NotNull
    @JsonFormat(pattern = "MM-dd-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dateEndTravel;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TravelRequest(String destination) {
        this.destination = destination;
    }

    public void setDateEndTravel(LocalDate dateEndTravel) {
        this.dateEndTravel = dateEndTravel;
    }

    public Travel convertRequestToEntity(Card card, String userAgent, String ipAddress) {
        return new Travel(card, this.destination, this.dateEndTravel, userAgent, ipAddress);
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDateEndTravel() {
        return dateEndTravel;
    }
}
