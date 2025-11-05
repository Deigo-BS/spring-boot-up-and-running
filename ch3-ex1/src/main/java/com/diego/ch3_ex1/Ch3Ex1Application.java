package com.diego.ch3_ex1;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class Ch3Ex1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch3Ex1Application.class, args);
	}

}

@RestController
@RequestMapping("/coffees")     //I'll elevate the portion of the URI mapping that is common to all methods
class RestApiDemoController{
    private final List<Coffee> coffees = new ArrayList<>();

    //some data to retrieve in order to confirm that everything is working as expected.
    public RestApiDemoController(){
        coffees.addAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Tres Pontas")));
    }

    //I choose to use an Iterable<Coffee> because any iterable type will satisfactorily provide this API's desired functionality
    //Retrieving all coffees
    //@RequestMapping(value = "/coffee", method = RequestMethod.GET)
    @GetMapping
    public Iterable<Coffee> getCoffees(){
        return coffees;
    }

    //Retrieving a single item
    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id){
        for (Coffee c : coffees){
            if (c.getId().equals(id)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    //POST (To create resources)
    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }


    //PUT (should update the specified resource if present; if the resource doesn't already exist, it should be created.)
    //Siguiendo las recomendaciones para PUT es requerido especificar el HTTP status codes, para eso ahora retornamos ResponseEntity<>
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable String id,
                                            @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for (Coffee c : coffees){
            if (c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    //DELETE (To delete a source)
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }
}


//3.2 Creating a simple Domain
class Coffee{
    private final String id;
    private String name;

    @JsonCreator    //Personalmente a mi me indicaba que no podia convertit el JSON que le pasaba, segun era error comun de Jackson pero con esto le indico que este es el constructor que debe usar
    public Coffee (String id, String name){
        this.id = id;
        this.name = name;
    }

    public Coffee (String name){
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}