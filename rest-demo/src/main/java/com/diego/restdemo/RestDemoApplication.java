package com.diego.restdemo;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

}

@Component
class DataLoader{

    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    //De esta manera ejecutamos este codigo apenas se inicia la aplicación
    @PostConstruct
    public void loadData(){
        coffeeRepository.saveAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Tres Pontas")));
    }

}


@RestController
@RequestMapping("/coffees")     //I'll elevate the portion of the URI mapping that is common to all methods
class RestApiDemoController{

    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;

        //Esto lo cambio a otro modulo (aqui arribita)
/*        this.coffeeRepository.saveAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Tres Pontas")));*/

    }


    //I choose to use an Iterable<Coffee> because any iterable type will satisfactorily provide this API's desired functionality
    //Retrieving all coffees
    //@RequestMapping(value = "/coffee", method = RequestMethod.GET)
    @GetMapping
    public Iterable<Coffee> getCoffees(){
        return coffeeRepository.findAll();
    }

    //Retrieving a single item
    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id){
        return coffeeRepository.findById(id);
    }

    //POST (To create resources)
    @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
        return coffeeRepository.save(coffee);
    }


    //PUT (should update the specified resource if present; if the resource doesn't already exist, it should be created.)
    //Siguiendo las recomendaciones para PUT es requerido especificar el HTTP status codes, para eso ahora retornamos ResponseEntity<>
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable String id,
                                            @RequestBody Coffee coffee){
        return (coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK)
                : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
    }

    //DELETE (To delete a source)
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id){
        coffeeRepository.deleteById(id);
    }
}


interface CoffeeRepository extends CrudRepository<Coffee, String> {

};

@Entity     // Indicates Coffee is a persistable entity
class Coffee{
    @Id     //to mark it as the database table's ID field.
    private String id;
    private String name;

    public Coffee (String id, String name){
        this.id = id;
        this.name = name;
    }

    public Coffee (String name){
        this(UUID.randomUUID().toString(), name);
    }

    //Java Persistence API requires a no-argument constructor for use when creating objects from database table rows,
    public Coffee (){}

    public void setId(String id) {
        this.id = id;
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