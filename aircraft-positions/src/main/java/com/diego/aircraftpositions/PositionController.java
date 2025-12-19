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

    /*Since the Consumer bean automatically checks for and processes messages automatically, the PositionController class and its
    * getCurrentAircraftPositions() method become dramatically leaner.
    * All references to WebClient can be removed, since getting a list of current positions is now only a matter of retrieving the current
    * contents of the repository.*/

    /*
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
    */

    @GetMapping("/aircraft")
    public String gerCurrentAircraftPositions(Model model){
        model.addAttribute("currentPositions", aircraftRespository.findAll());
        return "positions";
    }
}
