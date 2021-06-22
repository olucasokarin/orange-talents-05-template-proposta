package br.com.zupedu.olucas.proposta.card.schedules;

import br.com.zupedu.olucas.proposta.card.model.Travel;
import br.com.zupedu.olucas.proposta.card.repository.TravelRepository;
import br.com.zupedu.olucas.proposta.connections.cards.CardNoticeRequest;
import br.com.zupedu.olucas.proposta.connections.cards.CardNoticeResponse;
import br.com.zupedu.olucas.proposta.connections.cards.CardsFeign;
import br.com.zupedu.olucas.proposta.connections.cards.StatusNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAsync
@EnableScheduling
public class ScheduleNoticeTravelCard {
    TravelRepository travelRepository;
    CardsFeign cardsFeign;

    @Autowired
    public ScheduleNoticeTravelCard(TravelRepository travelRepository, CardsFeign cardsFeign) {
        this.travelRepository = travelRepository;
        this.cardsFeign = cardsFeign;
    }

    @Scheduled(fixedDelayString = "${schedule.time.notice.travel.fixedDelay}")
    public void schedulingNoticeTravelOnCard() {
        List<Travel> noticeTravelWithoutSync = travelRepository.getNoticeTravelWithoutSync();

        List<Travel> noticeTravelWithSync = noticeTravelWithoutSync.stream()
                .map(travel -> {
                    CardNoticeResponse cardNoticeResponse = cardsFeign.noticeTravel(travel.getNumberCard(),
                            new CardNoticeRequest(travel.getDestination(), travel.getDateEndTravel()));

                    if (cardNoticeResponse.getResultado() == StatusNotice.CRIADO)
                        travel.syncNoticeWithAPI();

                    return travel;
                }).collect(Collectors.toList());

        travelRepository.saveAll(noticeTravelWithSync);
    }
}
