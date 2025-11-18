package com.diego.jpaoracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JpaOracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaOracleApplication.class, args);
	}

}
