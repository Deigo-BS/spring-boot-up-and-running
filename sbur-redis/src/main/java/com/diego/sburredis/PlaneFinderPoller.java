package com.diego.sburredis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
public class PlaneFinderPoller {

    private WebClient client = WebClient.create("http://localhost:7634/aircraft");

    private final RedisOperations<String, Aircraft> redisOperations;
    private final RedisConnectionFactory connectionFactory;

    public PlaneFinderPoller(RedisOperations<String, Aircraft> redisOperations, RedisConnectionFactory connectionFactory){
        this.redisOperations = redisOperations;
        this.connectionFactory = connectionFactory;
    }

    @Scheduled(fixedRate = 10000)
    public void pollPlanes(){
        //to clear any previously saved Aircraft
        connectionFactory.getConnection().serverCommands().flushDb();

        //to retrieve and save current positions
        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter( plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(aircraft -> redisOperations.opsForValue().set(aircraft.getReg(),aircraft));


        //to report the results of the latest capture.
        redisOperations.opsForValue()
                .getOperations()
                .keys("*")
                .forEach(kac -> System.out.println(redisOperations.opsForValue().get(kac)));

    }


}
