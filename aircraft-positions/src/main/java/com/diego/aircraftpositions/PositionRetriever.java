package com.diego.aircraftpositions;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Configuration
public class PositionRetriever {
    private final AircraftRespository aircraftRespository;

    @Bean
    Consumer<List<Aircraft>> retrieveAircraftPositions(){
        return acList -> {
            aircraftRespository.deleteAll();

            aircraftRespository.saveAll(acList);

            aircraftRespository.findAll().forEach(System.out::println);
        };
    }
}
