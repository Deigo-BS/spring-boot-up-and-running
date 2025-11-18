package com.diego.sburjpa;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class PlaneFinderPoller {
    @NonNull
    private final AircraftRepository aircraftRepository;
    private WebClient webClient =
            WebClient.create("http://localhost:7634/aircraft");

    @Scheduled(fixedRate = 1000)
    private void pollPlanes() {
        aircraftRepository.deleteAll();

        webClient.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                //.forEach(aircraftRepository::save);
                //La linea
                .forEach( aircraft -> {
                    if(aircraft.getId() != null && aircraftRepository.existsById(aircraft.getId())){
                        aircraftRepository.save(aircraft);  //auctualización
                    }else {
                        aircraft.setId(null);
                        aircraftRepository.save(aircraft); //forzamos inserción
                    }
                });

        aircraftRepository.findAll().forEach(System.out::println);
    }

}
