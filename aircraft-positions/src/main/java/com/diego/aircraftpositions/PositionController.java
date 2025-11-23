package com.diego.aircraftpositions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Controller
public class PositionController {
    @NonNull
    private final AircraftRespository aircraftRespository;
    private WebClient client =
            WebClient.create("http://localhost:7634/aircraft");

    @GetMapping("/aircraft")
    public String gerCurrentAircraftPositions(Model model){
        aircraftRespository.deleteAll();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .toStream()
                .forEach(aircraftRespository::save);

        model.addAttribute("currentPositions", aircraftRespository.findAll());
        return "positions";
    }

}
