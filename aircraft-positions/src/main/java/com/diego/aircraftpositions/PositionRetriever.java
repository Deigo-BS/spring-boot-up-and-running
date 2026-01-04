package com.diego.aircraftpositions;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Configuration
public class PositionRetriever {
    private final AircraftRespository aircraftRespository;
    private final WebSocketHandler webSocketHandler;

    @Bean
    Consumer<List<Aircraft>> retrieveAircraftPositions(){
        return acList -> {
            aircraftRespository.deleteAll();

            aircraftRespository.saveAll(acList);

            //aircraftRespository.findAll().forEach(System.out::println);
            sendPositions();
        };
    }

    private void sendPositions(){
        if (aircraftRespository.count() > 0){
            for (WebSocketSession sessionInList : webSocketHandler.getSessionList()){
                try {
                    sessionInList.sendMessage(new TextMessage(aircraftRespository.findAll().toString()));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
