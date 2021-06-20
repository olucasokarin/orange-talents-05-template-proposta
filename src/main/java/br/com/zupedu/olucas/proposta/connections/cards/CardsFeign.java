package br.com.zupedu.olucas.proposta.connections.cards;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "card", url = "${card.host}", fallback = CardsFeignFallback.class)
public interface CardsFeign {

    @PostMapping
    CardAssociateResponse getCard(@Valid @RequestBody CardAssociateRequest request);

    @PostMapping("/{id}/bloqueios")
    CardLockResponse lockCard(@PathVariable("id") String id, @Valid @RequestBody CardLockRequest request);
}
