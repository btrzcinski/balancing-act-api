package com.btsoftworks.balancingact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class BalancingActApiApplication  {

    public static void main(String[] args) {
        SpringApplication.run(BalancingActApiApplication.class, args);
    }

}
